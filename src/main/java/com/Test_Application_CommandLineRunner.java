package com;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import javax.persistence.ManyToOne;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mem.model.Mem;
import com.mem.model.MemRepository;

import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Mem3;



@SpringBootApplication
public class Test_Application_CommandLineRunner implements CommandLineRunner {
    
	@Autowired
	MemRepository repository ;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public static void main(String[] args) {
        SpringApplication.run(Test_Application_CommandLineRunner.class);
    }

    @Override
    public void run(String...args) throws Exception {

    	//● 新增

//		Mem mem1 = new Mem();
//		mem1.setMemAcount("asdqwe13");
//		mem1.setMemPassword("qwer1234");
//		mem1.setMemName("吳永志45678901234567890");
//		mem1.setMemGender("男");
//		mem1.setMemBirthday(java.sql.Date.valueOf("2002-01-01"));
//		mem1.setMemEmail("q54321333@qwertt.com");
//		mem1.setMemAddr("新竹縣北埔鄉隘子7鄰20號");
//		mem1.setMemMobile("0912388888");
//		mem1.setMemStatus("未驗證");
//		
//		repository.save(mem1);

		//● 修改
//		Mem mem2 = new Mem();
//		mem2.setMemId(240001);
//		mem2.setMemAcount("asdqwe13");
//		mem2.setMemPassword("qwer1234");
//		mem2.setMemName("吳永志12");
//		mem2.setMemGender("男");
//		mem2.setMemBirthday(java.sql.Date.valueOf("2002-01-01"));
//		mem2.setMemEmail("qwe123@qwertt.com");
//		mem2.setMemAddr("新竹縣北埔鄉隘子7鄰20號");
//		mem2.setMemMobile("0912345678");
//		mem2.setMemStatus("未驗證");
//		
//		repository.save(mem2);
		
		

    	//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	Optional<Mem> optional = repository.findById(240001);
//    	Mem mem3 = optional.get();
//		System.out.print(mem3.getMemId() + ",");
//		System.out.print(mem3.getMemAcount() + ",");
//		System.out.print(mem3.getMemName() + ",");
//		System.out.print(mem3.getMemBirthday() + ",");
//		System.out.print(mem3.getMemEmail() + ",");
//		System.out.print(mem3.getMemAddr() + ",");
//		System.out.print(mem3.getMemStatus()+ ",");
	
		
      
    	
		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	List<Mem> list = repository.findAll();
//		for (Mem aMem : list) {
//			System.out.print(aMem.getMemId() + ",");
//			System.out.print(aMem.getMemAcount() + ",");
//			System.out.print(aMem.getMemName() + ",");
//			System.out.print(aMem.getMemBirthday() + ",");
//			System.out.print(aMem.getMemEmail() + ",");
//			System.out.print(aMem.getMemAddr() + ",");
//			System.out.print(aMem.getMemStatus()+ ",");
//			System.out.println();
//		}


		//● 複合查詢-getAll(map) 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("memId", new String[] { "24000" });
//		map.put("memName", new String[] { "林" });
//		map.put("memEmail", new String[] { "q" });
//		map.put("memMobile", new String[] { "097" });
//		map.put("memStatus", new String[] { "未驗證" });
//
//		
//		List<Mem> list2 = HibernateUtil_CompositeQuery_Mem3.getAllC(map,sessionFactory.openSession());
//		for (Mem aMem : list2) {
//			System.out.print(aMem.getMemId() + ",");
//			System.out.print(aMem.getMemAcount() + ",");
//			System.out.print(aMem.getMemName() + ",");
//			System.out.print(aMem.getMemBirthday() + ",");
//			System.out.print(aMem.getMemEmail() + ",");
//			System.out.print(aMem.getMemAddr() + ",");
//			System.out.print(aMem.getMemStatus()+ ",");
//			System.out.println();
//		}
//
    }
}
