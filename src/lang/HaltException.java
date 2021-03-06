package com.googlecode.prolog_cafe.lang;

public class HaltException extends SystemException {
  private final int status;

  public HaltException(int status) {
    super("halt(" + status + ")");
    this.status = status;
  }

  public int getStatus() {
    return status;
  }
}
