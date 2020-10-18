package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

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
			//create objects
			Instructor tempInstructor =
					new Instructor("Priyo","Bithi","pb@gmail.com");
			InstructorDetail tempInstructorDetail =
					new InstructorDetail(
							"http://www.hello.com/youtube","Codding");
			
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
			factory.close();
		}
	}

}
