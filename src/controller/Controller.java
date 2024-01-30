package controller;

import model.Course;
import model.Student;
import model.Teacher;
import view.LoginMenu;
import view.MainMenu;
import view.StudentMenu;
import view.TeacherMenu;

public class Controller {
    private Student loggedInStudent;
    private Teacher loggedInTeacher;
    private final LoginMenu loginMenu;
    private final MainMenu mainMenu;
    private final TeacherMenu teacherMenu;
    private final StudentMenu studentMenu;

    public Controller() {
        loginMenu = new LoginMenu(this);
        mainMenu = new MainMenu(this);
        teacherMenu = new TeacherMenu(this);
        studentMenu = new StudentMenu(this);
    }

    public boolean isLoggedInUserStudent() {
        return loggedInStudent != null;
    }

    public void run() {
        if (loginMenu.run().equals("exit")) return;
        while (true) {
            switch (mainMenu.run()) {
                case "teacher menu":
                    teacherMenu.run();
                    break;
                case "student menu":
                    studentMenu.run();
                    break;
                case "logout":
                    if (loginMenu.run().equals("exit")) return;
                    break;
            }
        }
    }

    public String register(String username, String password, String role) {
        if (Teacher.getTeacherByUsername(username) != null || Student.getStudentByUsername(username) != null)
            return " username gablan vojood darad";

        if (password.length() < 5 )
            return " password za'eif ast";

        if (role.equals("teacher"))
            Teacher.addTeacher(username, password);
        else if (role.equals("student"))
            Student.addStudent(username, password);
        else
            return "role namo'tabar ast!";
        return "register movaffag bood";
    }

    public String login(String username, String password) {
        if ((loggedInTeacher = Teacher.getTeacherByUsername(username)) != null) {
            if (!loggedInTeacher.isPasswordCorrect(password)) {
                loggedInTeacher = null;
                return " password eshtebah ast!";
            }
            return "login movaffag bood";
        } else if ((loggedInStudent = Student.getStudentByUsername(username)) != null) {
            if (!loggedInStudent.isPasswordCorrect(password)) {
                loggedInStudent = null;
                return "password eshtebah ast!";
            }
            return "login movaffag bood";
        } else
            return "usere moredenazar mojood nist!";
    }

    public String logout() {
        loggedInStudent = null;
        loggedInTeacher = null;
        return "logout movaffag bood";
    }

    public String addCourse(String name, int capacity) {
        loggedInTeacher.addCourse(name, capacity);
        return "course add shod";
    }

    public String showMyCourses() {
        String showedCourses = "";
        for (Course course : this.loggedInStudent.getCourses()) {
            showedCourses+=course.toString()+'\n';
        }
        return showedCourses;
    }

    public String showAllCourses() {
        String allCourses = "";
        for (Teacher teacher : Teacher.getTeachers()) {
            for (Course course : teacher.getCourses()) {
                allCourses+=course.toString()+'\n';
            }
        }
        return allCourses;
    }

    public String takeCourse(String name) {
        Course course = Course.getCourseByName(name);
        if (course == null)
            return " course vojood nadarad";

        if (course.isFull())
            return "zarfiat por ast";

        if (loggedInStudent.getCourses().contains(course))
            return course.getName() +" gablan akhz shode ast "  ;

        loggedInStudent.takeCourse(course);
        return "course akhz shod";
    }
}