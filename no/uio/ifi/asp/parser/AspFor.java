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
		enterParser("for");
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
    Main.log.prettyWrite("for");
		name.prettyPrint();
		Main.log.prettyWrite("in");
		exp.prettyPrint();
		Main.log.prettywrite(":");
		sut.prettyPrint();
  }

	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
		return null;
	}
}


//e-o-f
