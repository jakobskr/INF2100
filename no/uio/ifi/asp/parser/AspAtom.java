package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspAtom extends AspSyntax {

	public AspAtom(int n) {
		super(n);
	}

	static AspAtom parse(Scanner s) {
		enterParser("Atom");
    AspAtom atom = null;
    Token temp = s.curToken();

    if (temp.kind == nameToken) {
      atom = AspName.parse(s);
    }

    if (temp.kind == integerToken) {
      atom = AspInt.parse(s);
    }

    if (temp.kind == floatToken) {
      atom = AspFloat.parse(s);
    }

    if (temp.kind == stringToken) {
      atom = AspString.parse(s);
    }

    if (temp.kind == trueToken || temp.kind == falseToken) {
      atom = AspBool.parse(s);
    }

    if (temp.kind == noneToken) {
      atom = AspNone.parse(s);
    }

    if (temp.kind == leftParToken) {
      atom = AspInnerExpression.parse(s);
    }

    if (temp.kind == leftBracketToken) {
      atom = AspListDisp.parse(s);
    }

    if (temp.kind == leftBraceToken) {
      atom = AspDictDisplay.parse(s);
    }


    leaveParser("Atom");

    if (atom == null) {
      parserError("Unexpected \"" + s.curToken() + "\" token", s.curLineNum());
    }
    return atom;
	}

	void prettyPrint() {
    int n = 0;
  }

	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return null;
  }

}
