package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspIf extends AspStmt {
  ArrayList<AspExpr> exps = new ArrayList<>();
  ArrayList<AspSuite> sut = new ArrayList<>();
  AspSuite elsut = null;

  AspIf(int n) {
    super(n);
  }

  static AspIf parse(Scanner s) {
    enterParser("if stmt");

    AspIf asif = new AspIf(s.curLineNum());

    skip(s, ifToken);

    while (true) {
      System.out.println("xd");
      asif.exps.add(AspExpr.parse(s));
      skip(s,colonToken);
      asif.sut.add(AspSuite.parse(s));

      TokenKind temp = s.curToken().kind;
      if (temp == elifToken ) skip (s , elifToken);

      else {
        break;
      }

    }

    if (s.curToken().kind == elseToken){
      skip(s,elseToken);
      skip(s,colonToken);
      asif.elsut = AspSuite.parse(s);
    }

    leaveParser("if stmt");
    return asif;
  }


  RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return null;
  }

  @Override
  void prettyPrint() {
    Main.log.prettyWriteLn();
    int nPrinted = 0;
    Main.log.prettyWrite("if ");
    for (AspExpr ant: exps) {
      if (nPrinted > 0)
      ant.prettyPrint();
      Main.log.prettyWrite(": ");
      sut.get(nPrinted).prettyPrint();
      ++nPrinted;
      if (elsut != null){
        Main.log.prettyWrite("else: ");
        Main.log.prettyWriteLn();
        elsut.prettyPrint();
      }
    }

  }

}
