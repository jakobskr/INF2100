package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeFloatValue extends RuntimeValue {
  double floatValue;


  public RuntimeFloatValue(double v) {
    this.floatValue = v;
  }

  /**
   * returns the float value
   * @param  String    what          what called the method
   * @param  AspSyntax where         where the method was called
   * @return           [description]
   */
	public double getFloatValue(String what, AspSyntax where){
		return this.floatValue;
	}

	public long getIntValue(String what, AspSyntax where){
		try{ return (long) floatValue;}
		catch (Exception a){
			runtimeError("cannot cast float to int", where);
		}
		return 92;
	}

  /**
   * retuns the typeName
   */
  @Override
  protected String typeName() {
    return "float";
  }

  /**
   * retuns the value as a string
   */
  @Override
  public String toString() {
    return Double.toString(this.floatValue);
  }

  /**
   * returns the value of this + v
   * @param  RuntimeValue v             the value to be added
   * @param  AspSyntax    where         where the method called
   * @return              RuntimeFloatValue
   */
  @Override
	public RuntimeValue evalAdd(RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue){
			return new RuntimeFloatValue(v.getFloatValue("float", where) + this.floatValue);
		}
		else if(v instanceof RuntimeIntValue){
			return new RuntimeFloatValue(v.getIntValue("float",where) + this.floatValue);
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be added to float!", where);
		}
		return null;
	}

  /**
   * retuns the value of this - v
   * @param  RuntimeValue v            the value to be subtracted
   * @param  AspSyntax    where        where the method was called
   * @return              RuntimeFloatValue
   */
	@Override
	public RuntimeValue evalSubtract(RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeFloatValue(this.floatValue - v.getFloatValue("float",where));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeFloatValue(this.floatValue - v.getIntValue("float",where));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be subtracted from float!", where);
		}
		return null;
	}

  /**
   * returns the value of this * v
   * @param  RuntimeValue v             the value to be multiplied
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeFloatValue
   */
	public RuntimeValue evalMultiply(RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeFloatValue(this.floatValue * v.getFloatValue("float",where));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeFloatValue(this.floatValue * v.getIntValue("float",where));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be multiply to float!", where);
		}
		return null;
	}

  /**
   * returns the value of this < v
   * @param  RuntimeValue v             value to be compared to
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
	public RuntimeValue evalLess(RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeBoolValue(this.floatValue < v.getFloatValue("float",where));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeBoolValue(this.floatValue < v.getIntValue("float",where));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be compared to float!", where);
		}
		return null;
	}

  /**
   * returns the value of this > v
   * @param  RuntimeValue v             the value to compared to
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
	public RuntimeValue evalGreater(RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeBoolValue(this.floatValue > v.getFloatValue("float",where));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeBoolValue(this.floatValue > v.getIntValue("float",where));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be compared to float!", where);
		}
		return null;
	}

  /**
   * returns the value of this <= v
   * @param  RuntimeValue v             the value to compared to
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
	public RuntimeValue evalLessEqual (RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeBoolValue(this.floatValue <= v.getFloatValue("float",where));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeBoolValue(this.floatValue <= v.getIntValue("float",where));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be compared to float!", where);
		}
		return null;
	}

  /**
   * retuns the valie of not this
   * @param  AspSyntax    where       where the method was called
   * @return              RuntimeBoolValue
   */
	public RuntimeValue evalNot (AspSyntax where){
		return  new RuntimeBoolValue(this.floatValue == 0.0);
	}

  /**
   * returns the value of this == v
   * @param  RuntimeValue v             the value to compared to
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
	public RuntimeValue evalEqual (RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeBoolValue(this.floatValue == v.getFloatValue("float",where));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeBoolValue(this.floatValue == v.getIntValue("float",where));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be compared to float!", where);
		}
		return null;
	}

  /**
   * returns the value of this % v
   * @param  RuntimeValue v             the value to modulad with
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeFloatValue
   */
	public RuntimeValue evalModulo (RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeFloatValue(this.floatValue -  v.getFloatValue("float",where) * Math.floor(this.floatValue / v.getFloatValue("float",where)));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeFloatValue(this.floatValue -  v.getIntValue("float",where) * Math.floor(this.floatValue / v.getIntValue("float",where)));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be compared to float!", where);
		}
		return null;
	}

  /**
   * returns the value of this
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
	public RuntimeValue evalPositive(AspSyntax where){
		return new RuntimeFloatValue(this.floatValue);
	}

  /**
   * returns this * -1
   * @return              RuntimeFloatValue
   */
	public RuntimeValue evalNegate(AspSyntax where){
		return new RuntimeFloatValue(-this.floatValue);
	}

  /**
   * returns the value of this / v
   * @param  RuntimeValue v             the value be divided with
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
	public RuntimeValue evalDivide(RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeFloatValue(this.floatValue / v.getFloatValue("float",where));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeFloatValue(this.floatValue /  v.getIntValue("float",where));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be compared to float!", where);
		}
		return null;
	}

  /**
   * returns the value of this // v
   * @param  RuntimeValue v             the value to intDivided with
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
	public RuntimeValue evalIntDivide(RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeFloatValue(Math.floor(this.floatValue / v.getFloatValue("float",where)));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeFloatValue(Math.floor(this.floatValue /  v.getIntValue("float",where)));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be compared to float!", where);
		}
		return null;
	}

  /**
   * returns the value of this != v
   * @param  RuntimeValue v             the value to compared to
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
	public RuntimeValue evalNotEqual(RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeBoolValue(this.floatValue !=  v.getFloatValue("float",where));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeBoolValue(this.floatValue !=  v.getFloatValue("float",where));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be compared to float!", where);
		}
		return null;
	}

  /**
   * returns the value of this >= v
   * @param  RuntimeValue v             the value to compared to
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeBoolValue
   */
	public RuntimeValue evalGreaterEqual(RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeBoolValue(this.floatValue >=  v.getFloatValue("float",where));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeBoolValue(this.floatValue >=  v.getFloatValue("float",where));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be compared to float!", where);
		}
		return null;
	}
}
