package hska.iwi.eShopMaster.model.database.dataobjects;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

public class Category {

	private int id;
	private String name;

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
