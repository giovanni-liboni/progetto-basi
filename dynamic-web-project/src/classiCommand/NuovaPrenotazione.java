package classiCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBMS;
import bean.PasseggeroBean;
import bean.VoloBean;

public class NuovaPrenotazione implements Command {

	DBMS dbms;
	
	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		String username = "", password = "", nome = "", cognome = "", nazionalita = "",documento = "",codicevolo = "";
		boolean tessera = false;
		if ( request.getParameter("username") != null )
		{
			username = request.getParameter("username");
		}
		if ( request.getParameter("password") != null )
		{
			password = request.getParameter("password");
		}
		if ( request.getParameter("codicevolo") != null )
		{
			codicevolo = request.getParameter("codicevolo");
		}
		if ( request.getParameter("nome") != null )
		{
			nome = request.getParameter("nome");
		}
		if ( request.getParameter("cognome") != null )
		{
			cognome = request.getParameter("cognome");
		}
		if ( request.getParameter("documento") != null )
		{
			documento = request.getParameter("documento");
			documento = documento.replaceAll("\\s","");
		}
		if ( request.getParameter("nazionalita") != null )
		{
			nazionalita = request.getParameter("nazionalita");
		}
		if ( request.getParameter("tessera") != null )
		{
			if ( request.getParameter("tessera").compareTo("false") == 0)
				tessera = false;
			else if( request.getParameter("tessera").compareTo("true") == 0 )
				tessera = true;
		}
		
		try
		{
			 dbms = new DBMS();
		}
		catch( final Exception e )
		{
			throw new ServletException("Non � possibile avere una connessione ad database: " + e.getMessage() );
		}
		// INSERIRE CONTROLLI IN CASO DI INSUCCESSO E SE L'UTENTE ESISTE BISOGNA RITORNARE SULLA STESSA PAGINA CON
		// UN MESSAGGIO DI ERRORE
		// NEL CASO SIA UN UTENTE CHE HA EFFETTUTATO L'ACCESSO ALLORA BISOGNA SOLO REGISTRARE LA NUOVA PRENOTAZIONE
		HttpSession session = request.getSession();
		if ( session.getAttribute("pass") == null )			
			dbms.newPasseggero(nome, cognome, nazionalita, documento, username, password, tessera);

		VoloBean beanVolo = dbms.getVolo(codicevolo);
		PasseggeroBean beanPasseggero = dbms.getPasseggero(documento);
		
			if ( dbms.newPrenotazione(beanVolo, beanPasseggero) )				
				request.setAttribute("status", "ok");
			
			
			return request.getRequestDispatcher("../esitoPage.jsp");
	}

}
