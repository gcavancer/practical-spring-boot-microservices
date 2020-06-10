package co.currere.service;

import co.currere.dao.NumberGuessDao;
import co.currere.dao.NumberGuessDaoImpl;
import co.currere.domain.Win;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/service")
public class NumberGuessService {

	static NumberGuessDao dao = new NumberGuessDaoImpl();
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response registerWin(Win win) {
		System.out.println("/registerWin: " + win);
		return Response.ok().entity(dao.registerWin(win)).build();
	}
	
	@GET
	@Path("/average")
	@Produces({ MediaType.TEXT_PLAIN })
	public int getAverageGuesses() {
		System.out.println("/getAverageGuesses()");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return dao.getAverageGuesses();
	}
}