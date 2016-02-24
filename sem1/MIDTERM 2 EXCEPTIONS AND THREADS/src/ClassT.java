import java.util.*;

public class ClassT extends Thread    {
    private String info;
    private Vector aVector;

    public ClassT (String info, Vector aVector) {
        this.info    = info;
        this.aVector = aVector;
    }

    static synchronized void staticInProtected1(String s) {
        System.err.println(s + ": ----> staticInProtected1");
        try {
                sleep(1000);
        }
        catch (  InterruptedException e ) {
            System.err.println("Interrupted!");
        }
        staticInProtected2(s);
        System.err.println(s + ": <----	");
    }

    static synchronized void staticInProtected2(String s) {
        System.err.println(s + ": ====>");
        try {
                sleep(1000);
        }
        catch (  InterruptedException e ) {
            System.err.println("Interrupted!");
        }
        System.err.println(s + ": ====>");
    }

    public void run () {
        staticInProtected1(info);
    }

    public static void main (String args []) {
        Vector aVector = new Vector();
        ClassT aClassT_0 = new ClassT("first",  aVector);
        ClassT aClassT_1 = new ClassT("second", aVector);

        ClassT.staticInProtected1("main");
        aClassT_0.start();
        aClassT_1.start();
        aClassT_0.staticInProtected1("aClassT_0");
        aClassT_1.staticInProtected1("aClassT_1");
    }
}