package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeStringValue extends RuntimeValue {
  String value;

  public RuntimeStringValue(String v) {
    value = v;
  }

  public String toString(){
    return value;
  }

  public String typeName() {
    return "String";
  }

  public String getStringValue(String what, AspSyntax where) {
    return value;
  }

  public boolean getBoolValue(String what, AspSyntax where) {
    return !(value.equals(""));
  }


  public RuntimeValue evalAdd(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeStringValue) {
      return new RuntimeStringValue(value + v.getStringValue("+ operand ", where));
    }

    runtimeError("Type error for +", where);
    return null;
  }

  @Override
  public RuntimeValue evalEqual(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeNoneValue) {
      return new RuntimeBoolValue(false);
    }

    if (v instanceof RuntimeStringValue) {
      return new RuntimeBoolValue(value.equals(v.getStringValue("== operand", where)));
    }

    runtimeError("Type error for ==.", where);
    return null;  // Required by the compiler
  }

  @Override
  public RuntimeValue evalGreater(RuntimeValue v, AspSyntax where) {

    if (v instanceof RuntimeStringValue) {
      return new RuntimeBoolValue(value.compareTo(v.getStringValue("< operand", where)) > 0 );
    }

    runtimeError("Type error for <.", where);
    return null;  // Required by the compiler
  }

  @Override
  public RuntimeValue evalGreaterEqual(RuntimeValue v, AspSyntax where) {

    if (v instanceof RuntimeStringValue) {
      return new RuntimeBoolValue(value.compareTo(v.getStringValue("<= operand", where)) >= 0 );
    }

    runtimeError("Type error for <=.", where);
    return null;  // Required by the compiler
  }

  public RuntimeValue evalLen(RuntimeValue v, AspSyntax where) {
    return new RuntimeIntValue(value.length());
  }

  @Override
  public RuntimeValue evalLess(RuntimeValue v, AspSyntax where) {

    if (v instanceof RuntimeStringValue) {
      return new RuntimeBoolValue(value.compareTo(v.getStringValue("> operand", where)) < 0 );
    }

    runtimeError("Type error for >.", where);
    return null;  // Required by the compiler
  }

  @Override
  public RuntimeValue evalLessEqual(RuntimeValue v, AspSyntax where) {

    if (v instanceof RuntimeStringValue) {
      return new RuntimeBoolValue(value.compareTo(v.getStringValue(">= operand", where)) <= 0 );
    }

    runtimeError("Type error for >=.", where);
    return null;  // Required by the compiler
  }

  public RuntimeValue evalMultiply(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeIntValue) {
      String ret = "";
      for (int i = 0; i < v.getIntValue("* operand",where) ;i++ ) {
        ret = ret + ret;
      }
      return new RuntimeStringValue(ret);
    }

    runtimeError("Type error for *", where);
    return null;
  }

  public boolean evalNot(RuntimeValue v, AspSyntax where) {
    return (value.equals(""));
  }

  @Override
  public RuntimeValue evalNotEqual(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeNoneValue) {
      return new RuntimeBoolValue(true);
    }

    if (v instanceof RuntimeStringValue) {
      return new RuntimeBoolValue(!(value.equals(v.getStringValue("!= operand", where))));
    }

    runtimeError("Type error for !=.", where);
    return null;  // Required by the compiler
  }

  public RuntimeValue evalSubscription(RuntimeValue v, AspSyntax where) {
	  if (v instanceof RuntimeIntValue) {
      if (0 <= v.getIntValue("index", where) && v.getIntValue("index", where)  < value.length()) {
        return new RuntimeStringValue(value.substring((int) v.getIntValue("index",where),(int) v.getIntValue("index",where)));
      }
      else {
        runtimeError(String.format("String index %d out of range",v.getIntValue("index",where)), where);
        return null;
      }
    }
    runtimeError("Index must be integer.", where);
    return null;  // Required by the compiler
  }
}
