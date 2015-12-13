package systemlogic.processcentricservices.rest.resources;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import systemlogic.businesslogicservices.bean.MeasureDefinitionBean;
import systemlogic.businesslogicservices.view.MeasureListDefinitionView;

@Stateless
@LocalBean
@Path("/measureTypes")
public class MeasureDefinitionResource {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	
	/**
	 * Request #9: GET /measureTypes should return the list of measures your
	 * model supports in the following formats:
	 * 
	 * @return
	 * list of measure types
	 */
	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getMeasureTypesXml() {

		MeasureListDefinitionView mdb = null;
		try {
			System.out.println("Getting list of measures type...");
			
			mdb = new MeasureListDefinitionView();			
			mdb.setMeasureType(MeasureDefinitionBean.getAllDefinition());
			
			if (null == mdb.getMeasureType() ) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.ok().entity(mdb).build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
}