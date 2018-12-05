package no.uio.ifi.asp.runtime;

import java.lang.NumberFormatException;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeStringValue extends RuntimeValue {
  String value;

  public RuntimeStringValue(String v) {
    value = v;
  }

  /*
   * reutns the value as a string
   */
  public String toString(){
    return value;
  }

  /*
   * returns the typename
   */
  public String typeName() {
    return "String";
  }

	public double getFloatValue(String what, AspSyntax where){
		try{ return Double.valueOf(value);}
		catch (NumberFormatException a){
			runtimeError("cannot parse string to float", where);
		}
		return 42.1337;
	}

  /*
   * retursn the string value
   */
  public String getStringValue(String what, AspSyntax where) {
    return value;
  }

  /*
   * returns the boolean value of the value
   */
  public boolean getBoolValue(String what, AspSyntax where) {
    return !(value.equals(""));
  }


  /**
   * returns the value of this + v
   * @param  RuntimeValue v             the value to be added
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeStringValue
   */
  public RuntimeValue evalAdd(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeStringValue) {
      return new RuntimeStringValue(value + v.getStringValue("+ operand ", where));
    }

    runtimeError("Type error for +", where);
    return null;
  }

  /**
   * returns the value of this == v
   * @param  RuntimeValue v             the value to compared to
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
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

  /**
   * returns the value of this < v
   * @param  RuntimeValue v             the value to compared to
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
  @Override
  public RuntimeValue evalGreater(RuntimeValue v, AspSyntax where) {

    if (v instanceof RuntimeStringValue) {
      return new RuntimeBoolValue(value.compareTo(v.getStringValue("< operand", where)) > 0 );
    }

    runtimeError("Type error for >.", where);
    return null;  // Required by the compiler
  }

  /**
   * returns the value of this >= v
   * @param  RuntimeValue v             the value to compared to
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
  @Override
  public RuntimeValue evalGreaterEqual(RuntimeValue v, AspSyntax where) {

    if (v instanceof RuntimeStringValue) {
      return new RuntimeBoolValue(value.compareTo(v.getStringValue("<= operand", where)) >= 0 );
    }

    runtimeError("Type error for >=.", where);
    return null;  // Required by the compiler
  }

  /*
   * returns the length of the string as a RuntimeIntValue
   */
  public RuntimeValue evalLen(RuntimeValue v, AspSyntax where) {
    return new RuntimeIntValue(value.length());
  }

  /**
   * returns the value of this < v
   * @param  RuntimeValue v             the value to compared to
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
  @Override
  public RuntimeValue evalLess(RuntimeValue v, AspSyntax where) {

    if (v instanceof RuntimeStringValue) {
      return new RuntimeBoolValue(value.compareTo(v.getStringValue("> operand", where)) < 0 );
    }

    runtimeError("Type error for <.", where);
    return null;  // Required by the compiler
  }

  /**
   * returns the value of this <= v
   * @param  RuntimeValue v             the value to compared to
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
  @Override
  public RuntimeValue evalLessEqual(RuntimeValue v, AspSyntax where) {

    if (v instanceof RuntimeStringValue) {
      return new RuntimeBoolValue(value.compareTo(v.getStringValue(">= operand", where)) <= 0 );
    }

    runtimeError("Type error for <=.", where);
    return null;  // Required by the compiler
  }

  /**
   * returns the value of this * v
   * @param  RuntimeValue v             the value to multiplied with
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
  public RuntimeValue evalMultiply(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeIntValue) {
      String ret = "";
      for (int i = 0; i < v.getIntValue("* operand",where) ;i++ ) {
        ret = ret + value;
      }
      return new RuntimeStringValue(ret);
    }

    runtimeError("Type error for *", where);
    return null;
  }

/*
 * returns the value of not this
 */
  public RuntimeValue evalNot(RuntimeValue v, AspSyntax where) {
    return new RuntimeBoolValue(value.equals(""));
  }

  /**
   * returns the value of this != v
   * @param  RuntimeValue v             the value to compared to
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
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

  /**
   * returns the value of this[v]
   * @param  RuntimeValue v             the value of the index
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeStringValue
   */
  public RuntimeValue evalSubscription(RuntimeValue v, AspSyntax where) {
	  if (v instanceof RuntimeIntValue) {
      if (0 <= v.getIntValue("index", where) && v.getIntValue("index", where)  < value.length()) {
        return new RuntimeStringValue(value.substring((int) v.getIntValue("index",where),(int) v.getIntValue("index",where) + 1));
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
