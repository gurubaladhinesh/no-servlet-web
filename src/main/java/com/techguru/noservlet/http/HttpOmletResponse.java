package com.techguru.noservlet.http;

public class HttpOmletResponse {

  private String rawResponse;

  public String getRawResponse() {
    return rawResponse;
  }

  public void setRawResponse(String rawResponse) {
    this.rawResponse = rawResponse;
  }

  public HttpOmletResponse(String rawResponse){
    this.rawResponse = rawResponse;
  }

}
