package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

/**
 * the none literal
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
public class AspNone extends AspAtom {

    public AspNone(int n) {
        super(n);
    }


    public static AspNone parse(Scanner s) {
    enterParser("none");
    System.out.println("Nuns be gucci");
    AspNone anon = new AspNone(s.curLineNum());

    Token temp = s.curToken();

    skip(s, noneToken);

    leaveParser("none");
    return anon;
    }

    /**
    * converts the syntax tree back to a readable asp program.
    */
    public void prettyPrint() {
    	Main.log.prettyWrite("none");
  }

    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return null;
  }


}
