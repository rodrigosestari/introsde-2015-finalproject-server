package systemlogic.businesslogicservices.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

import datasources.localdatabaseservice.entity.Goal;
import systemlogic.businesslogicservices.bean.PersonBean;
import systemlogic.businesslogicservices.dto.GoalDto;
import systemlogic.businesslogicservices.view.GoalView;

public class GoalDelegate {

	public final static List<String> myMappingFiles = Arrays.asList("dozerMappings.xml");

	public static Goal mapToGoal(GoalDto bean) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (Goal) mapper.map(bean, Goal.class);
	}

	public static GoalDto mapFromGoal(Goal goal) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (GoalDto) mapper.map(goal, GoalDto.class);
	}

	public static List<GoalDto> mapFromGoalList(List<Goal> mm) {
		ArrayList<GoalDto> bl = null;
		if ((mm != null) && (mm.size() > 0)) {
			bl = new ArrayList<GoalDto>();
			for (Goal p : mm) {
				bl.add(mapFromGoal(p));
			}
		}
		return bl;
	}

	public static GoalView dtoToView(GoalDto dto) {
		GoalView result = new GoalView();
		result.setEnd(PersonBean.dateToString(dto.getEnd()));
		result.setIdGoal(dto.getIdGoal());
		result.setMeasureDefinition(dto.getMeasureDefinition());
		result.setPerson(dto.getPerson());
		result.setStart(PersonBean.dateToString(dto.getStart()));
		result.setType(dto.getType().toString());
		result.setValue(dto.getValue());
		return result;
	}

	public static List<GoalView> dtoToView(List<GoalDto> dto) {
		ArrayList<GoalView> bl = null;
		if ((dto != null) && (dto.size() > 0)) {
			bl = new ArrayList<GoalView>();
			for (GoalDto p : dto) {
				bl.add(dtoToView(p));
			}
		}
		return bl;
	}

}
