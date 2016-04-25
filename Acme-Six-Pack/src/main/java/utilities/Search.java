package utilities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Gym;
import fulltext.FullTextConstraint;
import fulltext.FullTextCustomQuery;

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

		// entityManagerFactory = Persistence
		// .createEntityManagerFactory("Acme-Six-Pack");

		EntityManager em = entityManagerFactory.createEntityManager();
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		fullTextEntityManager.createIndexer().startAndWait();
		em.getTransaction().begin();

		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Gym.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.bool()
				.must(qb.keyword().onFields("name", "description", "postalAddress", "phoneNumber").matching(toSearch).createQuery()).createQuery();

		FullTextCustomQuery cq = new FullTextCustomQuery("id", FullTextConstraint.EQUALS, 3);
		luceneQuery = fullTextCustomQueryBuilder(cq, luceneQuery, qb);

		// .must(
		// qb.range()
		// .onField("fee")
		// .above(25.0)
		// .createQuery()).createQuery();

		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Gym.class);

		// Sort sort = new Sort();

		// execute search
		@SuppressWarnings("unchecked")
		List<Gym> result = jpaQuery.getResultList();

		System.out.println("**************");
		for (Object o : result)
			if (o instanceof Gym) {
				Gym i = (Gym) o;
				System.out.println("name: " + i.getName() + ", postalAddress: " + i.getPostalAddress() + ", description: " + i.getDescription()
						+ ", phoneNumber: " + i.getPhoneNumber() + ", fee: " + i.getFee());
			}
		System.out.println("**************");

		em.getTransaction().commit();
		em.close();
	}

	public static org.apache.lucene.search.Query fullTextCustomQueryBuilder(FullTextCustomQuery customQuery, org.apache.lucene.search.Query query,
			QueryBuilder qb) {
		org.apache.lucene.search.Query result;
		result = query;
		if (customQuery.getConstraint().equals(FullTextConstraint.EQUALS)) {
			result = qb.bool().must(result).must(qb.keyword().onField(customQuery.getField()).ignoreAnalyzer().matching(customQuery.getObject()).createQuery())
					.createQuery();

		} else if (customQuery.getConstraint().equals(FullTextConstraint.GREATER_OR_EQUALS_THAN)) {
			result = qb.bool().must(result).must(qb.range().onField(customQuery.getField()).above(customQuery.getObject()).createQuery()).createQuery();

		} else if (customQuery.getConstraint().equals(FullTextConstraint.GREATER_THAN)) {
			result = qb.bool().must(result).must(qb.range().onField(customQuery.getField()).above(customQuery.getObject()).excludeLimit().createQuery())
					.createQuery();

		} else if (customQuery.getConstraint().equals(FullTextConstraint.LOWER_THAN)) {
			result = qb.bool().must(result).must(qb.range().onField(customQuery.getField()).below(customQuery.getObject()).excludeLimit().createQuery())
					.createQuery();

		} else if (customQuery.getConstraint().equals(FullTextConstraint.LOWER_OR_EQUALS_THAN)) {
			result = qb.bool().must(result).must(qb.range().onField(customQuery.getField()).below(customQuery.getObject()).createQuery()).createQuery();
		}
		return result;
	}
}
