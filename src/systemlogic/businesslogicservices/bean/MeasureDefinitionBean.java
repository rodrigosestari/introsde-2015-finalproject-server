package systemlogic.businesslogicservices.bean;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import datasources.localdatabaseservice.dao.LifeCoachDao;
import datasources.localdatabaseservice.entity.MeasureDefinition;
import systemlogic.businesslogicservices.convert.MeasureDefinitionDelegate;
import systemlogic.businesslogicservices.convert.MeasureHistoryDelegate;
import systemlogic.businesslogicservices.dto.MeasureDefinitionDto;

public class MeasureDefinitionBean {
	// database operations
	public static MeasureDefinition getMeasureDefinitionById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		MeasureDefinition p = em.find(MeasureDefinition.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	/**
	 * get all meausere types
	 * 
	 * @return list of MeasureDefinition
	 */
	public static List<MeasureDefinition> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<MeasureDefinition> list = em.createNamedQuery("MeasureDefinition.findAll", MeasureDefinition.class)
				.getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return list;
	}

	/**
	 * insert a new measure type
	 * 
	 * @param p
	 *            MeasureDefinition to insert
	 * @return MeasureDefinition inserted
	 */
	public static MeasureDefinitionDto insertMeasureDefinition(MeasureDefinitionDto p) {
		MeasureDefinition md  = MeasureDefinitionDelegate.mapToMeasure(p);
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(md);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

		LifeCoachDao.instance.closeConnections(em);
		p = MeasureDefinitionDelegate.mapFromMeasure(md);
		return p;
	}

	/**
	 * update a mesuare type
	 * 
	 * @param p
	 *            object MeasureDefinition to update
	 * @return object MeasureDefinition updated
	 */
	public static MeasureDefinition updateMeasureDefinition(MeasureDefinition p) {
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
	 * remove a measure type from db
	 * 
	 * @param p
	 *            object MeasureDefinition
	 */
	public static void removeMeasureDefinition(MeasureDefinition p) {
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
	 * Get a measure type from your name
	 * 
	 * @param name
	 *            measure name type
	 * @return object MeasureDefinition
	 */
	static public MeasureDefinitionDto getMeasureDefinitionByName(String name) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();

		MeasureDefinition me = em.createNamedQuery("MeasureDefinition.findbyName", MeasureDefinition.class)
				.setParameter("name", name).getSingleResult();

		LifeCoachDao.instance.closeConnections(em);
		MeasureDefinitionDto dto = MeasureDefinitionDelegate.mapFromMeasure(me);
		return dto;
	}

}
