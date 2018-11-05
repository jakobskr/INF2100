package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeStringValue extends RuntimeValue {
  String value;

  pubic RuntimeStringValue(String v) {
    value = v;
  }

  public String toString(){
    return value;
  }

  public String getStringValue(String what, AspSyntax where) {
    return value;
  }

  public Boolean getBoolValue(String what, AspSyntax where) {
    return value;
  }


  public RuntimeValue evalAdd(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeStringValue) {
      return new RuntimeStringValue(value + v.getStringValue("string", where));
    }

    runtimeError("Type error for +", where);
    return null;
  }



}
