package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspFactor extends AspSyntax {
  ArrayList<AspPrimary> prims = new ArrayList<>();
	ArrayList<AspFactorPrefix> prefs = new ArrayList<>();
	ArrayList<AspFactorOpr> factop = new ArrayList<>();


	//ASK FOR HELP:
	//skal listen inneholde [term comp term comp term] eller [term term term] med en ekstra liste med [comp comp comp] i seg?


  AspFactor(int n) {
    super(n);
  }

  static AspFactor parse(Scanner s) {
    enterParser("factor");

    AspFactor afct = new AspFactor(s.curLineNum());
    while (true) {

			Token tok = s.curToken();
      if (anyFactorPrefix(tok.kind)) {
        afct.prefs.add(AspFactorPrefix.parse(s));
      }

			else {
        afct.prefs.add(null);
      }

      afct.prims.add(AspPrimary.parse(s));
      tok = s.curToken();
      if (anyFactorOpr(tok.kind)) {
        afct.factop.add(AspFactorOpr.parse(s));
      }
			else {break;}
    }

    leaveParser("factor");
    return afct;
  }


  //add this to the parse method for easy acess of something i guess
  public static boolean anyFactorPrefix(TokenKind tk) {
    if (tk == plusToken || tk == minusToken) {
      return true;


    }
    return false;
  }

  public static boolean anyFactorOpr(TokenKind tk) {
    if (tk == slashToken || tk == doubleSlashToken || tk == astToken || tk == percentToken) {
      return true;
    }
    return false;
  }

  @Override
  void prettyPrint() {
    int nPrinted = 0;

    for (AspPrimary p: prims) {
      if (nPrinted > 0) {
        Main.log.prettyWrite(" ");
        factop.get(nPrinted-1).prettyPrint();
        Main.log.prettyWrite(" ");
      }
			if(prefs.get(nPrinted) != null){
				prefs.get(nPrinted).prettyPrint();
			}
      p.prettyPrint(); ++nPrinted;
    }
  }

  // @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
