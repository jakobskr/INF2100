package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactorPrefix {
  public Token tok;

  String image;

  public AspFactorPrefix(Token tok) {
    this.tok = tok;
  }

  public String toString() {
     return "AspFactorPrefix: "+ tok;
  }
}
