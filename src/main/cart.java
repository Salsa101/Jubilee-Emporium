package main;

//Group 3
//2602159414 - Kanya Fadhillah Azmi
//2602165631 - Afrida Salsabila Putri
//2602152244 - Bernice Abigail Barakati
//2602162503 - Daniel Satrya Dewangga

public class cart extends user {
	private String itemname;
	private int itemqty;
	
	public cart(String name, String email, String password, int coins) {
		super(name, email, password, coins);
		
	}

	public cart(String name, String email, String password, int coins, String itemname, int itemqty) {
		super(name, email, password, coins);
		this.itemname = itemname;
		this.itemqty = itemqty;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public int getItemqty() {
		return itemqty;
	}

	public void setItemqty(int itemqty) {
		this.itemqty = itemqty;
	}
	
	public void addItem(String itemname, int quantity) {
        if (this.itemname.equals(itemname)) {
            this.itemqty += quantity;
        } else {
            this.itemname = itemname;
            this.itemqty = quantity;
        }
    }

}
