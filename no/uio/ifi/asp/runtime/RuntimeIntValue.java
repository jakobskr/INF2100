package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeIntValue extends RuntimeValue {
  long intValue;


  public RuntimeIntValue(long v) {
    intValue = v;
  }

  @Override
  public String toString() {
    return Long.toString(intValue);
  }

  @Override
  protected String typeName() {
    return "integer";
  }

  @Override
  public long getIntValue(String what, AspSyntax where) {
    return intValue;
  }

  @Override
  public boolean getBoolValue(String what, AspSyntax where) {
    return 0 != intValue;
  }

  @Override
  public RuntimeValue evalEqual(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeNoneValue) {
      return new RuntimeBoolValue(false);
    }

    else if (v instanceof RuntimeIntValue) {
      return new RuntimeBoolValue(this.intValue == v.getIntValue("== operand", where));
    }

    else if (v instanceof RuntimeFloatValue) {
      return new RuntimeBoolValue(this.intValue == v.getFloatValue("== operand", where));
    }

    runtimeError("Type error for ==", where);
    return null;  // Required by the compiler
  }

  public RuntimeValue evalAdd(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeFloatValue) {
      return new RuntimeFloatValue(v.getFloatValue("+ Operand", where) + intValue);
    }

    else if (v instanceof RuntimeIntValue) {
      return new RuntimeIntValue(intValue + v.getIntValue("+ operand", where));
    }

    runtimeError("'+' undefined for "+ v.typeName()+"!", where);
	  return null;  // Required by the compiler!
  }

  public RuntimeValue evalDivide(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeFloatValue) {
      return new RuntimeFloatValue(intValue / v.getFloatValue("/ operand", where) );
    }

    else if (v instanceof RuntimeIntValue) {
      return new RuntimeIntValue(intValue / v.getIntValue("/ operand", where));
    }

    runtimeError("'/' undefined for "+typeName()+"!", where);
    return null;  // Required by the compiler!
  }

  @Override
  public RuntimeValue evalGreater(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeIntValue) {
      return new RuntimeBoolValue(this.intValue > v.getIntValue("< operand", where));
    }

    else if (v instanceof RuntimeFloatValue) {
      return new RuntimeBoolValue(this.intValue > v.getFloatValue("< operand", where));
    }

    runtimeError("Type error for ==", where);
    return null;  // Required by the compiler
  }

  @Override
  public RuntimeValue evalGreaterEqual(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeIntValue) {
      return new RuntimeBoolValue(this.intValue >= v.getIntValue("<= operand", where));
    }

    else if (v instanceof RuntimeFloatValue) {
      return new RuntimeBoolValue(this.intValue >= v.getFloatValue("<= operand", where));
    }

    runtimeError("Type error for ==", where);
    return null;  // Required by the compiler
  }

  public RuntimeValue evalIntDivide(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeFloatValue) {
      return new RuntimeFloatValue(Math.floor(intValue / v.getFloatValue("// operand", where) ));
    }

    else if (v instanceof RuntimeIntValue) {
      return new RuntimeIntValue((long) Math.floor(intValue / v.getIntValue("// operand", where)));
    }

    runtimeError("'/' undefined for "+typeName()+"!", where);
    return null;  // Required by the compiler!
  }

  @Override
  public RuntimeValue evalLess(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeIntValue) {
      return new RuntimeBoolValue(this.intValue < v.getIntValue("< Operand", where));
    }

    else if (v instanceof RuntimeFloatValue) {
      return new RuntimeBoolValue(this.intValue < v.getFloatValue("< operand", where));
    }

    runtimeError("Type error for ==", where);
    return null;  // Required by the compiler
  }

  @Override
  public RuntimeValue evalLessEqual(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeIntValue) {
      return new RuntimeBoolValue(this.intValue <= v.getIntValue("<= operand", where));
    }

    else if (v instanceof RuntimeFloatValue) {
      return new RuntimeBoolValue(this.intValue <= v.getFloatValue("operand", where));
    }

    runtimeError("Type error for ==", where);
    return null;  // Required by the compiler
  }

  public RuntimeValue evalModulo(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeFloatValue) {
      return new RuntimeFloatValue(intValue - v.getFloatValue("% operand",where) * Math.floor(intValue / v.getFloatValue("float", where) ));
    }

    else if (v instanceof RuntimeIntValue) {
      return new RuntimeIntValue(Math.floorMod(intValue, v.getIntValue("% operand", where)));
    }

    runtimeError("'/' undefined for "+typeName()+"!", where);
    return null;  // Required by the compiler!
  }

  public RuntimeValue evalMultiply(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeFloatValue) {
      return new RuntimeFloatValue(v.getFloatValue("* operand", where) * intValue);
    }

    else if (v instanceof RuntimeIntValue) {
      return new RuntimeIntValue(intValue * v.getIntValue("* operand", where));
    }

    runtimeError("'+' undefined for "+ v.typeName()+"!", where);
	  return null;  // Required by the compiler!
  }



  public RuntimeValue evalNegate(AspSyntax where) {
    return new RuntimeIntValue(intValue * -1);
  }

  @Override
  public RuntimeValue evalNotEqual(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeNoneValue) {
      return new RuntimeBoolValue(true);
    }

    else if (v instanceof RuntimeIntValue) {
      return new RuntimeBoolValue(this.intValue != v.getIntValue("!= operand", where));
    }

    else if (v instanceof RuntimeFloatValue) {
      return new RuntimeBoolValue(this.intValue != v.getFloatValue("!= operand", where));
    }

    runtimeError("Type error for !=", where);
    return null;  // Required by the compiler
  }

  public RuntimeValue evalNot(AspSyntax where) {
	  return new RuntimeBoolValue(0 == intValue);
  }

  public RuntimeValue evalPositive(AspSyntax where) {
    return new RuntimeIntValue(intValue);
  }

  public RuntimeValue evalSubtract(RuntimeValue v, AspSyntax where) {
    if (v instanceof RuntimeFloatValue) {
      return new RuntimeFloatValue(intValue - v.getFloatValue("- operand", where));
    }

    else if (v instanceof RuntimeIntValue) {
      return new RuntimeIntValue(intValue - v.getIntValue("- operand", where));
    }

    runtimeError("'+' undefined for "+ v.typeName()+"!", where);
    return null;  // Required by the compiler!
  }


}// lukker klassen
