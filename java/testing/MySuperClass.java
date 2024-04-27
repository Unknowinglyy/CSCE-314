
/* Written by Hyunyoung Lee for CSCE 314 Students
   Example for accessing inner & outer class, and other reflection
      methods, such as obtaining Class objects.
*/

import java.lang.reflect.*; // for reflection operations
import java.util.*;
import javax.swing.*;
import static java.lang.System.out;
import static java.lang.System.err;

public class MySuperClass {
  public MySuperClass() { 
    out.println("I'm your super class!");
  }
}


class MyClass extends MySuperClass {
  private String aSecret() {
    return ("Here is the secret!");
  }
  public void work(int i, String s) {
    out.println("MyClass.work: " + i + " " + s);
  }
  protected MyClass(String s) {
    out.println(s);
  }
  public MyClass() {
    out.println("1: "+aSecret());

    // class Outer as inner class of MyClass
    class Outer1 {
       public void show() {
         // inner class of class Outer
         class Inner {
            public void show() {
              out.print("2: "+getClass().getName() + " .. inner in .. ");
              out.println(getClass().getEnclosingClass());    
            }
         }
         out.print("3: "+getClass().getName() + " __ inner in __ ");
         out.println(getClass().getEnclosingClass());
         // invoke inner class show() function
         Inner i = new Inner();
         i.show();
      }
    }
    // invoke outer class show() function
    Outer1 o1 = new Outer1();
    o1.show();
    class Outer2 {
       public void show() { 
         out.print("4: "+getClass().getName() + " is inner of "); 
         out.println(getClass().getEnclosingClass().getName());
         out.print("5: "+getClass().getCanonicalName() + " is inner of "); 
         out.println(getClass().getEnclosingClass().getCanonicalName());
         out.print("6: "+getClass().getSimpleName() + " is inner of "); 
         out.println(getClass().getEnclosingClass().getSimpleName());
       } 
    }
    Outer2 o2 = new Outer2();
    out.println("7: "+o2.getClass().getCanonicalName());
    o2.show();
  } // end of MyClass()

  public static void main(String[] args) {
    // test getClass() and class
    MyClass a = new MyClass();
    Class c = a.getClass();
    out.println("\n===== Obtaining a Class object");
    out.println("a.getClass() is " + c);

    int i = 5;
    // c = i.getClass(); // compile error
    c = ((Integer) i).getClass();
    out.println("i.getClass() is " + c);

    c = MyClass.class;
    out.println("MyClass.class is " + c);

    // test forName()
    Class cls = null;
    try {
      cls = Class.forName("MyClass");
    } catch (ClassNotFoundException e) {
      err.println(e);
    }
    out.println("forName(MyClass) is " + cls);
    // different names of MyClass
    out.println("\nMyClass class is " + cls);
    out.println("MyClass.getName() = " + cls.getName());
    out.println("MyClass.getCanonicalName() = " + cls.getCanonicalName());
    out.println("MyClass.getSimpleName() = " + cls.getSimpleName());

    // test other Class methods
    out.println("\n===== Testing other reflection methods");
    out.println("superclass of javax.swing.JButton is: " +
                 javax.swing.JButton.class.getSuperclass());
    out.println("superclass of MyClass is: " + 
                 cls.getSuperclass()); 
    out.println("superclass getName() of MyClass is: " + 
                 cls.getSuperclass().getName()); 

    out.println("superclass of superclass of MyClass is: " + 
                 cls.getSuperclass().getSuperclass()); 

    out.println("superclass of superclass of superclass of MyClass is: " + 
                 cls.getSuperclass().getSuperclass().getSuperclass()); 

    Method[] allPublicMethods = cls.getMethods();
    out.println("\nAll (public) methods of MyClass:");
    for (Method m : allPublicMethods)
           out.println("   " + m); 

    Method[] methods = cls.getDeclaredMethods();
    out.println("\nAll declared methods of MyClass:");
    for (Method m : methods)
    //    if (Modifier.isPublic(m.getModifiers()))
           out.println("   " + m); 

    // constructor(s)

    Object b = new Object();
    c = b.getClass();
    out.println("\nObject class is " + c);
    out.println("Object.getName() = " + c.getName());
    out.println("Object.getCanonicalName() = " + c.getCanonicalName());
    out.println("Object.getSimpleName() = " + c.getSimpleName());

    c = System.console().getClass();
    out.println("\nConsole class is " + c);

    c = boolean.class; //can do this with other primitive types too (just can't use the getClass() method on them)
    //c = Boolean.class;
    out.println("boolean type is " + c);
    out.println("boolean type getTypeName() is " + c.getTypeName());

    out.println("---1");
    Constructor[] constructs = cls.getConstructors();
    out.println("Only public:");
    for (Constructor co : constructs)
        out.println("constructor: " + co);

    constructs = cls.getDeclaredConstructors();
    out.println("All constructors:");
    for (Constructor co : constructs)
        out.println("constructor: " + co);

    out.println("---2");
    Class<?>[] ac = Character.class.getClasses(); // including inherited
    for (Class aa : ac)
        out.println("Character class : " + aa + 
                  "\n                : " + aa.getCanonicalName());
    out.println();
    ac = Character.class.getDeclaredClasses(); // explicitly declared in this
    for (Class aa : ac)
        out.println("declared class: " + aa);
    out.println("enclosing class: " + Character.class.getEnclosingClass());

    // to test type erasure
    List<Integer> vi = new LinkedList<Integer>();
    List<String> vs = new LinkedList<String>();
    out.println("vi is "+vi.getClass().getName());
    out.println("vi is "+vi.getClass().getCanonicalName());
    out.println("vi is "+vi.getClass().getSimpleName());
    out.println("vs is "+vs.getClass());
    if (vi.getClass().equals(vs.getClass()))
       out.println("vi and vs are the same!");
    else
       out.println("vi and vs are different!");
  }
}

