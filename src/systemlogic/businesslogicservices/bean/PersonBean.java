package systemlogic.businesslogicservices.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import datasources.localdatabaseservice.dao.LifeCoachDao;
import datasources.localdatabaseservice.entity.MeasureDefinition;
import datasources.localdatabaseservice.entity.MeasureHistory;
import datasources.localdatabaseservice.entity.Person;
import systemlogic.businesslogicservices.dto.HealthProfileDto;
import systemlogic.businesslogicservices.dto.MeasureTypeDto;
import systemlogic.businesslogicservices.dto.PersonDto;

public class PersonBean {
	

	/**
	 * Get person thorugh id person
	 * 
	 * @param personId
	 *            id person
	 * @return object Person
	 */
	public static Person getPersonById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Person p = em.find(Person.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	/**
	 * Get person through id person
	 * 
	 * @param personId
	 *            id person
	 * @return object PersonBean
	 */
	public static PersonDto getPersonBeanById(int personId) {
		PersonDto pb = null;
		try {
			Person p = getPersonById(personId);
			if (p != null) {
				pb = new PersonDto();
				pb.setBirthdate(dateToString(p.getBirthdate()));
				pb.setFirstname(p.getName());
				pb.setLastname(p.getLastname());
				pb.setHealthprofile(HealthProfileDto
						.getHealthProfileFromMeasureList(MeasureHistoryBean.getHealthMeasureHistoryOldPerson(personId)));

				pb.setIdPerson(p.getIdPerson());
			}
		} catch (Exception e) {
			pb = null;
		}

		return pb;

	}

	/**
	 * get all person
	 * 
	 * @return list of Person
	 */
	public static List<Person> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return list;
	}

	/**
	 * Get all person from DB
	 * 
	 * @param lastMeasure
	 *            get only the last measure
	 * @return list of PersonBean
	 */
	public static List<PersonDto> getAllBean(boolean lastMeasure) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
		ArrayList<PersonDto> pbl = null;
		if ((null != list) && (list.size() > 0)) {
			pbl = new ArrayList<PersonDto>();
			for (Person p : list) {
				try {
					PersonDto pb = new PersonDto();
					pb.setBirthdate(dateToString(p.getBirthdate()));
					pb.setFirstname(p.getName());
					pb.setLastname(p.getLastname());
					if (lastMeasure) {
						pb.setHealthprofile(HealthProfileDto.getHealthProfileFromMeasureList(
								MeasureHistoryBean.getHealthMeasureHistoryOldPerson(p.getIdPerson())));
					} else {
						pb.setHealthprofile(HealthProfileDto.getHealthProfileFromMeasure(
								MeasureHistoryBean.getHealthMeasureHistoryById(p.getIdPerson())));
					}
					pb.setIdPerson(p.getIdPerson());
					pbl.add(pb);
				} catch (Exception e) {
				}

			}
		}
		LifeCoachDao.instance.closeConnections(em);
		return pbl;
	}

	/**
	 * Insert a Person in DB
	 * 
	 * @param p
	 *            Object Person to insert
	 * @return object Person inserted
	 */
	public static Person insertPerson(Person p) {
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
	 * Insert a PersonBean in DB
	 * 
	 * @param pb
	 *            Object PersonBean to insert
	 * @return object PersonBean inserted
	 */
	public static PersonDto insertPersonBean(PersonDto pb) {
		Person p = new Person();

		p.setBirthdate(stringToDate(pb.getBirthdate()));
		p.setLastname(pb.getLastname());
		p.setName(pb.getFirstname());
		p = insertPerson(p);
		pb.setIdPerson(p.getIdPerson());
		try {
			HealthProfileDto hp = pb.getHealthprofile();
			if ((null != hp) && (null != hp.getMeasure()) && (hp.getMeasure().size() > 0)) {
				for (MeasureTypeDto mb : hp.getMeasure()) {

					MeasureHistory m = new MeasureHistory();
					m.setPerson(p);

					MeasureDefinition md = MeasureDefinitionBean.getMeasureDefinitionByName(mb.getMeasure());
					if (md == null) {
						md = new MeasureDefinition();
						md.setMeasureName(mb.getMeasure());
						md = MeasureDefinitionBean.insertMeasureDefinition(md);
					}
					m.setMeasureDefinition(md);

					m.setCreated(new Date());
					m.setValue(String.valueOf(mb.getValue()));
					MeasureHistoryBean.insertHealthMeasureHistory(m);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pb;

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

