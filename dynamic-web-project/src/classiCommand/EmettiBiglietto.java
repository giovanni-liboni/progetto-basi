package classiCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBMS;

public class EmettiBiglietto implements Command {

	DBMS dbms = null;
	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {		
		
		String numPrenotazione = "";
		if ( request.getParameter("numPrenotazione") != null )
			numPrenotazione = request.getParameter("numPrenotazione");
		
		try
		{
			 dbms = new DBMS();
		}
		catch( final Exception e )
		{
			throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
		}
		
		
		request.setAttribute("numPrenotazione", numPrenotazione);
		
		return request.getRequestDispatcher("../emettiBigliettoPage.jsp");
	}
}
