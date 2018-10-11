package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspPass extends AspStmt {
  String name;

    public AspPass(int n) {
        super(n);
    }

    public static AspPass parse(Scanner s) {
    	enterParser("pass");
    	AspPass apas = new AspPass(s.curLineNum());

			skip(s,passToken);
			skip(s,newLineToken);
    	leaveParser("pass");
    	return apas;
    }

    public void prettyPrint() {
    int n = 0;
  }

    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return null;
  }
}