package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspPrimary extends AspSyntax {
	ArrayList<AspPrimarySuffix> prisuf = new ArrayList<>();
  AspAtom atom;
	//ASK FOR HELP:
	//skal listen inneholde [term comp term comp term] eller [term term term] med en ekstra liste med [comp comp comp] i seg?

  AspPrimary(int n) {
    super(n);
  }

  static AspPrimary parse(Scanner s) {
    enterParser("primary");

    AspPrimary aprm = new AspPrimary(s.curLineNum());
    aprm.atom = AspAtom.parse(s);
		while (true) {

			Token tok = s.curToken();
      if (tok.kind == leftParToken ) aprm.prisuf.add(AspArguments.parse(s));
			else if (tok.kind == leftBracketToken ) aprm.prisuf.add(AspSubscription.parse(s));
			else {break;}
    }

    leaveParser("primary");
    return aprm;
  }

  // @Override
   void prettyPrint() {
  //   int nPrinted = 0;
	//
  //   for (AspNotTest at: terms) {
  //     if (nPrinted > 0)
  //     Main.log.prettyWrite(" factor ");
  //     ant.prettyPrint(); ++nPrinted;
  //   }
}

  // @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
