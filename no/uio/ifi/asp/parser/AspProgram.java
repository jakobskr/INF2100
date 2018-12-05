package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


  /**
   * contains 0-n statements
   * @author jakobskr
   * @author Sigurson
   * @version dato
   */
public class AspProgram extends AspSyntax {
  ArrayList<AspStmt> stmts = new ArrayList<>();

  AspProgram(int n) {
    super(n);
  }

  public static AspProgram parse(Scanner s) {
    enterParser("program");

    AspProgram ap = new AspProgram(s.curLineNum());
    while (s.curToken().kind != eofToken) {
      //-- Must be changed in part 2:
      ap.stmts.add(AspStmt.parse(s));
      //AspName.parse(s);
      // s.readNextToken();
    }

    leaveParser("program");
    return ap;
  }


  /**
  * converts the syntax tree back to a readable asp program.
  */
  @Override
	public void prettyPrint() {
    for (AspStmt ant: stmts) {
      ant.prettyPrint();
    }
	}

  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 4:
    for (AspStmt stmt: stmts) {
      try {
        stmt.eval(curScope);
      } catch (RuntimeReturnValue rrv) {
        RuntimeValue.runtimeError("Return statement outside function!",
                                  rrv.lineNum);
        }
    }
    return null;
  }
}
