package systemlogic.businesslogicservices.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

import datasources.localdatabaseservice.entity.MeasureHistory;
import systemlogic.businesslogicservices.dto.HealthProfileDto;
import systemlogic.businesslogicservices.dto.MeasureHistoryDto;
import systemlogic.businesslogicservices.dto.MeasureListHistoryDto;
import systemlogic.businesslogicservices.dto.MeasureTypeDto;

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
	
	/**
	 * trasnform a list of MeasureHistory into MeasureHistoryBean
	 * 
	 * @param measure
	 *            list of MeasureHistory
	 * @return object MeasureHistoryBean
	 * 
	 */
	public static MeasureListHistoryDto getHistoryBeanFromMeasureList(List<MeasureHistoryDto> measure) {
		MeasureListHistoryDto hp = null;
		if (measure != null){
			hp =  new MeasureListHistoryDto();
			hp.setMeasure(measure);
		}
			
		
		return hp;
	}
	
	/**
	 * get a List of MeasureHistory and put into
	 * a HealthProfile.
	 * 
	 * @param measure
	 * List of MeasureHistory entity
	 * @return
	 *  a HealthProfile structure
	 */
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
	
	/**
	 * get a list of MeasureHistory from MeasureHistory
	 * 
	 * @param measure
	 *            object MeasureHistory
	 * @return object MeasureHistoryBean
	 * 
	 */
	public static MeasureListHistoryDto getHistoryBeanFromMeasure(MeasureHistory measure) {
		ArrayList<MeasureHistoryDto> m = new ArrayList<MeasureHistoryDto>();
		MeasureHistoryDto dto = mapFromMeasure(measure);
		m.add(dto);
		return getHistoryBeanFromMeasureList(m);
	}


}
