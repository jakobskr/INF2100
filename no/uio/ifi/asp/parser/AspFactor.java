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
			if (tok.kind == plusToken ) {
        afct.prefs.add(new AspFactorPrefix(tok));
        skip(s, tok.kind);
      }
			else if (tok.kind == minusToken ) {
        afct.prefs.add(new AspFactorPrefix(tok));
        skip(s, tok.kind);
      }
			else {
        afct.prefs.add(new AspFactorPrefix(null));
      }

      afct.prims.add(AspPrimary.parse(s));
      tok = s.curToken();
      if (tok.kind == slashToken) {
        afct.factop.add(new AspFactorOpr(tok));
        skip(s,tok.kind);
      }
			else if (tok.kind == doubleSlashToken) {
        afct.factop.add(new AspFactorOpr(tok));
        skip(s, tok.kind);
      }
      else if (tok.kind == astToken) {
        afct.factop.add(new AspFactorOpr(tok));
        skip(s, tok.kind);
      }
      else if (tok.kind == percentToken) {
        afct.factop.add(new AspFactorOpr(tok));
        skip(s, tok.kind);
      }
			else {break;}
    }

    leaveParser("factor");
    return afct;
  }

  @Override
  void prettyPrint() {
    int nPrinted = 0;

    for (AspPrimary p: prims) {
      if (nPrinted > 0)
      main.log.prettyWrite(factop.get(nPrinted-1).tok.toString());
			if(prims(nPrinted).tok != null){
				main.log.prettyWrite(prims(nPrinted).tok.toString());
			}
      prims.prettyPrint(); ++nPrinted;
    }
  }

  // @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
