package com.techguru.noservlet.http.omlet.impl;

import com.techguru.noservlet.http.HttpOmletRequest;
import com.techguru.noservlet.http.omlet.Omlet;
import java.util.Map;

public class HelloWorldOmlet implements Omlet {

  @Override
  public String handleGET(HttpOmletRequest httpRequest) {
    Map<String,String> paramMap = httpRequest.getParams();
    String name = paramMap.getOrDefault("name","unknown user");
    return "Hello, "+name;
  }
}
