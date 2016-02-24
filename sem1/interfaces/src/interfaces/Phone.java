package interfaces;

public class Phone implements InCommon {

    int price; 

    public void volume() {
	System.out.println("Pott!volume: 0.5l");
    }

    public void setPrice(int x) {
	int letSee= (int)(price * MIMUM_INCREASE);
	price = letSee > x ? x : letSee ;
    }
}
