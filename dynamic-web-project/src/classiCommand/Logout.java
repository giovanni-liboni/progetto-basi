package classiCommand;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBMS;

public class Logout implements Command {

	DBMS dbms;
	
	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
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
