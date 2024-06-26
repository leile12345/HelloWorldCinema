package com.mem.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Mem3;

@Service("memService")
public class MemService {
	
	@Autowired
	MemRepository repository;
	
	@Autowired
	private SessionFactory sessionFactory;


	

	
	public void addMem(Mem mem) {
		repository.save(mem);
    }

    public void updateMem(Mem mem) {
    	repository.save(mem);
    }

//    public void deleteMem(Integer memId) {
//    	
//    }

    public Mem getMemById(Integer memId) {
    	Optional<Mem> optional =repository.findById(memId);
    	return optional.orElse(null);
    }

   
    
    public List<Mem> getAllMem() {
        return repository.findAll();
    }


    
    public List<Mem> getAllMem(Map<String, String[]> map){
    	return HibernateUtil_CompositeQuery_Mem3.getAllC(map,sessionFactory.openSession());
    }
    
    
    
    //==================================================
    public Mem getMemByAccount(String memAcount) {
    	return repository.findByMemAcount(memAcount);
    }
    
    public Mem getMemByEmail(String memEmail) {
    	return repository.findByMemEmail(memEmail);
    }
    
    public List<Mem> getMemByStatus(String memStatus){
    	return repository.findByMemStatus(memStatus);
    }
    
   
   
}
