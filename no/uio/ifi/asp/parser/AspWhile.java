package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;
/**
 * contains 1 expression then 1 suite
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */

class AspWhile extends AspStmt{
	AspSuite sut;
	AspExpr exp;

	AspWhile(int n) {
    super(n);
  }

	static AspWhile parse(Scanner s) {
		enterParser("while stmt");

		AspWhile awil = new AspWhile(s.curLineNum());

		skip(s, whileToken);
		awil.exp = AspExpr.parse(s);
		skip(s,colonToken);
		awil.sut = AspSuite.parse(s);

		leaveParser("while stmt");
		return awil;

	}
	@Override
	/**
	* converts the syntax tree back to a readable asp program.
	*/
  void prettyPrint() {
		Main.log.prettyWriteLn();
  	Main.log.prettyWrite("while");
		exp.prettyPrint();
		Main.log.prettyWrite(": ");
		sut.prettyPrint();
  }

	public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
		for(AspExpr e: exp ){
			curScope.assign(name, exp.eval(curScope));
			sut.eval(curScope);
		}
		return null;
	}
}


//e-o-f
