package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;
import java.util.HashMap;

public class RuntimeDictValue extends RuntimeValue {
  HashMap<String, RuntimeValue> dict;


  public RuntimeDictValue(HashMap<String, RuntimeValue> v) {
    dict = v;
  }

  public boolean getBoolValue(String what, AspSyntax where) {
    return dict.size() > 0;
  }

  public String typeName() {
    return "Dict";
  }

  public RuntimeValue evalNot(AspSyntax where) {
    return new RuntimeBoolValue(!(dict.size() > 0));
  }

  public RuntimeValue evalSubscription(RuntimeValue v, AspSyntax where) {
	  if (v instanceof RuntimeStringValue) {
      if (dict.containsKey(v.getStringValue("key", where))) {
        return dict.get(v.getStringValue("key", where));
      }
      else {
        runtimeError(String.format("No such key '%s'",v.getStringValue("key",where)), where);
        return null;
      }
    }
    runtimeError("Dict Key must be a text String.", where);
    return null;  // Required by the compiler
  }

}
