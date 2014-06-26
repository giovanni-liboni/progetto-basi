package classiCommand;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.DBMS;

public class AjaxRicercaVolo implements Command {

	private DBMS dbms = null ;
	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			ServletException, IOException {

        String partenza = request.getParameter("ajax");
                
        Map<String, String> map = new LinkedHashMap<String, String>();
        String json = null;
        
		try
		{
			 dbms = new DBMS();
		}
		catch( final Exception e )
		{
			throw new ServletException("Non è possibile avere una connessione ad database: " + e.getMessage() );
		}
		
        if (partenza.equals("Verona")) {
        	map.put("1", "");
        	map.put("2", "Cristiano Ronaldo");
        	map.put("3", "David Beckham");
        	map.put("4", "Diego Maradona");
        } else if (partenza.equals("Monaco")) {
        	map.put("1", "Sourav Ganguly");
        	map.put("2", "Sachin Tendulkar");
        	map.put("3", "Lance Klusener");
        	map.put("4", "Michael Bevan");
        } else if (partenza.equals("Select arrivo")) {
        	map.put("1", "Select Player");
        }

        json = new Gson().toJson(map);            
        response.setContentType("application/json");
        response.getWriter().write(json);
		
		return null;
	}

}
