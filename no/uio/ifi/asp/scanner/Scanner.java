package no.uio.ifi.asp.scanner;

import java.io.*;
import java.util.*;

import no.uio.ifi.asp.main.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class Scanner {
  private LineNumberReader sourceFile = null;
  private String curFileName;
  private ArrayList<Token> curLineTokens = new ArrayList<>();
  private int indents[] = new int[100];
  private int numIndents = 0;
  private final int tabDist = 4;


  public Scanner(String fileName) {
    curFileName = fileName;
    indents[0] = 0;  numIndents = 1;

    try {
      sourceFile = new LineNumberReader(
      new InputStreamReader(
      new FileInputStream(fileName),
      "UTF-8"));
    } catch (IOException e) {
      scannerError("Cannot read " + fileName + "!");
    }
  }


  private void scannerError(String message) {
    String m = "Asp scanner error";
    if (curLineNum() > 0)
    m += " on line " + curLineNum();
    m += ": " + message;

    Main.error(m);
  }


  public Token curToken() {
    while (curLineTokens.isEmpty()) {
      readNextLine();
    }
    return curLineTokens.get(0);
  }


  public void readNextToken() {
    if (! curLineTokens.isEmpty())
    curLineTokens.remove(0);
  }


  public boolean anyEqualToken() {
    for (Token t: curLineTokens) {
      if (t.kind == equalToken) return true;
    }
    return false;
  }


  private void readNextLine() {
    curLineTokens.clear();

    // Read the next line:
    String line = null;
    try {
      line = sourceFile.readLine();
      System.out.println(curLineNum());
      if (line == null) {
        sourceFile.close();
        curLineTokens.add(new Token(eofToken, curLineNum()));
        sourceFile = null;
        return;
      } else {
        Main.log.noteSourceLine(curLineNum(), line);
      }
    } catch (IOException e) {
      sourceFile = null;
      scannerError("Unspecified I/O error!");
    }

    //-- Must be changed in part 1:
    genTokens(line, curLineNum());
    // Terminate line:
    curLineTokens.add(new Token(newLineToken,curLineNum()));

    for (Token t: curLineTokens)
    Main.log.noteToken(t);
  }

  public int curLineNum() {
    return sourceFile!=null ? sourceFile.getLineNumber() : 0;
  }

  private int findIndent(String s) {
    int indent = 0;

    while (indent<s.length() && s.charAt(indent)==' ') indent++;
    return indent;
  }

  private String expandLeadingTabs(String s) {
    String newS = "";
    for (int i = 0;  i < s.length();  i++) {
      char c = s.charAt(i);
      if (c == '\t') {
        do {
          newS += " ";
        } while (newS.length()%tabDist != 0);
      } else if (c == ' ') {
        newS += " ";
      } else {
        newS += s.substring(i);
        break;
      }
    }
    return newS;
  }

  private void genTokens(String line, int linNum) {
    char[] chars = line.toCharArray();
    String test = "";
    int i = 0;
    if (chars[0] == '#') {
      return;
    }
    System.out.println(line);
    while (i < chars.length) {
      System.out.println(i + " "  + chars.length + chars[i]);


      if (isLetterAZ(chars[i])) {
        while(i < chars.length && isLetterAZ(chars[i]) ) {
          test = test + chars[i];
          i++;
        }
        Token test2 = new Token(nameToken, linNum);
        test2.name = test;
        curLineTokens.add(test2);
        test = "";
        continue;
      }

      else if (chars[i] == '=') {
        if (chars[i + 1] == '=') {
          //ADD case here
        }

        else {
          System.out.println("skjer");
          curLineTokens.add(new Token (equalToken,linNum));
          i++;
          continue;
        }
      }

      else if(chars[i] == '\'') {
        String str = "";
        i++;
        while(chars[i] != '\'') {
          System.out.println(chars[i]);
          str = str + chars[i];
          i++;
        }
        Token tok = new Token (stringToken, linNum);
        tok.stringLit = str;
        curLineTokens.add(tok);
        i++;
        continue;
      }

      else if(chars[i] == '\"') {
        System.out.println("fuck u xd");
        String str = "";
        i++;
        while(chars[i] != '\"') {
          System.out.println(chars[i]);
          str = str + chars[i];
          i++;
        }

        Token tok = new Token (stringToken, linNum);
        tok.stringLit = str;
        curLineTokens.add(tok);
        i++;
        continue;
      }

      else if (chars[i] == ',') {
         curLineTokens.add(new Token(commaToken, linNum));
         i++;
         continue;
      }

      else if (chars[i] == '(') {
         curLineTokens.add(new Token(leftParToken, linNum));
         i++;
         continue;
      }

      else if (chars[i] == ')') {
         curLineTokens.add(new Token(rightParToken, linNum));
         i++;
         continue;
      }

      else if (chars[i] == '[') {
         curLineTokens.add(new Token(rightBracketToken, linNum));
         i++;
         continue;
      }

      else if (chars[i] == ']') {
         curLineTokens.add(new Token(rightBracketToken, linNum));
         i++;
         continue;
      }

      else {
        i++;
        continue;
      }
    }
  }

  private boolean isLetterAZ(char c) {
    return ('A'<=c && c<='Z') || ('a'<=c && c<='z') || (c=='_');
  }


  private boolean isDigit(char c) {
    return '0'<=c && c<='9';
  }


  public boolean isCompOpr() {
    TokenKind k = curToken().kind;
    //-- Must be changed in part 2:
    return false;
  }


  public boolean isFactorPrefix() {
    TokenKind k = curToken().kind;
    //-- Must be changed in part 2:
    return false;
  }


  public boolean isFactorOpr() {
    TokenKind k = curToken().kind;
    //-- Must be changed in part 2:
    return false;
  }


  public boolean isTermOpr() {
    TokenKind k = curToken().kind;
    //-- Must be changed in part 2:
    return false;
  }
}
