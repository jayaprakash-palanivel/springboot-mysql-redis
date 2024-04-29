package com.jp.springbootredis.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee")
@Data

public class Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9080199609203805194L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeId;
	
	private String employeeName;
	
	private String emailAddress;
	
	private long mobileNumber;

}
