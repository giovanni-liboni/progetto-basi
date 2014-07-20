package classiCommand;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChiSiamo implements Command {

	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			ServletException, IOException {

		return request.getRequestDispatcher("../chisiamo.jsp");
	}

}
