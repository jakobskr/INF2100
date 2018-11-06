package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

/**
 * contains 0-n <string literal, expr> tuples
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspDictDisplay extends AspAtom {
  HashMap<AspString, AspExpr> exps = new HashMap<AspString, AspExpr>();

  AspDictDisplay(int n) {
    super(n);
  }

  /**
   * parses the asp DictDisplay
   * @param  Scanner s             [description]
   * @return         [description]
   */
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
  /**
   * converts the syntax tree back to a readable asp program.
   */
  void prettyPrint() {
    int nPrinted = 0;
		Main.log.prettyWrite("{");

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

	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
		HashMap<String,RuntimeValue> h = new HashMap<String,RuntimeValue>();
		for (AspString s : exps.keySet()) {
			h.put(s.value, exps.get(s).eval(curScope));
		}
    return new RuntimeDictValue(h);
  }
}
