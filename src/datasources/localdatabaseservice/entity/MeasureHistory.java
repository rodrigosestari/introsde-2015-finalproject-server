package datasources.localdatabaseservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the "HealthMeasureHistory" database table.
 * 
 */
@Entity
@Table(name = "MeasureHistory")

@NamedQueries({ @NamedQuery(name = "MeasureHistory.findAll", query = "SELECT h FROM MeasureHistory h"),
		@NamedQuery(name = "MeasureHistory.findPerson", query = "SELECT h FROM MeasureHistory h WHERE h.person.idPerson = :id "),
		@NamedQuery(name = "MeasureHistory.findOldMeasurePerson", query = "SELECT h FROM MeasureHistory h WHERE h.person.idPerson = :id GROUP BY h.measureDefinition, h.person ORDER BY h.idMeasureHistory DESC"),
		@NamedQuery(name = "MeasureHistory.findPersonTypeID", query = "SELECT h FROM MeasureHistory h WHERE h.person.idPerson = :id and h.measureDefinition.measureName = :md and h.idMeasureHistory = :idhm"),
		@NamedQuery(name = "MeasureHistory.findPersonDefinition", query = "SELECT h FROM MeasureHistory h WHERE h.person.idPerson = :id and h.measureDefinition.measureName = :md") ,
		@NamedQuery(name = "MeasureHistory.deleteImport", query = "DELETE FROM MeasureHistory WHERE idext is not null AND person.idPerson = :id") 
		})
@XmlRootElement
public class MeasureHistory implements Serializable {
	

	private static final long serialVersionUID = 3691697186849968325L;

	@Id
	@GeneratedValue(generator = "sqlite_mhistory")
	@TableGenerator(name = "sqlite_mhistory", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "MeasureHistory")
	@Column(name = "idMeasureHistory")
	private int idMeasureHistory;

	@Temporal(TemporalType.DATE)
	@Column(name = "created")
	private Date created;

	@Column(name = "value")
	private String value;

	@ManyToOne
	@JoinColumn(name = "idMeasureDef", referencedColumnName = "idMeasureDef")
	private MeasureDefinition measureDefinition;

	// notice that we haven't included a reference to the history in Person
	// this means that we don't have to make this attribute XmlTransient
	@ManyToOne
	@JoinColumn(name = "idPerson", referencedColumnName = "idPerson")
	private Person person;

	@Column(name = "id_ext", nullable=true)
	private String idext = null;
	
	
	public String getIdext() {
		return idext;
	}

	public void setIdext(String idext) {
		this.idext = idext;
	}

	public int getIdMeasureHistory() {
		return idMeasureHistory;
	}

	public void setIdMeasureHistory(int idMeasureHistory) {
		this.idMeasureHistory = idMeasureHistory;
	}

	public Date getCreated() {

		return created;
	}

	public void setCreated(Date created) {

		this.created = created;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MeasureDefinition getMeasureDefinition() {
		return measureDefinition;
	}

	public void setMeasureDefinition(MeasureDefinition measureDefinition) {
		this.measureDefinition = measureDefinition;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMeasureHistory;
		result = prime * result + ((measureDefinition == null) ? 0 : measureDefinition.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
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
		MeasureHistory other = (MeasureHistory) obj;
		if (idMeasureHistory != other.idMeasureHistory)
			return false;
		if (measureDefinition == null) {
			if (other.measureDefinition != null)
				return false;
		} else if (!measureDefinition.equals(other.measureDefinition))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}

}
