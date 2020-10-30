package com.yoshihide.springboot;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mydata")
public class MyData {
	@OneToMany(mappedBy = "mydata", cascade = CascadeType.ALL)
	@Column(nullable = true)
	@Getter
	@Setter
	private List<MsgData> msgdatas;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull
	@Getter
	@Setter
	private long id;

	@Column(length = 50, nullable = false)
	@NotEmpty
	@Getter
	@Setter
	private String name;

	@Column(length = 200, nullable = true)
	@Email
	@Getter
	@Setter
	private String mail;

	@Column(nullable = true)
	@Min(0)
	@Max(200)
	@Getter
	@Setter
	private Integer age;

}
