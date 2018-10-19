package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

/**
 * the compOpr literal
 */
public class AspCompOpr extends AspSyntax{
  public Token tok;

  String image;

  public AspCompOpr(int n) {
    super(n);
  }

  /**
   * parses the compOpr
   * @param  Scanner s             scanner s
   * @return         [description]
   */
  public static AspCompOpr parse(Scanner s) {
    enterParser("comp opr");
    AspCompOpr afop = new AspCompOpr(s.curLineNum());

    Token tok = s.curToken();

    if (tok.kind == notEqualToken) {
      afop.tok = tok;
      skip(s,tok.kind);
    }

    else if (tok.kind == greaterToken) {
      afop.tok = tok;
      skip(s, tok.kind);
    }
    else if (tok.kind == lessToken) {
      afop.tok = tok;
      skip(s, tok.kind);
    }
    else if (tok.kind == doubleEqualToken) {
      afop.tok = tok;
      skip(s, tok.kind);
    }

    else if (tok.kind == lessEqualToken) {
      afop.tok = tok;
      skip(s, tok.kind);
    }

    else if (tok.kind == greaterEqualToken) {
      afop.tok = tok;
      skip(s, tok.kind);
    }

    else {
      parserError("unexpected token \"" + tok + "\"", s.curLineNum());
    }

    leaveParser("comp opr");
    return afop;
  }

  public String toString() {
     return "AspCompOpr: "+ tok;
  }


  /**
   * converts the syntax tree back to a readable asp program.
   */
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
