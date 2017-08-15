package com.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String email;
	private String name;
	private String firstName;
	private String lastName;
	private int age;

	public User() {
	}

	public User(long id) {
		this.id = id;
	}

	public User(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public User(long id, String email, String name, String firstName,
			String lastName, int age) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long value) {
		this.id = value;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
