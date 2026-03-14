package com.example.movieservice.model;

public class Movie {

    private int id;
    private String name;
    private String language;
    private int price;
	public Movie(int id, String name, String language, int price) {
		super();
		this.id = id;
		this.name = name;
		this.language = language;
		this.price = price;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

    
}
