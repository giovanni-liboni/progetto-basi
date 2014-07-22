package classiCommand;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import bean.BigliettoBean;
import bean.PasseggeroBean;
import bean.PrenotazioneBean;

import com.oreilly.servlet.MultipartRequest;

import database.DBMS;

public class UploadImage implements Command {

	DBMS dbms;

	
	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			ServletException, IOException {
		
		try
		{
			dbms = new DBMS();
		}
		catch( final Exception e )
		{
			throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
		}
		
		MultipartRequest multi = new MultipartRequest(request,".");
		
		HttpSession session = request.getSession();
		PasseggeroBean beanPasseggero = (PasseggeroBean) session.getAttribute("pass");
		
		if ( beanPasseggero != null )
		{
			File f = multi.getFile("image");
			if (f==null) 
			{
				request.setAttribute("msg", "Specificare un file.");
				return request.getRequestDispatcher("/error.jsp");
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
			}

			
		}
		System.out.println("Reindirizzo la pagina");
		
		// PASSO 

		request.setAttribute("pass", beanPasseggero);
		
		ArrayList<PrenotazioneBean> vipb = dbms.getPrenotazioni(beanPasseggero.getDocumento().replaceAll("\\s",""));
		ArrayList<BigliettoBean> vbb = dbms.getBiglietti(beanPasseggero.getDocumento());
		
		session.setAttribute("pass", beanPasseggero);
		request.setAttribute("prenotazioni", vipb);
		request.setAttribute("biglietti", vbb);
		
		
		return request.getRequestDispatcher("../bigliettiPage.jsp");
	}

}
