package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspProgram extends AspSyntax {
  //-- Must be changed in part 2:
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
      System.out.println(s.curToken());
      // s.readNextToken();
    }

    leaveParser("program");
    return ap;
  }


  @Override
	void prettyPrint() {
		atom.prettyPrint();
    for (AspStmt ant: stmts) {
      ant.prettyPrint();
    }
	}

  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 4:
    return null;
  }
}
