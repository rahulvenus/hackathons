package interfaces;

public class VCR implements InCommon {

    int price; 

    public void volume() {
	System.out.println("Mug!volume: 0.3l");
    }

    public void setPrice(int x) {
	int letSee= (int)(price * MIMUM_INCREASE);
	price = letSee > x ? x : letSee ;
    }
}
