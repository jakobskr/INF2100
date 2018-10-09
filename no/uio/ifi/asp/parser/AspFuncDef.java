package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspFuncDef extends AspStmt {
  ArrayList<AspName> names = new ArrayList<>();
	AspSuite sut = null;
	AspName name = null;


  AspFuncDef(int n) {
    super(n);
  }

  static AspFuncDef parse(Scanner s) {
    enterParser("func def");
    AspFuncDef afuc = new AspFuncDef(s.curLineNum());
		skip(s, defToken);
		afuc.name = AspName.parse(s);
		skip(s, leftParToken);
    while (s.curToken().kind != rightParToken) {

			afuc.names.add(AspName.parse(s));

			TokenKind temp = s.curToken().kind;
			if (temp == commaToken ){
				skip (s , commaToken);
				if(s.curToken().kind == rightParToken){
					parserError("expected name but found nothing", s.curLineNum());
				}
			}

    }

		skip(s, rightParToken);
		skip(s, colonToken);

		afuc.sut = AspSuite.parse(s);

    leaveParser("func def");
    return afuc;
  }


  RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return null;
  }

  @Override
  void prettyPrint() {
    int nPrinted = 0;
    //
    // for (AspNotTest at: terms) {
    //   if (nPrinted > 0)
    //   Main.log.prettyWrite(" factor ");
    //   ant.prettyPrint(); ++nPrinted;
    // }
  }
}
