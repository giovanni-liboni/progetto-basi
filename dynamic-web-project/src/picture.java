import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import bean.Biglietto;
import bean.Passeggero;
import bean.Prenotazione;

import com.oreilly.servlet.MultipartRequest;

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
					
					if (f == null) 
					{
						request.setAttribute("msg", "Specificare un file.");
						rd = request.getRequestDispatcher("/error.jsp");
					} 
					else 
					{
						final int IMG_WIDTH = 70;
						final int IMG_HEIGHT = 70;
						byte[] imageInByte = null;
						
						// Riduco la grandezza dell'immagine per favorire le performance del database
						try {
							// Leggo il file e lo salvo in un oggetto di tipo BufferedImage
							BufferedImage image = ImageIO.read(f);
							
							// Recupero il tipo dell'immagine
							int type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
							
							// Trasformo l'immmagine con dimensioni 70x70
							BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
							Graphics2D g = resizedImage.createGraphics();
							g.drawImage(image, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
							g.dispose();
							
							// Trasformo l'immagine in un array di byte
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							ImageIO.write( resizedImage, "png", baos );
							baos.flush();
							imageInByte = baos.toByteArray();
							baos.close();
							
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						dbms.addPictureToPasseggero(beanPasseggero, imageInByte );
						
						request.setAttribute("pass", beanPasseggero);
						
						ArrayList<Prenotazione> vipb = dbms.getPrenotazioni(beanPasseggero.getDocumento().replaceAll("\\s",""));
						ArrayList<Biglietto> vbb = dbms.getBiglietti(beanPasseggero.getDocumento());
						
						session.setAttribute("pass", beanPasseggero);
						request.setAttribute("prenotazioni", vipb);
						request.setAttribute("biglietti", vbb);
						
						rd = request.getRequestDispatcher("../bigliettiPage.jsp");	
					}
				}
				else
					rd = request.getRequestDispatcher("../index.jsp");

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
				
				try{

					// recupero il documento del passeggero
					String documento = request.getParameter("documento");
					
					// recupero il passeggero
					Passeggero passeggero = dbms.getPasseggero(documento);
								
					byte[] photo = passeggero.getPicture();
					
					// controllo che sia presente una foto 
					if ( photo != null )
					{
							
						//imposto il tipo della risposta alla JSP
						response.setContentType("image/jpeg");
						
						response.setContentLength(photo.length);
						response.getOutputStream().write(photo);
					
					}
				}
				catch( Exception ex ){}
			}
		}
		if( rd != null )
			rd.forward(request,response);
	}
}