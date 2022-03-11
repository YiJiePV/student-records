package hw3.karena;	

//the course object, storing Course CourseName Credit and Grade
public class Course {
	private String courseNum;	
	private String courseName;	
	private int credit;	
	private double grade;	
	
	public Course(String num, String name, int c, double g) {
		courseNum = num;	
		courseName = name;	
		credit = c;	
		grade = g;	
	}
	
	//Get course number
	public String getCourseNumber() {
		return courseNum;	
	}
	
	//Get course name
	public String getCourseName() {
		return courseName;	
	}
	
	//Get credit
	public int getCredit() {
		return credit;	
	}
	
	//Get grade
	public double getGrade() {
		return grade;	
	}
	
	//Get grade * credit
	public double gradeCredit() {
		return grade * credit;
	}
	
	//toString method
	public String toString(){
		return courseNum + " " + courseName + " " + credit + " " + grade;	
	}
}
