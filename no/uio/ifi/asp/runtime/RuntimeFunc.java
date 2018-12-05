package no.uio.ifi.asp.runtime;


import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.parser.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeFunc extends RuntimeValue {

	String name;
	ArrayList<String> parameters;
	AspSuite suite;
	RuntimeScope curScope;


	public RuntimeFunc (String name, ArrayList<String> parameters, AspSuite suite, RuntimeScope curScope ){
		this.name = name;
		this.parameters = parameters;
		this.suite = suite;
		this.curScope = curScope;


	}

	protected String typeName() {
		return "Function";
	}

	public RuntimeValue eval (ArrayList<RuntimeValue> actualParams, AspSyntax where){
		try{
			RuntimeScope newScope = new RuntimeScope();
			for (int i = 0; i < parameters.size(); i++){
				newScope.assign(parameters.get(i), actualParams.get(i));
			}
			suite.eval(newScope);
			for (int i = 0; i < parameters.size(); i++){
				curScope.assign(parameters.get(i),newScope.find(parameters.get(i), where));
			}
		}
		catch (RuntimeReturnValue rrv) {
			return rrv.value;
		}
		return new RuntimeNoneValue();


	}


}
