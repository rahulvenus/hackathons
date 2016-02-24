package testPrep;

/**
 * This class implements a inner class.
 *
 * @version   $Id$
 *
 * @author    Axel T. Schreiner
 * @author    hp bischof
 *
 * Revisions:
 *	$Log$
 */
class InnerClass {
    static class A {
	static void hi () {
		System.err.println("A.hi");
	}
    }

    class B {
	void hi () {
		System.err.println("B.hi");
	}
    }

    void hi () {
	     class C {
		void hi () {
			System.err.println("C.hi");
		}
	     }
	     Object o = new C() {
		void hi () {
			System.err.println("D.hi");
		}
	    };
	    ((C)o).hi(); 
	    new C().hi(); 
	    new B().hi();
    }
    static public void main (String args []) {
	     new InnerClass().hi();
	     A.hi();
    }
}