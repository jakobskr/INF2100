package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspSubscription extends AspPrimarySuffix {
  AspExpr exps = null;
	//ASK FOR HELP:
	//skal listen inneholde [term comp term comp term] eller [term term term] med en ekstra liste med [comp comp comp] i seg?


  AspSubscription(int n) {
    super(n);
  }

  static AspSubscription parse(Scanner s) {
    enterParser("primary suffix");
    enterParser("subscription");

    AspSubscription asub = new AspSubscription(s.curLineNum());

		skip(s, leftBracketToken);

		asub.exps = AspExpr.parse(s);
		TokenKind temp = s.curToken().kind;

		skip(s, rightBracketToken);


    leaveParser("arguments");
    leaveParser("primary suffix");
    return asub;
  }

  @Override
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
