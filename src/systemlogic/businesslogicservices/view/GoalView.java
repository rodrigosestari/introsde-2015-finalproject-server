package systemlogic.businesslogicservices.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import systemlogic.businesslogicservices.dto.MeasureDefinitionDto;
import systemlogic.businesslogicservices.dto.PersonDto;

@XmlRootElement(name = "goal")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "idGoal", "start", "end","type","signal","value","measureDefinition","person"})
public class GoalView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7134461149845736132L;

	private int idGoal;

	private String start;

	private String end;

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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
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
		return "GoalView [idGoal=" + idGoal + ", start=" + start + ", end=" + end + ", type=" + type + ", value="
				+ value + ", measureDefinition=" + measureDefinition + ", person=" + person + ", signal=" + signal
				+ "]";
	}

	
}
