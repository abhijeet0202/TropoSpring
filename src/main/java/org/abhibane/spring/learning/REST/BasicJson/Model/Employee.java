package org.abhibane.spring.learning.REST.BasicJson.Model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@JsonProperty("first-name")
	String firstName;
	
	@JsonProperty("last-name")
	String lastName;
	
	int age;
	
	@JsonIgnore
	boolean married;
	
	@Embedded
	Address address;

}
