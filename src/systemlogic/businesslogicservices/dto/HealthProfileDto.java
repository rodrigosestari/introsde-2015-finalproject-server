package systemlogic.businesslogicservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import datasources.localdatabaseservice.entity.MeasureHistory;

@XmlRootElement
public class HealthProfileDto implements Serializable {


	private static final long serialVersionUID = -7294911323864810080L;
	private List<MeasureTypeDto> measure;

	public HealthProfileDto() {
	}

	@XmlElement(name = "measureType")
	public List<MeasureTypeDto> getMeasure() {
		return measure;
	}

	public void setMeasure(List<MeasureTypeDto> measure) {
		this.measure = measure;
	}

	/**
	 * get a MeasureHistory and put into
	 * a HealthProfile.
	 * @param measure
	 * List of MeasureHistory entity
	 * @return
	 * a HealthProfile structure
	 * 
	 */
	public static HealthProfileDto getHealthProfileFromMeasure(MeasureHistory measure) {
		ArrayList<MeasureHistory> m = new ArrayList<MeasureHistory>();
		m.add(measure);
		return getHealthProfileFromMeasureList(m);
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

	@Override
	public String toString() {
		return "HealthProfile [measure=" + measure + "]";
	}
	
	
}