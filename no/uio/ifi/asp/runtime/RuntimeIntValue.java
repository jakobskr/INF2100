package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeIntValue extends RuntimeValue {
  int intValue;


  public RuntimeIntValue(int v) {
    intValue = v;
  }


  @Override
  protected String typeName() {
    return "integer";
  }


  @Override
  public String toString() {
    return Integer.toString(intValue);
  }

}
