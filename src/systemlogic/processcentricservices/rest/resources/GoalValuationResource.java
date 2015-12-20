package systemlogic.processcentricservices.rest.resources;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import systemlogic.businesslogicservices.bean.GoalBean;
import systemlogic.businesslogicservices.view.GoalResultViewList;

@Stateless
@LocalBean
@Path("/goalValuation")
public class GoalValuationResource {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Path("{goalId}")
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getGoalValuation(@PathParam("goalId") int idgoal) {
		GoalResultViewList dto = GoalBean.analiseGoal(idgoal);
		try {

			if (null == dto) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.ok().entity(dto).build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

}
