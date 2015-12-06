package systemlogic.businesslogicservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import datasources.localdatabaseservice.entity.MeasureDefinition;
import systemlogic.businesslogicservices.bean.MeasureDefinitionBean;

@XmlRootElement(name = "measureTypes")
public class MeasureDefinitionDto implements Serializable{


	private static final long serialVersionUID = -6959456534581259572L;
	private List<String> measureType = new ArrayList<String>();

	@XmlElement(name = "measureType")
	public List<String> getMeasureType() {
		return measureType;
	}

	public void setMeasureType(List<String> measureType) {
		this.measureType = measureType;
	}

	public MeasureDefinitionDto() {

	}

	/**
	 * get all measure names
	 * @return
	 * list  of String 
	 */
	public static List<String> getAll() {
		List<String> mtl = null;
		List<MeasureDefinition> mdl = MeasureDefinitionBean.getAll();
		if ((null != mdl) && (mdl.size() > 0)) {
			mtl = new ArrayList<String>();
			for (MeasureDefinition m : MeasureDefinitionBean.getAll()) {
				mtl.add(m.getMeasureName());
			}
		}
		return mtl;
	}

	@Override
	public String toString() {
		return "MeasureDefinitionBean [measureType=" + measureType + "]";
	}

}
