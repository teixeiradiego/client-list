package com.teixeiradiego.contactlist.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "person")
public class Person {
	
	@Id
	private Integer id;
	private String name;
	
	@Column(name = "photo_url")
	private String photoUrl;
	
	
}
