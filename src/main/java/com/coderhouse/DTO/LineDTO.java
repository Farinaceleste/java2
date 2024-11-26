package com.coderhouse.DTO;

import java.math.BigDecimal;

public class LineDTO {
		
		private Integer lineId;

		private Integer quantity;

		private String description;

		private BigDecimal price;

		public Integer getLineId() {
			return lineId;
		}

		public void setLineId(Integer lineId) {
			this.lineId = lineId;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}



	}

