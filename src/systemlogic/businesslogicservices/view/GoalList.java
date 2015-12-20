package systemlogic.businesslogicservices.view;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "goals")
public class GoalList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1134609521749657026L;

	@XmlElement(name = "goal")
	public List<GoalView> getGoals() {
		return goals;
	}

	public void setGoals(List<GoalView> goals) {
		this.goals = goals;
	}

	private List<GoalView> goals;

	@Override
	public String toString() {
		return "GoalList [goals=" + goals + "]";
	}
	
	

}
