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

import classiCommand.AreaPersonale;
import classiCommand.Command;
import classiCommand.Contatti;
import classiCommand.EmettiBiglietto;
import classiCommand.Login;
import classiCommand.Logout;
import classiCommand.NewBiglietto;
import classiCommand.NuovaPrenotazione;
import classiCommand.Prenotazione;
import classiCommand.RicercaVolo;
import classiCommand.Voli;
/**
 * Questa classe gestisce le richieste HTTP
 * TODO: - LOGOUT 
 * 		 - LISTA BIGLIETTI
 * 		 - BIGLIETTI INFO
 * 		 
 */
@WebServlet("/main")
public class main extends HttpServlet {
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
	
	public void init( final ServletConfig config ) throws ServletException
	{
		commands.put("areapersonale", new AreaPersonale());
		commands.put("ricercavolo", new RicercaVolo());
		commands.put("volipage", new Voli());
		commands.put("prenotazione", new Prenotazione());
		commands.put("nuovaprenotazione", new NuovaPrenotazione());
		commands.put("logout", new Logout());
		commands.put("login", new Login());
		commands.put("contatti", new Contatti());
		commands.put("emettibiglietto", new EmettiBiglietto());
		commands.put("newBiglietto", new NewBiglietto() );
		
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = null;
		String actionKey = request.getParameter("ps");
		if( actionKey == null || actionKey.equals(""))
			rd = request.getRequestDispatcher("../index.jsp");
		
		else
		{
			Command command = commands.get(actionKey);
			
			try {
				rd = command.execute(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		rd.forward(request,response);
	}
}