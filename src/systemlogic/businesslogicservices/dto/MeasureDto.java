package systemlogic.businesslogicservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import datasources.localdatabaseservice.entity.MeasureHistory;

import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "measure")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "mid", "value", "created" })
public class MeasureDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9163938455236472286L;

	private Integer mid;

	private double value;

	private String created;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
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
	
	
	@Override
	public String toString() {
		return "MeasureHistoryBean [mid=" + mid + ", value=" + value + ", created=" + created + "]";
	}

}