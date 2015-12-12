package systemlogic.processcentricservices.soap.sw;

import java.util.List;

import javax.jws.WebService;

import systemlogic.businesslogicservices.dto.MeasureHistoryDto;
import systemlogic.businesslogicservices.dto.PersonDto;
import systemlogic.businesslogicservices.view.MeasureListHistoryView;


//Service Implementation

@WebService(endpointInterface = "systemlogic.processcentricservices.soap.sw.Health", serviceName = "HealthService")
public class HealthImpl implements Health {

	@Override
	public PersonDto readPerson(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonDto> getPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addPerson(PersonDto person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updatePerson(PersonDto person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deletePerson(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MeasureListHistoryView readPersonHistory(Long id, String measureType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> readMeasureTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeasureHistoryDto readPersonMeasure(Long id, String measureType, Long mid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long savePersonMeasure(Long id, MeasureHistoryDto m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updatePersonMeasure(Long id, MeasureHistoryDto m) {
		// TODO Auto-generated method stub
		return null;
	}


}
