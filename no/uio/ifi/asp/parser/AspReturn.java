package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

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
  void prettyPrint() {
    Main.log.prettyWrite("return ");
		expr.prettyPrint();
		Main.log.prettyWriteLn();

  }

  // @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
