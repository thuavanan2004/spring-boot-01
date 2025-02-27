package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
	private Long id;
	private String name;
	private String district;
	private Integer numberOfBasement;
	private Long floorArea;
	private String managerName;
	private String managerPhone;
	private String serviceFee;
	private String brokerageFee;
	private String staffId;
	private String rentAreaTo;
	private String rentAreaFrom;
	private String rentPriceTo;
	private String rentPriceFrom;
	private List<String> typeCodeList = new ArrayList<>();
	
	
	
	public BuildingSearchBuilder(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.district = builder.district;
		this.numberOfBasement = builder.numberOfBasement;
		this.floorArea = builder.floorArea;
		this.managerName = builder.managerName;
		this.managerPhone = builder.managerPhone;
		this.serviceFee = builder.serviceFee;
		this.brokerageFee = builder.brokerageFee;
		this.staffId = builder.staffId;
		this.rentAreaTo = builder.rentAreaTo;
		this.rentAreaFrom = builder.rentAreaFrom;
		this.rentPriceTo = builder.rentPriceTo;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.typeCodeList = builder.typeCodeList;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDistrict() {
		return district;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public Long getFloorArea() {
		return floorArea;
	}
	
	public String getManagerName() {
		return managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public String getBrokerageFee() {
		return brokerageFee;
	}
	public List<String> getTypeCodeList() {
		return typeCodeList;
	}
	public String getStaffId() {
		return staffId;
	}
	public String getRentAreaTo() {
		return rentAreaTo;
	}
	public String getRentAreaFrom() {
		return rentAreaFrom;
	}
	public String getRentPriceTo() {
		return rentPriceTo;
	}
	public String getRentPriceFrom() {
		return rentPriceFrom;
	}

	public static class Builder {
		private Long id;
		private String name;
		private String district;
		private Integer numberOfBasement;
		private Long floorArea;
		private String managerName;
		private String managerPhone;
		private String serviceFee;
		private String brokerageFee;
		private String staffId;
		private String rentAreaTo;
		private String rentAreaFrom;
		private String rentPriceTo;
		private String rentPriceFrom;
		private List<String> typeCodeList = new ArrayList<>();
		
		
		public Builder setId(Long id) {
			this.id = id;
			return this;
		}
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}
		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		public Builder setFloorArea(Long floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}
		public Builder setManagerPhone(String managerPhone) {
			this.managerPhone = managerPhone;
			return this;
		}
		public Builder setServiceFee(String serviceFee) {
			this.serviceFee = serviceFee;
			return this;
		}
		public Builder setBrokerageFee(String brokerageFee) {
			this.brokerageFee = brokerageFee;
			return this;
		}
		public Builder setStaffId(String staffId) {
			this.staffId = staffId;
			return this;
		}
		public Builder setRentAreaTo(String rentAreaTo) {
			this.rentAreaTo = rentAreaTo;
			return this;
		}
		public Builder setRentAreaFrom(String rentAreaFrom) {
			this.rentAreaFrom = rentAreaFrom;
			return this;
		}
		public Builder setRentPriceTo(String rentPriceTo) {
			this.rentPriceTo = rentPriceTo;
			return this;
		}
		public Builder setRentPriceFrom(String rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;
		}
		public Builder setTypeCodeList(List<String> typeCodeList) {
			this.typeCodeList = typeCodeList;
			return this;
		}
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
		
	}
	
}
