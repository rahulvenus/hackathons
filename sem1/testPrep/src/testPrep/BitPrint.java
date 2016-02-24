package testPrep;

class BitPrint {
	private void printBytes (String what, int value) {
		System.out.print(what + "\t=\t" + value + "\t=\t");
		for ( int index = 31; index >= 0 ; index --)	{
			if ( ( ( 1 << index ) & value ) == ( 1 << index ) )
				System.out.print("1");
			else
				System.out.print("0");
				
		}
		System.out.println();
	}

	public static void main (String args []) {
		BitPrint aBitPrint = new BitPrint();
		
		System.out.println(2);
		aBitPrint.printBytes("3       ", 3);
		aBitPrint.printBytes("4       ", 4);
		aBitPrint.printBytes("7       ", 7);
		aBitPrint.printBytes("-3      ", -3);
		aBitPrint.printBytes("-4      ", -4);
		aBitPrint.printBytes("-7      ", -7);
		aBitPrint.printBytes("5       ", 5);
		aBitPrint.printBytes("5 >> 1  ", (5 >> 1));
		aBitPrint.printBytes("5       ", 5);
		aBitPrint.printBytes("5 >>> 1  ", (5 >>> 1));
		aBitPrint.printBytes("-5      ", -5);
		aBitPrint.printBytes("-5 >> 1 ", (-5 >> 1));
		aBitPrint.printBytes("-5 >>> 1", (-5 >>> 1));
	}
}