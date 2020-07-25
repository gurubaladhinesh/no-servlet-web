package com.techguru.noservlet.http.omlet;

import com.techguru.noservlet.http.omlet.impl.HelloWorldOmlet;

public class OmletFactory {

  public static Omlet getOmlet(String path){
    Omlet omlet;
    switch (path){
      case "/helloworld":
        omlet = new HelloWorldOmlet();
        break;
      default:
        omlet = null;
        break;
    }
    return omlet;
  }

}
