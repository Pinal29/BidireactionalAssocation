package com.cg.hibernatecache;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class MainClass {
	public static void main(String[] args) {
		StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
	    Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();  
	    
		  SessionFactory factory=meta.getSessionFactoryBuilder().build();  
		   Session session=factory.openSession();  
		  Transaction t= session.beginTransaction(); 	
			
		Students student1 = new Students("Sam", "Disilva", "Maths");
		Students student2 = new Students("Joshua", "Brill", "Science");
		Students student3 = new Students("Peter", "Pan", "Physics");

		University university = new University("CAMBRIDGE", "ENGLAND");
		List<Students> allStudents = new ArrayList<Students>();

		student1.setUniversity(university);
		student2.setUniversity(university);
		student3.setUniversity(university);

		allStudents.add(student1);
		allStudents.add(student2);
		allStudents.add(student3);

		university.setStudents(allStudents);

		session.persist(university);// Students will be presisted automatically, thanks to CASCADE.ALL defined on students
		// property of University class.

				//List<Students> students = (List<Students>) 

			//	session.createQuery("from Student ").list();
				//for (Students s : students) {
				//System.out.println("Student Details : " + s);
				//System.out.println("Student University Details: "
				//+ s.getUniversity());
				//}

// Note that now you can also access the relationship from University to Student

				session.getTransaction().commit();
				session.close();
				System.out.println("Success");
}
		

}
