package java_project;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Project {
	public static void main(String[] args) {
        try {
            String[] professor = {"박재영", "정진규", "최은일"};
            String[] students = {"A", "B", "C", "D", "E"};
            String[] namelecture = {"객체지향언어", "운영체제", "인공지능"};
            String[] classroom = {"혜화관", "명진관", "학림관"};

            LC1 l1 = new LC1(namelecture[0], professor[0], classroom[0], "수요일 1,2,3");
            LC2 l2 = new LC2(namelecture[1], professor[1], classroom[1], "금요일 1,2,3");
            LC3 l3 = new LC3(namelecture[2], professor[2], classroom[2], "월요일 5,6,7");

            View[] lectureList = {l1, l2, l3};

            chooseClass(lectureList, students);

            printViews(lectureList);
        } catch (Exception e) {
            System.out.println("예외가 발생했습니다: " + e.getMessage());
        }
    }
	
    private static void chooseClass(View[] lectureList, String[] students) {
    	try (Scanner scanner = new Scanner(System.in)) {
    		System.out.println("\n수강신청");
    		System.out.println("0.미신청 1.객체지향언어 2.운영제체 3.인공지능");
    		
    		for (String student : students) {
    			int chooseLecture;

                do {
                    System.out.println("\n" + student + " 학생 : ");
                    chooseLecture = scanner.nextInt() - 1;

                    if (chooseLecture == -1) {
            			System.out.println("미신청");
                    }
                    else if (chooseLecture >= 0 && chooseLecture < lectureList.length) {
                    	Lecture lecture = (Lecture) lectureList[chooseLecture];
                        lecture.overStudent(student);
                    } else {
                        System.out.println("잘못된 과목입니다. 다시 선택하세요.");
                    }
                } while (chooseLecture < -1 || chooseLecture >= lectureList.length);
            }
    	}        
    }

    private static void printViews(View[] lectureList) {
        try {
            System.out.println("\n교수 View");
            for (View lecture : lectureList) {
                lecture.profView();
            }
            System.out.print("\n");

            System.out.println("강의실 View");
            for (View lecture : lectureList) {
                lecture.classroomView();
            }
            System.out.print("\n");

            System.out.println("과목 View");
            for (View lecture : lectureList) {
                lecture.namelectureView();
            }
            System.out.print("\n");

            System.out.println("학생 View");
            for (View lecture : lectureList) {
                ((Lecture) lecture).studentView();
            }
        } catch (Exception e) {
            System.out.println("예외가 발생했습니다: " + e.getMessage());
        }
    }
}

interface View {
    void profView();

    void classroomView();

    void namelectureView();

    void studentView();
}

abstract class Lecture implements View {
    private String namelecture;
    private String professor;
    private String classroom;
    private String time;
    private List<String> students;

    public Lecture(String namelecture, String professor, String classroom, String time) {
        this.namelecture = namelecture;
        this.professor = professor;
        this.classroom = classroom;
        this.time = time;
        this.students = new ArrayList<>();
    }

    public String getNamelecture() {
        return namelecture;
    }

    public String getProfessor() {
        return professor;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getTime() {
        return time;
    }

    public List<String> getStudents() {
        return students;
    }

    protected String getStudentString() {
        return String.join(", ", students);
    }

    public boolean overStudent(String name) {
	    if (students.size() < 2) {
	        students.add(name);
	        return true;
	    } else {
	        System.out.println("신청 불가");
	        return false;
	    }
	}

    public void profView() {
        System.out.printf("%-10s%-15s%-10s%-15s%-15s\n", getProfessor() + "교수", getNamelecture(), getClassroom(), getTime(), getStudentString() + "학생");
    }

    public void classroomView() {
        System.out.printf("%-10s%-15s%-10s%-15s%-15s\n", getClassroom(), getTime(), getProfessor() + "교수", getNamelecture(), getStudentString() + "학생");
    }

    public void namelectureView() {
        System.out.printf("%-10s%-15s%-10s%-15s%-15s\n", getNamelecture(), getProfessor() + "교수", getClassroom(), getTime(), getStudentString() + "학생");
    }

    public void studentView() {
        for (String student : students) {
            System.out.printf("%-5s%-10s%-10s%-10s%-15s%-10s\n", student + "학생", getProfessor() + "교수", getNamelecture(), getClassroom(), getTime(), getStudentString() + "학생");
        }
    }
}

class LC1 extends Lecture {
    public LC1(String namelecture, String professor, String classroom, String time) {
        super(namelecture, professor, classroom, time);
    }
}

class LC2 extends Lecture {
    public LC2(String namelecture, String professor, String classroom, String time) {
        super(namelecture, professor, classroom, time);
    }
}

class LC3 extends Lecture {
    public LC3(String namelecture, String professor, String classroom, String time) {
        super(namelecture, professor, classroom, time);
    }
}