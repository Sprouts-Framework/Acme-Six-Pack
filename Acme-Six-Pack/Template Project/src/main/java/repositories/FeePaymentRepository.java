package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.FeePayment;

@Repository
public interface FeePaymentRepository extends PagingAndSortingRepository<FeePayment, Integer>{
	
	/* 
	 * Devuelve las feePayment activas del customer y gym que recibe como parámetros
	 * */
	@Query("select fp from FeePayment fp where fp.customer.id=?2 and fp.gym.id=?1 and fp.activationDay<=?3 and fp.inactivationDay>=?3")
	FeePayment findActiveFeePaymentByGym(int gymId, int customerId, Date today);
	
	/*Devuelve los FeePayment que se pisan con otro cuyas fechas son recibidas como parámetro, que pertenezcan
	 * al customer customerId y estén relacionadas con el gym gymId*/
	@Query("select f from FeePayment f where ((f.activationDay<=?1 and f.inactivationDay>=?1) or (f.activationDay<=?2 and f.inactivationDay>=?2) or (f.activationDay>=?1 and f.inactivationDay<=?2) or (f.activationDay<=?1 and f.inactivationDay>=?2)) and f.customer.id=?3 and f.gym.id=?4")
	Collection<FeePayment> findOverlappedFeePayments(Date activationDay, Date inactivationDay, int customerId, int gymId);
	
	/*
	 * Encuentra los feepayments del customer cuyo id es customerId que no hayan expirado aún.
	 * */
	@Query("select f from FeePayment f where f.inactivationDay>=?1 and f.customer.id = ?2 order by f.gym.name desc")
	Page<FeePayment> findNonInactiveFeePayments(Date today, int customerId, Pageable page);	
	
	/*
	 * Encuentra todas las FeePayments asociadas al gimnasio que recibe como parámetro.
	 * */
	@Query("select fp from FeePayment fp where fp.gym.id=?1")
	Page<FeePayment> findFeePaymentsByGym(int gymId, Pageable page);
	
	@Query("select fp from FeePayment fp where fp.gym.id=?1")
	Collection<FeePayment> findFeePaymentsByGym(int gymId);

}