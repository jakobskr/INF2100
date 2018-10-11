package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspTermOpr extends AspSyntax{
  public Token tok;

  String image;

  public AspTermOpr(int n) {
    super(n);
  }

  public static AspTermOpr parse(Scanner s) {
    enterParser("term opr");
    AspTermOpr afop = new AspTermOpr(s.curLineNum());

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

    leaveParser("term opr");
    return afop;
  }


  @Override
  void prettyPrint() {
    Main.log.prettyWrite(tok.kind.toString());
  }

  // @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
	//oh hey mark
}
