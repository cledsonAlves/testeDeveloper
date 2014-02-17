package com.br.objetos;

import java.util.List;

public class Marca {
	private int id;
	private String created;
	private String image;
	private String name;
	private String description;
	private List<Produto_Colection> product_collection ;
	
	
	
	public List<Produto_Colection> getProduct_collection() {
		return product_collection;
	}
	public void setProduct_collection(List<Produto_Colection> product_collection) {
		this.product_collection = product_collection;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
	
}
