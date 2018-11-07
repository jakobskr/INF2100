package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeNoneValue extends RuntimeValue {
    /*
     * returns the typename
     */
    @Override
    protected String typeName() {
	return "None";
    }

    /*
     * returns the value as a string
     */
    @Override
    public String toString() {
	    return "None";
    }

    /*
     * returns the boolean value of the value
     */
    @Override
    public boolean getBoolValue(String what, AspSyntax where) {
	    return false;
    }


    /**
     * returns the value of this == v
     * @param  RuntimeValue v             the value to compared to
     * @param  AspSyntax    where         where the method was called
     * @return              RuntimeBoolValue
     */
    @Override
    public RuntimeValue evalEqual(RuntimeValue v, AspSyntax where) {
	    return new RuntimeBoolValue(v instanceof RuntimeNoneValue);
    }


    /*
     * returns the value of not this
     */
    @Override
    public RuntimeValue evalNot(AspSyntax where) {
	    return new RuntimeBoolValue(true);
    }

    /**
     * returns the value of this != v
     * @param  RuntimeValue v             the value to compared to
     * @param  AspSyntax    where         where the method was called
     * @return              RuntimeBoolValue
     */
    @Override
    public RuntimeValue evalNotEqual(RuntimeValue v, AspSyntax where) {
	    return new RuntimeBoolValue(!(v instanceof RuntimeNoneValue));
    }
}
