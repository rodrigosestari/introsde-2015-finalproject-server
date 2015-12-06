package datasources.localdatabaseservice.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import datasources.localdatabaseservice.dao.LifeCoachDao;

/**
 * The persistent class for the "MeasureDefinition" database table.
 * 
 */
@Entity
@Table(name = "MeasureDefinition")
@NamedQueries({
		@NamedQuery(name = "MeasureDefinition.findbyName", query = "SELECT h FROM MeasureDefinition h WHERE h.measureName = :name"),
		@NamedQuery(name = "MeasureDefinition.findAll", query = "SELECT h FROM MeasureDefinition h") })
@XmlRootElement
public class MeasureDefinition implements Serializable {
	

	private static final long serialVersionUID = -7320798646552225232L;

	@Id
	@GeneratedValue(generator = "sqlite_measuredef")
	@TableGenerator(name = "sqlite_measuredef", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "MeasureDefinition")
	@Column(name = "idMeasureDef")
	private int idMeasureDef;

	@Column(name = "measureName")
	private String measureName;

	public int getIdMeasureDef() {
		return this.idMeasureDef;
	}

	public void setIdMeasureDef(int idMeasureDef) {
		this.idMeasureDef = idMeasureDef;
	}

	public String getMeasureName() {
		return this.measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMeasureDef;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeasureDefinition other = (MeasureDefinition) obj;
		if (idMeasureDef != other.idMeasureDef)
			return false;
		return true;
	}


	
}
