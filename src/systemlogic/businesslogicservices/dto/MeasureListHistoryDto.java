package systemlogic.businesslogicservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import datasources.localdatabaseservice.entity.MeasureHistory;
import systemlogic.businesslogicservices.bean.MeasureHistoryBean;
import systemlogic.businesslogicservices.convert.MeasureHistoryDelegate;

@XmlRootElement(name = "measureHistory")
public class MeasureListHistoryDto implements Serializable {


	private static final long serialVersionUID = 3521996173451216932L;
	private List<MeasureHistoryDto> measure;

	public MeasureListHistoryDto() {
	}

	@XmlElement(name = "measure")
	public List<MeasureHistoryDto> getMeasure() {
		return measure;
	}

	public void setMeasure(List<MeasureHistoryDto> measure) {
		this.measure = measure;
	}


	
	@Override
	public String toString() {
		return "MeasureHistoryBean [measure=" + measure + "]";
	}

}