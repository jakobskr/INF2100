package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


/**
 * the factoropr
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
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

  /**
  * converts the syntax tree back to a readable asp program.
  */
  @Override
  void prettyPrint() {
    int nPrinted = 0;
    Main.log.prettyWrite(tok.kind.toString());
  }

  /**
   * is implemented in an outside in aproach, like an ogre, but you dont peel it and just look at it from a distance.
   * you just look at the factor operator and go "yup, that's an operator" instead of actually asking it.
   * @param  curScope           current scope
   * @return                    -
   * @throws RuntimeReturnValue -
   */
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }
	//oh hey mark
}
