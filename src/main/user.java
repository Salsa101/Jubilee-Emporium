package main;

//Group 3
//2602159414 - Kanya Fadhillah Azmi
//2602165631 - Afrida Salsabila Putri
//2602152244 - Bernice Abigail Barakati
//2602162503 - Daniel Satrya Dewangga

import java.util.ArrayList;

public class user {
	private String Name;
	private String email;
	private String Password;
	private int coins;
	
	private ArrayList<cart> cart;
	private ArrayList<inventory> inv;
	

	public user(String name, String email, String password, int coins) {
		super();
		Name = name;
		this.email = email;
		Password = password;
		this.coins = coins;
		this.cart = new ArrayList<cart>();
		this.inv = new ArrayList<inventory>();
		
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public ArrayList<cart> getCart() {
		return cart;
	}

	public void setCart(ArrayList<cart> cart) {
		this.cart = cart;
	}
	
	public ArrayList<inventory> getInv() {
		return inv;
	}

	public void setInv(ArrayList<inventory> inv) {
		this.inv = inv;
	}

	public void clearcart() {
		cart.clear();
	}
	
	
}

