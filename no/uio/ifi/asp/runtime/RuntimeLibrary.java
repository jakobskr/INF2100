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
    				System.out.print(val + " ");
    			}
    			System.out.print("\n");
    			return new RuntimeNoneValue();
    		}
    	});

			//int
    	assign("int", new RuntimeFunc("int") {
    		@Override
    		public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
    			checkNumParams(actualParams, 1, "int", where);
    			return new RuntimeIntValue(actualParams.get(0).getIntValue("int", where));
    		}
    	});

			//float
    	assign("float", new RuntimeFunc("float") {
    		@Override
    		public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
    			checkNumParams(actualParams, 1 , "float", where);
    			return new RuntimeFloatValue(actualParams.get(0).getFloatValue("float", where));
    		}
    	});

			//string
    	assign("str", new RuntimeFunc("str") {
    		@Override
    		public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
    			checkNumParams(actualParams, 1, "str", where);
    			return new RuntimeStringValue(actualParams.get(0).toString());
    		}
    	});

			//input
    	assign("input", new RuntimeFunc("input") {
    		@Override
    		public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
	    		checkNumParams(actualParams, 1, "input", where);
	    		System.out.print(actualParams.get(0));
	    		RuntimeStringValue val = new RuntimeStringValue(keyboard.nextLine());
	    		return val;
    		}
    	});

			//range
			assign("range", new RuntimeFunc("range") {
				@Override
				public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
					checkNumParams(actualParams, 2, "str", where);
					ArrayList<RuntimeValue> val = new ArrayList<RuntimeValue>();
					int p = (int) actualParams.get(1).getIntValue("int", where);
					for(int i = (int) actualParams.get(0).getIntValue("int", where); i < p; i++){
						val.add(new RuntimeIntValue(i));
					}
					return new RuntimeListValue(val);
				}
			});

      //rand
      assign("rand", new RuntimeFunc("rand") {
        @Override
        public RuntimeValue evalFuncCall(ArrayList<RuntimeValue> actualParams, AspSyntax where) {
          checkNumParams(actualParams, 0, "rand", where);
          return new RuntimeIntValue(4);
        }}
      );


    }


    private void checkNumParams(ArrayList<RuntimeValue> actArgs,
				int nCorrect, String id, AspSyntax where) {
	if (actArgs.size() != nCorrect)
	    RuntimeValue.runtimeError("Wrong number of parameters to "+id+"!",where);
    }
}
