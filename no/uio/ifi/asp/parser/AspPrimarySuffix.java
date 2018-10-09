package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public abstract class AspPrimarySuffix extends AspSyntax {

	public AspPrimarySuffix(int n) {
		super(n);
	}


	static AspPrimarySuffix parse(Scanner s) {
		return null;
	}

	abstract void prettyPrint();

	abstract RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue;

}
