package no.uio.ifi.asp.scanner;

import java.util.*;

import no.uio.ifi.asp.main.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

/**
 * The token that the asp scanner generates
 * Can be a varied amount of different kinds, see TokenKind.java
 * @author jakobskr
 * @author Sigurson
 * @version date
 */
public class Token {
  public TokenKind kind;
  public String name, stringLit;
  public long integerLit;
  public double floatLit;
  public int lineNum;


  Token(TokenKind k) {
    this(k, 0);
  }


  Token(TokenKind k, int lNum) {
    kind = k;  lineNum = lNum;
  }


  /**
   * dont know what this does, it might come back in part 2,3 or 4
   */
  void checkResWords() {
    if (kind != nameToken) return;

    for (TokenKind tk: EnumSet.range(andToken,yieldToken)) {
      if (name.equals(tk.image)) {
        kind = tk;  break;
      }
    }
  }

  /**
   * returns the value of the token in string  form
   * @return the return string is in the form of ("%s token on line %d", kind, lineNum)
   */
  public String showInfo() {
    String t = kind + " token";
    if (lineNum > 0) {
      t += " on line " + lineNum;
    }

    switch (kind) {
      case floatToken: t += ": " + floatLit;  break;
      case integerToken: t += ": " + integerLit;  break;
      case nameToken: t += ": " + name;  break;
      case stringToken:
      if (stringLit.indexOf('"') >= 0)
      t += ": '" + stringLit + "'";
      else
      t += ": " + '"' + stringLit + '"';
      break;
    }
    return t;
  }

  /**
   * returns the tokenkind of the token
   * @return the tokenkind of the token
   */
  public String toString() {
    return kind.toString();
  }
}
