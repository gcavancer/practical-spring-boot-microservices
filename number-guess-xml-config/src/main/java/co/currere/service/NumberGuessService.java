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
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Win test() {
		return new Win(20);
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response registerWin(Win win) {
		System.out.println("/register service : " + win);
		return Response.ok().entity(dao.registerWin(win)).build();
	}
	
	@GET
	@Path("/average")
	@Produces({ MediaType.TEXT_PLAIN })
	public int getAverageGuesses() {
		System.out.println("getAverageGuesses() called.");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dao.getAverageGuesses();
	}

}
