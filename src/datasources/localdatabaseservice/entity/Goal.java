package datasources.localdatabaseservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Goal")
public class Goal  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3253485616153492944L;

	@Id
	@GeneratedValue(generator = "sqlite_goal")
	@TableGenerator(name = "sqlite_goal", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Goal")
	@Column(name = "id_goal")
	private int idMeasureHistory;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateStart")
	private Date start;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateEnd")
	private Date end;


	@Column(name = "type")
	private String type;
	
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

	public int getIdMeasureHistory() {
		return idMeasureHistory;
	}

	public void setIdMeasureHistory(int idMeasureHistory) {
		this.idMeasureHistory = idMeasureHistory;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	public String toString() {
		return "Goal [idMeasureHistory=" + idMeasureHistory + ", start=" + start + ", end=" + end + ", type=" + type
				+ ", value=" + value + ", measureDefinition=" + measureDefinition + ", person=" + person + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMeasureHistory;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Goal other = (Goal) obj;
		if (idMeasureHistory != other.idMeasureHistory)
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	

}
