import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBMS;
import util.InfoBigliettoBean;
import util.InfoPrenotazioneBean;
import util.PasseggeroBean;
import util.VoloBean;
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
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {

		//Definizione e recupero dell'eventuale parametro della servlet
		String date = "";
		String partenza = "";
		String arrivo = "";
		String pass = "";
		String ps = "";
		
		Vector< VoloBean > bean = null;
		//Dichiaro l'oggetto Dispatcher necessario per passare il controllo ad una JSP o una pagina HTML
		RequestDispatcher rd = null;
	
		try {
			PasseggeroBean beanPasseggero = null;
			DBMS dbms = new DBMS();
		
			
			if ( request.getParameter("pass") != null )
			{
				pass = request.getParameter("pass");
				beanPasseggero = dbms.getPasseggero(pass);
			}
			
			if ( request.getParameter("ps") != null )
			{
				ps = request.getParameter("ps");
			}
			
			if ( ps.equals("ricercavolo"))
			{
//				SEZIONE RICERCA VOLO
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
				if ( request.getParameter("pass") != null )
				{
					pass = request.getParameter("pass");
				}
				
				
				if ( partenza != null && arrivo != null && date != null )
				{
					// DEVO CONTROLLARE CHE I VALORI SIANO GIUSTI
					boolean legalPartenza = false;
					Vector< String > partenze = dbms.getPartenze();
					for( String str : partenze )
					{
						if ( str.toUpperCase().compareTo(partenza.toUpperCase()) == 0)
							{
								legalPartenza = true;
								break;
							}
					}
					boolean legalArrivo = false;
					Vector< String > arrivi = dbms.getArrivi();
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
						
						//Aggiungo il Vector come attributo della richiesta HTTP
						request.setAttribute("voli",bean);
						
						//Preparo il Dispatcher
						rd = request.getRequestDispatcher("../voliPage.jsp");
					}
					else
					{
						request.setAttribute("partenze", partenze);
						request.setAttribute("arrivi", arrivi);
						request.setAttribute("pass",beanPasseggero);
						rd = request.getRequestDispatcher("../ricercavolo.jsp");
					}
				}
			}
			else if ( ps.equals("login"))
			{
				String username = null;
				String password = null;
//				SEZIONE LOGIN
				if ( request.getParameter("username") != null )
				{
					username = request.getParameter("username");
				}
				if ( request.getParameter("password") != null )
				{
					password = request.getParameter("password");
				}
				if( username != null && password != null )
				{
					if( dbms.isLogin(username, password))
					{
						
						// PASSO 
						beanPasseggero = dbms.getPasseggero(username);
						request.setAttribute("pass", beanPasseggero);
						
						// APRO UNA NUOVA SESSIONE
						HttpSession session = request.getSession();
						
						Vector<InfoPrenotazioneBean> vipb = dbms.getPrenotazioni(beanPasseggero.getDocumento().replaceAll("\\s",""));
						Vector<InfoBigliettoBean> vbb = dbms.getBiglietti(beanPasseggero.getDocumento());
						if ( vipb == null || vipb.size() == 0)
							rd = request.getRequestDispatcher("../errorPage.jsp");
						
						session.setAttribute("pass", beanPasseggero);
						request.setAttribute("prenotazioni", vipb);
						request.setAttribute("biglietti", vbb);
						
						rd = request.getRequestDispatcher("../bigliettiPage.jsp");
					}
					else
					{
						rd = request.getRequestDispatcher("../errorPage.jsp");
					}
				}
			}
			else if ( ps.equals("nuovaprenotazione"))
			{
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
				// INSERIRE CONTROLLI IN CASO DI INSUCCESSO E SE L'UTENTE ESISTE BISOGNA RITORNARE SULLA STESSA PAGINA CON
				// UN MESSAGGIO DI ERRORE
				// NEL CASO SIA UN UTENTE CHE HA EFFETTUTATO L'ACCESSO ALLORA BISOGNA SOLO REGISTRARE LA NUOVA PRENOTAZIONE
				HttpSession session = request.getSession();
				if ( session.getAttribute("pass") == null )			
					dbms.newPasseggero(nome, cognome, nazionalita, documento, username, password, tessera);
		
					if ( dbms.newPrenotazione(codicevolo, documento) )				
						request.setAttribute("status", "ok");
					
					rd = request.getRequestDispatcher("../esitoPage.jsp");
			}
			else if ( ps.equals("emettiBiglietto") )
			{
				String numPrenotazione = "";
				if ( request.getParameter("emettiBiglietto") != null )
					numPrenotazione = request.getParameter("emettiBiglietto");
				
				// inserire biglietto
//				if ( !numPrenotazione.equals("") )
//					dbms.newBiglietto(documento, codicevolo, prezzo, id_prenotazione);
				// ( distanza / durata ) * 21,13 --> prezzo
			}
			
                //Passo il controllo alla JSP
                rd.forward(request,response);
		} catch(Exception e) {  
			//Gestisco eventuali eccezioni visualizzando lo stack delle chiamate
			e.printStackTrace();
		}
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {

		//Definizione e recupero dell'eventuale parametro della servlet
		String ps = "";
		
		//Dichiaro l'oggetto Dispatcher necessario per passare il controllo ad una JSP o una pagina HTML
		RequestDispatcher rd = null;
		PasseggeroBean beanPasseggero = null;
		DBMS dbms = null;
		try {
			dbms = new DBMS();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	
		if (request.getParameter("ps") != null) {// Ottengo se presente il parametro 'ps'
			ps = request.getParameter("ps");
		}	
		try {



			if (ps.equals("") || ps == null ) {
							
				rd = request.getRequestDispatcher("../index.jsp");
				
			}
			else if( ps.equals("ricercavolo") )
			{
				
				Vector< String > partenze = dbms.getPartenze();
				Vector< String > arrivi = dbms.getArrivi();
				
				request.setAttribute("partenze", partenze);
				request.setAttribute("arrivi", arrivi);
				
				rd = request.getRequestDispatcher("../ricercavolo.jsp");
			}
			else if( ps.equals("areapersonale") )
			{
				HttpSession session = request.getSession();
				if ( session.getAttribute("pass") == null )
					rd = request.getRequestDispatcher("../login.jsp");	
				else
				{

					beanPasseggero = (PasseggeroBean) session.getAttribute("pass");
					Vector<InfoPrenotazioneBean> vipb = dbms.getPrenotazioni(beanPasseggero.getDocumento().replaceAll("\\s",""));
					Vector<InfoBigliettoBean> vbb = dbms.getBiglietti(beanPasseggero.getDocumento());
					
					if ( vipb == null || vipb.size() == 0)
						rd = request.getRequestDispatcher("../errorPage.jsp");
					
					request.setAttribute("pass", beanPasseggero);
					request.setAttribute("prenotazioni", vipb);
					request.setAttribute("biglietti", vbb);
					
					rd = request.getRequestDispatcher("../bigliettiPage.jsp");
				}
			}			
			// RICERCO IL VOLO A PARTIRE DALLA DATA, PARTENZA E ARRIVO
			else if (ps.equals("prenotazione")) { 
				
				
				String codiceVolo = null;
				
				/*
				 * Ottengo i parametri per la ricerca
				 */
				if ( request.getParameter("codiceVolo") != null )
				{
					codiceVolo = request.getParameter("codiceVolo");
				}
				VoloBean beanVolo = dbms.getVolo(codiceVolo);			

				//Delego l'esecuzione della query alla classe di interazione con il DB			
				//Aggiungo il Vector come attributo della richiesta HTTP
				request.setAttribute("volo",beanVolo);
				
				HttpSession session = request.getSession();
				beanPasseggero = (PasseggeroBean) session.getAttribute("pass");
				
				request.setAttribute("pass",beanPasseggero);
				//Preparo il Dispatcher 
				rd = request.getRequestDispatcher("../prenotazionePage.jsp");	
			}
			else if ( ps.equals("logout"))
			{
				HttpSession session = request.getSession();
				session.invalidate();
				
				Vector< String > partenze = dbms.getPartenze();
				Vector< String > arrivi = dbms.getArrivi();
				
				request.setAttribute("partenze", partenze);
				request.setAttribute("arrivi", arrivi);
				
				rd = request.getRequestDispatcher("../ricercavolo.jsp");
			}
			else if( ps.equals("contatti") )
			{
				rd = request.getRequestDispatcher("../contatti.html");
			}

                //Passo il controllo alla JSP
                rd.forward(request,response);

		} catch(Exception e) {  
			//Gestisco eventuali eccezioni visualizzando lo stack delle chiamate
			e.printStackTrace();
		}
    }
}