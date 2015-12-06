package systemlogic.businesslogicservices.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

import datasources.localdatabaseservice.entity.MeasureDefinition;
import datasources.localdatabaseservice.entity.MeasureHistory;
import datasources.localdatabaseservice.entity.Person;
import systemlogic.businesslogicservices.dto.HealthProfileDto;
import systemlogic.businesslogicservices.dto.MeasureDefinitionDto;
import systemlogic.businesslogicservices.dto.MeasureTypeDto;
import systemlogic.businesslogicservices.dto.PersonDto;

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

	public static MeasureDefinition mapToMeasure(
			MeasureDefinitionDto personbean) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (MeasureDefinition) mapper.map(personbean,MeasureDefinition.class);
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
	
	public static HealthProfileDto getHealthProfileFromMeasureList(List<MeasureHistory> measure) {
		HealthProfileDto hp = null;
		List<MeasureTypeDto> lmb = new ArrayList<MeasureTypeDto>();

		if ((null != measure) && (measure.size() > 0)) {
			hp = new HealthProfileDto();
			for (MeasureHistory mh : measure) {
				MeasureTypeDto mb = new MeasureTypeDto();
				mb.setMeasure(mh.getMeasureDefinition().getMeasureName());
				mb.setValue(Double.parseDouble(mh.getValue()));
				lmb.add(mb);
			}
			hp.setMeasure(lmb);
		}
		return hp;
	}
	

}
