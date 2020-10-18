package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstrutorCoursesDemo {

	public static void main(String[] args) {
		//Create session factory 
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		//Create Session
		Session session = factory.getCurrentSession();
		
		try {
			
			System.out.println("transacion started");
			session.beginTransaction();
			
			//get the instructor from db
			int theId=1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Instructor: " +tempInstructor);
			
			//get the courses of the Instructor
			System.out.println("Courses: " +tempInstructor.getCourses());
			
			session.getTransaction().commit();
			System.out.println("All done!");
		}

		finally {
			session.close();
			factory.close();
		}
	}

}
