package model;

public class Soldier {
	private int id;
	private String name;
	private String surname;
	private String gun;

	public Soldier(int id, String name, String surname, String gun) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gun = gun;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGun() {
		return gun;
	}

	public void setGun(String gun) {
		this.gun = gun;
	}

}
