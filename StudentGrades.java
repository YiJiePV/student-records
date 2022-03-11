package hw3.karena;	
/*Karena Qian
* 8.2.2021
* CS 211 Section C
* Instructor: Neelakantan Kartha
* Student Records */
/* This is a program that performs different functions to store and display student academic info
 * from a student activity input file.*/

import java.util.*;	
import java.io.*;	
public class StudentGrades {

	//stores student info under their ID
	private static Map<Integer, Student> students;	
	//stores course info under course number
	private static Map<String, Map<String, QuarterInfo>> courses = new TreeMap<>();

	//The following three methods are for reading input file
	public static String getQuarterKey(String quarter, String year) {
		if(quarter.compareTo("Autumn") == 0) {
			return year + " 0" ;
		}
		else if(quarter.compareTo("Winter") == 0) {
			return year + " 1";
		}
		else if(quarter.compareTo("Spring") == 0) {
			return year + " 2";
		}
		else if(quarter.compareTo("Summer") == 0) {
			return year + " 3";
		}
		return null;
	}
	//creates students map
	public static Map<Integer, Student> readStudentFile(Scanner s) {
		Map<Integer, Student> map = new TreeMap<>();	
		while(s.hasNextLine()) {
			Scanner sLine = new Scanner(s.nextLine());	
			sLine.useDelimiter("	");	
			String firstname = sLine.next();	
			String lastname = sLine.next();	
			int key = sLine.nextInt();
			Student student;	
			if(map.containsKey(key)) {
				student = map.get(key);	
			}
			else {
				student = new Student(firstname + " " + lastname);	
				map.put(key, student);	
			}
			String courseNum = sLine.next();
			String courseName = sLine.next();	
			String quarter = sLine.next();	
			String year = sLine.next();	
			int credit = sLine.nextInt();	
			double grade = sLine.nextDouble();	
			Course c = new Course(courseNum, courseName, credit, grade);	
			QuarterInfo q;	
			if(student.getQuarterNames().contains(getQuarterKey(quarter, year))) {
				q = student.getQuarter(getQuarterKey(quarter, year));	
			}
			else {
				q = new QuarterInfo(quarter + " " + year);	
				student.addToInfo(getQuarterKey(quarter, year), q);	
			}
			q.addCourse(c);	
		}
		return map;	
	}
	//creates courses map
	public static Map<String, Map <String, QuarterInfo>> readCourseFile(Scanner s){
		Map<String, Map <String, QuarterInfo>> map = new TreeMap<>();	
		while(s.hasNextLine()) {
			Scanner sLine = new Scanner(s.nextLine());	
			sLine.useDelimiter("	");	
			sLine.next();	 // skip first name
			sLine.next();	 //skip last name
			sLine.nextInt();	 //skip student id
			String courseNum = sLine.next();	
			String courseName = sLine.next();	
			Map <String, QuarterInfo> quarters;	
			if(map.containsKey(courseNum)) {
				quarters = map.get(courseNum);	
			}
			else {
				quarters = new HashMap<>();	
				map.put(courseNum, quarters);	
			}
			String quarter = sLine.next();	
			String year = sLine.next();	
			QuarterInfo q;	
			if(quarters.containsKey(getQuarterKey(quarter, year))) {
				q = quarters.get(getQuarterKey(quarter, year));	
			}
			else {
				q = new QuarterInfo(quarter + " " + year);	
				quarters.put(getQuarterKey(quarter, year), q);	
			}
			int credit = sLine.nextInt();	
			double grade = sLine.nextDouble();	
			Course c = new Course(courseNum, courseName, credit, grade);	
			q.addCourse(c);	
		
		}
		return map;	
	}
	//main method
	public static void main(String[] args) throws FileNotFoundException {
		//Sorry about the lack of user friendliness, I don't know how to ask for user file input
		Scanner s = new Scanner(new File("StudentsListv2"));	
		students = readStudentFile(s);
		
		s = new Scanner(new File("StudentsListv2"));
		courses = readCourseFile(s);
		
		Student newS = students.get(1122);
		
//		1. Given a student (ID) and given a quarter, 
//		get a report of all the courses the student took in that quarter 
//		and the grades they got that quarter for each course. 
//		Also report the average GPA for the quarter and the cumulative GPA upto that quarter.
//		
		newS.quarterReport(1122, getQuarterKey("Autumn", "2021"));
//		
//		2. Get a report of all the academic work that a particular student has completed: 
//		It should look something like:
//
//		Student Name: <Name>
//
//		Student ID: <ID>
//
//		Overall GPA: <Report Overall GPA>
//
//		Autumn 2020:
//
//		Course1 CourseName Credit GPA1
//
//		Course2 CourseName Credit GPA2
//
//		Average GPA for Autum 2020: <Report Overall GPA for this quarter>
//
//		Scholarship Status: Dean's List <Print this line only if 
//		overall GPA for the quarter is >= 3.5).
//
//		 
//
//		Spring 2021:
//
//		Course1 CourseName Credit GPA1
//
//		Course2 CourseName Credit GPA2
//
//		Average GPA for Spring 2021: <Report Overall GPA for this quarter>
//
//		Scholarship Status: Dean's List <Print this line only if 
//		overall GPA for the quarter is >= 3.5).
//		
		 newS.academicWork(1122);	
//
//
//		3.  Get a report of all the students and their average cumulative GPAs (????).
//
		allReport();	
//		
//		4.  Given a quarter, get a list of all students who took classes that quarter 
//		and their average GPAs for that quarter.
//		
		quarterList("Autumn 2021");	
		
//
//		5.  Given a particular course, report average GPA, max GPA and min GPA in that course  
//		for the last five quarters. It should look like the following:
//
//		Course Number: <Number>
//
//		Course Name: <Name>
//
//		Credits: <Credits>
//
//		Autumn  2018  : <Average GPA for Autumn 2018> <max GPA for Autumn 2018> 
//		<min GPA  for Autumn 2018>
//
//		Spring   2017:  <Average GPA for Spring 2017> <max GPA for Spring 2017> 
//		<min GPA  for Spring 2017>
//
//		....
		reportGPA("ENGL201");	
		
//
//		6. List the unique courses that have been offered, sorted alphabetically. 
//		Note: CS 211 in Fall 2020 and  CS 211 in Spring 2021 will count as a single course, 
//		not two different courses. Note that you cannot assume that the courses appear 
//		ordered by year in the input file.
//		
		listCourses();	
		
//
//		7. Given a quarter, list the students that qualify for a presidential scholarship 
//		that quarter. Here are the requirements for a presidential scholarship:   
//			Overall GPA >= 3.8 and GPA >= 3.5 in the last three quarters. 
//			(You can assume that every year has four quarters: Autumn, Winter, Spring and Summer.)
//		
		listQualifiedStudents("Summer 2021");

	}
	
	//3
	public static void allReport(){
		List<Integer> keys = new ArrayList<>(students.keySet());
		Collections.sort(keys);
		for(int i = 0; i < keys.size(); i++){
			int id = keys.get(i);	
			Student s = students.get(id);	
			System.out.printf(id + " " + s.getName() + " " + "%.1f \n\n", s.cumulativeGPA());	
		} 
		System.out.println("\n");
	}
	 
	//4
	public static void quarterList(String quarter){
		String[] seasonYear = quarter.split(" ");
		String quarterKey = getQuarterKey(seasonYear[0], seasonYear[1]);
		List<Integer> keys = new ArrayList<>(students.keySet());
		Collections.sort(keys);
		for(int i = 0; i < keys.size(); i++){
		  	int id = keys.get(i);	
		  	Student s = students.get(id);	
		  	List<String> quarters = s.getQuarterNames();	
		  	if(quarters.contains(quarterKey)){
		  		QuarterInfo q = s.getQuarter(quarterKey);	
		  		System.out.println(s.getName() + " " + id + " " + q.averageGPA() + "\n");	
		  	}
		}
	}
	
	//5
	public static void reportGPA(String courseNum){
		System.out.println("Course Number: " + courseNum);	
		Map<String, QuarterInfo> quarters = courses.get(courseNum);
		List<String> quarterKey = new ArrayList<>(quarters.keySet());
		Collections.sort(quarterKey);
		QuarterInfo q = quarters.get(quarterKey.get(0));
		ArrayList<Course> c = q.getCourses();
		System.out.println("Course Name: " + c.get(0).getCourseName());	
		System.out.println("Credits: " + c.get(0).getCredit());
		for(int i = 0; i < quarterKey.size(); i++){
			Set<Double> grades = new TreeSet<>();
			double total = 0;
			int counter = 0;
			q = quarters.get(quarterKey.get(i));
			c = q.getCourses();
			for(int j = 0; j < c.size(); j++) {
				Course cls = c.get(j);
				double grade = cls.getGrade();	
				total += grade;
				counter++;
				grades.add(grade);
			}
			List<Double> minMax = new ArrayList<>(grades);
			System.out.print(q.getQuarter() + ": ");
			System.out.printf("%.1f", (total / counter));  
			System.out.println(" " +  minMax.get(grades.size() - 1) + " " + minMax.get(0));
	  	}
		System.out.println();
	}
	
	//6
	public static void listCourses() {
		List<String> classes = new ArrayList<>(courses.keySet());
		for(int i = 0; i < classes.size() - 1; i++) {
			Map<String, QuarterInfo> quarters = courses.get(classes.get(i));
			List<String> quarterKey = new ArrayList<>(quarters.keySet());
			QuarterInfo q = quarters.get(quarterKey.get(0));
			ArrayList<Course> c = q.getCourses();
			System.out.println(classes.get(i) + ": " + c.get(0).getCourseName());
		}
	} 
	
	//7
	public static void listQualifiedStudents(String quarter){
		String[] seasonYear = quarter.split(" ");
		String quarterKey = getQuarterKey(seasonYear[0], seasonYear[1]);
		System.out.println("Qualified Students of the Presidential Scholarship: ");	
		List<Integer> s = new ArrayList<>(students.keySet());
		for(int i = 0; i < s.size(); i++){
			int id = s.get(i);
			Student student = students.get(id);
			if(student.getQuarterNames().contains(quarterKey) && 
					student.overallGPA() >= 3.8 && student.toPresentGPA(quarterKey) >= 3.5){
				System.out.println(student.getName() + " " + id);	
			}
		}
	}


}
