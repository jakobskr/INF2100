package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

/**
 * contains 1 expression
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspSubscription extends AspPrimarySuffix {
  AspExpr exps = null;

  AspSubscription(int n) {
    super(n);
  }

  static AspSubscription parse(Scanner s) {
    enterParser("subscription");

    AspSubscription asub = new AspSubscription(s.curLineNum());

		skip(s, leftBracketToken);

		asub.exps = AspExpr.parse(s);
		TokenKind temp = s.curToken().kind;

		skip(s, rightBracketToken);


    leaveParser("subscription");
    return asub;
  }

  @Override
  /**
  * converts the syntax tree back to a readable asp program.
  */
  void prettyPrint() {
    Main.log.prettyWrite("[");
		exps.prettyPrint();
		Main.log.prettyWrite("]");

  }

  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
