import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import util.*;
/**
 * Questa classe gestisce le richieste HTTP
 * 
 */
public class main extends HttpServlet {
    /**
     * Questo metodo risponde alle richieste HTTP di tipo GET. Elabora le richieste, impostando
     * gli eventuali attributi necessari, e ridirige la visualizzazione alle pagine jsp relative.
     *
     * @param request Oggetto HttpServletRequest dal quale ottenere informazioni circa la
     *                richiesta effettuata.
     * @param response Oggetto HttpServletResponse per l'invio delle risposte.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {

		//Definizione e recupero dell'eventuale parametro della servlet
		String ps = "";
		
		//Dichiaro l'oggetto Dispatcher necessario per passare il controllo ad una JSP o una pagina HTML
		RequestDispatcher rd = null;
	
		if (request.getParameter("ps") != null) {// Ottengo se presente il parametro 'ps'
			ps = request.getParameter("ps");
		}
	
		try {
			// Oggetto per l'interazione con il Database
			DBMS dbms = new DBMS();

			if (ps.equals("")) {
				// Parametro ps assente o vuoto, visualizzo la home page del sito.
				//Preparo il Dispatcher
				rd = request.getRequestDispatcher("../home.jsp");
			}			
			
			// RICERCO IL VOLO A PARTIRE DALLA DATA, PARTENZA E ARRIVO
			if (ps.equals("ricvolo")) { 
//              //visualizzo le informazioni di dettaglio del dipartimento
//				//Delego l'esecuzione della query alla classe di interazione con il DB
//				//Recupero il risultato della query come bean
//				
//				//Aggiungo il Vector come attributo della richiesta HTTP
//				request.setAttribute("info",info);
//				//Preparo il Dispatcher
//				rd = request.getRequestDispatcher("../pagina3.jsp");	
			}
				
			if (ps.equals("corsi")) { 
//				//Visualizzo le info del corsi di studio attivi presso il dipartimento di informatica
//				//Delego l'esecuzione della query alla classe di interazione con il DB
//				//Recupero il risultato della query come vettore di bean
//				// FUNZIONALIT� DA IMPLEMENTARE
//				Vector<CorsoStudiBean> corsi = dbms.getCorsiDip(30);
//				request.setAttribute("corsi",corsi);
//				//Preparo il Dispatcher
//				rd = request.getRequestDispatcher("../pagina4.jsp");
			}
			
			if (ps.equals("ins")) { 
//				//Visualizzo la lista di tutti gli insegnamenti erogati 
//				//nell'anno accademico 2013/2011 per il corso di studi "id"
//				// FUNZIONALIT� DA IMPLEMENTARE
//				String id = "";	//Recupero l'id del corso
//				if (request.getParameter("id") != null) 
//				{
//					// Ottengo se presente il parametro 'id'
//					id = request.getParameter("id");
//				}
//		              //Delego l'esecuzione della query alla classe di interazione con il DB
//		              //Recupero il risultato della query come vettore di bean
//		              //Preparo il Dispatcher
//				Vector<InsErogatoBean> bean =  dbms.getInsegnamentiErogati( Integer.parseInt(id) );
//				request.setAttribute("ins", bean );      
//				rd = request.getRequestDispatcher("../pagina6.jsp");
								
			}
                //Passo il controllo alla JSP
                rd.forward(request,response);

		} catch(Exception e) {  
			//Gestisco eventuali eccezioni visualizzando lo stack delle chiamate
			e.printStackTrace();
		}
    }
}