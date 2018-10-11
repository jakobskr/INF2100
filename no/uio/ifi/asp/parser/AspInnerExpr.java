package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspInnerExpression extends AspAtom {
  AspExpr exps;


  AspInnerExpression(int n) {
    super(n);
  }

  static AspInnerExpression parse(Scanner s) {
    enterParser("inner expression");

    AspInnerExpression asub = new AspInnerExpression(s.curLineNum());

		skip(s, leftParToken);

		asub.exps = AspExpr.parse(s);
		TokenKind temp = s.curToken().kind;

		skip(s, rightParToken);

    leaveParser("inner expression");
    return asub;
  }

  @Override
  void prettyPrint() {
    exps.prettyPrint();
  }

  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
