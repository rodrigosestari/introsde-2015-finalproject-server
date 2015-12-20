package systemlogic.businesslogicservices.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "goalresult")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {  "expectedValue", "value", "result", "data" })
public class GoalResultView {

	
	private String expectedValue;
	private String value;
	private String result;
	private String data;


	public String getExpectedValue() {
		return expectedValue;
	}
	public void setExpectedValue(String expectedValue) {
		this.expectedValue = expectedValue;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "GoalResultView [ expectedValue=" + expectedValue + ", value=" + value
				+ ", result=" + result + ", data=" + data + "]";
	}

	
}
