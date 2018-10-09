package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspBool extends AspAtom {
  boolean value;

    public AspBool(int n) {
        super(n);
    }

    public static AspBool parse(Scanner s) {
        enterParser("Boolean");
    AspBool abol = new AspBool(s.curLineNum());

    Token temp = s.curToken();

		if (temp.kind == trueToken){
			abol.value = true;
		}
		else if(temp.kind == falseToken){
			abol.value = false;
		}
    skip(s, trueToken, falseToken);

    leaveParser("Bool");
    return abol;
    }

		@Override
		void prettyPrint() {
			if(value){
				Main.log.prettyWrite("true");
			}
			else{
				Main.log.prettyWrite("false");
			}
		}
  }

    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return null;
  }


}
