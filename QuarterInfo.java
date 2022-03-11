package hw3.karena;	

//QuarterInfo Object
import java.util.*;	
public class QuarterInfo {
	//for certain pieces of info:
	// quarter name, list of courses, average GPA for quarter
	String quarter;	
	ArrayList<Course> courses;	
	
	public QuarterInfo(String s) {
		quarter = s;	
		courses = new ArrayList<>();	
	}
	
	//Add to course list
	public void addCourse(Course c) {
		courses.add(c);	
	}
	
	//Gets course list
	public ArrayList<Course> getCourses(){
		return courses;	
	}
	
	//Gets quarter
	public String getQuarter() {
		return quarter;	
	}
	
	//calculate average GPA for the quarter (idk if recursion can be done here lol)
	public double averageGPA() { 
		double total = 0;	
		int counter = 0;
		for(Course c : courses) {
			total += c.getGrade();	
			counter++;	
		}
		return total / counter;	
	}
	
	//toString method
	public String toString() {
		return quarter + ": " + courses.toString() + "\n";	
	}
}
