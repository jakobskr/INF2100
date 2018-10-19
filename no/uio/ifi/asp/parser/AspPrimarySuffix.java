package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

/**
 * abstract superclass for primary suffix
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
public abstract class AspPrimarySuffix extends AspSyntax {

	public AspPrimarySuffix(int n) {
		super(n);
	}


	static AspPrimarySuffix parse(Scanner s) {
		enterParser("primary suffix");
		AspPrimarySuffix apsu = null;
		Token tok = s.curToken();

		if (tok.kind == leftParToken) {
			apsu = AspArguments.parse(s);
		}

		else if (tok.kind == leftBracketToken) {
			apsu = AspSubscription.parse(s);
		}

		leaveParser("primary suffix");
		return apsu;

	}

	abstract void prettyPrint();

	abstract RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue;

}
