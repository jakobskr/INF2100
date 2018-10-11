package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspString extends AspAtom {
  String value;

    public AspString(int n) {
        super(n);
    }

    public static AspString parse(Scanner s) {
        enterParser("String");
    AspString astr = new AspString(s.curLineNum());
    Token temp = s.curToken();
    astr.value = temp.stringLit;
    skip(s, stringToken);

    leaveParser("String");
    return astr;
    }

    public void prettyPrint() {
	    Main.log.prettyWrite(value);
  }

    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return null;
  }


}
