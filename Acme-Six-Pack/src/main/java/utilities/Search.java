package utilities;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Gym;

public class Search {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Creating the Scanner
		System.out.println("Por favor, escriba su b�squeda:");
		Scanner sc = new Scanner(System.in);
		String toSearch = sc.next();

		// Creating the EntityManagerFactory
		EntityManagerFactory entityManagerFactory;
		
		ApplicationContext dataSourceContext;
		dataSourceContext = new ClassPathXmlApplicationContext("classpath:spring/datasource.xml");
		entityManagerFactory = (EntityManagerFactory) dataSourceContext.getBean("entityManagerFactory");

		
		EntityManager em = entityManagerFactory.createEntityManager();
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
				.getFullTextEntityManager(em);
		em.getTransaction().begin();

		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder().forEntity(Gym.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword()
				.onFields("name").matching(toSearch)
				.createQuery();

		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query jpaQuery = fullTextEntityManager
				.createFullTextQuery(luceneQuery, Gym.class);

		// execute search
		@SuppressWarnings("unchecked")
		List<Gym> result = jpaQuery.getResultList();

		System.out.println("**************");
		for (Object o : result)
			if (o instanceof Gym) {
				Gym i = (Gym) o;
				System.out.println("name: "+i.getName()+", postalAddress: "+i.getPostalAddress()+", description: "+i.getDescription());
			}
		System.out.println("**************");

		em.getTransaction().commit();
		em.close();
	}

}