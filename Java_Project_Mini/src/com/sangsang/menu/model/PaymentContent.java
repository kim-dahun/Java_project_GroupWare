package com.sangsang.menu.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PaymentContent implements Serializable {

	public interface Entity {

		public static final String TBL_NAME = "paymentcontent";
		public static final String COL_NAME = "name";
		public static final String COL_EMPNO = "empno";
		public static final String COL_BASESAL = "basesal";
		public static final String COL_BONUS = "bonus";
		public static final String COL_OVERTIMEPAY = "overtimepay";
		public static final String COL_MEALS = "meals";
		public static final String COL_VEHICLEMAINTENANCE = "vehiclemaintenance";
		public static final String COL_SEVERANCEPAY = "severancepay";
		public static final String COL_INCOMETAX = "incometax";
		public static final String COL_RESIDENTTAX = "residenttax";
		public static final String COL_HEALTHINSURANCEPREMIUM = "HEALTHINSURANCEPREMIUM";
		public static final String COL_NATIONALPENSION = "nationalpension";
		public static final String COL_EMPLOYMENTINSURANCE = "employmentinsurance";
		public static final String COL_OTHERDEDUCTIONS = "otherdeductions";
		public static final String COL_DEDUCTIONSREASON = "deductionsreason";
		public static final String COL_PAYMONTH = "paymonth";
		
	}

	private String name;
	private String empno;
	private String posName;
	private String deptName;
	// 지급내역
	private int baseSal;
	private int bonus;
	private int overtimePay;
	private int meals;
	private int vehicleMaintenance;
	private int severancePay;
	// 공제내역
	private int incomeTax;
	private int residentTax;
	private int healthInsurancePremium;
	private int nationalPension;
	private int employmentInsurance;
	private int OtherDeductions;
	private String DeductionsReason;
	private String paymentMonth;
	public PaymentContent() {

	}

	public PaymentContent(String name, String empno, String posName, String deptName, int baseSal, int bonus,
			int overtimePay, int meals, int vehicleMaintenance, int severancePay, int incomeTax, int residentTax,
			int healthInsurancePremium, int nationalPension, int employmentInsurance, int otherDeductions,
			String deductionsReason, String paymentMonth) {
		super();
		this.name = name;
		this.empno = empno;
		this.posName = posName;
		this.deptName = deptName;
		this.baseSal = baseSal;
		this.bonus = bonus;
		this.overtimePay = overtimePay;
		this.meals = meals;
		this.vehicleMaintenance = vehicleMaintenance;
		this.severancePay = severancePay;
		this.incomeTax = incomeTax;
		this.residentTax = residentTax;
		this.healthInsurancePremium = healthInsurancePremium;
		this.nationalPension = nationalPension;
		this.employmentInsurance = employmentInsurance;
		OtherDeductions = otherDeductions;
		DeductionsReason = deductionsReason;
		this.paymentMonth = paymentMonth;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public int getBaseSal() {
		return baseSal;
	}

	public void setBaseSal(int baseSal) {
		this.baseSal = baseSal;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getOvertimePay() {
		return overtimePay;
	}

	public void setOvertimePay(int overtimePay) {
		this.overtimePay = overtimePay;
	}

	public int getMeals() {
		return meals;
	}

	public void setMeals(int meals) {
		this.meals = meals;
	}

	public int getVehicleMaintenance() {
		return vehicleMaintenance;
	}

	public void setVehicleMaintenance(int vehicleMaintenance) {
		this.vehicleMaintenance = vehicleMaintenance;
	}

	public int getSeverancePay() {
		return severancePay;
	}

	public void setSeverancePay(int severancePay) {
		this.severancePay = severancePay;
	}

	public int getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(int incomeTax) {
		this.incomeTax = incomeTax;
	}

	public int getResidentTax() {
		return residentTax;
	}

	public void setResidentTax(int residentTax) {
		this.residentTax = residentTax;
	}

	public int getHealthInsurancePremium() {
		return healthInsurancePremium;
	}

	public void setHealthInsurancePremium(int healthInsurancePremium) {
		this.healthInsurancePremium = healthInsurancePremium;
	}

	public int getNationalPension() {
		return nationalPension;
	}

	public void setNationalPension(int nationalPension) {
		this.nationalPension = nationalPension;
	}

	public int getEmploymentInsurance() {
		return employmentInsurance;
	}

	public void setEmploymentInsurance(int employmentInsurance) {
		this.employmentInsurance = employmentInsurance;
	}

	public int getOtherDeductions() {
		return OtherDeductions;
	}

	public void setOtherDeductions(int otherDeductions) {
		OtherDeductions = otherDeductions;
	}

	public String getDeductionsReason() {
		return DeductionsReason;
	}

	public void setDeductionsReason(String deductionsReason) {
		DeductionsReason = deductionsReason;
	}

	public String getPaymentMonth() {
		return paymentMonth;
	}

	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}

	// 지급 연월
	

}
