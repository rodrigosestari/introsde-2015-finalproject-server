package systemlogic.businesslogicservices.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

import datasources.localdatabaseservice.entity.MeasureHistory;
import systemlogic.businesslogicservices.view.HealthProfileView;
import systemlogic.businesslogicservices.view.MeasureHistoryView;
import systemlogic.businesslogicservices.view.MeasureListHistoryView;
import systemlogic.businesslogicservices.view.MeasureTypeView;

public class MeasureHistoryDelegate {

	public final static List<String> myMappingFiles = Arrays.asList("dozerMappings.xml");

	public static MeasureHistory MeasureHistory(MeasureHistoryView bean) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (MeasureHistory) mapper.map(bean, MeasureHistory.class);
	}

	public static MeasureHistoryView mapFromMeasure(
			MeasureHistory measure) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (MeasureHistoryView) mapper.map(measure,MeasureHistoryView.class);
	}

	public static List<MeasureHistoryView> mapFromMeasureList(
			List<MeasureHistory> measurel) {
		ArrayList<MeasureHistoryView> bl = null;
		if ((measurel != null) && (measurel.size() > 0)) {
			bl = new ArrayList<MeasureHistoryView>();
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
	public static MeasureListHistoryView getHistoryBeanFromMeasure(MeasureHistory measure) {
		ArrayList<MeasureHistoryView> m = new ArrayList<MeasureHistoryView>();
		MeasureHistoryView dto = mapFromMeasure(measure);
		m.add(dto);
		return getHistoryBeanFromMeasureList(m);
	}


}
