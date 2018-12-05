package no.uio.ifi.asp.runtime;


import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.parser.*;
import no.uio.ifi.asp.parser.AspSyntax;

// For part 4:

public class RuntimeReturnValue extends Exception {
    public int lineNum;
    RuntimeValue value;

    public RuntimeReturnValue(RuntimeValue v, int lNum) {
	    value = v;  lineNum = lNum;
    }

		protected String typeName() {
			return "Return value";
		}

		public void eval(AspSyntax where){
			
		}

}
