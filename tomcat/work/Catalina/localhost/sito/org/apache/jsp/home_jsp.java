/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.30
 * Generated at: 2014-05-20 16:34:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t<meta charset=\"ISO-8859-1\">\n");
      out.write("\t<link rel=\"shortcut icon\" href=\"../img/favicon.ico\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"../css/comune.css\">\n");
      out.write("\t<title>  Dipartimento di Informatica </title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<div class=\"titolo\">\n");
      out.write("\t<div>\n");
      out.write("\t\t<a href=\"main?ps=info\">\n");
      out.write("\t\t\t<img class=\"center\" src=\"http://www.di.univr.it/documenti//Dipart/logo/logo199986.gif\">\n");
      out.write("\t\t</a>\n");
      out.write("\t</div>\n");
      out.write("\t<h1> Dipartimento di Informatica </h1>\n");
      out.write("\t<h2> Universit&agrave degli Studi di Verona</h2>\n");
      out.write("\t</div>\t\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t<div style=\"text-align: center; height: 300px\"> \n");
      out.write("\t\t<br/>   <br/><br/>\n");
      out.write("\t\t  <br/><br/>\n");
      out.write("\t\t <br/><br/>\n");
      out.write("\t\t<a href=\"main?ps=corsi\" style=\"\tfont-size: 20px; letter-spacing: 1px;\" > Didattica erogata </a> <br/><br/>\n");
      out.write("\t\t<a href=\"../pagina5.html\" style=\"\tfont-size: 20px; letter-spacing: 1px;\" > Iscriviti alla mailing list! </a> <br/><br/>\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("\t<div class=\"info\" style=\" width:550px;\">\n");
      out.write("\t\t<ul style=\" width:550px;\" >\n");
      out.write("\t\t\t<li> \n");
      out.write("\t\t\t\t<label>Indirizzo:</label>\n");
      out.write("\t\t\t\t<a href=\"../pagina2.html\" > Strada le Grazie 15 - 37134 Verona </a>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t\t<label> Email:</label>\n");
      out.write("\t\t\t\t<a href=\"mailto:segreteria.di@ateneo.univr.it\">segreteria.di@ateneo.univr.it </a>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t\t<label> Telefono:</label>\n");
      out.write("\t\t\t\t<a> 045 802 7071 - 045 8027802 </a>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t\t<label> Fax: </label>\n");
      out.write("\t\t\t\t<a> 045 802 7068</a>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\n");
      out.write("\t\t</ul>\n");
      out.write("\t\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
