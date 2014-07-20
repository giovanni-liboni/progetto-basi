package classiCommand;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Biglietto;
import bean.Passeggero;
import bean.Prenotazione;
import database.DBMS;

public class Login implements Command {

	RequestDispatcher rd = null;
	DBMS dbms;
	
	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		String username = null;
		String password = null;

		if ( request.getParameter("username") != null )
		{
			username = request.getParameter("username");
		}
		if ( request.getParameter("password") != null )
		{
			password = request.getParameter("password");
		}

		try
		{
			dbms = new DBMS();
		}
		catch( final Exception e )
		{
			throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
		}
		
		
		if( username != null && password != null )
		{
			try {
				if( dbms.isLogin(username, password))
				{
					
					// PASSO 
					Passeggero beanPasseggero = dbms.getPasseggeroFromLogin(username);
					request.setAttribute("pass", beanPasseggero);
					
					// APRO UNA NUOVA SESSIONE
					HttpSession session = request.getSession();
					
					ArrayList<Prenotazione> vipb = dbms.getPrenotazioni(beanPasseggero.getDocumento().replaceAll("\\s",""));
					ArrayList<Biglietto> vbb = dbms.getBiglietti(beanPasseggero.getDocumento());
					
					session.setAttribute("pass", beanPasseggero);
					request.setAttribute("prenotazioni", vipb);
					request.setAttribute("biglietti", vbb);
					
					rd = request.getRequestDispatcher("../bigliettiPage.jsp");
				}
				else
				{
					rd = request.getRequestDispatcher("../errorPage.jsp");
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return rd;
	}

}
