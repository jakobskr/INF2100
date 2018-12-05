package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


/**
 * the return statement
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
public class AspReturn extends AspStmt {

  AspExpr expr;

  public AspReturn(int n) {
    super(n);
  }

  public static AspReturn parse(Scanner s) {
    enterParser("return stmt");
    skip(s, returnToken);
    AspReturn aret = new AspReturn(s.curLineNum());
    aret.expr = AspExpr.parse(s);
    skip(s,newLineToken);
    leaveParser("return stmt");
    return aret;
  }

  @Override
  /**
	* converts the syntax tree back to a readable asp program.
	*/
  void prettyPrint() {
    Main.log.prettyWrite("return ");
		expr.prettyPrint();
		Main.log.prettyWriteLn();

  }

  // @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    if( expr == null){
			throw new RuntimeReturnValue(new RuntimeNoneValue(), lineNum);
		}
		RuntimeValue val = expr.eval(curScope);
    trace("return " + val.toString());
		RuntimeReturnValue ret = new RuntimeReturnValue(val, lineNum);
		throw ret;
  }
}
