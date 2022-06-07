package com.zemoso.hibernate.demo;

import com.zemoso.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetCoursesForNivesh {
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

            //get the student nivesh from database
            int id = 4;
            Student tempStudent = session.get(Student.class,id);

            System.out.println("\nLoaded student: " + tempStudent);
            //System.out.println("Courses: " + tempStudent.getCourses());

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
