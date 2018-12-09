package com.teixeiradiego.clientlist.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class Client {
	
	@Id
	private Integer id;
	private String name;
	
	@Column(name = "photo_url")
	private String photoUrl;
	
	
}
