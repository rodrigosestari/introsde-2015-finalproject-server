package systemlogic.businesslogicservices.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

import datasources.localdatabaseservice.entity.MeasureHistory;
import systemlogic.businesslogicservices.bean.PersonBean;
import systemlogic.businesslogicservices.dto.MeasureHistoryDto;
import systemlogic.businesslogicservices.view.HealthProfileView;
import systemlogic.businesslogicservices.view.MeasureHistoryView;
import systemlogic.businesslogicservices.view.MeasureListHistoryView;
import systemlogic.businesslogicservices.view.MeasureTypeView;

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

	public static MeasureHistory mapToMeasure(
			MeasureHistoryDto measure) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (MeasureHistory) mapper.map(measure,MeasureHistory.class);
	}
	
	
	
	public static List<MeasureHistoryView> mapViewFromMeasureList(
			List<MeasureHistory> measurel) {
		List<MeasureHistoryView> viewList = new ArrayList<MeasureHistoryView>();
		List<MeasureHistoryDto>  dtoList = mapFromMeasureList(measurel);
		for (MeasureHistoryDto dto : dtoList){
			viewList.add(dtoToView(dto));
		}
		return viewList;
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
	public static MeasureListHistoryView getHistoryBeanFromMeasureList(List<MeasureHistoryView> measure) {
		MeasureListHistoryView hp = null;
		if (measure != null){
			hp =  new MeasureListHistoryView();
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
	public static HealthProfileView getHealthProfileFromMeasureList(List<MeasureHistory> measure) {
		HealthProfileView hp = null;
		List<MeasureTypeView> lmb = new ArrayList<MeasureTypeView>();

		if ((null != measure) && (measure.size() > 0)) {
			hp = new HealthProfileView();
			for (MeasureHistory mh : measure) {
				MeasureTypeView mb = new MeasureTypeView();
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
	public static MeasureListHistoryView getHistoryBeanFromMeasure(MeasureHistoryDto measure) {		
		ArrayList<MeasureHistoryView> m = new ArrayList<MeasureHistoryView>();
		MeasureHistoryView dto = dtoToView(measure);
		m.add(dto);
		return getHistoryBeanFromMeasureList(m);
	}
	public static MeasureHistoryDto viewToDto(MeasureHistoryView view){
		MeasureHistoryDto dto = new MeasureHistoryDto();
		dto.setCreated(PersonBean.stringToDate(view.getCreated()));
		dto.setIdMeasureHistory(view.getMid());
		dto.setValue(String.valueOf(view.getValue()));
		return dto;
	}
	
	public static MeasureHistoryView dtoToView (MeasureHistoryDto dto){
		MeasureHistoryView view = new MeasureHistoryView();
		view.setCreated(PersonBean.dateToString(dto.getCreated()));	
		view.setMid(dto.getIdMeasureHistory());
		view.setValue(Double.valueOf(dto.getValue()));
		return view;
	}

}
