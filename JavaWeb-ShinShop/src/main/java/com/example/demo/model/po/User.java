package com.example.demo.model.po;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 自動產生UserID
	@Column(name = "user_id")
	private Integer userId;
	
	private String name;
	private String phone;
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@Column(name = "last_login_time")
	private LocalDateTime lastLoginTime;
	
	@Column(name = "update_time")
	private LocalDateTime updateTime;
	
	@Column(name = "create_time")
	private LocalDateTime createTime;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@OneToMany(mappedBy = "user") // 用來查詢該用戶所有訂單
	@ToString.Exclude //用於防止序列化時的循環依賴錯誤
	private List<Order> orders;

	
	
}
