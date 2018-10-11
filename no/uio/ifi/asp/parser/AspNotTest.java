package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspNotTest extends AspSyntax {

  AspComparison comp;
  Boolean notto = false;

  public AspNotTest(int n) {
    super(n);
  }

  public static AspNotTest parse(Scanner s) {
    enterParser("not test");

    AspNotTest ant = new AspNotTest(s.curLineNum());

    if (s.curToken().kind == notToken) {
      // ask for help on this!
      skip(s, notToken);
      ant.notto = true;
    }

    ant.comp = AspComparison.parse(s);

    leaveParser("not test");
    return ant;
  }

  @Override
  void prettyPrint() {
    if(! notto){
			Main.log.prettyWrite( "! ");
		}
		comp.prettyPrint();
  }

  // @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
}
