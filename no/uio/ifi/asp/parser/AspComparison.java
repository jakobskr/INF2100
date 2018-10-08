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
    System.out.println("comparison");
   AspComparison acmp = new AspComparison(s.curLineNum());
   while (true) {
     acmp.terms.add(AspTerm.parse(s));
          Token tok = s.curToken();

     if (tok.kind == greaterToken ) acmp.ops.add(new AspCompOpr (tok));
           else if (tok.kind == lessToken) acmp.ops.add(new AspCompOpr (tok));
           else if (tok.kind == equalToken) acmp.ops.add(new AspCompOpr (tok));
           else if (tok.kind == lessEqualToken) acmp.ops.add(new AspCompOpr (tok));
           else if (tok.kind == greaterEqualToken) acmp.ops.add(new AspCompOpr (tok));
           else if (tok.kind == notEqualToken) acmp.ops.add(new AspCompOpr (tok));
           else {break;}
           s.readNextToken();
   }

   leaveParser("comparison");
   return acmp;
  }

  @Override
  void prettyPrint() {
    int nPrinted = 0;

    /*for (AspNotTest at: terms) {
      if (nPrinted > 0)
      Main.log.prettyWrite(" and ");
      ant.prettyPrint(); ++nPrinted;
    }*/
  }

  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
