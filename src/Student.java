
public class Student {

    private String name;
    private String major;
    private double GPA;

    public Student() { }

    public Student(String name, String major, double GPA) {
        this.name = name;
        this.major = major;
        this.GPA = GPA;
    }

    public String getName() { return name; }
    public String getMajor() { return major; }
    public double getGPA() { return GPA; }

    public void setName(String name){
        this.name = name;
    }
    public void setMajor(String major) {
        this.major = major ;
    }
    public void setGPA(double GPA){
        this.GPA = GPA;
    }

    public String toString() {
        String s = "\tName = " + name +
                "\tMajor = " + major +
                "\tGPA = " + GPA;
        return s;
    }


}
