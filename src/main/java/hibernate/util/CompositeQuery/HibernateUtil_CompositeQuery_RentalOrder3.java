package hibernate.util.CompositeQuery;

import java.sql.Date;
import java.util.*;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mem.model.Mem;
import com.rental.model.RentalOrder;
import com.screen.*;

public class HibernateUtil_CompositeQuery_RentalOrder3 {


    public static Predicate getPredicateForAnyDB(CriteriaBuilder builder, Root<RentalOrder> root, String columnName,
            String value) {
        Predicate predicate = null;


        if(columnName.contains("startdate") && columnName.contains("enddate")) {
			predicate = builder.between(root.get("rentalDate"),Date.valueOf("startdate"), Date.valueOf("enddate"));
		} else if ("startdate".equals(columnName)) {
            predicate = builder.greaterThanOrEqualTo(root.get("rentalDate"), Date.valueOf(value));
        } else if ("enddate".equals(columnName)) {
            predicate = builder.lessThanOrEqualTo(root.get("rentalDate"), Date.valueOf(value));
        }

      
        if ("rentalId".equals(columnName)) {
                predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
        }else if ("screenId".equals(columnName) ) {
        	 Join<RentalOrder, Screen> screenJoin = root.join("screen");
             predicate = builder.equal(screenJoin.get("screenId"), value);
        }else if ("memAcount".equals(columnName)) {
            Join<RentalOrder, Mem> memJoin = root.join("mem");
            predicate = builder.equal(memJoin.get("memAcount"), value);
        }
        
        if (predicate == null) {
            predicate = builder.isTrue(builder.literal(true));
        }

        return predicate;
    }



    // 获取租赁订单的复合查询结果
    @SuppressWarnings("unchecked")
    public static List<RentalOrder> getAllC(Map<String, String[]> map, Session session) {
		Transaction tx = null;
		List<RentalOrder> list = null;

		try {
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<RentalOrder> criteriaQuery = builder.createQuery(RentalOrder.class);
			Root<RentalOrder> root = criteriaQuery.from(RentalOrder.class);

			List<Predicate> predicateList = new ArrayList<>();

			int count = 0;
			for (String key : map.keySet()) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;

					predicateList.add(getPredicateForAnyDB(builder, root, key, value.trim()));

					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			System.out.println("predicateList.size()=" + predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[0]));
			criteriaQuery.orderBy(builder.asc(root.get("rentalId")));
			Query query = session.createQuery(criteriaQuery);
			list = query.getResultList();

			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw ex;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return list;
	}

}