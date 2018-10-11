package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactorPrefix extends AspSyntax{
  public Token tok;

  String image;

  public AspFactorPrefix(int n) {
    super(n);
  }

  public static AspFactorPrefix parse(Scanner s) {
    enterParser("factor prefix");
    AspFactorPrefix afop = new AspFactorPrefix(s.curLineNum());

    Token tok = s.curToken();
    if (tok.kind == minusToken) {
      afop.tok = tok;
      skip(s,tok.kind);
    }
    else if (tok.kind == plusToken) {
      afop.tok = tok;
      skip(s, tok.kind);
    }

    else {
      parserError("unexpected token \"" + tok + "\"", s.curLineNum());
    }

    leaveParser("factor prefix");
    return afop;
  }

  public String toString() {
     return "AspFactorPrefix: "+ tok;
  }


  @Override
  void prettyPrint() {
    int nPrinted = 0;
    Main.log.prettyWrite(tok.kind.toString());
  }

  // @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
	//oh hey mark
}
