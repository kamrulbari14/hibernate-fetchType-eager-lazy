package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteCourseDemo {

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
			
			//get course with Id
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
			System.out.println("Deleting: " +tempCourse);
	
			//delete course
			session.delete(tempCourse);
			
			
			session.getTransaction().commit();
			System.out.println("All done!");
		}

		finally {
			session.close();
			factory.close();
		}
	}

}
