package com.ora.springdataes.model;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

//clustername= elasticsearch
//@Document(indexName = "elasticsearch", type = "user", shards = 1, replicas = 0, refreshInterval = "-1")
@Document(indexName = "my_index", type = "user", shards=1)
public class UserDoc {

	@Id
	private long id;

	private String email;

	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserDoc(long id, String email, String name) {
		super();
		this.id = id;
		this.setEmail(email);
		this.name = name;
	}

	public UserDoc() {
		super();
		// TODO Auto-generated constructor stub
	}

}