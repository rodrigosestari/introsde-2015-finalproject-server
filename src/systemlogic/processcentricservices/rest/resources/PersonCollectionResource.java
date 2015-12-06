package systemlogic.processcentricservices.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import datasources.localdatabaseservice.entity.MeasureDefinition;
import datasources.localdatabaseservice.entity.MeasureHistory;
import datasources.localdatabaseservice.entity.Person;
import systemlogic.businesslogicservices.dto.MeasureDto;
import systemlogic.businesslogicservices.dto.MeasureHistoryDto;
import systemlogic.businesslogicservices.dto.PeopleDto;
import systemlogic.businesslogicservices.dto.PersonDto;

@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/person")
public class PersonCollectionResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// will work only inside a Java EE application
	@PersistenceUnit(unitName = "introsde-jpa")
	EntityManager entityManager;

	// will work only inside a Java EEapplication
	@PersistenceContext(unitName = "introsde-jpa", type = PersistenceContextType.TRANSACTION)
	private EntityManagerFactory entityManagerFactory;

	// Return the list of people to the user in the browser
	/**
	 * Request #1: GET /person should list all the people (see above Person
	 * model to know what data to return here) in your database (wrapped under
	 * the root element "people")
	 * 
	 * @return
	 * List of PersonBean in DB
	 */
	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response  getPersonsBrowser() {
		List<PersonDto> people = null;
		PeopleDto peopleBean = null;		
		try {
			System.out.println("Getting list of people...");
			people = Person.getAllBean(true);			
			if (people == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				peopleBean =  new PeopleDto();
				peopleBean.setPerson(people);
				return Response.ok().entity(peopleBean).build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}

	}

	// retuns the number of people
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		System.out.println("Getting count...");
		List<Person> people = Person.getAll();
		int count = people.size();
		return String.valueOf(count);
	}

	/**
	 * Request #4: POST /person should create a new person and return the newly
	 * created person with its assigned id (if a health profile is included,
	 * create also those measurements for the new person).
	 * 
	 * @param person
	 * a PersonBean to insert in DB
	 * @return
	 * the PersonBean created
	 * @throws IOException
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response newPerson(PersonDto person) throws IOException {
		PersonDto pb = null;
		try {
			System.out.println("Creating new person...");
			pb = Person.insertPersonBean(person);
			if (pb == null) {
				return Response.noContent().build();
			} else {
				return Response.ok().entity(pb).build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}

	}

	// Defines that the next path parameter after the base url is
	// treated as a parameter and passed to the PersonResources
	// Allows to type http://localhost:599/base_url/1
	// 1 will be treaded as parameter todo and passed to PersonResource
	@Path("{personId}")
	public PersonResource getPerson(@PathParam("personId") int id) {
		return new PersonResource(uriInfo, request, id);
	}

	/**
	 * Request #6: GET /person/{id}/{measureType} should return the list of
	 * values (the history) of {measureType} (e.g. weight) for person identified
	 * by {id}
	 * 
	 * @param id
	 * id of person
	 * @param md
	 * measure type
	 * @return
	 * MeasureHistoryBean values
	 */
	@GET
	@Path("{personId}/{measureType}")
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getMeasurement(@PathParam("personId") int id, @PathParam("measureType") String md) {
		MeasureHistoryDto mhb = null;
		try {
			System.out.println("Getting measurement from DB with Person: " + id + " measure: " + md);
			mhb = MeasureHistoryDto.getHistoryBeanFromMeasureList(MeasureHistory.getAllForMeasureType(id, md));
			if (mhb == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.ok().entity(mhb).build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	/**
	 * Request #7: GET /person/{id}/{measureType}/{mid} should return the value
	 * of {measureType} (e.g. weight) identified by {mid} for person identified
	 * by {id}
	 * 
	 * @param id
	 * id person
	 * @param md
	 * measure type
	 * @param mid
	 * id measure
	 * @return
	 * MeasureHistoryBean values
	 */
	@GET
	@Path("{personId}/{measureType}/{mid}")
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getMeasurementById(@PathParam("personId") int id, @PathParam("measureType") String md,
			@PathParam("mid") int mid) {

		MeasureHistoryDto mhb = null;
		try {
			System.out.println("Getting measurement from DB with Person: " + id + " measure: " + md + " id measure:"+mid);
			MeasureHistory mh = MeasureHistory.getMeasureTypeById(id, md, mid);
			ArrayList<MeasureHistory> l = new ArrayList<MeasureHistory>();
			l.add(mh);
			mhb = MeasureHistoryDto.getHistoryBeanFromMeasureList(l);
			if (mhb == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.ok().entity(mhb).build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	/**
	 * Request #8: POST /person/{id}/{measureType} should save a new value for
	 * the {measureType} (e.g. weight) of person identified by {id} and archive
	 * the old value in the history
	 * 
	 * @param id
	 * id person
	 * @param md
	 * measure type
	 * @param mb
	 * new MeasureBean
	 * @return
	 * the MeasureBean created
	 * @throws IOException
	 */
	@POST
	@Path("{personId}/{measureType}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response newMeasure(@PathParam("personId") int id, @PathParam("measureType") String md, MeasureDto mb)
			throws IOException {

		MeasureHistoryDto mhb = null;
		try {
			System.out.println("Creating new measure...");

			Person p = Person.getPersonById(id);
			if (null == p) {
				//if person dont found, come created it
				p = new Person();
				p.setIdPerson(id);
				p = Person.insertPerson(p);
			}
			MeasureDefinition m = MeasureDefinition.getMeasureDefinitionByName(md);
			if (null == m) {
				//if measure dont found, come created it
				m = new MeasureDefinition();
				m.setMeasureName(md);
				m = MeasureDefinition.insertMeasureDefinition(m);
			}

			//create new measure
			MeasureHistory mh = new MeasureHistory();
			mh.setCreated(Person.stringToDate(mb.getCreated()));
			mh.setMeasureDefinition(m);
			mh.setPerson(p);
			mh.setValue(String.valueOf(mb.getValue()));

			mh = MeasureHistory.insertMeasure(mh);
			if (mh != null) {
				mhb = new MeasureHistoryDto();
				mhb.setMeasure(MeasureHistory.getBeanAllForMeasureType(id, md));
			}

			if (mhb == null) {
				return Response.noContent().build();
			} else {
				return Response.ok().entity(mhb).build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

}