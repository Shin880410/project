package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 自動產生UserID
	private int userId;
	
	private String userName;
	
	private String userAccount;
	
	private String password;
	
	private String phone;
	
	private String lastLoginTime;
	
	private String createTime;

	
	
	
	
}
