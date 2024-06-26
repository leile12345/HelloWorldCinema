package hibernate.util.CompositeQuery;

import java.util.*;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mem.model.Mem;

public class HibernateUtil_CompositeQuery_Mem3 {
	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<Mem> root, String columnName,
			String value) {
		Predicate predicate = null;

		if ("memId".equals(columnName)) {
			predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
		} else if ("memStatus".equals(columnName)) {
			predicate = builder.equal(root.get(columnName), value);
		} else if ("memName".equals(columnName) || "memEmail".equals(columnName) || "memMobile".equals(columnName)) {
			predicate = builder.like(root.get(columnName), "%" + value + "%");
		}

		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<Mem> getAllC(Map<String, String[]> map, Session session) {
		Transaction tx = null;
		List<Mem> list = null;

		try {
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Mem> criteriaQuery = builder.createQuery(Mem.class);
			Root<Mem> root = criteriaQuery.from(Mem.class);

			List<Predicate> predicateList = new ArrayList<>();

			int count = 0;
			for (String key : map.keySet()) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;

					predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value.trim()));

					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			System.out.println("predicateList.size()=" + predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[0]));
			criteriaQuery.orderBy(builder.asc(root.get("memId")));
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
