package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "order_id")
	private int orderId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "total_amount")
	private int totalAmount;
	
	private String remark;
	private String pickupType;
	private String paymentType;
	private String orderStatus;
	
	@Column(name = "create_time")
	private LocalDateTime createTime;
	
	@Column(name = "update_time")
	private LocalDateTime updateTime;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	
}
