package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspName extends AspAtom {
  String name;

	public AspName(int n) {
		super(n);
	}


	public static AspName parse(Scanner s) {
		enterParser("Name");
    AspName anam = new AspName(s.curLineNum());

    Token temp = s.curToken();

    anam.name = temp.name;
    skip(s, nameToken);
    leaveParser("Name");
    return anam;
	}

	public void prettyPrint() {
    int n = 0;
  }

	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return null;
  }


}
