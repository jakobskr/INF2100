package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;




/**
 * Contains 0-n expressions.
 *
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspArguments extends AspPrimarySuffix {
  ArrayList<AspExpr> exps = new ArrayList<>();



	//ASK FOR HELP:
	//skal listen inneholde [term comp term comp term] eller [term term term] med en ekstra liste med [comp comp comp] i seg?


  AspArguments(int n) {
    super(n);
  }

  static AspArguments parse(Scanner s) {
    enterParser("arguments");

    AspArguments aarg = new AspArguments(s.curLineNum());

		skip(s, leftParToken);


    while (s.curToken().kind != rightParToken) {

			aarg.exps.add(AspExpr.parse(s));

			TokenKind temp = s.curToken().kind;
			if (temp == commaToken) {
        skip(s,commaToken);
        if (s.curToken().kind == rightParToken) {
          parserError("Expected expression but got nothing", s.curLineNum());
        }
      }
    }

		skip(s, rightParToken);
    leaveParser("arguments");
    return aarg;
  }

	@Override
	public void prettyPrint() {
		int nPrinted = 0;
		Main.log.prettyWrite("(");
		for (AspExpr ant: exps) {
			if (nPrinted > 0)
			Main.log.prettyWrite(", ");
			ant.prettyPrint(); ++nPrinted;
		}
		Main.log.prettyWrite(")");
	}

  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
