package com.techguru.noservlet.http;

import com.techguru.noservlet.http.omlet.Omlet;
import com.techguru.noservlet.http.omlet.OmletFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class HttpRequestHandler {

  public static String handle(HttpOmletRequest httpRequest)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    String path = httpRequest.getPath();
    String method = httpRequest.getMethod();

    Object response = null;

    Omlet omlet = OmletFactory.getOmlet(path);

    if(omlet == null) response="";
    else {
      Method handleMethod = omlet.getClass().getMethod("handle" + method, HttpOmletRequest.class);
      response = handleMethod.invoke(omlet, httpRequest);
    }
    return (String) response;
  }
}
