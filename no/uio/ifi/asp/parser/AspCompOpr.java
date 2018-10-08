package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspCompOpr {
  public Token tok;
  public String image;

  AspCompOpr(Token tok) {
    this.tok = tok;
  }

  public Token getToken() {
    return tok;
  }
}
