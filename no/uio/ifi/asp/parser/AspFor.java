package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspFor extends AspStmt{
	AspSuite sut;
	AspName name;
	AspExpr exp;

	AspFor(int n) {
    super(n);
  }

	static AspFor parse(Scanner s) {
		enterParser("For");

		AspFor afor = new AspFor(s.curLineNum());

		skip(s, forToken);
		afor.name = AspName.parse(s);
		skip(s, inToken);
		afor.exp = AspExpr.parse(s);
		skip(s,colonToken);
		afor.sut = AspSuite.parse(s);

		leaveParser("for");
		return afor;

	}
	@Override
  void prettyPrint() {
    // int nPrinted = 0;
		//
    // for (AspNotTest at: terms) {
    //   if (nPrinted > 0)
    //   Main.log.prettyWrite(" for ");
    //   ant.prettyPrint(); ++nPrinted;
    // }
  }

	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
		return null;
	}
}


//e-o-f
