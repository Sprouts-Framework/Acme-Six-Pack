package utilities;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Gym;

public class Search {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

		// Creating the Scanner
		System.out.println("Por favor, escriba su búsqueda:");
		Scanner sc = new Scanner(System.in);
		String toSearch = sc.next();

		// Creating the EntityManagerFactory
		EntityManagerFactory entityManagerFactory;
		
		ApplicationContext dataSourceContext;
		dataSourceContext = new ClassPathXmlApplicationContext("classpath:spring/datasource.xml");
		entityManagerFactory = (EntityManagerFactory) dataSourceContext.getBean("entityManagerFactory");
		
//		entityManagerFactory = Persistence
//				.createEntityManagerFactory("Acme-Six-Pack");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
				.getFullTextEntityManager(em);
		fullTextEntityManager.createIndexer().startAndWait();
		em.getTransaction().begin();

		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder().forEntity(Gym.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword()
				.onFields("name","description","postalAddress","phoneNumber").matching(toSearch)
				.createQuery();

		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query jpaQuery = fullTextEntityManager
				.createFullTextQuery(luceneQuery, Gym.class);
		
//		Sort sort = new Sort();

		// execute search
		@SuppressWarnings("unchecked")
		List<Gym> result = jpaQuery.getResultList();

		System.out.println("**************");
		for (Object o : result)
			if (o instanceof Gym) {
				Gym i = (Gym) o;
				System.out.println("name: "+i.getName()+", postalAddress: "+i.getPostalAddress()+", description: "+i.getDescription()+", phoneNumber: "+i.getPhoneNumber()+", fee: "+i.getFee());
			}
		System.out.println("**************");

		em.getTransaction().commit();
		em.close();
	}

}
