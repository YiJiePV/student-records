package hw3.karena;	
//Student objects
import java.util.*;	

public class Student {
	//carries overall categories of info:
//Firstname  Lastname    Quarter + Year
//Overall GPA    Average GPA for certain quarter, etc.
	private String name;	
	//quarter information sorted by quarter name (quarter + year)
	private Map<String, QuarterInfo> info = new TreeMap<>();	
	
	public Student(String n) {
		name = n;	
	}
	
	//Add info to quarter list
	public void addToInfo(String quarter, QuarterInfo q) {
		info.put(quarter, q);	
	}
	
	//Gets student name
	public String getName() {
		return name;	
	}
	
	//Gets list of quarter names
	public List<String> getQuarterNames(){
		Set<String> quarters = new HashSet<>(info.keySet());
		List<String> l = new ArrayList<>(quarters);
		Collections.sort(l);
		return l;	
	}
	
	//Gets quarter info of certain quarter
	public QuarterInfo getQuarter(String quarter) {
		return info.get(quarter);	
	}
	
	//Get cumulative GPA upto a quarter (possible recursion???)
	public double toQuarterGPA(String quarter) {
		List<String> l = getQuarterNames();
		double grade = 0;
		int credit = 0;
		for(int i = 0; i < l.size(); i++) {
			ArrayList<Course> c = info.get(l.get(i)).getCourses();
			for(int j = 0; j < c.size(); j++) {
				grade += c.get(j).gradeCredit();
				credit += c.get(j).getCredit();
			}
			if(l.get(i).equals(quarter)) {
				break;
			}
		}
		return grade / credit;
	}
	
	//Get overall cumulative GPA
	public double cumulativeGPA() {
		List<String> l = getQuarterNames();
		return toQuarterGPA(l.get(l.size() - 1));
	}
	
	//Gets past three quarters
	public ArrayList<String> pastThreeQuarters(String quarter){
		ArrayList<String> s = new ArrayList<>();
		String[] split = quarter.split(" ");
		int year = Integer.parseInt(split[0]);
		int q = Integer.parseInt(split[1]);
		for(int i = 0; i < 3; i++) {
			if(q > 0) {
				q--;	
			}
			else {
				year--;
				q = 3;
			}
			s.add(year + " " + q);
		}
		return s;
	}
	
	//Get cumulative GPA from past three quarters 
	public double toPresentGPA(String quarter) {
		List<String> l = getQuarterNames();
		double grade = 0;
		int credit = 0;
		ArrayList<String> threeQuarters = pastThreeQuarters(quarter);
		for(int i = 0; i < threeQuarters.size(); i++) {
			if(l.contains(threeQuarters.get(i))) {
				ArrayList<Course> c = info.get(l.get(i)).getCourses();
				for(int k = 0; k < c.size(); k++) {
					grade += c.get(k).gradeCredit();
					credit += c.get(k).getCredit();
				}
			}
		}
		return grade / credit;
	}
	
	//Get Overall GPA
	public double overallGPA() {
		double total = 0;	
		int counter = 0;	
		List<String> quarter = getQuarterNames();
		for(int i = 0; i < quarter.size(); i++) {
			String key = quarter.get(i);
			total += info.get(key).averageGPA();	
			counter++;	
		}
		return total / counter;	
	}
	
	//1
	public void quarterReport(int id, String quarter){
		  QuarterInfo q = getQuarter(quarter);	
		  ArrayList<Course> a = q.getCourses();	
		  for(int i = 0; i < a.size() - 1; i++){
		  System.out.println(a.get(i));	
		  }
		  System.out.format(name + "'s Quarter Report: \nAverage GPA: " + q.averageGPA() + 
				  " Cumulative GPA: " + "%.1f",toQuarterGPA(quarter));
		  System.out.println("\n");
	}
	
	//2
	 public void academicWork(int id){	
		System.out.println("Student Name: " + name + "\n");	
	    System.out.println("Student ID: " + id + "\n");	
	    System.out.println("Overall GPA: " + overallGPA() + "\n");
	    List<String> quarter = getQuarterNames();
	    for(int i = 0; i < quarter.size(); i++){
	    	String key = quarter.get(i);
	    	System.out.println(info.get(key).getQuarter() + ":" + "\n");	
	    	ArrayList<Course> listC = info.get(key).getCourses();	
	    	for(int j = 0; j < listC.size(); j++){
	    		System.out.println(listC.get(j) + "\n");	
	    	}
	    	double overallGPA = info.get(key).averageGPA();	
	    	System.out.println("Average GPA for " + info.get(key).getQuarter() + 
	    						": " + overallGPA + "\n");	
	    	if(overallGPA >= 3.5){
	   			System.out.println("Scholarship Status: Dean's List" + "\n");	
 			}
	    	System.out.println("\n");
	    }
	    System.out.println();
	 }
	
	 //toString method (for testing)
	public String toString() {
		return name + ": " + info.toString() + "\n";	
	}
}
