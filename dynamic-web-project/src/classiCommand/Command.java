package classiCommand;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public RequestDispatcher execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException;
}
