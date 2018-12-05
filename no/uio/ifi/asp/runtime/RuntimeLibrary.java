package no.uio.ifi.asp.runtime;

import java.util.ArrayList;
import java.util.Scanner;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeLibrary extends RuntimeScope {
    private Scanner keyboard = new Scanner(System.in);

    public RuntimeLibrary() {
    	//len
    	assign("len", new RuntimeFunc("func") {
    		@Override
    		public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
    			checkNumParams(actualParams, 1, "len", where);
    			System.out.println(actualParams.get(0).evalLen(where));
    			return actualParams.get(0).evalLen(where);
    	}});

    	//print
    	assign("print", new RuntimeFunc("print") {
    		@Override
    		public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
    			for (RuntimeValue val: actualParams) {
    				System.out.print(val);
    			}
    			System.out.print("\n");
    			return null;
    		}
    	});
    }


    private void checkNumParams(ArrayList<RuntimeValue> actArgs, 
				int nCorrect, String id, AspSyntax where) {
	if (actArgs.size() != nCorrect)
	    RuntimeValue.runtimeError("Wrong number of parameters to "+id+"!",where);
    }
}
