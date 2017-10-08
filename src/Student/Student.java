package Student;

import java.io.File;
import java.io.Serializable;

/*
 * 用来将学生信息序列化
 */
public class Student implements Serializable{
	private String name;
	private String sex;
	private String major;
	private String number;
	private String birther;
	private String grade;
	private File imagePic;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBirther() {
		return birther;
	}
	public void setBirther(String birther) {
		this.birther = birther;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public File getImagePic() {
		return imagePic;
	}
	public void setImagePic(File imagePic) {
		this.imagePic = imagePic;
	}
	
	
	
	

}
