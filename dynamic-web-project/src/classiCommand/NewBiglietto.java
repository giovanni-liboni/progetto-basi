package classiCommand;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PrenotazioneBean;
import database.DBMS;

public class NewBiglietto implements Command {

	DBMS dbms;
	PrenotazioneBean preno = null;
	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			ServletException, IOException {
		try
		{
			 dbms = new DBMS();
		}
		catch( final Exception e )
		{
			throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
		}
		
		String numPrenotazione = "";
		if ( request.getParameter("numPrenotazione") != null )
			numPrenotazione = request.getParameter("numPrenotazione");
		
		// inserire biglietto
		if ( !numPrenotazione.equals("") )
		{
			preno = dbms.getPrenotazione(numPrenotazione);		
			dbms.newBiglietto( preno, new BigDecimal (preno.getVolo().getTratta().getDurata() * 0.123) );
			
			request.setAttribute("biglietti", dbms.getBiglietti(preno.getPasseggero().getDocumento()));
			request.setAttribute("prenotazioni", dbms.getPrenotazioni(preno.getPasseggero().getDocumento()));
			request.setAttribute("pass", dbms.getPasseggero( preno.getPasseggero().getDocumento() ));
		}
		return request.getRequestDispatcher("../bigliettiPage.jsp");
	}

}
