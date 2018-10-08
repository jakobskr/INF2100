package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspSuite extends AspSyntax{
	ArrayList<AspStmt> stms = new ArrayList<>();

	AspSuite(int n) {
    super(n);
  }

	static AspSuite parse(Scanner s) {
		enterParser("suite");

		AspSuite asut = new AspSuite(s.curLineNum());

		skip(s, newLineToken);
		skip(s, indentToken);

		while ( s.curToken().kind != dedentToken){
			asut.stms.add(AspStmt.parse(s));
		}

		skip(s, dedentToken);
		leaveParser("suite");
		return asut;

	}

	// @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }

	public void prettyPrint() {
		//
	}

}


//e-o-f
