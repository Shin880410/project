package com.example.demo.model.dto;

import java.util.List;

public class OrderDto {
	public class PlaceOrderDto {
	    private String pickupType;
	    private String paymentType;
	    private String remark;
	    private List<OrderItemDto> items;

	    public String getPickupType() {
			return pickupType;
		}

		public void setPickupType(String pickupType) {
			this.pickupType = pickupType;
		}

		public String getPaymentType() {
			return paymentType;
		}

		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public List<OrderItemDto> getItems() {
			return items;
		}

		public void setItems(List<OrderItemDto> items) {
			this.items = items;
		}

		public static class OrderItemDto {
	        private int itemId;
	        private String options;
	        private int quantity;
	        private int unitPrice;
	        
			public int getItemId() {
				return itemId;
			}
			public void setItemId(int itemId) {
				this.itemId = itemId;
			}
			public String getOptions() {
				return options;
			}
			public void setOptions(String options) {
				this.options = options;
			}
			public int getQuantity() {
				return quantity;
			}
			public void setQuantity(int quantity) {
				this.quantity = quantity;
			}
			public int getUnitPrice() {
				return unitPrice;
			}
			public void setUnitPrice(int unitPrice) {
				this.unitPrice = unitPrice;
			}
	        
	        
	    }
	}
}
