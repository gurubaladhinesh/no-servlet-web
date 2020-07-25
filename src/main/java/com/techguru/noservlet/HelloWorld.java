package com.techguru.noservlet;

import com.techguru.noservlet.http.HttpOmletRequest;
import com.techguru.noservlet.http.HttpOmletResponse;
import com.techguru.noservlet.http.HttpRequestHandler;
import com.techguru.noservlet.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HelloWorld {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    private static final int PORT = 8080;

    public static void main(String[] args)
            throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                logger.info("Server running and listening for client request");
                Socket clientSocket = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream out = clientSocket.getOutputStream();
                String line;
                StringBuilder requestBuilder = new StringBuilder();
                while ((line = in.readLine()) != null && !line.equals("")) {
                    requestBuilder.append(line);
                }
                String rawRequest = requestBuilder.toString();
                logger.info("Raw HTTP request::\n{}", rawRequest);
                HttpOmletRequest httpRequest = HttpUtils.parse(rawRequest);

                if ("/exit".equalsIgnoreCase(httpRequest.getPath())) {
                    logger.info("Shutting down...");
                    break;
                }

                String response = HttpRequestHandler.handle(httpRequest);
                HttpOmletResponse httpResponse = HttpUtils.compose(response);
                logger.info("Raw HTTP response::\n{}", httpResponse.getRawResponse());

                out.write(httpResponse.getRawResponse().getBytes(StandardCharsets.UTF_8));

                out.flush();
                in.close();
                out.close();
                clientSocket.close();
            }
        } finally {
            logger.info("Server shutdown completed");
            if (serverSocket != null) serverSocket.close();
        }
    }
}
