package classiCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.text.WordUtils;

import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;

import database.DBMS;
import bean.Passeggero;
import bean.Volo;

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
			throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
		}
		// Recupero la sessione corrente
		HttpSession session = request.getSession();
		
		// Controllo che non esista un passeggero con lo stesso documento e recupero il passeggero se esiste
		Passeggero beanPasseggero = dbms.getPasseggero(documento);
		
		// Se esiste un passeggero con lo stesso documento allora deve eseguire l'accesso prima di poter prenotare
		if( beanPasseggero == null )
		{
			nome = WordUtils.capitalizeFully(nome);
			cognome = WordUtils.capitalizeFully(cognome);
			beanPasseggero = dbms.newPasseggero(nome, cognome, nazionalita, documento, username, password, tessera);
		}
		// Se esiste il passeggero ma non è loggato
		else if( beanPasseggero != null && session.getAttribute("pass") == null)
		{
			request.setAttribute("status", "loginfirst");
			return request.getRequestDispatcher("../esitoPage.jsp");
		}
		
		// Procedo con la nuova prenotazione
		
		// Recupero il volo
		Volo beanVolo = dbms.getVolo(codicevolo);
		
		if ( dbms.newPrenotazione(beanVolo, beanPasseggero) )				
				request.setAttribute("status", "ok");
		else
			request.setAttribute("status", "fail");	
		
		return request.getRequestDispatcher("../esitoPage.jsp");
	}

}
