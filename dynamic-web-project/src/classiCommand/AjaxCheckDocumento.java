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

public class AjaxCheckDocumento implements Command{

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
		
		String documento = request.getParameter("documento");
		
		if ( dbms.checkDocumento(documento) )
			map.put("isFree", "true");
		else
			map.put("isFree", "false");

        json = new Gson().toJson(map);            
        response.setContentType("application/json");
        response.getWriter().write(json);
		
		return null;
	}
}
