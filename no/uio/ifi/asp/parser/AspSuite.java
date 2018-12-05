package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

/**
 * contains 1-n statements
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
public class AspSuite extends AspSyntax{
	ArrayList<AspStmt> stms = new ArrayList<>();

	AspSuite(int n) {
    super(n);
  }

	static AspSuite parse(Scanner s) {
		enterParser("suite");

		AspSuite asut = new AspSuite(s.curLineNum());

		skip(s, newLineToken);
		skip(s, indentToken);

		while (s.curToken().kind != dedentToken){
			asut.stms.add(AspStmt.parse(s));
		}

		skip(s, dedentToken);
		leaveParser("suite");
		return asut;

	}

	// @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    for (AspStmt stmt : stms) {
    	stmt.eval(curScope);
    }
    return null;
    }

	/**
	* converts the syntax tree back to a readable asp program.
	*/
	public void prettyPrint() {
		Main.log.prettyWriteLn();
		Main.log.prettyIndent();
		for(AspStmt st: stms){
			st.prettyPrint();
		}
		Main.log.prettyDedent();
	}

}


//e-o-f
