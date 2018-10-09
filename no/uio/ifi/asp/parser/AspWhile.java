package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspWhile extends AspStmt{
	AspSuite sut;
	AspExpr exp;

	AspWhile(int n) {
    super(n);
  }

	static AspWhile parse(Scanner s) {
		enterParser("while");

		AspWhile awil = new AspWhile(s.curLineNum());

		skip(s, whileToken);
		awil.exp = AspExpr.parse(s);
		skip(s,colonToken);
		awil.sut = AspSuite.parse(s);

		leaveParser("while");
		return awil;

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
