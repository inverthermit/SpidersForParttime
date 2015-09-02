package com.jeiel.test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


//0.School;1.Level;2.Title;3.Type;4.ApplicationFee;5.Tuition Fee;6.Academic Requirements;
//7.IELTS Average;8.IELTS Low;9.Structure;10.Length(month);11.Month of Entry;12.Scholarship  
public class Major{
	private String school;
	private String level;
	private String title;
	private String type;
	private String applicationFee;
	private String tuitionFee;
	private String academicRequirements;
	private String IELTS_Avg;
	private String IELTS_Low;
	private String IELTS_l;
	private String IELTS_s;
	private String IELTS_r;
	private String IELTS_w;
	private LinkedHashMap<String, String>structure;
	private String length;
	private String monthOfEntry;
	private LinkedHashMap<String, String>scholarship;

	public Major(){
		school="";
		level="";
		title="";
		type="";
		applicationFee="";
		tuitionFee="";
		academicRequirements="";
		IELTS_Avg="";
		IELTS_Low="";
		IELTS_l="";
		IELTS_s="";
		IELTS_r="";
		IELTS_w="";
		structure=new LinkedHashMap<String, String>();
		length="";
		monthOfEntry="";
		scholarship=new LinkedHashMap<String, String>();
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApplicationFee() {
		return applicationFee;
	}

	public void setApplicationFee(String applicationFee) {
		this.applicationFee = applicationFee;
	}

	public String getTuitionFee() {
		return tuitionFee;
	}

	public void setTuitionFee(String tuitionFee) {
		this.tuitionFee = tuitionFee;
	}

	public String getAcademicRequirements() {
		return academicRequirements;
	}

	public void setAcademicRequirements(String academicRequirements) {
		this.academicRequirements = academicRequirements;
	}

	public String getIELTS_Avg() {
		return IELTS_Avg;
	}

	public void setIELTS_Avg(String iELTS_Avg) {
		IELTS_Avg = iELTS_Avg;
	}

	public String getIELTS_Low() {
		return IELTS_Low;
	}

	public void setIELTS_Low(String iELTS_Low) {
		IELTS_Low = iELTS_Low;
	}

	public String getIELTS_l() {
		return IELTS_l;
	}

	public void setIELTS_l(String iELTS_l) {
		IELTS_l = iELTS_l;
	}

	public String getIELTS_s() {
		return IELTS_s;
	}

	public void setIELTS_s(String iELTS_s) {
		IELTS_s = iELTS_s;
	}

	public String getIELTS_r() {
		return IELTS_r;
	}

	public void setIELTS_r(String iELTS_r) {
		IELTS_r = iELTS_r;
	}

	public String getIELTS_w() {
		return IELTS_w;
	}

	public void setIELTS_w(String iELTS_w) {
		IELTS_w = iELTS_w;
	}

	public LinkedHashMap<String, String> getStructure() {
		return structure;
	}

	public void setStructure(LinkedHashMap<String, String> structure) {
		this.structure = structure;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getMonthOfEntry() {
		return monthOfEntry;
	}

	public void setMonthOfEntry(String monthOfEntry) {
		this.monthOfEntry = monthOfEntry;
	}

	public LinkedHashMap<String, String> getScholarship() {
		return scholarship;
	}

	public void setScholarship(LinkedHashMap<String, String> scholarship) {
		this.scholarship = scholarship;
	};
	
	

}