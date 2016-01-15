package systemlogic.processcentricservices.rest.resources;

import java.io.IOException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import systemlogic.businesslogicservices.bean.MeasureDefinitionBean;
import systemlogic.businesslogicservices.bean.MeasureHistoryBean;
import systemlogic.businesslogicservices.bean.PersonBean;
import systemlogic.businesslogicservices.dto.MeasureDefinitionDto;
import systemlogic.businesslogicservices.dto.MeasureHistoryDto;
import systemlogic.businesslogicservices.dto.PersonDto;
import systemlogic.businesslogicservices.view.MeasureHistoryImportView;
import systemlogic.businesslogicservices.view.MeasureListHistoryImportView;

@Stateless
@LocalBean
@Path("/adapter")
public class AdapterResource {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@POST
	@Path("{personId}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response newMeasureImport(@PathParam("personId") int id, MeasureListHistoryImportView mb)
			throws IOException {

		try {
			System.out.println("Creating new measure importa...");
			MeasureHistoryDto dto = null;
			PersonDto p = PersonBean.getPersonById(id);
			if ((null != p) && (null != mb)) {

				MeasureHistoryBean.deleteImport(id);

				for (MeasureHistoryImportView v : mb.getMeasure()) {

					MeasureDefinitionDto m = MeasureDefinitionBean.getMeasureDefinitionByName(v.getMeasureType().toLowerCase());
					if (null == m) {
						// if measure dont found, come created it
						m = new MeasureDefinitionDto();
						m.setMeasureName(v.getMeasureType());
						m = MeasureDefinitionBean.insertMeasureDefinition(m);
					}

					dto = new MeasureHistoryDto();
					dto.setIdext(v.getId_ext());
					dto.setMeasureDefinition(m);
					dto.setPerson(p);
					dto.setCreated(PersonBean.stringToDate(v.getCreated()));
					dto.setValue(String.valueOf(v.getValue()));

					dto =MeasureHistoryBean.insertMeasure(dto);

				}

			} else {
				return Response.noContent().build();
			}

			  UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			  builder.path("");
		      return Response.created(builder.build()).build();

		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}