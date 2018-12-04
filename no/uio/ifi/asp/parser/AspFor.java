package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

/**
 * Contains a name. an expression and then a suite
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspFor extends AspStmt{
	AspSuite sut;
	AspName name;
	AspExpr exp;

	AspFor(int n) {
    super(n);
  }

	static AspFor parse(Scanner s) {
		enterParser("for stmt");
		AspFor afor = new AspFor(s.curLineNum());
		skip(s, forToken);
		afor.name = AspName.parse(s);
		skip(s, inToken);
		afor.exp = AspExpr.parse(s);
		skip(s,colonToken);
		afor.sut = AspSuite.parse(s);

		leaveParser("for stmt");
		return afor;

	}

	/**
	* converts the syntax tree back to a readable asp program.
	*/
	@Override
  void prettyPrint() {
		Main.log.prettyWriteLn();
    Main.log.prettyWrite("for ");
		name.prettyPrint();
		Main.log.prettyWrite(" in ");
		exp.prettyPrint();
		Main.log.prettyWrite(":");
		sut.prettyPrint();
  }

	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
		RuntimeValue temp = exp.eval(curScope);
		if (temp instanceof RuntimeListValue) {
			RuntimeListValue list = (RuntimeListValue) temp;			
			RuntimeValue ret = null;
			for (RuntimeValue r: list.listvalue) {
				curScope.assign(name.name, r);
			}
		}

		else {
			RuntimeValue.runtimeError("for loop range is not a list", this);
		}
		return null;
	}
}


//e-o-f
