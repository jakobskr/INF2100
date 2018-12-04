package no.uio.ifi.asp.runtime;

import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeListValue extends RuntimeValue{
	public ArrayList<RuntimeValue> listvalue;

	public RuntimeListValue(ArrayList<RuntimeValue> list){
		this.listvalue = list;
	}

	/**
	 * returns the typename
	 */
  @Override
  protected String typeName() {
    return "list";
  }

	/*
	 * returns the list as a string
	 */
  @Override
  public String toString() {
    String tmp = "[";
		int ct = 0;
		for(RuntimeValue val: listvalue){
			if(ct > 0){tmp = tmp + ", ";}
			tmp = tmp + val.toString();
			ct ++;
		}
		return tmp + "]";
  }

	/**
	 * returns the value of this[v]
	 * @param  RuntimeValue v             the index
	 * @param  AspSyntax    where         where the method was called
	 * @return              RuntimeValue
	 */
	public RuntimeValue evalSubscription(RuntimeValue v, AspSyntax where){
		if(v instanceof RuntimeIntValue){
			if (v.getIntValue("index", where) > this.listvalue.size() || v.getIntValue("index", where) < 0){
				runtimeError("index out of bounds", where);
				return null;
			}
			return this.listvalue.get((int) v.getIntValue("index" ,where));
		}
		runtimeError("index can only be int", where);
		return null;
	}

	/*
	 * returns the length of the list as a RuntimeIntValue
	 */
	public RuntimeValue evalLen(){
		return new RuntimeIntValue(this.listvalue.size());
	}

	/**
   * returns the value of this + v
   * @param  RuntimeValue v             the value to be added
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeListValue
   */
	public RuntimeValue evalAdd(RuntimeValue v, AspSyntax where){
		if(v instanceof RuntimeListValue){
			RuntimeListValue r = (RuntimeListValue) v;
			ArrayList<RuntimeValue> p = new ArrayList<RuntimeValue>();
			p.addAll(listvalue);
			p.addAll(r.listvalue);
			return new RuntimeListValue(p);
		}
		runtimeError("can only concatinate lists", where);
		return null;
	}

	/**
   * returns the value of this * v
   * @param  RuntimeValue v             the value to multiplied with
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeListValue
   */
	public RuntimeValue evalMultiply(RuntimeValue v, AspSyntax where){
		RuntimeValue newlist = new RuntimeListValue(new ArrayList<RuntimeValue>());
		for (int x = 0; x < v.getIntValue("mul", where) ; x++ ) {
			newlist = newlist.evalAdd(this, where);
		}
		return newlist;
	}

	/*
	 * returns the boolean value of the list
	 */
	public boolean getBoolValue(){
		if( this.listvalue.size() > 0){
			return false;
		}
		return true;
	}

	@Override
	public void evalAssignElem(RuntimeValue inx, RuntimeValue val, AspSyntax where) {
		if (inx instanceof RuntimeIntValue) {
			//change this in part 4
		}
	}

}

//eof
