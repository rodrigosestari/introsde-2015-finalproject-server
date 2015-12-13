package systemlogic.businesslogicservices.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import datasources.localdatabaseservice.entity.MeasureDefinition;
import datasources.localdatabaseservice.entity.MeasureHistory;
import datasources.localdatabaseservice.entity.Person;
import datasources.storageservices.LifeCoachDao;
import systemlogic.businesslogicservices.convert.MeasureDefinitionDelegate;
import systemlogic.businesslogicservices.convert.PersonDelegate;
import systemlogic.businesslogicservices.dto.MeasureDefinitionDto;
import systemlogic.businesslogicservices.dto.PersonDto;
import systemlogic.businesslogicservices.view.HealthProfileView;
import systemlogic.businesslogicservices.view.MeasureTypeView;

public class PersonBean {
	

	/**
	 * Get person thorugh id person
	 * 
	 * @param personId
	 *            id person
	 * @return object Person
	 */
	public static PersonDto getPersonById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Person p = em.find(Person.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		
		return PersonDelegate.mapFromPerson(p);
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
			 pb = getPersonById(personId);			
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
	public static List<PersonDto> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
		List<PersonDto> listDto = PersonDelegate.mapFromPersonList(list);
		LifeCoachDao.instance.closeConnections(em);
		return listDto;
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
					
					pb.setFirstname(p.getName());
					pb.setLastname(p.getLastname());
					if (lastMeasure) {
						pb.setHealthprofile(MeasureDefinitionDelegate.getHealthProfileFromMeasureList(
								MeasureHistoryBean.getHealthMeasureHistoryOldPerson(p.getIdPerson())));
					} else {
						pb.setHealthprofile(MeasureHistoryBean.getHealthProfileFromMeasure(
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
	public static PersonDto insertPerson(PersonDto p) {
		Person pe = PersonDelegate.mapToPerson(p);
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(pe);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

		LifeCoachDao.instance.closeConnections(em);
		p  = PersonDelegate.mapFromPerson(pe);
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
		
				
		pb = insertPerson(pb);
		pb.setIdPerson(pb.getIdPerson());
		try {
			HealthProfileView hp = pb.getHealthprofile();
			if ((null != hp) && (null != hp.getMeasure()) && (hp.getMeasure().size() > 0)) {
				Person person = PersonDelegate.mapToPerson(pb);
				for (MeasureTypeView mb : hp.getMeasure()) {

					MeasureHistory m = new MeasureHistory();

					m.setPerson(person);

					MeasureDefinitionDto md = MeasureDefinitionBean.getMeasureDefinitionByName(mb.getMeasure());
					if (md == null) {
						md = new MeasureDefinitionDto();
						md.setMeasureName(mb.getMeasure()); 
						
						md = MeasureDefinitionBean.insertMeasureDefinition(md);
					}
					MeasureDefinition mdEntity = MeasureDefinitionDelegate.mapToMeasure(md);
					m.setMeasureDefinition(mdEntity);

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
	public static void removePerson(PersonDto p) {
		Person pe = PersonDelegate.mapToPerson(p);
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			pe = em.merge(pe);
			em.remove(pe);
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
			//SimpleDateFormat sdf =  new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
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

