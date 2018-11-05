package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeFloatValue extends RuntimeValue {
  double floatValue;


  public RuntimeFloatValue(double v) {
    this.floatValue = v;
  }

	public double getFloatValue(String what, AspSyntax where){
		return this.floatValue;
	}


  @Override
  protected String typeName() {
    return "float";
  }


  @Override
  public String toString() {
    return Double.toString(this.floatValue);
  }

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

	public RuntimeValue evalNot (RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeBoolValue(this.floatValue == 0.0);
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be compared to float!", where);
		}
		return null;
	}

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

	public RuntimeValue evalModulo (RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeFloatValue(this.floatValue -  v.getFloatValue("float",where) * Math.floor(this.floatValue / v.getFloatValue("float",where)));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeFloatValue(this.floatValue -  v.getFloatValue("float",where) * Math.floor(this.floatValue / v.getFloatValue("float",where)));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be compared to float!", where);
		}
		return null;
	}

	public RuntimeValue evalPositive(RuntimeValue v, AspSyntax where){
		return new RuntimeFloatValue(this.floatValue);
	}

	public RuntimeValue evalNegate(RuntimeValue v, AspSyntax where){
		return new RuntimeFloatValue(-this.floatValue);
	}

	public RuntimeValue evalDivide(RuntimeValue v, AspSyntax where){
		if (v instanceof RuntimeFloatValue ){
			return  new RuntimeFloatValue(this.floatValue / v.getFloatValue("float",where));
		}
		else if (v instanceof RuntimeIntValue){
			return  new RuntimeFloatValue(this.floatValue /  v.getFloatValue("float",where));
		}
		else{
			runtimeError("Type error: "+ v +" of " + v.typeName() + " cannot be compared to float!", where);
		}
		return null;
	}

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
