package com.rental.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mem.model.Mem;

@Repository
public interface RentalOrderRepository  extends JpaRepository<RentalOrder, Integer>{
	List<RentalOrder>findRentalOrderByResult(String result);

	
}
