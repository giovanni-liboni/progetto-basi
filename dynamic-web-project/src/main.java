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
	
//	
//	
//	
//	@Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//	throws IOException, ServletException {
//
//		//Definizione e recupero dell'eventuale parametro della servlet
//		String date = "";
//		String partenza = "";
//		String arrivo = "";
//		String pass = "";
//		String ps = "";
//		
//		ArrayList< VoloBean > bean = null;
//		//Dichiaro l'oggetto Dispatcher necessario per passare il controllo ad una JSP o una pagina HTML
//		RequestDispatcher rd = null;
//	
//		try {
//			PasseggeroBean beanPasseggero = null;
//			DBMS dbms = new DBMS();
//		
//			
//			if ( request.getParameter("pass") != null )
//			{
//				pass = request.getParameter("pass");
//				beanPasseggero = dbms.getPasseggero(pass);
//			}
//			
//			if ( request.getParameter("ps") != null )
//			{
//				ps = request.getParameter("ps");
//			}
//			else if ( ps.equals("emettiBiglietto") )
//			{
//				String numPrenotazione = "";
//				if ( request.getParameter("emettiBiglietto") != null )
//					numPrenotazione = request.getParameter("emettiBiglietto");
//				
//				// inserire biglietto
////				if ( !numPrenotazione.equals("") )
////					dbms.newBiglietto(documento, codicevolo, prezzo, id_prenotazione);
//				// ( distanza / durata ) * 21,13 --> prezzo
//			}
//			
//                //Passo il controllo alla JSP
//                rd.forward(request,response);
//		} catch(Exception e) {  
//			//Gestisco eventuali eccezioni visualizzando lo stack delle chiamate
//			e.printStackTrace();
//		}
//    }
}