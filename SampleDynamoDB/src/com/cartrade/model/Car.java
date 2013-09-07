package com.cartrade.model;

public class Car {
	private String			maker;
	private String			model;
	private double			price;
	private Transmission	transmission;
	private FuelType		fuelType;
	private String			milage;

	public static enum Transmission {
		MANUAL, AUTOMATIC
	}

	public static enum FuelType {
		int fuelType;
		FuelType(){
			
		}
		PETROL, DIESEL, CNG, SOLAR, ELECTRIC
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public String getMilage() {
		return milage;
	}

	public void setMilage(String milage) {
		this.milage = milage;
	}

}
