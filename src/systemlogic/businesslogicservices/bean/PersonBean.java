package systemlogic.businesslogicservices.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import datasources.localdatabaseservice.dao.LifeCoachDao;
import datasources.localdatabaseservice.entity.MeasureHistory;
import datasources.localdatabaseservice.entity.Person;
import systemlogic.businesslogicservices.dto.MeasureDto;
import systemlogic.businesslogicservices.dto.PersonDto;

public class PersonBean {
	
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
	public static List<MeasureHistory> getAllForMeasureType(int id, String md) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<MeasureHistory> list = em.createNamedQuery("MeasureHistory.findPersonDefinition", MeasureHistory.class)
				.setParameter("id", id).setParameter("md", md).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return list;

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
	public static List<MeasureDto> getBeanAllForMeasureType(int id, String md) {
		List<MeasureHistory> mhl = getAllForMeasureType(id, md);
		ArrayList<MeasureDto> mbl = new ArrayList<MeasureDto>();

		for (MeasureHistory mh : mhl) {
			MeasureDto mb = new MeasureDto();
			mb.setCreated(Person.dateToString(mh.getCreated()));
			mb.setMid(mh.getIdMeasureHistory());
			mb.setValue(Double.parseDouble(mh.getValue()));
			mbl.add(mb);
		}
		return mbl;

	}

	/**
	 * insert a new measure
	 * 
	 * @param m
	 *            object MeasureHistory to insert
	 * @return object MeasureHistory inserted
	 */
	public static MeasureHistory insertMeasure(MeasureHistory m) {

		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(m);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

		LifeCoachDao.instance.closeConnections(em);

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
	public static MeasureHistory getMeasureTypeById(int id, String md, int idmh) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();

		MeasureHistory ret = em.createNamedQuery("MeasureHistory.findPersonTypeID", MeasureHistory.class)
				.setParameter("id", id).setParameter("md", md).setParameter("idhm", idmh).getSingleResult();
		LifeCoachDao.instance.closeConnections(em);
		return ret;

	}

	/**
	 * Update a PersonBean in DB
	 * 
	 * @param p
	 *            object PersonBean
	 * @return object PersonBean updated
	 */
	public static Person updatePerson(Person p) {
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
	 * Update a PersonBean in DB
	 * 
	 * @param pb
	 *            object PersonBean
	 * @return object PersonBean updated
	 */
	public static Person updatePerson(PersonDto pb) {

		Person p = new Person();

		p.setBirthdate(stringToDate(pb.getBirthdate()));
		p.setIdPerson(pb.getIdPerson());
		p.setLastname(pb.getLastname());
		p.setName(pb.getFirstname());
		return updatePerson(p);
	}

	/**
	 * Delete Person in DB
	 * 
	 * @param p
	 *            object Person
	 */
	public static void removePerson(Person p) {
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
	 * Trasform String Date in object Data
	 * 
	 * @param data
	 *            a object Date
	 * @return a String data yyyy-MM-dd
	 */
	public static Date stringToDate(String data) {
		Date dataresult = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dataresult = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataresult;
	}

	/**
	 * Trasform object Data in String Date
	 * 
	 * @param data
	 *            a object Date
	 * @return a String data yyyy-MM-dd
	 */
	public static String dateToString(Date data) {
		String dataresult = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dataresult = sdf.format(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataresult;
	}
	
}

