package classiCommand;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class UploadImage implements Command {

	@Override
	public RequestDispatcher execute(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			ServletException, IOException {
		
		MultipartRequest multi = new MultipartRequest(request,"/tmp/");
		
		File f = multi.getFile("image");
		if (f==null) {
			request.setAttribute("msg", "Specificare un file.");
			return request.getRequestDispatcher("/error.jsp");
		} else {
			String fileName = multi.getFilesystemName("image");
		}

		return null;
	}

}
