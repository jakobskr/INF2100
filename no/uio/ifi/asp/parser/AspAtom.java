package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;
/**
 * An abstract superclass for all the literals
 *
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
public abstract class AspAtom extends AspSyntax {

	public AspAtom(int n) {
		super(n);
	}

	/**
	 * checks with literal we have to parse
	 * @param  Scanner s             [description]
	 * @return         [description]
	 */
	static AspAtom parse(Scanner s) {
		enterParser("atom");
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


    leaveParser("atom");

    if (atom == null) {
      parserError("Unexpected \"" + s.curToken() + "\" token", s.curLineNum());
    }
    return atom;
	}

	/**
	 * converts the syntax tree back to a readable asp program.
	 */
	@Override
	abstract void prettyPrint();

	/**
	 * atom is abstract and the eval method is not to be used.
	 * @param  curScope           current scope
	 * @return                    -
	 * @throws RuntimeReturnValue -
	 */
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return null;
  }

}
