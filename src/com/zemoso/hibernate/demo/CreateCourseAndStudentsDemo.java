package com.zemoso.hibernate.demo;

import com.zemoso.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCourseAndStudentsDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession(); {

        }
        try {

            //start a transaction
            session.beginTransaction();

            //create a course
            Course course1 = new Course("Pacman - How to score million points");

            Course course2 = new Course("Rubik's cube - how to speed cube");
            Course course3 = new Course("Atari 2600 - Game Development");

            //save the course
            System.out.println("Saving the course: ");
            //session.save(course1);
            session.save(course2);
            session.save(course3);

            System.out.println("Saved the course: " + course2 + course3);

            // create the students
            Student student1 = new Student("Nivesh","Syreddy","nivesh123@gmail.com");
            Student student2 = new Student("Rama","Raju","ram2233@gmail.com");
            Student student3 = new Student("Gopal","Das","gopalg@gmail.com");

            //add students to the course
//            course1.addStudent(student1);
//            course1.addStudent(student2);
//            course1.addStudent(student3);

            course2.addStudent(student1);
            course2.addStudent(student3);

            course3.addStudent(student2);
            course2.addStudent(student3);

            //save the students
            System.out.println("\nSaving the students");
            session.save(student1);
            session.save(student2);
            session.save(student3);
            System.out.println("Saved students " + course1.getStudents());

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
