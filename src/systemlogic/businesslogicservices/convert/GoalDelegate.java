package systemlogic.businesslogicservices.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

import datasources.localdatabaseservice.entity.Goal;
import systemlogic.businesslogicservices.dto.GoalDto;

public class GoalDelegate {

	public final static List<String> myMappingFiles = Arrays.asList("dozerMappings.xml");

	public static Goal mapToGoal(GoalDto bean) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (Goal) mapper.map(bean, Goal.class);
	}

	public static GoalDto mapFromGoal(
			Goal measure) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (GoalDto) mapper.map(measure,GoalDto.class);
	}

	public static List<GoalDto> mapFromGoalList(
			List<Goal> mm) {
		ArrayList<GoalDto> bl = null;
		if ((mm != null) && (mm.size() > 0)) {
			bl = new ArrayList<GoalDto>();
			for (Goal p : mm) {
				bl.add(mapFromGoal(p));
			}
		}
		return bl;
	}

}
