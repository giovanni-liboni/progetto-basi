package classiCommand;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Biglietto;
import bean.Passeggero;
import bean.Prenotazione;

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
		Passeggero beanPasseggero = (Passeggero) session.getAttribute("pass");
		
		if ( beanPasseggero != null )
		{
			File f = multi.getFile("image");
			if (f==null) 
			{
				request.setAttribute("msg", "Specificare un file.");
				return request.getRequestDispatcher("/error.jsp");
			} 
			else 
				dbms.addPictureToPasseggero(beanPasseggero, f);

			
		}
		System.out.println("Reindirizzo la pagina");
		
		// PASSO 

		request.setAttribute("pass", beanPasseggero);
		
		ArrayList<Prenotazione> vipb = dbms.getPrenotazioni(beanPasseggero.getDocumento().replaceAll("\\s",""));
		ArrayList<Biglietto> vbb = dbms.getBiglietti(beanPasseggero.getDocumento());
		
		session.setAttribute("pass", beanPasseggero);
		request.setAttribute("prenotazioni", vipb);
		request.setAttribute("biglietti", vbb);
		
		
		return request.getRequestDispatcher("../bigliettiPage.jsp");
	}

}
