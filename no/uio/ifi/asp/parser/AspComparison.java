package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspComparison extends AspSyntax {
  ArrayList<AspTerm> terms = new ArrayList<>();
  ArrayList<AspCompOpr> ops = new ArrayList<>();
  //ASK FOR HELP:
  //skal listen inneholde [term comp term comp term] eller [term term term] med en ekstra liste med [comp comp comp] i seg?


  public AspComparison(int n) {
    super(n);
  }

  static AspComparison parse(Scanner s) {
    enterParser("comparison");
    AspComparison acmp = new AspComparison(s.curLineNum());
    while (true) {
      acmp.terms.add(AspTerm.parse(s));
      Token tok = s.curToken();

      if (anyCompOpr(tok.kind)) acmp.ops.add(AspCompOpr.parse(s));
      else {break;}
      //s.readNextToken();
    }

    leaveParser("comparison");
    return acmp;
  }

  @Override
  void prettyPrint() {
    int nPrinted = 0;

    for (AspTerm ant: terms) {
      if (nPrinted > 0) {
        Main.log.prettyWrite(" ");
        ops.get(nPrinted-1).prettyPrint();
        Main.log.prettyWrite(" ");
      }
      ant.prettyPrint();
      nPrinted++;
    }
  }

  public static boolean anyCompOpr(TokenKind tk) {
    if (tk == greaterToken || tk == lessToken || tk == doubleEqualToken || tk == lessEqualToken
    || tk == greaterEqualToken || tk == notEqualToken) {
      return true;
    }
    return false;
  }


  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
