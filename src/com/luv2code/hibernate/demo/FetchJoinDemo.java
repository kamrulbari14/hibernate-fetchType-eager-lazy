package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			
			// Option 2: Hibernate Query with HQL 
			//get the instructor from db
			int theId=1;
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
									+ "JOIN FETCH i.courses "
									+ "where i.id=:theInstructorId ",
							Instructor.class);
			//set the parameter on query
			query.setParameter("theInstructorId", theId);
			
			//execute query and get Instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("Instructor: " +tempInstructor);
			
			session.getTransaction().commit();
			System.out.println("All done!");
			
			//get the session close
			session.close();
			
			//still it will load the courses as it's saved on the memory
			System.out.println("\nNow the session is closed...\n");
			
			//get the courses of the Instructor
			System.out.println("Courses: " +tempInstructor.getCourses());
		}

		finally {
			session.close();
			factory.close();
		}
	}

}
