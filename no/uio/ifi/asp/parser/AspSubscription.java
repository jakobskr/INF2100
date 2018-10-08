package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspSubscription extends AspPrimarySuffix {
  ArrayList<AspExpr> exps = new ArrayList<>();
	//ASK FOR HELP:
	//skal listen inneholde [term comp term comp term] eller [term term term] med en ekstra liste med [comp comp comp] i seg?


  AspSubscription(int n) {
    super(n);
  }

  static AspSubscription parse(Scanner s) {
    enterParser("subscription");

    AspSubscription asub = new AspSubscription(s.curLineNum());

		skip(s, leftBracketToken);

		asub.exps.add(AspExpr.parse(s));
		TokenKind temp = s.curToken().kind;

		skip(s, rightBracketToken);


    leaveParser("arguments");
    return asub;
  }

  @Override
  void prettyPrint() {
    int nPrinted = 0;

    // for (AspNotTest at: terms) {
    //   if (nPrinted > 0)
    //   Main.log.prettyWrite(" factor ");
    //   ant.prettyPrint(); ++nPrinted;
    // }
  }

  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
