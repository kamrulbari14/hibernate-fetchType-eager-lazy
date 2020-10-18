package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
			//create objects
			Instructor tempInstructor =
					new Instructor("K","Bithi","kb@gmail.com");
			InstructorDetail tempInstructorDetail =
					new InstructorDetail(
							"http://www.hello.com/youtube","Nothing");
			
			//associate objects
			tempInstructor.setIntructorDetail(tempInstructorDetail);
			
			System.out.println("transacion started");
			session.beginTransaction();

			//save the instructor
			//Note: this will also save InstrutorDetail because of cascadeType.ALL
			System.out.println("Saving Instructor:"+tempInstructor);
			session.save(tempInstructor);
			
			session.getTransaction().commit();
			System.out.println("All done!");
		}

		finally {
			session.close();
			factory.close();
		}
	}

}
