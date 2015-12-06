package systemlogic.businesslogicservices.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

import datasources.localdatabaseservice.entity.Goal;
import systemlogic.businesslogicservices.dto.GoalDto;

public class GoalDelegate {

	public final static List<String> myMappingFiles = Arrays.asList("dozerMappings.xml");

	public static Goal MeasureHistory(GoalDto bean) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (Goal) mapper.map(bean, Goal.class);
	}

	public static GoalDto mapFromMeasure(
			Goal measure) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (GoalDto) mapper.map(measure,GoalDto.class);
	}

	public static List<GoalDto> mapFromMeasureList(
			List<Goal> measurel) {
		ArrayList<GoalDto> bl = null;
		if ((measurel != null) && (measurel.size() > 0)) {
			bl = new ArrayList<GoalDto>();
			for (Goal p : measurel) {
				bl.add(mapFromMeasure(p));
			}
		}
		return bl;
	}

}
