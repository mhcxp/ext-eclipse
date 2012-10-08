package mos.demo.blueprint;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorlServlet1 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String m_registrationPath;

	public HelloWorlServlet1(){

		m_registrationPath = "/h1";
	}

	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html");

		final PrintWriter writer = response.getWriter();
		writer.println("<html><body align='center'>");
		writer.println("<h1>Spring≤‚ ‘“ª</h1>");
		writer.println("<img src='/images/logo.png' border='0'/>");
		writer.println("<h1>" + getServletConfig().getInitParameter("from")
				+ "</h1>");
		writer.println("<p>");
		writer.println("Served by servlet registered at: " + m_registrationPath);
		writer.println("<br/>");
		writer.println("Servlet Path: " + request.getServletPath());
		writer.println("<br/>");
		writer.println("Path Info: " + request.getPathInfo());
		writer.println("</p>");
		writer.println("</body></html>");
	}

}
