package hska.iwi.eShopMaster.model.database.datatransferobject;

import hska.iwi.eShopMaster.model.database.dataobjects.Category;

public class ProductDTO {
	private int id;

	private String name;

	private double price;

	private String details;

	private int category_id;

	public ProductDTO() {
	}

	public ProductDTO(int id, String name, double price, String details, int category_id) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.details = details;
		this.category_id = category_id;
	}

	public ProductDTO(String name, double price, String details, int category_id) {
		this.name = name;
		this.price = price;
		this.details = details;
		this.category_id = category_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
}
