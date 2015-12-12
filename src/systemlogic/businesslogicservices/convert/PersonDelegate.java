package systemlogic.businesslogicservices.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;

import datasources.localdatabaseservice.entity.Person;
import systemlogic.businesslogicservices.bean.MeasureHistoryBean;
import systemlogic.businesslogicservices.dto.PersonDto;

public class PersonDelegate {

	public final static List<String> myMappingFiles = Arrays.asList("dozerMappings.xml");

	public static Person mapToPerson(
			PersonDto personbean) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (Person) mapper.map(personbean,Person.class);
	}

	public static PersonDto mapFromPerson(Person person) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		PersonDto pb =(PersonDto) mapper.map(person,PersonDto.class);
		pb.setHealthprofile(MeasureHistoryDelegate.getHealthProfileFromMeasureList(MeasureHistoryBean.getHealthMeasureHistoryOldPerson(pb.getIdPerson())));
		return pb;
	}

	public static List<PersonDto> mapFromPersonList(
			List<Person> personList) {
		List<PersonDto> result = null;
		if ((personList != null) && (personList.size() > 0)) {
			result = new ArrayList<PersonDto>();
			for (Person p : personList) {
				PersonDto pb = mapFromPerson(p);
				pb.setHealthprofile(MeasureHistoryDelegate.getHealthProfileFromMeasureList(MeasureHistoryBean.getHealthMeasureHistoryOldPerson(pb.getIdPerson())));				
				result.add(pb);
			}

		}
		return result;

	}
}
