import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classiCommand.AjaxCheckDocumento;
import classiCommand.AjaxCheckUsername;
import classiCommand.AjaxRicercaVolo;
import classiCommand.Command;

@WebServlet("/ajax")
public class ajax extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1523222844878429701L;
	/**
     * Questo metodo risponde alle richieste HTTP di tipo GET. Elabora le richieste, impostando
     * gli eventuali attributi necessari, e ridirige la visualizzazione alle pagine jsp relative.
     *
     * @param request Oggetto HttpServletRequest dal quale ottenere informazioni circa la
     *                richiesta effettuata.
     * @param response Oggetto HttpServletResponse per l'invio delle risposte.
     */
	private HashMap<String,Command> commands = new HashMap<String,Command>();
	Command command = null;
	
	public void init( final ServletConfig config ) throws ServletException
	{
		commands.put("ricercavolo", new AjaxRicercaVolo() );
		commands.put("checkusername", new AjaxCheckUsername() );
		commands.put("checkdocumento", new AjaxCheckDocumento() );
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = null;
		String ajax = request.getParameter("ps");
		
		if( ajax != null )
		{
			try {
				commands.get(ajax).execute(request, response);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		else if( ajax == null || ajax.equals(""))
			rd = request.getRequestDispatcher("../index.jsp");

		if( rd != null )
			rd.forward(request,response);
	}
}