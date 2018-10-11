package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFloat extends AspAtom {
  double value;

    public AspFloat(int n) {
        super(n);
    }


    public static AspFloat parse(Scanner s) {
        enterParser("Float");
    System.out.println("Floating is good");
    AspFloat aflt = new AspFloat(s.curLineNum());

    Token temp = s.curToken();

    aflt.value = temp.floatLit;
    skip(s, floatToken);
    leaveParser("Float");
    return aflt;
    }

    public void prettyPrint() {
	    Main.log.prettyWrite(Double.toString(value));
  }

    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return null;
  }


}
