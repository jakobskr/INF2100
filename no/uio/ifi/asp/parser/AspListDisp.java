package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspListDisp extends AspAtom {
  ArrayList<AspExpr> exps = new ArrayList<>();

	//ASK FOR HELP:
	//skal listen inneholde [term comp term comp term] eller [term term term] med en ekstra liste med [comp comp comp] i seg?


  AspListDisp(int n) {
    super(n);
  }

  static AspListDisp parse(Scanner s) {
    enterParser("List");
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
    leaveParser("List");
    return alst;
  }

  @Override
  void prettyPrint() {
  //   int nPrinted = 0;
  //
  //   for (AspNotTest at: terms) {
  //     if (nPrinted > 0)
  //     Main.log.prettyWrite(" list ");
  //     ant.prettyPrint(); ++nPrinted;
  //   }
  }
}
