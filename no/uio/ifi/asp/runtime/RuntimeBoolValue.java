package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

/**
 * The RuntimeBoolvalue
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
public class RuntimeBoolValue extends RuntimeValue {
    boolean boolValue;

    public RuntimeBoolValue(boolean v) {
	    boolValue = v;
    }

    /**
     * @return the typevalue
     */
    @Override
    protected String typeName() {
	    return "boolean";
    }

    /**
     * @return the value as a string
     */
    @Override
    public String toString() {
      return (boolValue ? "True" : "False");
    }

    /**
     * return the boolean value of the runtimevalue
     * @param  String    what          what called it
     * @param  AspSyntax where         where it was called
     * @return           boolean value
     */
    @Override
    public boolean getBoolValue(String what, AspSyntax where) {
	    return boolValue;
    }

    /**
     * returns the value of this == x
     * @param  RuntimeValue v             value to compare to
     * @param  AspSyntax    where         where it was called
     * @return              RuntimeBoolValue
     */
    @Override
    public RuntimeValue evalEqual(RuntimeValue v, AspSyntax where) {
	    if (v instanceof RuntimeNoneValue) {
	      return new RuntimeBoolValue(false);
	    }
	    runtimeError("Type error for ==.", where);
      return null;  // Required by the compiler
    }

    /**
     * returns the value of not this
     * @param  AspSyntax where         where the method was called
     * @return           RuntimeBoolValue
     */
    @Override
    public RuntimeValue evalNot(AspSyntax where) {
      return new RuntimeBoolValue(! boolValue);
    }

    /**
     * returns the value of this != v
     * @param  RuntimeValue v             value to compare to
     * @param  AspSyntax    where         where the method was calle
     * @return              RuntimeBoolValue
     */
    @Override
    public RuntimeValue evalNotEqual(RuntimeValue v, AspSyntax where) {
      if (v instanceof RuntimeNoneValue) {
	      return new RuntimeBoolValue(true);
	  }
	  runtimeError("Type error for !=.", where);
      return null;  // Required by the compiler
    }
}
