package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspDictDisplay extends AspAtom {
  HashMap<AspString, AspExpr> exps = new HashMap<AspString, AspExpr>();

	//ASK FOR HELP:
	//skal listen inneholde [term comp term comp term] eller [term term term] med en ekstra liste med [comp comp comp] i seg?

  AspDictDisplay(int n) {
    super(n);
  }

  static AspDictDisplay parse(Scanner s) {
    enterParser("dict display");
    AspDictDisplay adict = new AspDictDisplay(s.curLineNum());
		skip(s, leftBraceToken);

    while (s.curToken().kind != rightBraceToken) {

			AspString tmp = AspString.parse(s);
			skip(s, colonToken);
			adict.exps.put(tmp, AspExpr.parse(s));

			TokenKind temp = s.curToken().kind;
			if (temp == commaToken ){
				skip(s , commaToken);
				if(s.curToken().kind == rightBraceToken){
					parserError("expected expression but found nothing", s.curLineNum());
				}
			}
    }

		skip(s, rightBraceToken);
    leaveParser("dict display");
    return adict;
  }

  @Override
  //This method needs some heavy duty testing after we manage to compile our project
  void prettyPrint() {
    int nPrinted = 0;
		Main.log.prettyWrite("{");
		/*for (AspString ant: exps) {
      if (nPrinted > 0)
      Main.log.prettyWrite(", ");
      ant.prettyPrint(); ++nPrinted;
			Main.log.prettyWrite(":");
			exps.get(ant).prettyPrint();
    }*/

    for(Map.Entry<AspString, AspExpr> entry : exps.entrySet()) {
      if (nPrinted > 0)
      Main.log.prettyWrite(", ");

      AspString astr = entry.getKey();
      astr.prettyPrint();
      Main.log.prettyWrite(":");
      AspExpr aexp = entry.getValue();
      aexp.prettyPrint();
      nPrinted++;
    }
    Main.log.prettyWrite("}");
  }
}
