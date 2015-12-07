package systemlogic.businesslogicservices.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import datasources.localdatabaseservice.entity.MeasureHistory;
import systemlogic.businesslogicservices.convert.MeasureHistoryDelegate;

@XmlRootElement
public class HealthProfileView implements Serializable {


	private static final long serialVersionUID = -7294911323864810080L;
	private List<MeasureTypeView> measure;

	public HealthProfileView() {
	}

	@XmlElement(name = "measureType")
	public List<MeasureTypeView> getMeasure() {
		return measure;
	}

	public void setMeasure(List<MeasureTypeView> measure) {
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
	public static HealthProfileView getHealthProfileFromMeasure(MeasureHistory measure) {
		ArrayList<MeasureHistory> m = new ArrayList<MeasureHistory>();
		m.add(measure);
		return MeasureHistoryDelegate.getHealthProfileFromMeasureList(m);
	}



	@Override
	public String toString() {
		return "HealthProfile [measure=" + measure + "]";
	}
	
	
}