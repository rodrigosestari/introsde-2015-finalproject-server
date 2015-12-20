package systemlogic.processcentricservices.rest.resources;

import java.io.IOException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import systemlogic.businesslogicservices.bean.GoalBean;
import systemlogic.businesslogicservices.bean.MeasureDefinitionBean;
import systemlogic.businesslogicservices.bean.PersonBean;
import systemlogic.businesslogicservices.dto.GoalDto;
import systemlogic.businesslogicservices.dto.MeasureDefinitionDto;
import systemlogic.businesslogicservices.dto.PersonDto;

@Stateless
@LocalBean
@Path("/goal")
public class GoalResource {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Path("{personId}")
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getGoalPerson(@PathParam("personId") int idperson) {
		PersonDto person = PersonBean.getPersonById(idperson);
		List<GoalDto> result = GoalBean.personGoals(person);

		try {

			if (null == result) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.ok().entity(result).build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}

	}

	@POST
	@Path("{personId}/{measureId}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createGoal(@PathParam("personId") int id, @PathParam("measureId") int idmeasure, GoalDto mb)
			throws IOException {
		PersonDto person = PersonBean.getPersonById(id);
		MeasureDefinitionDto defintion = MeasureDefinitionBean.getDefinitionById(idmeasure);
		if ((person != null) && (defintion != null)) {
			mb.setPerson(person);
			mb.setMeasureDefinition(defintion);
			mb = GoalBean.insertGoal(mb);
		}

		try {

			if (null == mb) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.ok().entity(mb).build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}

	}

	@DELETE
	@Path("{goalId}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response deleteGoal(@PathParam("goalId") int id) throws IOException {
		try {
			GoalDto dto = GoalBean.getGoal(id);
			if (dto != null) {
				GoalBean.removeGoal(dto);
			}
			return Response.status(Response.Status.MOVED_PERMANENTLY).build();

		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}