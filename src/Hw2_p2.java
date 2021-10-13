import java.io.BufferedReader;
import java.io.FileReader;

public class Hw2_p2 {

    public static MyLinkedList<Student> readStudents(String szFileName) {
        FileReader fr = null;
        BufferedReader bf = null;

        try {
            fr = new FileReader(szFileName);
            bf = new BufferedReader(fr);

            MyLinkedList<Student> lstStudent = new MyLinkedList<>();

            String szLine = null;
            while ((szLine = bf.readLine()) != null) {

                String[] szItems = szLine.split(",");

                Student student = new Student();
                student.setName(szItems[0].trim());
                student.setMajor(szItems[1].trim());
                student.setGPA(Double.valueOf(szItems[2].trim()));

                lstStudent.addLast(student);
            }

            return lstStudent;
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

    public static int higherThan(MyLinkedList<Student> StudentList, double gpa) {

        int nNum = 0;

        Student student = StudentList.removeFirst();
        while (student != null) {
            if (student.getGPA() >= gpa) {
                System.out.println(student);
                nNum++;
            }

            student = StudentList.removeFirst();
        }

        return nNum;
    }

    public static void main(String[] args) throws Exception {
        MyLinkedList<Student> lstStudent = readStudents("/Users/wangxiaofeng/Github-Thinkman/Assignment/src/student_input2.txt");

        System.out.println("\nAll students:");
        MyLinkedList<Student> temp = lstStudent.clone();
        Student student = temp.removeFirst();
        while (student != null) {
            System.out.println(student);

            student = temp.removeFirst();
        }

        final double GPA = 3.3;
        System.out.println("\nAll students with GPA higher than " + GPA);
        int numStudents = higherThan(lstStudent.clone(), GPA);
        System.out.println("\nNumber of students with GPA higher than " + GPA + " is: " + numStudents);
    }
}
