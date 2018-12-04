package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


/**
 * Contains a contains 1-n tuples of <expr,suite>
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspIf extends AspStmt {
  ArrayList<AspExpr> exps = new ArrayList<>();
  ArrayList<AspSuite> sut = new ArrayList<>();
  AspSuite elsut = null;

  AspIf(int n) {
    super(n);
  }

  static AspIf parse(Scanner s) {
    enterParser("if stmt");

    AspIf asif = new AspIf(s.curLineNum());

    skip(s, ifToken);

    while (true) {
      asif.exps.add(AspExpr.parse(s));
      skip(s,colonToken);
      asif.sut.add(AspSuite.parse(s));

      TokenKind temp = s.curToken().kind;
      if (temp == elifToken ) skip (s , elifToken);

      else {
        break;
      }

    }

    if (s.curToken().kind == elseToken){
      skip(s,elseToken);
      skip(s,colonToken);
      asif.elsut = AspSuite.parse(s);
    }

    leaveParser("if stmt");
    return asif;
  }


  RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
		int i = 0;
		while (i<exps.size()){
			if(exps.get(i).eval(curScope).getBoolValue("if", this)){
				trace("if True alt #" + (i + 1));
        sut.get(i).eval(curScope);
				return null;
			}
			i++;
		}
		if(elsut != null){
      trace("else: ...");
			elsut.eval(curScope);
		}
    return null;
  }

  @Override
  /**
	* converts the syntax tree back to a readable asp program.
	*/
  void prettyPrint() {
    int nPrinted = 0;
    Main.log.prettyWrite("if ");
    for (AspExpr ant: exps) {
      if (nPrinted > 0){

				Main.log.prettyWrite("elif ");
			}
      ant.prettyPrint();
      Main.log.prettyWrite(": ");
      sut.get(nPrinted).prettyPrint();
      ++nPrinted;

    }
		if (elsut != null){

			Main.log.prettyWrite("else: ");
			elsut.prettyPrint();
		}

  }

}
