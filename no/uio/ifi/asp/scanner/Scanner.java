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
  private LinkedList<Integer> stack = new LinkedList<Integer>();

  public Scanner(String fileName) {
    curFileName = fileName;
    indents[0] = 0;  numIndents = 1;
    stack.addLast(0);

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
      //System.out.println(curLineNum());
      if (line == null) {
        sourceFile.close();

        for (int i = 0; i < stack.size() - 1; i++ ) {
          curLineTokens.add(new Token(dedentToken));
        }

        curLineTokens.add(new Token(eofToken));
        for (Token t: curLineTokens)
        Main.log.noteToken(t);
        sourceFile = null;
        return;
      } else {
        Main.log.noteSourceLine(curLineNum(), line);
      }
    } catch (IOException e) {
      sourceFile = null;
      scannerError("Unspecified I/O error!");
    }

    if (line.length() == 0) {
      return;
    }

    //-- Must be changed in part 1:
    line = expandLeadingTabs(line);
    //System.out.println("indent : " + indent);

    for (char c: line.toCharArray()) {
      if (c == ' ') {
        continue;
      }

      else if(c == '#') {
        return;
      }

      else break;
    }


    indentDo(line, curLineNum());
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

  private void genTokens(String line, int  lineNum) {
    char[] chars = line.toCharArray();
    int i = 0;
    if (chars[0] == '#') {
      return;
    }
    System.out.println(line);
    while (i < chars.length) {
      Token tok = null;
      //System.out.println(i + " "  + chars.length + chars[i]);


      if (isLetterAZ(chars[i])) {
        String str = "";
        while(i < chars.length && isLetterAZ(chars[i]) ) {
          str = str + chars[i];
          i++;
        }
        tok = getKeyword(str,  lineNum);
        curLineTokens.add(tok);
        continue;
      }

      else if(chars[i] == '\'') {
        String str = "";
        i++;
        while(chars[i] != '\'') {
          str = str + chars[i];
          i++;

          if(i == chars.length ) {
            scannerError("Unexpected end of line, expected a \'");
            break;
          }
        }
        tok = new Token (stringToken,  lineNum);
        tok.stringLit = str;
        curLineTokens.add(tok);
        i++;
        continue;
      }

      else if(chars[i] == '\"') {
        String str = "";
        i++;
        while(chars[i] != '\"') {
          str = str + chars[i];
          i++;

          if(i == chars.length ) {
            scannerError("Unexpected end of line, expected a \"");
            break;
          }
        }

        tok = new Token (stringToken,  lineNum);
        tok.stringLit = str;
        curLineTokens.add(tok);
        i++;
        continue;
      }

      else if (isDigit(chars[i])) {
        String str = "";
        Boolean isFloat = false;

        while(chars.length > i && isDigit(chars[i])) {
          str = str + chars[i];
          i++;
        }

        if (i < chars.length && chars[i] == '.') {
          str = str + chars[i];
          isFloat = true;
          i++;

          while(chars.length > i && isDigit(chars[i])) {
            str = str + chars[i];
            i++;
          }
        }

        if (isFloat) {
          tok = new Token (floatToken,  lineNum);
          tok.floatLit = Double.parseDouble(str);
        }

        else {
          tok = new Token (integerToken,  lineNum);
          tok.integerLit = Integer.parseInt(str);
        }
        curLineTokens.add(tok);
        continue;
      }

      else if (chars[i] == ',') {
         curLineTokens.add(new Token(commaToken,  lineNum));
         i++;
         continue;
      }

      else if (chars[i] == '(') {
         curLineTokens.add(new Token(leftParToken,  lineNum));
         i++;
         continue;
      }

      else if (chars[i] == ')') {
         curLineTokens.add(new Token(rightParToken,  lineNum));
         i++;
         continue;
      }

      else if (chars[i] == '[') {
         curLineTokens.add(new Token(leftBracketToken,  lineNum));
         i++;
         continue;
      }

      else if (chars[i] == ']') {
         curLineTokens.add(new Token(rightBracketToken,  lineNum));
         i++;
         continue;
      }

      else if (chars[i] == '{') {
         curLineTokens.add(new Token(leftBraceToken,  lineNum));
         i++;
         continue;
      }

      else if (chars[i] == '}') {
         curLineTokens.add(new Token(rightBraceToken,  lineNum));
         i++;
         continue;
      }

      else if (chars[i] == '=') {
      	if (chars[i+1] == '='){
      		curLineTokens.add(new Token(doubleEqualToken,  lineNum));
      		i++;
      	}
      	else{
      		curLineTokens.add(new Token(equalToken,  lineNum));
      	}
      	i++;
      	continue;
      }

      else if (chars[i] == '<') {
      	if (chars[i+1] == '='){
      		curLineTokens.add(new Token(lessEqualToken,  lineNum));
      		i++;
      	}
      	else{
      		curLineTokens.add(new Token(lessToken,  lineNum));
      	}
      	i++;
      	continue;
      }

      else if (chars[i] == '>') {
      	if (chars[i+1] == '='){
      		curLineTokens.add(new Token(greaterEqualToken,  lineNum));
      		i++;
      	}
      	else{
      		curLineTokens.add(new Token(greaterToken,  lineNum));
      	}
      	i++;
      	continue;
      }

      else if (chars[i] == '!') {
      	if (chars[i+1] == '='){
      		curLineTokens.add(new Token(notEqualToken,  lineNum));
      		i++;
      	}
      	else{
      		curLineTokens.add(new Token(notToken,  lineNum));
      	}
      	i++;
      	continue;
      }

      else if (chars[i] == '/') {
      	if (chars[i+1] == '/'){
      		curLineTokens.add(new Token(slashToken,  lineNum));
      		i++;
      	}
      	else{
      		curLineTokens.add(new Token(doubleSlashToken,  lineNum));
      	}
      	i++;
      	continue;
      }

      else if (chars[i] == '*') {
      	curLineTokens.add(new Token(astToken,  lineNum));
      	i++;
      	continue;
      }

      else if (chars[i] == '-') {
      	curLineTokens.add(new Token(minusToken,  lineNum));
      	i++;
      	continue;
      }

      else if (chars[i] == '%') {
      	curLineTokens.add(new Token(percentToken,  lineNum));
      	i++;
      	continue;
      }

      else if (chars[i] == '+') {
      	curLineTokens.add(new Token(plusToken,  lineNum));
      	i++;
      	continue;
      }

      else if (chars[i] == ':') {
      	curLineTokens.add(new Token(colonToken,  lineNum));
      	i++;
      	continue;
      }

      else if (chars[i] == ',') {
      	curLineTokens.add(new Token(commaToken,  lineNum));
      	i++;
      	continue;
      }

      else if (chars[i] == ' ') {
        i++;
        continue;
      }

      else {
        scannerError("Illegal");
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

  private Token getKeyword(String word, int  lineNum){
    Token tok = null;

    if( word.equals("and") ){
  		tok = new Token(andToken,  lineNum);
  	}
  	else if( word.equals("def") ){
  		tok = new Token(defToken,  lineNum);
  	}
  	else if( word.equals("elif") ){
  		tok = new Token(elifToken,  lineNum);
  	}
  	else if( word.equals("else") ){
  		tok = new Token(elseToken,  lineNum);
  	}
  	else if( word.equals("False") ){
  		tok = new Token(falseToken,  lineNum);
  	}
  	else if( word.equals("for") ){
  		tok = new Token(forToken,  lineNum);
  	}
  	else if( word.equals("if") ){
  		tok = new Token(ifToken,  lineNum);
  	}
  	else if( word.equals("in") ){
  		tok = new Token(inToken,  lineNum);
  	}
  	else if( word.equals("None") ){
  		tok = new Token(noneToken,  lineNum);
  	}
  	else if( word.equals("not") ){
  		tok = new Token(notToken,  lineNum);
  	}
  	else if( word.equals("or") ){
  		tok = new Token(orToken,  lineNum);
  	}
  	else if( word.equals("pass") ){
  		tok = new Token(passToken,  lineNum);
  	}
  	else if( word.equals("return") ){
  		tok = new Token(returnToken,  lineNum);
  	}
  	else if( word.equals("true") ){
  		tok = new Token(trueToken,  lineNum);
  	}
  	else if( word.equals("while") ){
  		tok = new Token(whileToken,  lineNum);
  	}
  	else{
  		tok = new Token(nameToken,  lineNum);
  		tok.name=word;
  	}
  	return tok;
  }

  private void indentDo(String instr, int  lineNum){
  	int dent;
  	int dedentct = 0;
  	// if is isEmpty ignore

  	dent = findIndent(instr);
  	if (dent > stack.getLast()) {
  		curLineTokens.add( new Token( indentToken,  lineNum));
      stack.addLast(dent);
  	}
  	else{
  		while (dent < stack.getLast() ) {
  			dedentct++;
  			stack.removeLast();
  		}
  		if (dent == stack.getLast() ) {
  			for(int i = 0; i < dedentct; i++ ) {
  				curLineTokens.add ( new Token( dedentToken,  lineNum));
  			}
  		}
  		else{
  			scannerError ( "indent depth does not match any start of block" );
  			return;
  		}
  	}
  }
}
