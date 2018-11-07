package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;
/**
 * the string literal
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
public class AspString extends AspAtom {
  String value;

    public AspString(int n) {
        super(n);
    }

    public static AspString parse(Scanner s) {
        enterParser("string literal");
    AspString astr = new AspString(s.curLineNum());
    Token temp = s.curToken();
    astr.value = temp.stringLit;
    skip(s, stringToken);

    leaveParser("string literal");
    return astr;
    }

    /**
    * converts the syntax tree back to a readable asp program.
    */
    public void prettyPrint() {
	    Main.log.prettyWrite("\"" + value + "\"");
  }
	/**
	 * string evaluates to a RuntimeStringValue
	 * @param  curScope           current scope
	 * @return                    RuntimeStringValue
	 * @throws RuntimeReturnValue -
	 */
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return new RuntimeStringValue(this.value);
  }


}
