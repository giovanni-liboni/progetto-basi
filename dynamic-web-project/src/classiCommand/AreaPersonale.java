package classiCommand;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BigliettoBean;
import bean.PasseggeroBean;
import bean.PrenotazioneBean;
import database.DBMS;

public class AreaPersonale implements Command {

	DBMS dbms;
	
	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		try
		{
			 dbms = new DBMS();
		}
		catch( final Exception e )
		{
			throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
		}
		
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		if ( session.getAttribute("pass") == null )
			rd  = request.getRequestDispatcher("../login.jsp");	
		else
		{

			PasseggeroBean beanPasseggero = (PasseggeroBean) session.getAttribute("pass");
			ArrayList<PrenotazioneBean> vipb = dbms.getPrenotazioni(beanPasseggero.getDocumento().replaceAll("\\s",""));
			ArrayList<BigliettoBean> vbb = dbms.getBiglietti(beanPasseggero.getDocumento());
						
			request.setAttribute("pass", beanPasseggero);
			request.setAttribute("prenotazioni", vipb);
			request.setAttribute("biglietti", vbb);
			
			rd = request.getRequestDispatcher("../bigliettiPage.jsp");
			
		}
		return rd;
	}
}
