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
		
		VoloBean beanVolo = dbms.getVolo(codiceVolo);

		//Delego l'esecuzione della query alla classe di interazione con il DB			
		//Aggiungo il ArrayList come attributo della richiesta HTTP
		request.setAttribute("volo",beanVolo);	
		
		HttpSession session = request.getSession();
		PasseggeroBean beanPasseggero = (PasseggeroBean) session.getAttribute("pass");
		
		request.setAttribute("pass",beanPasseggero);

		//Preparo il Dispatcher 
		RequestDispatcher rd = request.getRequestDispatcher("../prenotazionePage.jsp");
		
		return rd;
	}

}
