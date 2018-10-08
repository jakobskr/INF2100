package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public abstract class AspStmt extends AspSyntax {

	public AspStmt(int n) {
		super(n);
	}


	static AspStmt parse(Scanner s) {
		enterParser("stmt");
		Token tok = s.curToken();

		AspStmt astm = null;

		if (tok.kind == forToken) {
			astm = AspFor.parse(s);
		}

		else if (tok.kind == ifToken) {
			//implement this
		}

		else if(tok.kind == whileToken) {
			//implement this
		}

		else {
			if (s.anyEqualToken()) {
				astm = AspAssignment.parse(s);
			}

			else {
				astm = AspExprStmt.parse(s);
			}
		}
		leaveParser("stmt");
		return astm;
	}

	abstract void prettyPrint();

	abstract RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue;

}
