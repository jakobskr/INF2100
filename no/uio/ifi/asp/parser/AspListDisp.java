package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


/**
 * contains 0-n expressions
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspListDisp extends AspAtom {
  ArrayList<AspExpr> exps = new ArrayList<>();

  AspListDisp(int n) {
    super(n);
  }

  static AspListDisp parse(Scanner s) {
    enterParser("list display");
    AspListDisp alst = new AspListDisp(s.curLineNum());
		skip(s, leftBracketToken);

    while (s.curToken().kind != rightBracketToken) {
			alst.exps.add(AspExpr.parse(s));
			TokenKind temp = s.curToken().kind;

			if (temp == commaToken ) {
        skip (s , commaToken);

        if (s.curToken().kind == rightBracketToken) {
          parserError("expected expression but found none", s.curLineNum());
        }
      }
    }

		skip(s, rightBracketToken);
    leaveParser("list display");
    return alst;
  }

  /**
  * converts the syntax tree back to a readable asp program.
  */
  @Override
  void prettyPrint() {
    int nPrinted = 0;
		Main.log.prettyWrite("[");
    for (AspExpr at: exps) {
      if (nPrinted > 0)
      Main.log.prettyWrite(", ");
      at.prettyPrint(); ++nPrinted;
    }
		Main.log.prettyWrite("]");
  }

	/**
	 * builds a RuntimeListValue from evaluations of expressions held in exps
	 *
	 * @param  curScope           current scope
	 * @return                    RuntimeListValue
	 * @throws RuntimeReturnValue -
	 */
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
		ArrayList<RuntimeValue> p = new ArrayList<RuntimeValue>();
		for (AspExpr e : exps ) {
			p.add(e.eval(curScope));
		}
    return new RuntimeListValue(p);
  }
}
