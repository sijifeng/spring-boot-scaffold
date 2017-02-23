package com.season.platform.web.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static org.apache.coyote.http11.Constants.a;

/**
 * Created by jiyc on 2017/2/23.
 */
@Entity
@Data
@Log4j
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, length = 5)
	private String name;
	@Column(nullable = false)
	private Integer age;

	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
}
