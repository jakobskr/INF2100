package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspTerm extends AspSyntax {
  ArrayList<AspFactor> factors = new ArrayList<>();
	ArrayList<AspTermOpr> ops = new ArrayList<>();

	//ASK FOR HELP:
	//skal listen inneholde [term comp term comp term] eller [term term term] med en ekstra liste med [comp comp comp] i seg?


  AspTerm(int n) {
    super(n);
  }

  static AspTerm parse(Scanner s) {
    enterParser("term");

    AspTerm atrm = new AspTerm(s.curLineNum());
    while (true) {
      atrm.factors.add(AspFactor.parse(s));
			Token tok = s.curToken();
      if (anyTermOpr(tok.kind)) atrm.ops.add(AspTermOpr.parse(s));
			else {break;}
    }

    leaveParser("term");
    return atrm;
  }

  @Override
  void prettyPrint() {
    int nPrinted = 0;

    for (AspFactor ant: factors) {
      if (nPrinted > 0) {
        Main.log.prettyWrite(" ");
        ops.get(nPrinted - 1).prettyPrint();
        Main.log.prettyWrite(" ");
      }
      ant.prettyPrint(); ++nPrinted;
    }
  }

  public static boolean anyTermOpr(TokenKind tk) {
    if (tk == plusToken || tk == minusToken) {
      return true;
    }
    return false;
  }

  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
