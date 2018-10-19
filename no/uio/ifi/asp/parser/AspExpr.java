package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


/**
 * contains 1-n AspAndTests
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
public class AspExpr extends AspSyntax {
  ArrayList<AspAndTest> andTests = new ArrayList<>();

  public AspExpr(int n) {
    super(n);
  }

  /**
   * parses the asp expr
   * @param  Scanner s             [description]
   * @return         [description]
   */
  public static AspExpr parse(Scanner s) {
    enterParser("expr");
    AspExpr expr = new AspExpr(s.curLineNum());
    while (true) {
      expr.andTests.add(AspAndTest.parse(s));
      if (s.curToken().kind != orToken) break;
      skip(s, orToken);
    }

    leaveParser("expr");
    return expr;
  }

  /**
  * converts the syntax tree back to a readable asp program.
  */
  @Override
  public void prettyPrint() {
    int nPrinted = 0;

    for (AspAndTest ant: andTests) {
      if (nPrinted > 0)
      Main.log.prettyWrite(" or ");
      ant.prettyPrint(); ++nPrinted;
    }
  }

  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
