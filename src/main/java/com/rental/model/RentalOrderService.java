package com.rental.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mem.model.Mem;
import com.mem.model.MemRepository;
import com.rental.model.RentalOrder;

import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_RentalOrder3;

@Service("RentalOrderService")
public class RentalOrderService {
	
	@Autowired
	RentalOrderRepository repository;
	
	@Autowired
	MemRepository memRepository;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void addRentalOrder(RentalOrder rentalOrder) {
		repository.save(rentalOrder);
    }

    public void updateRentalOrder(RentalOrder rentalOrder) {
    	repository.save(rentalOrder);
    }



    public RentalOrder getRentalOrderById(Integer rentalId) {
    	Optional<RentalOrder> optional =repository.findById(rentalId);
    	return optional.orElse(null);
    }

   
    
    public List<RentalOrder> getAllRentalOrder() {
        return repository.findAll();
    }


    
    public List<RentalOrder> getAllRentalOrder(Map<String, String[]> map){
    	return HibernateUtil_CompositeQuery_RentalOrder3.getAllC(map, sessionFactory.openSession());
    }
    
    public List<RentalOrder> getRentalOrderByResult(String result){
    	return repository.findRentalOrderByResult(result);
    } 
    
    public List<RentalOrder> getRentalOrderByMemId(Integer memId){
    	Mem mem = memRepository.findByMemId(memId);
    	return repository.findRentalOrderByMem(mem);
    } 

    public List<RentalOrder> getRentalOrderByPaymentStatus(String paymentStatus){
    	
    	return repository.findRentalOrderByPaymentStatus(paymentStatus);
    } 
    
}
