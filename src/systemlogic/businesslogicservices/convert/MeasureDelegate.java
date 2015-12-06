package systemlogic.businesslogicservices.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

import datasources.localdatabaseservice.entity.MeasureHistory;
import systemlogic.businesslogicservices.dto.MeasureDto;

public class MeasureDelegate {

	public final static List<String> myMappingFiles = Arrays.asList("dozerMappings.xml");

	public static MeasureHistory MeasureHistory(MeasureDto bean) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (MeasureHistory) mapper.map(bean, MeasureHistory.class);
	}

	public static MeasureDto mapFromMeasure(
			MeasureHistory measure) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (MeasureDto) mapper.map(measure,MeasureDto.class);
	}

	public static List<MeasureDto> mapFromMeasureList(
			List<MeasureHistory> measurel) {
		ArrayList<MeasureDto> bl = null;
		if ((measurel != null) && (measurel.size() > 0)) {
			bl = new ArrayList<MeasureDto>();
			for (MeasureHistory p : measurel) {
				bl.add(mapFromMeasure(p));
			}
		}
		return bl;
	}

}
