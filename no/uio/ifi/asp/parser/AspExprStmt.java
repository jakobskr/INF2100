package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;
/**
 * Contains an AspExpr
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
public class AspExprStmt extends AspStmt {

  AspExpr expr;

  public AspExprStmt(int n) {
    super(n);
  }

  /**
   * parser the AspExprStmt
   * @param  Scanner s             AspScanner
   * @return         [description]
   */
  public static AspExprStmt parse(Scanner s) {
    enterParser("expr stmt");

    AspExprStmt aes = new AspExprStmt(s.curLineNum());

    aes.expr = AspExpr.parse(s);
    skip(s,newLineToken);

    leaveParser("expr stmt");
    return aes;
  }

  /**
  * converts the syntax tree back to a readable asp program.
  */
  @Override
  void prettyPrint() {
    expr.prettyPrint();
		Main.log.prettyWriteLn();

  }

  /**
   * evaluates the delicious statement held within
   * @param  curScope           current scope
   * @return                    RuntimeValue from evaluation of expr
   * @throws RuntimeReturnValue -
   */
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return expr.eval(curScope);
  }
}
