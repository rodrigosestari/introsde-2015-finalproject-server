package systemlogic.businesslogicservices.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import datasources.localdatabaseservice.entity.MeasureHistory;
import datasources.storageservices.LifeCoachDao;
import systemlogic.businesslogicservices.convert.MeasureHistoryDelegate;
import systemlogic.businesslogicservices.dto.MeasureHistoryDto;
import systemlogic.businesslogicservices.view.HealthProfileView;
import systemlogic.businesslogicservices.view.MeasureHistoryView;
import systemlogic.businesslogicservices.view.MeasureListHistoryView;

public class MeasureHistoryBean {

	/**
	 * get measure by id
	 * 
	 * @param id
	 *            id measure
	 * @return object MeasureHistory
	 */
	public static MeasureHistory getHealthMeasureHistoryById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		MeasureHistory p = em.find(MeasureHistory.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	/**
	 * It takes latest measures by type from person
	 * 
	 * @param idPerson
	 *            id person
	 * @return list of measure
	 */
	public static List<MeasureHistory> getHealthMeasureHistoryOldPerson(int idPerson) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();

		List<MeasureHistory> pd = em.createNamedQuery("MeasureHistory.findOldMeasurePerson", MeasureHistory.class)
				.setParameter("id", idPerson).getResultList();

		LifeCoachDao.instance.closeConnections(em);
		return pd;
	}

	/**
	 * get all MeasureHistory
	 * 
	 * @return list of MeasureHistory
	 */
	public static List<MeasureHistory> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<MeasureHistory> list = em.createNamedQuery("HealthMeasureHistory.findAll", MeasureHistory.class)
				.getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return list;
	}

	/**
	 * get all measure from person
	 * 
	 * @param idPerson
	 *            id person
	 * @return list of MeasureHistory
	 */
	public static List<MeasureHistory> getAllbyPerson(int idPerson) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<MeasureHistory> list = null;
		list = em.createNamedQuery("MeasureHistory.findPerson", MeasureHistory.class).setParameter("id", idPerson)
				.getResultList();

		LifeCoachDao.instance.closeConnections(em);
		return list;
	}

	/**
	 * insert a new MeasureHistory
	 * 
	 * @param p
	 *            object MeasureHistory to insert
	 * @return object MeasureHistory inserted
	 */
	public static MeasureHistory insertHealthMeasureHistory(MeasureHistory p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(p);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	/**
	 * update a measure
	 * 
	 * @param p
	 *            object MeasureHistory to update
	 * @return object MeasureHistory updated
	 */
	public static MeasureHistory updateHealthMeasureHistory(MeasureHistory p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			p = em.merge(p);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	/**
	 * remove a measure
	 * 
	 * @param p
	 *            object MeasureHistory to remove
	 */
	public static void removeHealthMeasureHistory(MeasureHistory p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			p = em.merge(p);
			em.remove(p);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

		LifeCoachDao.instance.closeConnections(em);
	}

	/**
	 * get all MeasureBean from person and type
	 * 
	 * @param id
	 *            id person
	 * @param md
	 *            measure type
	 * @return list of MeasureBean
	 */
	public static List<MeasureHistoryView> getAllForMeasureType(int id, String md) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<MeasureHistory> list = em.createNamedQuery("MeasureHistory.findPersonDefinition", MeasureHistory.class)
				.setParameter("id", id).setParameter("md", md).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		List<MeasureHistoryView> listDto = MeasureHistoryDelegate.mapViewFromMeasureList(list);
		return listDto;

	}

	/**
	 * get all MeasureBean from person and type
	 * 
	 * @param id
	 *            id person
	 * @param md
	 *            measure type
	 * @return list of MeasureBean
	 */
	public static List<MeasureHistoryView> getBeanAllForMeasureType(int id, String md) {
		List<MeasureHistoryView> mhl = getAllForMeasureType(id, md);
		return mhl;

	}

	/**
	 * insert a new measure
	 * 
	 * @param m
	 *            object MeasureHistory to insert
	 * @return object MeasureHistory inserted
	 */
	public static MeasureHistoryDto insertMeasure(MeasureHistoryDto m) {
		MeasureHistory entity = MeasureHistoryDelegate.mapToMeasure(m);
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

		LifeCoachDao.instance.closeConnections(em);
		m = MeasureHistoryDelegate.mapFromMeasure(entity);
		return m;

	}

	/**
	 * Get a measure from person, measure type and id measure
	 * 
	 * @param id
	 *            id person
	 * @param md
	 *            measure type
	 * @param idmh
	 *            id measure history
	 * @return
	 */
	public static MeasureHistoryView getMeasureTypeById(int id, String md, int idmh) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();

		MeasureHistory ret = em.createNamedQuery("MeasureHistory.findPersonTypeID", MeasureHistory.class)
				.setParameter("id", id).setParameter("md", md).setParameter("idhm", idmh).getSingleResult();
		LifeCoachDao.instance.closeConnections(em);
		MeasureHistoryView dto = MeasureHistoryDelegate.dtoToView(MeasureHistoryDelegate.mapFromMeasure(ret));
		return dto;

	}
	
	public static boolean deleteImport(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		int cc = -1;
		tx.begin();
		try {
			cc =em.createNamedQuery("MeasureHistory.deleteImport", MeasureHistory.class).setParameter("id", id).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		
		return 1 == cc;
	}

	public static MeasureListHistoryView getSumPersonMeasureDay(int idPerson, int idMeasure, String dataDa,
			String dataA, boolean sum) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		MeasureListHistoryView result = null;
		List<MeasureHistoryView> measure = null;

		String param ="max";
		if (sum)
			param ="sum";
		
		String sql = "select  "+param+"(value), strftime('%Y-%m-%d', datetime(created/1000, 'unixepoch')) from MeasureHistory  where idMeasureDef = "
				+ idMeasure + " and  idPerson = " + idPerson
				+ " group by strftime('%d', datetime(created/1000, 'unixepoch'))   ";
		Query query = em.createNativeQuery(sql);

		List<Object[]> list = query.getResultList();
		if (null != list) {
			result =  new MeasureListHistoryView();
			measure = new ArrayList<MeasureHistoryView>();
			for (Object[] obj : list) {
				try {
					MeasureHistoryView mh = new MeasureHistoryView();
					mh.setValue((double) obj[0]);
					mh.setCreated((String) obj[1]);
					measure.add(mh);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			result.setMeasure(measure);
		}
		LifeCoachDao.instance.closeConnections(em);

		return result;
	}
	
	
	public static MeasureListHistoryView getSumPersonMeasureMonth(int idPerson, int idMeasure, String dataDa,
			String dataA, boolean sum) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		MeasureListHistoryView result = null;
		List<MeasureHistoryView> measure = null;

		String param ="max";
		if (sum)
			param ="sum";
		
		String sql = "select  "+param+"(value), strftime('%Y-%m-01', datetime(created/1000, 'unixepoch')) from MeasureHistory  where idMeasureDef = "
				+ idMeasure + " and  idPerson = " + idPerson
				+ " group by strftime('%Y-%m', datetime(created/1000, 'unixepoch'))   ";
		Query query = em.createNativeQuery(sql);

		List<Object[]> list = query.getResultList();
		if (null != list) {
			result =  new MeasureListHistoryView();
			measure = new ArrayList<MeasureHistoryView>();
			for (Object[] obj : list) {
				try {
					MeasureHistoryView mh = new MeasureHistoryView();
					mh.setValue((double) obj[0]);
					mh.setCreated((String) obj[1]);
					measure.add(mh);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			result.setMeasure(measure);
		}
		LifeCoachDao.instance.closeConnections(em);

		return result;
	}

	/**
	 * get a MeasureHistory and put into a HealthProfaile.
	 * 
	 * @param measure
	 *            List of MeasureHistory entity
	 * @return a HealthProfile structure
	 * 
	 */
	public static HealthProfileView getHealthProfileFromMeasure(MeasureHistory measure) {
		ArrayList<MeasureHistory> m = new ArrayList<MeasureHistory>();
		m.add(measure);
		return MeasureHistoryDelegate.getHealthProfileFromMeasureList(m);
	}

}
