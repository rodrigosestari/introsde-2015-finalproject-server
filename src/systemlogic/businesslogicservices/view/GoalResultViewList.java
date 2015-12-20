package systemlogic.businesslogicservices.view;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import systemlogic.businesslogicservices.dto.GoalDto;

@XmlRootElement(name = "goalview")
public class GoalResultViewList {
	
	private List<GoalResultView> goals;

	private GoalDto goaltype;
	
	@XmlElement(name = "goals")
	public List<GoalResultView> getGoalResultList() {
		return goals;
	}

	public void setGoalResultList(List<GoalResultView> goalResultList) {
		this.goals = goalResultList;
	}

	@XmlElement(name = "goaltype")
	public GoalDto getGoal() {
		return goaltype;
	}

	public void setGoal(GoalDto goaltype) {
		this.goaltype = goaltype;
	}

	@Override
	public String toString() {
		return "GoalResultViewList [goals=" + goals + ", goaltype=" + goaltype + "]";
	}
	
	
	
}
