package systemlogic.businesslogicservices.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "measure")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "id_ext", "value", "created","measureType" })
public class MeasureHistoryImportView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9163938455236472286L;

	private String id_ext;

	private double value;

	private String created;
	
	private String measureType;
	


	public String getMeasureType() {
		return measureType;
	}

	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}

	public String getId_ext() {
		return id_ext;
	}

	public void setId_ext(String id_ext) {
		this.id_ext = id_ext;
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


	
	@Override
	public String toString() {
		return "MeasureHistoryBean [id_ext=" + id_ext + ", value=" + value + ", created=" + created + "]";
	}

}