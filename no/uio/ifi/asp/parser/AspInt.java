package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;
/**
 * the int literal
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
public class AspInt extends AspAtom {
  long value;

    public AspInt(int n) {
        super(n);
    }


    public static AspInt parse(Scanner s) {
        enterParser("integer literal");
    AspInt aint = new AspInt(s.curLineNum());

    Token temp = s.curToken();

    aint.value = temp.integerLit;
    skip(s, integerToken);

    leaveParser("integer literal");
    return aint;
    }


    /**
    * converts the syntax tree back to a readable asp program.
    */
    public void prettyPrint() {
    	Main.log.prettyWrite(Long.toString(value));
  }

    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return new RuntimeIntValue(this.value);
  }


}
