package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

/**
 * contains 1 atom, then 0-n primary suffix
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspPrimary extends AspSyntax {
	ArrayList<AspPrimarySuffix> prisuf = new ArrayList<>();
  AspAtom atom;
  AspPrimary(int n) {
    super(n);
  }

  static AspPrimary parse(Scanner s) {
    enterParser("primary");

    AspPrimary aprm = new AspPrimary(s.curLineNum());
    aprm.atom = AspAtom.parse(s);
		while (true) {

			Token tok = s.curToken();
      if (subOrArgs(tok.kind)) aprm.prisuf.add(AspPrimarySuffix.parse(s));
			else {break;}
    }

    leaveParser("primary");
    return aprm;
  }

  // @Override

	/**
	* converts the syntax tree back to a readable asp program.
	*/
  void prettyPrint() {
		atom.prettyPrint();
    for (AspPrimarySuffix ant: prisuf) {
      ant.prettyPrint();
    }
	}

	/**
	 * checs if we are reading a subscription or arguments
	 * @param  TokenKind tk            [description]
	 * @return           [description]
	 */
	public static boolean subOrArgs(TokenKind tk) {
		return tk == leftParToken || tk == leftBracketToken;
	}

	/**
	 * primary evaluates to the eval of the atom inside, except if it contains a subscription.
	 * if it does it returns the evalSubscription instead. if there are multiple subscriptions this is repeated.
	 * @param  curScope            current scope
	 * @return                    RuntimeValue
	 * @throws RuntimeReturnValue -
	 */
	public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
		//-- Must be changed in part 3:

		RuntimeValue v = atom.eval(curScope);
		for(AspPrimarySuffix prim: prisuf){
			if(prim instanceof AspSubscription){
				v = v.evalSubscription(prim.eval(curScope),this);
			}
			if (prim instanceof AspArguments) {
				StringBuilder traceBld = new StringBuilder("Call function ");
				traceBld.append( v.showInfo()); traceBld.append(" with params [");
				AspArguments args = (AspArguments) prim;
				ArrayList<RuntimeValue> vals = new ArrayList<RuntimeValue>();
				int len = args.exps.size();
				int i = 1;
				for (AspExpr exp : args.exps) {
					RuntimeValue temp_val = exp.eval(curScope);
					vals.add(temp_val);
					if (i != len) {
						traceBld.append(temp_val.showInfo()); traceBld.append(", ");
					}
					else {
						traceBld.append(temp_val.showInfo());
					}
					i++;
				}
				traceBld.append("]");
				trace(traceBld.toString());
				v = v.evalFuncCall(vals, this);
			}
		}

		return v;
	}

}
