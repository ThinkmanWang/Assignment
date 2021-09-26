

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Hw2_p1 {

	public static int findByMajor(Student[] students, String major) {
		// implement this method
		if (null == students
				|| students.length <= 0
				|| null == major
				|| major.length() <= 0) {
			return 0;
		}

		int nNum = 0;
		for (Student student : students) {
			if (student.getMajor().equals(major)) {
				System.out.println(student);
				nNum++;
			}
		}

		return nNum;
	}

	public static Student[] readStudents(String szFileName) {
		FileReader fr = null;
		BufferedReader bf = null;

		try {
			fr = new FileReader(szFileName);
			bf = new BufferedReader(fr);

			Student[] aryStudent = null;

			int nLine = 0;
			int nNum = 0;
			String szLine = null;
			while ((szLine = bf.readLine()) != null) {
				if (0 == nLine) { //read the number of students in the file
					nNum = Integer.valueOf(szLine);
					aryStudent = new Student[nNum];
				} else {
					String[] szItems = szLine.split(",");

					Student student = new Student();
					student.setName(szItems[0].trim());
					student.setMajor(szItems[1].trim());
					student.setGPA(Double.valueOf(szItems[2].trim()));

					aryStudent[nLine - 1] = student;
				}

				nLine++;
			}

			return aryStudent;
		} catch (Exception ex) {
			return null;
		} finally {
			try {
				bf.close();
				fr.close();
			} catch (Exception ex) {

			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		// complete this main method
		
		// read input file
		// create and store student objects in an array
		Student[] students = readStudents("/Users/wangxiaofeng/Github-Thinkman/Assignment/src/student_input1.txt");
			
		// in the following code, students[ ] is an array of student objects
		
		System.out.println("\nAll students:");
		for (int i=0; i<students.length; i++) {

			System.out.println(students[i]);
		}

		String major = "CSE";

		System.out.println("\nAll students with major " + major);
		int numStudents = findByMajor(students, major);
		System.out.println("\nNumber of students with major " + major + " is: " + numStudents);
		
	}

}
