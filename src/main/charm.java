package main;

//Group 3
//2602159414 - Kanya Fadhillah Azmi
//2602165631 - Afrida Salsabila Putri
//2602152244 - Bernice Abigail Barakati
//2602162503 - Daniel Satrya Dewangga

public class charm {
	private String Name;
	private String description;
	private int price;
	private int stock;
	private String imagepath;
	
	public charm(String name, String description, int price, int stock, String imagepath) {
		super();
		Name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.imagepath = imagepath;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imageurl) {
		this.imagepath = imageurl;
	}
}

