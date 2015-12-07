package systemlogic.processcentricservices.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

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

import systemlogic.businesslogicservices.bean.MeasureDefinitionBean;
import systemlogic.businesslogicservices.bean.MeasureHistoryBean;
import systemlogic.businesslogicservices.bean.PersonBean;
import systemlogic.businesslogicservices.convert.MeasureHistoryDelegate;
import systemlogic.businesslogicservices.dto.MeasureDefinitionDto;
import systemlogic.businesslogicservices.dto.MeasureHistoryDto;
import systemlogic.businesslogicservices.dto.PersonDto;
import systemlogic.businesslogicservices.view.MeasureHistoryView;
import systemlogic.businesslogicservices.view.MeasureListDefinitionView;
import systemlogic.businesslogicservices.view.MeasureListHistoryView;
import systemlogic.businesslogicservices.view.PeopleView;

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
		PeopleView peopleBean = null;		
		try {
			System.out.println("Getting list of people...");
			people = PersonBean.getAllBean(true);			
			if (people == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				peopleBean =  new PeopleView();
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
		List<PersonDto> people = PersonBean.getAll();
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
			pb = PersonBean.insertPersonBean(person);
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
		MeasureListHistoryView mhb = null;
		try {
			System.out.println("Getting measurement from DB with Person: " + id + " measure: " + md);
			mhb = MeasureHistoryDelegate.getHistoryBeanFromMeasureList(MeasureHistoryBean.getAllForMeasureType(id, md));
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

		MeasureListHistoryView mhb = null;
		try {
			System.out.println("Getting measurement from DB with Person: " + id + " measure: " + md + " id measure:"+mid);
			MeasureHistoryView mh = MeasureHistoryBean.getMeasureTypeById(id, md, mid);
			ArrayList<MeasureHistoryView> l = new ArrayList<MeasureHistoryView>();
			l.add(mh);
			mhb = MeasureHistoryDelegate.getHistoryBeanFromMeasureList(l);
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
	public Response newMeasure(@PathParam("personId") int id, @PathParam("measureType") String md, MeasureHistoryView mb)
			throws IOException {

		MeasureListHistoryView mhb = null;
		try {
			System.out.println("Creating new measure...");

			PersonDto p = PersonBean.getPersonById(id);
			if (null == p) {
				//if person dont found, come created it
				p = new PersonDto();
				p.setIdPerson(id);
				p = PersonBean.insertPerson(p);
			}
			MeasureDefinitionDto m = MeasureDefinitionBean.getMeasureDefinitionByName(md);
			if (null == m) {
				//if measure dont found, come created it
				m = new MeasureDefinitionDto();
				m.setMeasureName(md);
				m = MeasureDefinitionBean.insertMeasureDefinition(m);
			}

			//create new measure
			MeasureHistoryDto mh = new MeasureHistoryDto();	
			mh.setCreated(PersonBean.stringToDate(mb.getCreated()));
			mh.setMid(mid);
			setMeasureDefinition(m);
			mh.setPerson(p);
			mh.setValue(String.valueOf(mb.getValue()));

			mh = MeasureHistoryDto.insertMeasure(mh);
			if (mh != null) {
				mhb = new MeasureListHistoryView();
				mhb.setMeasure(MeasureHistoryDto.getBeanAllForMeasureType(id, md));
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