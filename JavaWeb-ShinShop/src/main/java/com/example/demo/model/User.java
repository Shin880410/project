package com.example.demo.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_account")
	private String userAccount;
	
	private String password;
	private String phone;
	
	@Column(name = "last_login_time")
	private LocalDateTime lastLoginTime;
	
	@Column(name = "update_time")
	private LocalDateTime updateTime;
	
	@Column(name = "create_time")
	private LocalDateTime createTime;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	
	
	
}
