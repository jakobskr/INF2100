package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


/**
 * Asp comparison  contains 1-n terms, and 0 to n-1 term oprs
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspComparison extends AspSyntax {
  ArrayList<AspTerm> terms = new ArrayList<>();
  ArrayList<AspCompOpr> ops = new ArrayList<>();

  public AspComparison(int n) {
    super(n);
  }

  /**
   * parser the comparisons
   * @param  Scanner s             [description]
   * @return         [description]
   */
  static AspComparison parse(Scanner s) {
    enterParser("comparison");
    AspComparison acmp = new AspComparison(s.curLineNum());
    while (true) {
      acmp.terms.add(AspTerm.parse(s));
      Token tok = s.curToken();

      if (anyCompOpr(tok.kind)) acmp.ops.add(AspCompOpr.parse(s));
      else {break;}
      //s.readNextToken();
    }

    leaveParser("comparison");
    return acmp;
  }


  /**
   * converts the syntax tree back to a readable asp program.
   */
  @Override
  void prettyPrint() {
    int nPrinted = 0;

    for (AspTerm ant: terms) {
      if (nPrinted > 0) {
        Main.log.prettyWrite(" ");
        ops.get(nPrinted-1).prettyPrint();
        Main.log.prettyWrite(" ");
      }
      ant.prettyPrint();
      nPrinted++;
    }
  }

  /**
   * checks if the given token is a valid compOpr token
   * @param  TokenKind tk            [description]
   * @return           [description]
   */
  public static boolean anyCompOpr(TokenKind tk) {
    if (tk == greaterToken || tk == lessToken || tk == doubleEqualToken || tk == lessEqualToken
    || tk == greaterEqualToken || tk == notEqualToken) {
      return true;
    }
    return false;
  }

	/**
	 * comparison evaluates to a boolean value dependant on the comparisons the tokens repressent.
	 * @param  curScope           current scope
	 * @return                    RuntimeBoolValue
	 * @throws RuntimeReturnValue -
	 */
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    RuntimeValue v = terms.get(0).eval(curScope);
    RuntimeValue ret = v;
    for (int i = 1; i < terms.size() ; i++) {

      switch (ops.get(i - 1).tok.kind) {

        case greaterToken: {
          if (!v.evalGreater(terms.get(i).eval(curScope), this).getBoolValue("compOpr",this)) {
            return new RuntimeBoolValue(false);
          }
          v = terms.get(i).eval(curScope);
          ret = new RuntimeBoolValue(true);
          break;
        }

        case greaterEqualToken: {
          if (!v.evalGreaterEqual(terms.get(i).eval(curScope), this).getBoolValue("compOpr",this)) {
            return new RuntimeBoolValue(false);
          }
          v = terms.get(i).eval(curScope);
          ret = new RuntimeBoolValue(true);

          break;
        }

        case lessToken: {
          if (!v.evalLess(terms.get(i).eval(curScope), this).getBoolValue("compOpr",this)) {
            return new RuntimeBoolValue(false);
          }
          v = terms.get(i).eval(curScope);
          ret = new RuntimeBoolValue(true);
          break;

        }

        case lessEqualToken: {
          if (!v.evalLessEqual(terms.get(i).eval(curScope), this).getBoolValue("compOpr",this)) {
            return new RuntimeBoolValue(false);
          }
          v = terms.get(i).eval(curScope);
          ret = new RuntimeBoolValue(true);
          break;
        }

        case doubleEqualToken: {
          if (!v.evalEqual(terms.get(i).eval(curScope), this).getBoolValue("compOpr",this)) {
            return new RuntimeBoolValue(false);
          }
          v = terms.get(i).eval(curScope);
          ret = new RuntimeBoolValue(true);
          break;
        }

        case notEqualToken: {
          if (!v.evalNotEqual(terms.get(i).eval(curScope), this).getBoolValue("compOpr",this)) {
            return new RuntimeBoolValue(false);
          }
          v = terms.get(i).eval(curScope);
          ret = new RuntimeBoolValue(true);
          break;
        }
      }
    }
    return ret;
  }

}
