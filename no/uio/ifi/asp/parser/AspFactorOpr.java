package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactorOpr extends AspSyntax{
  public Token tok;

  String image;

  public AspFactorOpr(int n) {
    super(n);
  }

  public static AspFactorOpr parse(Scanner s) {
    enterParser("factor opr");
    AspFactorOpr afop = new AspFactorOpr(s.curLineNum());

    Token tok = s.curToken();
    if (tok.kind == slashToken) {
      afop.tok = tok;
      skip(s,tok.kind);
    }
    else if (tok.kind == doubleSlashToken) {
      afop.tok = tok;
      skip(s, tok.kind);
    }
    else if (tok.kind == astToken) {
      afop.tok = tok;
      skip(s, tok.kind);
    }
    else if (tok.kind == percentToken) {
      afop.tok = tok;
      skip(s, tok.kind);
    }

    else {
      parserError("unexpected token \"" + tok + "\"", s.curLineNum());
    }

    leaveParser("factor opr");
    return afop;
  }

  public String toString() {
     return "AspFactorOpr: "+ tok;
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
