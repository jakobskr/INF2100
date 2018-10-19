package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


/**
 * Contains 1-n AspNotTests.
 *
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
class AspAndTest extends AspSyntax {
  ArrayList<AspNotTest> notTests = new ArrayList<>();

  AspAndTest(int n) {
    super(n);
  }

  /**
   * Parses the AspAndTest
   * @param  Scanner s             the scanner
   * @return returns the parsed AspAndTest
   */
  static AspAndTest parse(Scanner s) {
    enterParser("and test");

    AspAndTest aat = new AspAndTest(s.curLineNum());
    while (true) {
      aat.notTests.add(AspNotTest.parse(s));
      if (s.curToken().kind != andToken) break;
      skip(s, andToken);
    }

    leaveParser("and test");
    return aat;
  }

  /**
   * converts the syntax tree back to a readable asp program.
   */
  @Override
  void prettyPrint() {
    int nPrinted = 0;

    for (AspNotTest ant: notTests) {
      if (nPrinted > 0)
      Main.log.prettyWrite(" and ");
      ant.prettyPrint(); ++nPrinted;
    }
  }

  // @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    //-- Must be changed in part 3:
    return null;
  }

}
