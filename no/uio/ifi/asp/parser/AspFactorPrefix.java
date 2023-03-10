package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

/**
 * the factoropr prefix literal
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
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

  /**
  * converts the syntax tree back to a readable asp program.
  */
  @Override
  void prettyPrint() {
    int nPrinted = 0;
    Main.log.prettyWrite(tok.kind.toString());
  }

  /**
   * see AspFactorOpr.eval for a proper description of why this has no proper return value
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
