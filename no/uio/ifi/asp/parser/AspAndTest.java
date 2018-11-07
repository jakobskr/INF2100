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

  /**
   * AndTest evaluates all stored not tests to check for an and test
   * @param  curScope           current scope
   * @return                    returns RuntimeBoolValue or RuntimeValue
   * @throws RuntimeReturnValue [description]
   */
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
    RuntimeValue v = notTests.get(0).eval(curScope);
    for (int i = 1; i < notTests.size() ; i++) {
      if (! v.getBoolValue("and operand", this)) {
        return v;
      }
      v = notTests.get(i).eval(curScope);
    }
    return v;
  }

}
