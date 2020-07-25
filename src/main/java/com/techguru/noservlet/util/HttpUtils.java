package com.techguru.noservlet.util;

import com.techguru.noservlet.http.HttpOmletRequest;
import com.techguru.noservlet.http.HttpOmletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public final class HttpUtils {

  private static final String htmlBody = "<html><body>${message}</body></html>";

  public static HttpOmletRequest parse(String request){

    String[] lines = request.split("\n");

    String firstLine = lines[0];

    String[] words = firstLine.split(" ");

    String method = words[0];
    String url = words[1];

    HttpOmletRequest httpRequest = new HttpOmletRequest().method(method).path(getPath(url)).params(getParams(url));
    return httpRequest;
  }

  public static HttpOmletResponse compose(String response){
    String body = htmlBody.replace("${message}",response);
    String rawResponse = "HTTP/1.1 200 OK\r\n"
        + "Content-Length: "+body.length()+"\r\n"
        + "Content-Type: text/html\r\n\r\n"
        + "\r\n\r\n"+body+"\r\n\r\n";
    return new HttpOmletResponse(rawResponse);
  }

  private static String getPath(String url){
    int index = url.indexOf("?");
    return index > 0 ? url.substring(0,index):url.substring(0);
  }

  private static Map<String,String> getParams(String url){
    int index = url.indexOf("?");
    Map<String,String> paramMap = new HashMap<>();
    if(index > 0){
      String paramString = url.substring(index+1);
      String[] params = paramString.split("&");
      for(String param: params){
        String[] kv = param.split("=");
        if(kv.length == 2){
          paramMap.put(kv[0],kv[1]);
        }
      }
    }
    return paramMap;
  }
}
