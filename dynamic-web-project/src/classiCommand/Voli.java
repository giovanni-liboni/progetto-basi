package classiCommand;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PasseggeroBean;
import bean.VoloBean;
import database.DBMS;

public class Voli implements Command {

	private String date = "";
	private String partenza = "";
	private String arrivo = "";
	private DBMS dbms;
	private RequestDispatcher rd = null;
	private ArrayList< VoloBean > bean = null;
	private PasseggeroBean beanPasseggero = null;
	
	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			ServletException, IOException {
		
		if ( request.getParameter("partenza") != null )
		{
			partenza = request.getParameter("partenza");
		}
		if ( request.getParameter("arrivo") != null )
		{
			arrivo = request.getParameter("arrivo");
		}
		if ( request.getParameter("date") != null )
		{
			date = request.getParameter("date");
		}		
		
		if ( partenza != null && arrivo != null && date != null )
		{
			try
			{
				dbms = new DBMS();
			}
			catch( final Exception e )
			{
				throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
			}
		
			// DEVO CONTROLLARE CHE I VALORI SIANO GIUSTI
			boolean legalPartenza = false;
			ArrayList< String > partenze = dbms.getPartenze();
			for( String str : partenze )
			{
				if ( str.toUpperCase().compareTo(partenza.toUpperCase()) == 0)
					{
						legalPartenza = true;
						break;
					}
			}
			boolean legalArrivo = false;
			ArrayList< String > arrivi = dbms.getArrivi();
			for( String s : arrivi )
			{
				if ( s.toUpperCase().compareTo(arrivo.toUpperCase()) == 0)
					{
						legalArrivo = true;
						break;
					}
			}
			if ( legalPartenza && legalArrivo && date != null && date != "" )
			{
				// Oggetto per l'interazione con il Database				
				// Oggetto per l'interazione con il Database
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsed;
				try {
				parsed = format.parse(date);
				} catch (Exception e) {
				format = new SimpleDateFormat("MM/dd/yyyy");
				parsed = format.parse(date);
				}
				
				java.sql.Date data_partenza = new java.sql.Date(parsed.getTime());
				
				bean = dbms.getRicercaVolo(data_partenza, partenza, arrivo);
				
				//Delego l'esecuzione della query alla classe di interazione con il DB
				//Recupero il risultato della query come bean
				
				//Aggiungo il ArrayList come attributo della richiesta HTTP
				request.setAttribute("voli",bean);
				
				//Preparo il Dispatcher
				rd = request.getRequestDispatcher("../voliPage.jsp");
			}
		}
		return rd;
	}

}
