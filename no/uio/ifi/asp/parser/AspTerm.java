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
      if (tok.kind == plusToken) atrm.ops.add(new AspTermOpr(tok));
			else if (tok.kind == minusToken) atrm.ops.add(new AspTermOpr(tok));
			else {break;}
    }

    leaveParser("term");
    return atrm;
  }

  @Override
  void prettyPrint() {
    int nPrinted = 0;

    for (AspFactor ant: factors) {
      if (nPrinted > 0)
      Main.log.prettyWrite(ops.get(nprinted-1).toString());
      ant.prettyPrint(); ++nPrinted;
    }
  }

  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
