package systemlogic.businesslogicservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import datasources.localdatabaseservice.entity.MeasureHistory;
import datasources.localdatabaseservice.entity.Person;
import systemlogic.businesslogicservices.bean.PersonBean;

@XmlRootElement(name = "measureHistory")
public class MeasureHistoryDto implements Serializable {


	private static final long serialVersionUID = 3521996173451216932L;
	private List<MeasureDto> measure;

	public MeasureHistoryDto() {
	}

	@XmlElement(name = "measure")
	public List<MeasureDto> getMeasure() {
		return measure;
	}

	public void setMeasure(List<MeasureDto> measure) {
		this.measure = measure;
	}

	/**
	 * get a list of MeasureHistory from MeasureHistory
	 * 
	 * @param measure
	 *            object MeasureHistory
	 * @return object MeasureHistoryBean
	 * 
	 */
	public static MeasureHistoryDto getHistoryBeanFromMeasure(MeasureHistory measure) {
		ArrayList<MeasureHistory> m = new ArrayList<MeasureHistory>();
		m.add(measure);
		return getHistoryBeanFromMeasureList(m);
	}

	/**
	 * trasnform a list of MeasureHistory into MeasureHistoryBean
	 * 
	 * @param measure
	 *            list of MeasureHistory
	 * @return object MeasureHistoryBean
	 * 
	 */
	public static MeasureHistoryDto getHistoryBeanFromMeasureList(List<MeasureHistory> measure) {
		MeasureHistoryDto hp = null;
		List<MeasureDto> lmb = new ArrayList<MeasureDto>();

		if ((null != measure) && (measure.size() > 0)) {
			hp = new MeasureHistoryDto();
			for (MeasureHistory mh : measure) {
				MeasureDto mb = new MeasureDto();
				mb.setCreated(PersonBean.dateToString(mh.getCreated()));
				mb.setMid(mh.getIdMeasureHistory());
				mb.setValue(Double.parseDouble(mh.getValue()));
				lmb.add(mb);
			}
			hp.setMeasure(lmb);
		}
		return hp;
	}

	@Override
	public String toString() {
		return "MeasureHistoryBean [measure=" + measure + "]";
	}

}