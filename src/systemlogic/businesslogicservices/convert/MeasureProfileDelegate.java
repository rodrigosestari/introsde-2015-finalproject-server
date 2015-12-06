package systemlogic.businesslogicservices.convert;

import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

import datasources.localdatabaseservice.entity.MeasureHistory;
import systemlogic.businesslogicservices.dto.MeasureDto;

public class MeasureProfileDelegate {

	public final static List<String> myMappingFiles = Arrays.asList("dozerMappings.xml");

	public static MeasureHistory mapToMeasure(
			MeasureDto measurebean) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (MeasureHistory) mapper.map(measurebean,MeasureHistory.class);
	}

	public static MeasureDto mapFromMeasure(MeasureHistory measure) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (MeasureDto) mapper.map(measure,MeasureDto.class);
	}
}
