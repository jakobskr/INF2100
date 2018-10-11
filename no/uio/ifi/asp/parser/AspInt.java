package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspInt extends AspAtom {
  long value;

    public AspInt(int n) {
        super(n);
    }


    public static AspInt parse(Scanner s) {
        enterParser("Int");
    AspInt aint = new AspInt(s.curLineNum());

    Token temp = s.curToken();

    aint.value = temp.integerLit;
    skip(s, integerToken);

    leaveParser("Int");
    return aint;
    }

    public void prettyPrint() {
    	Main.log.prettyWrite(value);
  }

    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    return null;
  }


}
