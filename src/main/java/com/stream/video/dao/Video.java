package com.stream.video.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Video {
	 @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private int id;
	  @Column
	  private String name;
	  @Column
	  private int type;
	  
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
	  public int getType() {
	    return type;
	  }
	  public void setType(int type) {
	    this.type = type;
	  }
}
