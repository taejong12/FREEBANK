package main.exam01.dto;

public class GymMachine {
	private String bodyparts, machine;
	private double met, growthfactor;

	//openManger에 사용
	public GymMachine() {
		// TODO Auto-generated constructor stub
	}
	// handleInsertManager 사용
	public GymMachine(String bodyparts, String machine, double met, double growthfactor) {
		// TODO Auto-generated constructor stub
		this.bodyparts = bodyparts;
		this.machine = machine;
		this.met = met;
		this.growthfactor = growthfactor;
	}

	public String getBodyparts() {
		return bodyparts;
	}
	public void setBodyparts(String bodyparts) {
		this.bodyparts = bodyparts;
	}
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	public double getMet() {
		return met;
	}
	public void setMet(double met) {
		this.met = met;
	}
	public double getGrowthfactor() {
		return growthfactor;
	}
	public void setGrowthfactor(double growthfactor) {
		this.growthfactor = growthfactor;
	}


}
