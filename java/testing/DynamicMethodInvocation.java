
/* Written by Hyunyoung Lee for CSCE 314 Students 
   Example for Dynamic method invocation.
   To compile: javac DynamicMethodInvocation.java
   To run: java DynamicMethodInvocation DynamicMethodInvocation
*/

import java.lang.reflect.*;
import java.lang.Class;
//import java.lang.ClassNotFoundException;
//import java.lang.InstantiationException;
import static java.lang.System.out;
import static java.lang.System.err;

public class DynamicMethodInvocation {
  public void work(int i, String s) {
     out.printf("Method work has been invoked, Called: i=%d, s=%s%n\n", i, s);
  }

  public static void main(String[] args) {
    //java args start after the class name that you want to run
    // DynamicMethodInvocation x = new DynamicMethodInvocation();
    // Class<?> clX = x.getClass();
    Class<?> clX = null;
    Object x = null;

    try {
    clX = Class.forName(args[0]); 
    } catch (ClassNotFoundException e) { err.println(e); }
    try {
    x = clX.getDeclaredConstructor().newInstance();
    } catch (NoSuchMethodException e)  { err.println(e); }
      catch (InvocationTargetException e) { err.println(e); }
      catch (InstantiationException e) { err.println(e); }
      catch (IllegalAccessException e) { err.println(e); }
    out.println("class of x: " + clX + '\n');

    // To find a method, need array of matching Class types.
    Class<?>[] argTypes = { int.class, String.class };

    // Find a Method object for the given method.
    Method toInvoke = null;
    try {
      toInvoke = clX.getMethod("work", argTypes);
      out.println("method found: " + toInvoke + '\n');
    } catch (NoSuchMethodException e) {
      err.println(e);
    }

    // To invoke the method, need the invocation arguments, as an Object array
    Object[] theArgs = { 42, "Chocolate Chips" };

    // The last step: invoke the method.
    try {
      toInvoke.invoke(x, theArgs);
    } catch (IllegalAccessException e) {
      err.println(e);
    } catch (InvocationTargetException e) {
      err.println(e);
    }
  } 
}

