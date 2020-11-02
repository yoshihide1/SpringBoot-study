package com.yoshihide.springboot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "msgdata")
public class MsgData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull
	@Getter
	@Setter
	private long id;

	@Column
	@NotEmpty
	@Getter
	@Setter
	private String title;

	@Column(nullable = false)
	@NotEmpty
	@Getter
	@Setter
	private String message;

	@ManyToOne
	@Getter
	@Setter
	private MyData mydata;

}
