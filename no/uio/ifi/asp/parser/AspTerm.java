package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

/**
 * contains 1-n factors and 0-(n-1) compoprs
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspTerm extends AspSyntax {
  ArrayList<AspFactor> factors = new ArrayList<>();
	ArrayList<AspTermOpr> ops = new ArrayList<>();

  AspTerm(int n) {
    super(n);
  }

  static AspTerm parse(Scanner s) {
    enterParser("term");

    AspTerm atrm = new AspTerm(s.curLineNum());
    while (true) {
      atrm.factors.add(AspFactor.parse(s));
			Token tok = s.curToken();
      if (anyTermOpr(tok.kind)) atrm.ops.add(AspTermOpr.parse(s));
			else {break;}
    }

    leaveParser("term");
    return atrm;
  }

  @Override
  /**
	* converts the syntax tree back to a readable asp program.
	*/
  void prettyPrint() {
    int nPrinted = 0;

    for (AspFactor ant: factors) {
      if (nPrinted > 0) {
        Main.log.prettyWrite(" ");
        ops.get(nPrinted - 1).prettyPrint();
        Main.log.prettyWrite(" ");
      }
      ant.prettyPrint(); ++nPrinted;
    }
  }

  /**
   * checks if the given token is a termopr
   * @param  TokenKind tk            [description]
   * @return           [description]
   */
  public static boolean anyTermOpr(TokenKind tk) {
    if (tk == plusToken || tk == minusToken) {
      return true;
    }
    return false;
  }


	/**
	 * term evaluates + and - expressions
	 * @param  curScope           current scope
	 * @return                    RuntimeValue
	 * @throws RuntimeReturnValue -
	 */
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    RuntimeValue v = factors.get(0).eval(curScope);
    for (int i = 1; i < factors.size() ; i++) {
      if (ops.get(i - 1).tok.kind == plusToken) {
        v = v.evalAdd(factors.get(i).eval(curScope), this);
      }
      else if (ops.get(i - 1).tok.kind == minusToken) {
        v = v.evalSubtract(factors.get(i).eval(curScope), this);
      }
    }
    return v;
  }
}
