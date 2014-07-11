package classiCommand;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DBMS;

public class RicercaVolo implements Command {
	private DBMS dbms;

	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, ServletException, IOException {
		//		SEZIONE RICERCA VOLO	
		try
		{
			dbms = new DBMS();
		}
		catch( final Exception e )
		{
			throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
		}

		ArrayList< String > partenze = dbms.getPartenze();

		request.setAttribute("partenze", partenze);
		
		return request.getRequestDispatcher("../ricercavolo.jsp");
	}
}
