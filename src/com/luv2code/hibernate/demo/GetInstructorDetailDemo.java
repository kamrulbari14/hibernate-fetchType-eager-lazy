package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		//Create session factory 
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		//Create Session
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("transacion started");
			session.beginTransaction();
			
			int theId=3;
			
			//create object of InstructorDetail
			
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, theId);
			
			//print instructorDetail
			
			System.out.println("tempInstructorDetail: " +tempInstructorDetail);
			
			//print associated class
			
			System.out.println("the associated class: " 
					+tempInstructorDetail.getInstructor());
			
			session.getTransaction().commit();
			System.out.println("All done!");
		}
		
		//this is for connection leak issue(exception handling)
		catch(Exception exc) {
			exc.printStackTrace();
		}

		finally {
			//get session closed
			
			session.close();
			factory.close();
		}
	}

}
