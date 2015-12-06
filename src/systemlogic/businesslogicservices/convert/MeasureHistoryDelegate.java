package systemlogic.businesslogicservices.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

import datasources.localdatabaseservice.entity.MeasureHistory;
import systemlogic.businesslogicservices.dto.MeasureHistoryDto;

public class MeasureHistoryDelegate {

	public final static List<String> myMappingFiles = Arrays.asList("dozerMappings.xml");

	public static MeasureHistory MeasureHistory(MeasureHistoryDto bean) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (MeasureHistory) mapper.map(bean, MeasureHistory.class);
	}

	public static MeasureHistoryDto mapFromMeasure(
			MeasureHistory measure) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (MeasureHistoryDto) mapper.map(measure,MeasureHistoryDto.class);
	}

	public static List<MeasureHistoryDto> mapFromMeasureList(
			List<MeasureHistory> measurel) {
		ArrayList<MeasureHistoryDto> bl = null;
		if ((measurel != null) && (measurel.size() > 0)) {
			bl = new ArrayList<MeasureHistoryDto>();
			for (MeasureHistory p : measurel) {
				bl.add(mapFromMeasure(p));
			}
		}
		return bl;
	}

}
