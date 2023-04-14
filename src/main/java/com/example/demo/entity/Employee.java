package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 255, nullable = false)
	@NotEmpty(message = "名前は必須です")
	private String name;
	
	@Column(nullable = false)
	@NotEmpty(message = "年齢は必須です")
	@Positive(message = "年齢は数値を入力してください")
	
	private String age;
	
	@Column(nullable = false)
	private String address;

	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Department department;
	
	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "created_at", updatable = false)
	private Date createdAt;

	@PrePersist
	public void onPrePersist() {
		setUpdatedAt(new Date());
		setCreatedAt(new Date());
	}

	@PreUpdate
	public void onPreUpdate() {
		setUpdatedAt(new Date());
	}
}