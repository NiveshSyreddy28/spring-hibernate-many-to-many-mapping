package com.zemoso.hibernate.demo;

import com.zemoso.hibernate.entity.Course;
import com.zemoso.hibernate.entity.Instructor;
import com.zemoso.hibernate.entity.InstructorDetail;
import com.zemoso.hibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCourseAndReviewsDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession(); {

        }
        try {


            //start a transaction
            session.beginTransaction();

            //create a course
            Course course1 = new Course("Pacman - How to score million points");

            //add some reviews
            course1.addReview(new Review("Great course...loved it"));
            course1.addReview(new Review("Cool Course, job well done"));
            course1.addReview(new Review("Not upto the mark"));
            course1.addReview(new Review("Should have been better"));

            //save the course and leverage the cascade all
            System.out.println("saving the course");
            System.out.println(course1);
            System.out.println(course1.getReviews());
            session.save(course1);

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
