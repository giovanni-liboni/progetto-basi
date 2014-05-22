import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import did.*;

/**
 * Questa classe gestisce le richieste riguardanti le ricerche all'interno della
 * base di dati. Risponde solamente a richieste HTTP di tipo GET. I possibili
 * parametri che vengono considerati e le relative azioni effettuate sono le
 * seguenti:
 * <br>
 * <ol>
 *   <li>nessun parametro: viene visualizzata la lista dei corsi di studio esistenti;</li>
 *   <li>parametro 'id': vengono visualizzate le informazioni (compresa il/i dipartimenti di appartenenza) 
 *       del corso di studi con l'id specificato.</li>
 * </ol>
 */
public class ServletCorsoStudiB extends HttpServlet {

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
		String id = "";
		String annoaccademico="";
	
		if (request.getParameter("id") != null) {
			
			// Ottengo se presente il parametro 'id'
			id = request.getParameter("id");
		}
		if (request.getParameter("annoaccademico") != null) {
			
			// Ottengo se presente il parametro 'annoaccademico'
			annoaccademico = request.getParameter("annoaccademico");
		}
	
		try {
			// Oggetto per l'interazione con il Database
			DBMS dbms = new DBMS();
			
			//Definizione dello stream di output
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=ISO-8859-1");
	
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"");
			out.println("      \"http://www.w3.org/TR/REC-html40/loose.dtd\">");
			out.println("<html>");
			out.println("<head>");
			
			// Determino la pagina da visualizzare
			if (id.equals("")) {          
				//recupero e visualizzo tutti i corsi di studio disponibili
				
				//Delego l'esecuzione della query alla classe di interazione con il DB
				//Recupero il risultato della query come un Vector contenente dei bean
				Vector css = dbms.getCorsiStudi();
				out.println("<title>Corsi di Studio Esistenti</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Corsi di Studio Esistenti:</h1>");
				out.println("<table>");
				out.println("<tr><th>Codice</th><th>Nome</th></tr>");			
				
				for (int i=0; i<css.size(); i++) {
					CorsoStudiBean csb =(CorsoStudiBean) css.get(i);
					out.println("<tr><td><a href=\"ServletCorsoStudiB?id="+csb.getId()+"\">"
							+csb.getCodice() + "</a></td><td>" + csb.getNomeCorsoStudi()+"</td></tr>");
				}
	
				out.println("</table>");
			}
			else if (annoaccademico.compareTo("") == 0){		
				
				//recupero e visualizzo le informazioni sul corso di studio con l'id specificato
				//Innanzittutto recupero le info presenti nella tabella CorsoStudi	
				Vector vcs = dbms.getCorsoStudi(Integer.parseInt(id));
				
				//il Vector conterrà un solo bean che quindi recupero subito
				CorsoStudiBean csb = (CorsoStudiBean)vcs.get(0);
				
				//Recupero poi il/i dipartimenti (possono essere più di una per i corsi interdipartimento) 
				//come un Vector contenente i nomi dei dipartimenti (non bean)
				Vector csf = dbms.getDipartCorso(Integer.parseInt(id));
				
				//Utilizzando le info sul corso genero la pagina HTML di output
				out.println("<title>Informazioni su un Corso di Studio</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h2>"+csb.getNomeCorsoStudi()+"</h2>");
				out.println("<ul><li><b>Codice</b>: "+csb.getCodice()+"</li>");
				out.println("<li><b>Abbreviazione</b>: "+csb.getAbbreviazione()+"</li>");
				out.println("<li><b>Durata anni</b>: "+csb.getDurataanni()+"</li>");
				out.println("<li><b>Sede</b>: "+csb.getSede()+"</li>");
				out.println("<li><b>Dipartimento</b>: ");
				
				for (int j=0; j<csf.size(); j++) 
				{
						out.print((String)csf.get(j));
                        if( j != csf.size()-1 )
                            out.println(",");
				}
				out.println("</li>");				
				
				out.println("<li><b>Informativa</b>: "+csb.getInformativa()+"</li>");
				
				out.println("<li><b>Anni accademici</b><ul style=\" list-style-type:none; padding:12px;\">");
			
				vcs = dbms.getAAErogazioni( csb.getId() );
				 
				for (int i=0; i<vcs.size(); i++) 
				{
					out.println("<li style=\" padding:5px;\"><a href=\"ServletCorsoStudiB?id="+csb.getId()+ 
								"&annoaccademico="+ vcs.get(i) + "\"> " + vcs.get(i) +"</a></li>");
				}
				out.println("</ul></ul>");
				out.println("<br/><br/>");
				
				//Visualizzo anche un link alla lista dei corsi di studi
				out.println("<a href=\"ServletCorsoStudiB\"><font color=\"00AA00\"> << Back</font></a>");
			}
			else
			{
				//recupero e visualizzo tutti i corsi di studio disponibili
				
				//Delego l'esecuzione della query alla classe di interazione con il DB
				//Recupero il risultato della query come un Vector contenente dei bean
				Vector css = dbms.getInsegnamentiErogati(Integer.parseInt(id), annoaccademico);
				out.println("<title>Insegnamenti</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Insegnamenti:</h1>");
				out.println("<table cellspacing=\"5\" cellpadding=\"5\" align=\"center\">");
				out.println("<tr><th>Nome</th><th>Codice</th><th>Crediti</th><th>Discriminante</th><th>Nome modulo</th><th>Discriminante modulo</th><th>Unit&agrave logistica</th></tr>");			
				
				for (int i=0; i<css.size(); i++) {
					InsErogatoBean ieb =(InsErogatoBean) css.get(i);
					
					// recupe
					out.println("<tr><td>" + ieb.getNomeins() +"</td><td>" + ieb.getCodiceins() +"</td><td>" + ieb.getCrediti() +"</td>" +
							"<td>" + ieb.getDescrizione() +"</td>" +
							"<td>" + ieb.getNomemodulo() +"</td>" +
							"<td>" + ieb.getDiscriminantemodulo() +"</td>" +
							"<td>" + ieb.getNomeunita() +"</td>" +
							"</tr>");
				}
	
				out.println("</table>");
				out.println("<br/><br/>");
				
				//Visualizzo anche un link alla lista dei corsi di studi
				out.println("<a href=\"ServletCorsoStudiB?id="+ id + "\"><font color=\"00AA00\"> << Back</font></a>");
				
			}
			//Termino la pagina HTML
			out.println("</body>");
			out.println("</html>");
			
		} catch(Exception e) {  
			//Gestisco eventuali eccezioni visualizzando lo stack delle chiamate
			e.printStackTrace();
		}
    }
}
