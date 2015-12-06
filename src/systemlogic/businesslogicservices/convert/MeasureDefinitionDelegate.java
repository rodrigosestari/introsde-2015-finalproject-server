package systemlogic.businesslogicservices.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

import datasources.localdatabaseservice.entity.MeasureDefinition;
import systemlogic.businesslogicservices.dto.MeasureDefinitionDto;

public class MeasureDefinitionDelegate {

	public final static List<String> myMappingFiles = Arrays.asList("dozerMappings.xml");

	public static MeasureDefinition MeasureHistory(MeasureDefinitionDto bean) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (MeasureDefinition) mapper.map(bean, MeasureDefinition.class);
	}

	public static MeasureDefinitionDto mapFromMeasure(
			MeasureDefinition measure) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (MeasureDefinitionDto) mapper.map(measure,MeasureDefinitionDto.class);
	}

	public static List<MeasureDefinitionDto> mapFromMeasureList(
			List<MeasureDefinition> measurel) {
		ArrayList<MeasureDefinitionDto> bl = null;
		if ((measurel != null) && (measurel.size() > 0)) {
			bl = new ArrayList<MeasureDefinitionDto>();
			for (MeasureDefinition p : measurel) {
				bl.add(mapFromMeasure(p));
			}
		}
		return bl;
	}

}
