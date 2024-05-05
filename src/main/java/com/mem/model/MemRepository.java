package com.mem.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemRepository extends JpaRepository<Mem, Integer>{
	Mem findByMemAcount(String memAcount);
}
