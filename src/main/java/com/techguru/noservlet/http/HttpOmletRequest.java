package com.techguru.noservlet.http;

import java.util.Map;

public class HttpOmletRequest {

  private String method;

  private String path;

  private Map<String, String> params;

  private Map<String, String> headers;

  public HttpOmletRequest(){

  }

  public String getPath() {
    return path;
  }

  public String getMethod() {
    return method;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public HttpOmletRequest method(String method){
    this.method = method;
    return this;
  }

  public HttpOmletRequest path(String path){
    this.path = path;
    return this;
  }

  public HttpOmletRequest params(Map<String,String> params){
    this.params = params;
    return this;
  }

  public HttpOmletRequest headers(Map<String,String> headers){
    this.headers = headers;
    return this;
  }
}
