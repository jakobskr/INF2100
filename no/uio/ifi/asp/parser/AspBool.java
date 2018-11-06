package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


/**
 * contains a bool literal
 *
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
public class AspBool extends AspAtom {
  boolean value;

    public AspBool(int n) {
        super(n);
    }

    /**
     * parses the bool literal
     * @param  Scanner s             [description]
     * @return         [description]
     */
    public static AspBool parse(Scanner s) {
        enterParser("boolean literal");
    AspBool abol = new AspBool(s.curLineNum());

    Token temp = s.curToken();

		if (temp.kind == trueToken){
			abol.value = true;
		}
		else if(temp.kind == falseToken){
			abol.value = false;
		}
    skip(s, trueToken, falseToken);

    leaveParser("boolean literal");
    return abol;
    }


    /**
     * converts the syntax tree back to a readable asp program.
     */
		@Override
		void prettyPrint() {
			if(value){
				Main.log.prettyWrite("True");
			}
			else{
				Main.log.prettyWrite("False");
			}
		}

    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
      return new RuntimeBoolValue(this.value);
    }
}
