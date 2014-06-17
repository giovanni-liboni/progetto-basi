package classiCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.PrenotazioneBean;
import database.DBMS;

public class EmettiBiglietto implements Command {

	
	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {		
		
		String numPrenotazione = "";
		
		if ( request.getParameter("numPrenotazione") != null )
			numPrenotazione = request.getParameter("numPrenotazione");
		
		request.setAttribute("numPrenotazione", numPrenotazione);
		
		return request.getRequestDispatcher("../emettiBigliettoPage.jsp");
	}
}
