package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

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
			
			System.out.println("Courses: " +tempInstructor.getCourses());
			
			session.getTransaction().commit();
			System.out.println("All done!");
			
			//get the session close
			session.close();
			
			//still it will load the courses as it's saved on the memory
			System.out.println("\nNow the session is closed...\n");
			
			//Applying option 1: (get the method when session is open)
			
		
			//get the courses of the Instructor
			System.out.println("Courses: " +tempInstructor.getCourses());
		}

		finally {
			session.close();
			factory.close();
		}
	}

}
