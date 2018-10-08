package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspTermOpr {
  public Token tok;

  String image;

  public AspTermOpr(Token tok) {
    this.tok = tok;
  }

  public String toString() {
     return "AspTermOpr: "+ tok;
  }
}
