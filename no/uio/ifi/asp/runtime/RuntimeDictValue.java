package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;
import java.util.HashMap;

/**
 * The RuntimeDictvalue
 * @author jakobskr
 * @author Sigurson
 * @version dato
 */
public class RuntimeDictValue extends RuntimeValue {
  HashMap<String, RuntimeValue> dict;


  public RuntimeDictValue(HashMap<String, RuntimeValue> v) {
    dict = v;
  }

  /**
   * gets the boolean value of the dict
   * @param  String    what          what called the method
   * @param  AspSyntax where         where the method was called
   * @return           [description]
   */
  public boolean getBoolValue(String what, AspSyntax where) {
    return dict.size() > 0;
  }

  /**
   * returns the dict as a string
   */
  public String toString() {
    String ret = "{";
    int c = 0;
    for (String key: dict.keySet()) {
      if (c > 0) {
        ret += ", ";
      }
      ret += "'" + key + "'" + ": " + dict.get(key).showInfo();
      c++;
    }
    return ret += "}";
  }

  /**
   * returns the typename of dict
   */
  public String typeName() {
    return "Dict";
  }

  /**
   * returns the value of not this
   * @param  AspSyntax where         where the method was called
   * @return           RuntimeBoolValue
   */
  public RuntimeValue evalNot(AspSyntax where) {
    return new RuntimeBoolValue(!(dict.size() > 0));
  }

  /**
   * returns the value of this[v]
   * @param  RuntimeValue v             value of index
   * @param  AspSyntax    where         where the method was called
   * @return              RuntimeValue
   */
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

  /**
   * adds a value to the dict
   * @param RuntimeValue inx   the key
   * @param RuntimeValue val   the Value
   * @param AspSyntax    where  where the method was called
   */
  public void evalAssignElem(RuntimeValue inx, RuntimeValue val, AspSyntax where) {
    if (inx instanceof RuntimeStringValue) {
      String index = inx.getStringValue("assignElem", where);
      if (dict.containsKey(index)) {
        dict.replace(index,val);
        return;
      }
      runtimeError("key not in the dictonaries",where);
      return;
    }
    runtimeError("Index must be an String", where);
    return;
  }

}
