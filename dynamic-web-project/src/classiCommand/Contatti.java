package classiCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Contatti implements Command {

	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) {		
		return request.getRequestDispatcher("../contatti.jsp");
	}

}
