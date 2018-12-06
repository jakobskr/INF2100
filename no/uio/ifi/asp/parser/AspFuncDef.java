package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


/**
 * Contains a name. then 0-n names, and then a suite
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
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
		ArrayList<String> parnames = new ArrayList<String>();
		trace("def " + name.name);
		for(AspName nam: names){
			parnames.add(nam.name);
		}
		curScope.assign(name.name, new RuntimeFunc(name.name,parnames,sut, curScope));
    return null;
  }

  /**
	* converts the syntax tree back to a readable asp program.
	*/
  @Override
  void prettyPrint() {
    int nPrinted = 0;
    Main.log.prettyWrite("def ");
		name.prettyPrint();
		Main.log.prettyWrite("(");
		nPrinted = 0;
		for (AspName p: names) {
      if (nPrinted > 0)
      Main.log.prettyWrite(", ");
      p.prettyPrint(); ++nPrinted;
    }
		Main.log.prettyWrite(")");
		Main.log.prettyWrite(":");
		sut.prettyPrint();
  }
}
