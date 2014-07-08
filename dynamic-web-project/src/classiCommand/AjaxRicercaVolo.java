package classiCommand;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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

        
                
        Map<String, String> map = new LinkedHashMap<String, String>();
        String json = null;
        
		try
		{
			 dbms = new DBMS();
		}
		catch( final Exception e )
		{
			throw new ServletException("Connection to dababase not possible: " + e.getMessage() );
		}
		
		String partenza = request.getParameter("part");
		String isPartenza = request.getParameter("ispartenza");
		
		if( isPartenza != null && isPartenza.compareTo("true") == 0 )
		{
			if( partenza != null)
			{
				ArrayList<String> s = dbms.getArrivi(partenza);
							
				for( String str : s )
				{
					map.put( str,str);
				}
			}
		}

        json = new Gson().toJson(map);            
        response.setContentType("application/json");
        response.getWriter().write(json);
		
		return null;
	}

}
