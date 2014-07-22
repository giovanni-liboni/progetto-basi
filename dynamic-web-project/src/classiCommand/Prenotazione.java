package classiCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBMS;
import bean.PasseggeroBean;
import bean.VoloBean;

public class Prenotazione implements Command {

	DBMS dbms;
	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		String codiceVolo = null;
		
		/*
		 * Ottengo i parametri per la ricerca
		 */
		if ( request.getParameter("codiceVolo") != null )
		{
			codiceVolo = request.getParameter("codiceVolo");
		}

		try
		{
			dbms = new DBMS();
		}
		catch( final Exception e )
		{
			throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
		}
		
		// Richiedo al DBMS il volo con il codice specificato
		VoloBean beanVolo = dbms.getVolo(codiceVolo);

		// Aggiungo l'attributo volo
		request.setAttribute("volo",beanVolo);	
		
		// Recupero la sessione
		HttpSession session = request.getSession();
		
		// Recupero l'attributo "pass" dalla sessione e lo salvo in un oggeto di tipo Passeggero
		PasseggeroBean beanPasseggero = (PasseggeroBean) session.getAttribute("pass");
		
		// Aggiungo l'attributo pass 
		request.setAttribute("pass",beanPasseggero);

		//Preparo il Dispatcher per passare il controllo alla jsp
		RequestDispatcher rd = request.getRequestDispatcher("../prenotazionePage.jsp");
		
		return rd;
	}

}
