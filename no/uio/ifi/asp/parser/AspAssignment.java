package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


/**
 * Asp assignment stores a name, 0-n subscriptions, an equal sign, an expression and then a newline for later evaluation
 *
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspAssignment extends AspStmt {
	ArrayList<AspSubscription> subs = new ArrayList<>();
	AspName name;
	AspExpr exp;

	AspAssignment(int n) {
    super(n);
  }

	/**
	 * parses AspAssignment
	 * @param  Scanner s             AspScanner
	 * @return itself
	 */
	static AspAssignment parse(Scanner s) {
		enterParser("assignment");
		AspAssignment aass = new AspAssignment(s.curLineNum());
		aass.name = AspName.parse(s);

		while ( s.curToken().kind != equalToken){
			aass.subs.add(AspSubscription.parse(s));
		}

		skip(s, equalToken);

		aass.exp = AspExpr.parse(s);

		skip(s, newLineToken);
		leaveParser("assignment");
		return aass;
	}

	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {

		if (subs.size() >= 1) {
			//if true this is a complex assignment!!
			String traceStr = name.name;
			RuntimeValue val = curScope.find(name.name, this);
			traceStr = traceStr;
			int i = 0;
			for (i = 0; i < subs.size() - 1; i++ ) {

				val = val.evalSubscription(subs.get(i).eval(curScope), this);
				traceStr = traceStr + "[" + val.toString() + "]"; 
			}
			traceStr = traceStr + "[" + subs.get(i).eval(curScope).toString() + "]";
			val.evalAssignElem(subs.get(i).eval(curScope), exp.eval(curScope), this);
			traceStr = traceStr + " = " + exp.eval(curScope).toString();
			trace(traceStr); 
			return null;
		}

		else {
			trace(name.name + " = " + exp.eval(curScope).toString());
			curScope.assign(name.name, exp.eval(curScope));
			return null;
		}
		
	}

	/**
   * converts the syntax tree back to a readable asp program.
   */
	@Override
  void prettyPrint() {

		name.prettyPrint();
    for (AspSubscription ant: subs) {
      ant.prettyPrint();
    }
		Main.log.prettyWrite(" = ");
		exp.prettyPrint();
		Main.log.prettyWriteLn();
  }
}
