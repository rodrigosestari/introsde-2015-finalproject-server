package systemlogic.businesslogicservices.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import datasources.localdatabaseservice.entity.Goal;
import datasources.storageservices.LifeCoachDao;
import systemlogic.businesslogicservices.convert.GoalDelegate;
import systemlogic.businesslogicservices.dto.GoalDto;
import systemlogic.businesslogicservices.dto.MeasureDefinitionDto;
import systemlogic.businesslogicservices.dto.PersonDto;

public class GoalBean {
	 
	 public enum TypeGoal {
		 DAILY,WEEKLY,MONTHLY 
		}
	 
		public static GoalDto insertGoal(GoalDto p) {
			Goal goal  = GoalDelegate.mapToGoal(p);
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {
				em.persist(goal);
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
			}

			LifeCoachDao.instance.closeConnections(em);
			p = GoalDelegate.mapFromGoal(goal);
			return p;
		}

		
		public static void removeGoal(GoalDto p) {
			Goal goal  = GoalDelegate.mapToGoal(p);
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {
				goal = em.merge(goal);
				em.remove(goal);
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
			}

			LifeCoachDao.instance.closeConnections(em);
		}
		
		public static GoalDto getGoal(int id) {			
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			GoalDto p = em.find(GoalDto.class, id);
	        LifeCoachDao.instance.closeConnections(em);
	        return p;
		}
	 
		
		public boolean createGoal(PersonDto person, MeasureDefinitionDto measure, TypeGoal type, Float value, Date start_date, Date end_date){
			GoalDto goaldto = new GoalDto();
			goaldto.setIdGoal(-1);
			goaldto.setPerson(person);
			goaldto.setMeasureDefinition(measure);
			goaldto.setType(type.toString());
			goaldto.setStart(start_date);
			goaldto.setEnd(end_date);
			goaldto = insertGoal(goaldto);
			if (goaldto.getIdGoal() > 0) 
				return true;
			return false;
			
		}
		
		public List<GoalDto> personGoals(PersonDto person){
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			List<Goal> list = em.createNamedQuery("Goal.findbyPerson", Goal.class).setParameter("person", person.getIdPerson()).getResultList();
			List<GoalDto> listDto = GoalDelegate.mapFromGoalList(list);
			LifeCoachDao.instance.closeConnections(em);
			return listDto;
		}
		
		public List<GoalDto> personTypeGoals(PersonDto person, TypeGoal type){
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			List<Goal> list = em.createNamedQuery("Goal.findbyPersonType", Goal.class).
					setParameter("person", person.getIdPerson()).
					setParameter("type", type.toString()).
					getResultList();
			List<GoalDto> listDto = GoalDelegate.mapFromGoalList(list);
			LifeCoachDao.instance.closeConnections(em);
			return listDto;
		}
}
