package systemlogic.businesslogicservices.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "goal")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoalDto implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6546071085851753736L;

	private int idGoal;

	private Date start;

	private Date end;

	private String type;

	private String value;

	private MeasureDefinitionDto measureDefinition;

	private PersonDto person;
	
	private String signal;

	public int getIdGoal() {
		return idGoal;
	}

	public void setIdGoal(int idGoal) {
		this.idGoal = idGoal;
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

	public MeasureDefinitionDto getMeasureDefinition() {
		return measureDefinition;
	}

	public void setMeasureDefinition(MeasureDefinitionDto measureDefinition) {
		this.measureDefinition = measureDefinition;
	}

	public PersonDto getPerson() {
		return person;
	}

	public void setPerson(PersonDto person) {
		this.person = person;
	}

	public String getSignal() {
		return signal;
	}

	public void setSignal(String signal) {
		this.signal = signal;
	}

	@Override
	public String toString() {
		return "GoalDto [idGoal=" + idGoal + ", start=" + start + ", end=" + end + ", type=" + type + ", value=" + value
				+ ", measureDefinition=" + measureDefinition + ", person=" + person + ", signal=" + signal + "]";
	}

	
	
	
}
