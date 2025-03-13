package main.exam01.dto;

import javafx.scene.control.Label;

public class GymMember {
	private String id, pwd, name, mem_type,gender;
	private int age;
	private double height, weight, bodyFat, met_consumption, 
	growthfactor_consumption, total_met_consumption, 
	total_growthfactor_consumption;
	
	private Label lbName, lbGender, lbAge, lbHeight, lbWeight, lbBodyFat;

	// 기본 생성자 -> selectAll, joinMember오류 때문에 사용
	public GymMember() {
		// TODO Auto-generated constructor stub
	}

	// 매게 변수 생성자 -> registerMember 사용하기 위해 만듬
	public GymMember(String id, String pwd, String name, String gender, int age, double height, double weight, double bodyFat,
			double metConsumption, double growthfactorConsumption, double totalMetConsumption, double totalGrowthfactorConsumption, String memType) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.bodyFat = bodyFat;
		this.met_consumption = metConsumption;
		this.growthfactor_consumption = growthfactorConsumption;
		this.total_met_consumption = totalMetConsumption;
		this.total_growthfactor_consumption = totalGrowthfactorConsumption;
		this.mem_type = memType;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMem_type() {
		return mem_type;
	}
	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getBodyFat() {
		return bodyFat;
	}
	public void setBodyFat(double bodyFat) {
		this.bodyFat = bodyFat;
	}
	public double getMet_consumption() {
		return met_consumption;
	}
	public void setMet_consumption(double met_consumption) {
		this.met_consumption = met_consumption;
	}
	public double getGrowthfactor_consumption() {
		return growthfactor_consumption;
	}
	public void setGrowthfactor_consumption(double growthfactor_consumption) {
		this.growthfactor_consumption = growthfactor_consumption;
	}
	public double getTotal_met_consumption() {
		return total_met_consumption;
	}
	public void setTotal_met_consumption(double total_met_consumption) {
		this.total_met_consumption = total_met_consumption;
	}
	public double getTotal_growthfactor_consumption() {
		return total_growthfactor_consumption;
	}
	public void setTotal_growthfactor_consumption(double total_growthfactor_consumption) {
		this.total_growthfactor_consumption = total_growthfactor_consumption;
	}


}
