package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;
/**
 * abstract super class for the different statements
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
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
			astm = AspIf.parse(s);
		}

		else if(tok.kind == whileToken) {
			astm = AspWhile.parse(s);
		}

		else if (tok.kind == passToken) {
			astm = AspPass.parse(s);
		}

		else if (tok.kind == returnToken) {
			astm = AspReturn.parse(s);
		}

		else if (tok.kind == defToken) {
			astm = AspFuncDef.parse(s);
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
