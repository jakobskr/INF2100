package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


/**
 * Contains an 1-n primaries, 0-x factoropr and 0-y factor prefixess
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspFactor extends AspSyntax {
  ArrayList<AspPrimary> prims = new ArrayList<>();
	ArrayList<AspFactorPrefix> prefs = new ArrayList<>();
	ArrayList<AspFactorOpr> factop = new ArrayList<>();


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


  /**
   * checks if the given token is a factor prefix
   * @param  TokenKind tk
   * @return
   */
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


  /**
  * converts the syntax tree back to a readable asp program.
  */
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
		int ct = 0;
		RuntimeValue v = prims.get(0).eval(curScope);
		if(prefs.get(0) != null){
			if(prefs.get(0).tok.kind == minusToken){
				v = v.evalNegate(this);
			}
		}
		RuntimeValue val = new RuntimeIntValue(0);
    for (int x = 1; x < prims.size(); x++) {
				val = prims.get(x).eval(curScope);
			if(prefs.get(x).tok.kind == minusToken){
				val = val.evalNegate(this);
			}
			switch(factop.get(x).tok.kind){
				case doubleSlashToken :{
					v = v.evalIntDivide(val,this);
					break;
				}

				case astToken :{
					v = v.evalMultiply(val,this);
					break;
				}
				case slashToken :{
					v = v.evalDivide(val,this);
					break;
				}
				case percentToken :{
					v = v.evalModulo(val,this);
					break;
				}
			}
    }
    return v;
  }
}
