package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspExpr extends AspSyntax {
  //-- Must be changed in part 2:
  ArrayList<AspAndTest> andTests = new ArrayList<>();

  public AspExpr(int n) {
    super(n);
  }

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
