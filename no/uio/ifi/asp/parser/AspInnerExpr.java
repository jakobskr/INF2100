package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

/**
 * Contains an expression
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspInnerExpression extends AspAtom {
  AspExpr exps;


  AspInnerExpression(int n) {
    super(n);
  }

  static AspInnerExpression parse(Scanner s) {
    enterParser("inner expr");

    AspInnerExpression asub = new AspInnerExpression(s.curLineNum());

		skip(s, leftParToken);

		asub.exps = AspExpr.parse(s);
		TokenKind temp = s.curToken().kind;

		skip(s, rightParToken);

    leaveParser("inner expr");
    return asub;
  }

  @Override
  /**
	* converts the syntax tree back to a readable asp program.
	*/
  void prettyPrint() {
    Main.log.prettyWrite("(");
    exps.prettyPrint();
    Main.log.prettyWrite(")");
  }

  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return exps.eval(curScope);
  }
}
