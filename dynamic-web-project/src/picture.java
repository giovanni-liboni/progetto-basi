import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Biglietto;
import bean.Passeggero;
import bean.Prenotazione;

import com.oreilly.servlet.MultipartRequest;
import com.sun.xml.internal.ws.util.ByteArrayBuffer;

import classiCommand.Command;
import classiCommand.DownloadImage;
import classiCommand.UploadImage;
import database.DBMS;


@WebServlet("/picture")
public class picture extends HttpServlet {
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
	DBMS dbms;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = null;
		
		// Imposto la grandezza massima della sezione dati del POST
		MultipartRequest multi = new MultipartRequest(request,"/tmp/", 2097152);
		
		String actionKey = (String) multi.getParameter("ps");
		if( actionKey == null || actionKey.equals(""))
			rd = request.getRequestDispatcher("../index.jsp");
		else
		{
			if( actionKey.compareTo("uploadimage") == 0)
			{
				try
				{
					dbms = new DBMS();
				}
				catch( final Exception e )
				{
					throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
				}
								
				HttpSession session = request.getSession();
				Passeggero beanPasseggero = (Passeggero) session.getAttribute("pass");
				
				if ( beanPasseggero != null )
				{
					File f = multi.getFile("image");
					if (f==null) 
					{
						request.setAttribute("msg", "Specificare un file.");
						rd = request.getRequestDispatcher("/error.jsp");
					} 
					else 
						dbms.addPictureToPasseggero(beanPasseggero, f);
					 

					request.setAttribute("pass", beanPasseggero);
					
					ArrayList<Prenotazione> vipb = dbms.getPrenotazioni(beanPasseggero.getDocumento().replaceAll("\\s",""));
					ArrayList<Biglietto> vbb = dbms.getBiglietti(beanPasseggero.getDocumento());
					
					session.setAttribute("pass", beanPasseggero);
					request.setAttribute("prenotazioni", vipb);
					request.setAttribute("biglietti", vbb);
					
					rd = request.getRequestDispatcher("../bigliettiPage.jsp");	
				}
				else
					rd = request.getRequestDispatcher("../index.jsp");

			}
			else if( actionKey.compareTo("downloadimage") == 0 )
			{
				try
				{
					dbms = new DBMS();
				}
				catch( final Exception e )
				{
					throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
				}
				//ottengo lo stream di output verso la JSP
				PrintWriter out = response.getWriter();
				
				try{
					int i;

					// recupero il documento del passeggero
					String documento = multi.getParameter("documento");
					
					// recupero il passeggero
					Passeggero passeggero = dbms.getPasseggero(documento);
					
					// controllo che sia presente una foto 
					if ( passeggero.getPicture() == null )
					{
						// restituisco l'immagine di default
					}
					else
					{
						InputStream is = new InputStream() {
							
							@Override
							public int read() throws IOException {
								return 0;
							}
						};
						is.read(passeggero.getPicture());
						
						BufferedInputStream bis = new BufferedInputStream(is);
						
						//imposto il tipo della risposta alla JSP
						response.setContentType("image/jpeg");
						
						//imposto la dimensione in byte della risposta alla JSP
						response.setContentLength(bis.available());
						
						//byte per byte copio l'immagine letta dal DB sullo stream verso la JSP
						while ((i = bis.read()) != -1) 
							out.write(i);
						
						//chiudo lo stream in lettura
						bis.close();
					}
				}
				catch( Exception ex ){}
			}
		}
		if( rd != null )
			rd.forward(request,response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = null;
		
		String actionKey = (String) request.getParameter("ps");
		if( actionKey == null || actionKey.equals(""))
			rd = request.getRequestDispatcher("../index.jsp");
		else
		{
			if( actionKey.compareTo("downloadimage") == 0 )
			{
				try
				{
					dbms = new DBMS();
				}
				catch( final Exception e )
				{
					throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
				}
				//ottengo lo stream di output verso la JSP
				BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
				
				try{
					int i;

					// recupero il documento del passeggero
					String documento = request.getParameter("documento");
					
					// recupero il passeggero
					Passeggero passeggero = dbms.getPasseggero(documento);
					
					// Stream di input per il file da inviare
					BufferedInputStream bis = null;
								
					byte[] photo = passeggero.getPicture();
					
					// controllo che sia presente una foto 
					if ( photo != null )
					{
					
//						ByteArrayInputStream bais = new ByteArrayInputStream( photo );
//						bis = new BufferedInputStream( bais );
						
					//imposto il tipo della risposta alla JSP
					response.setContentType("image/jpeg");
					
					//imposto la dimensione in byte della risposta alla JSP
//					response.setContentLength();
					
					response.setContentLength(photo.length);
					response.getOutputStream().write(photo);
					
//			        byte[] buffer = new byte[512];
//			        int length;
//			        while ((length = bis.read(buffer)) > 0)
//			        	out.write(buffer, 0, length);
//					
//					//chiudo lo stream in lettura
//					bis.close();
					}
				}
				catch( Exception ex ){}
			}
		}
		if( rd != null )
			rd.forward(request,response);
	}
}