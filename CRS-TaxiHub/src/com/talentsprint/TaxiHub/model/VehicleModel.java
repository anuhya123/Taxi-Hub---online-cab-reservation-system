package com.talentsprint.TaxiHub.model;

public class VehicleModel {
	String vehicle_num;
	String registration_num;
	String cost_per_km;
	String status;
	
	public String getVehicle_num() {
		return vehicle_num;
	}
	public void setVehicle_num(String vehicle_num) {
		this.vehicle_num = vehicle_num;
	}
	public String getRegistration_num() {
		return registration_num;
	}
	public void setRegistration_num(String registration_num) {
		this.registration_num = registration_num;
	}
	public String getCost_per_km() {
		return cost_per_km;
	}
	public void setCost_per_km(String cost_per_km) {
		this.cost_per_km = cost_per_km;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
