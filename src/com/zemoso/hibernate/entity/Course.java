package com.zemoso.hibernate.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public @Data class Course {

    //define fields
    //annotate fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,
                            CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
            )
    private List<Student> students;
    //define constructors

    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }

    //add method to add reviews
    public void addReview(Review review){
        if (reviews == null){
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    public void addStudent(Student student){
        if (students == null){
            students = new ArrayList<>();
        }
        students.add(student);
    }

}
