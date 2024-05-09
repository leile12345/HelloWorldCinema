package com.rental.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalOrderRepository  extends JpaRepository<RentalOrder, Integer>{
	List<RentalOrder>findRentalOrderByResult(String result);
}
