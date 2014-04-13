package io.crowdcode.vehicle.web.util;

import java.io.PrintWriter;

public class HtmlWriter {
	
	private PrintWriter out;
	
	public HtmlWriter(PrintWriter out) {
		this.out = out;
	}

	public HtmlWriter print(String text) {
		out.print(text);
		return this;
	}
	
	public HtmlWriter println(String text) {
		out.println(text);
		return this;
	}
	
	public HtmlWriter beginHtml() {
		println("<html>");
		return this;
	}
	
	public HtmlWriter closeHtml() {
		println("</html>");
		return this;
	}
	
	public HtmlWriter defaultHeader() {
		println("<head>");
		println("<title>Vehicle Fleet Management</title>");
		println("<link href=\"css/vehicle.css\" rel=\"stylesheet\"/>");
	    println("<link href=\"css/bootstrap.css\" rel=\"stylesheet\"/>");
	    println("<link href=\"css/bootstrap-responsive.css\" rel=\"stylesheet\"/>");
	    println("</head>");
		return this;
	}
	
	public HtmlWriter beginMain() {
		print("<body>");
		print("<div class=\"container-narrow\">");
		print("  <div class=\"masthead\">");
		print("		<h3 class=\"muted\">Vehicle Fleet Manager</h3>");
		print("  </div>");
		print("  <hr/>");
		print("<div class=\"row-fluid marketing\">");
		return this;
	}
	
	public HtmlWriter beginFluid() {
		print("<div class=\"row-fluid marketing\">");
		return this;
	}
	
	public HtmlWriter closeFluid() {
		print("</div>");
		return this;
	}
	
	public HtmlWriter closeMain() {
		print("</div>");
		print("</body>");
		return this;
	}
	
	public HtmlWriter footer() {
		print("<hr/>");
		print("<div class=\"footer\">");
		print("	<p>&copy; by crowdcode</p>");
		print("</div>");
		print("<script src=\"js/jquery.js\"></script>");
		print("<script src=\"js/bootstrap.js\"></script>");
		return this;
	}
	
	public HtmlWriter button(String link, String name) {
		print("<a class=\"btn btn-large \" href=\""+link+"\">"+name+"</a>");
		return this;
	}
	
	public HtmlWriter buttonSuccess(String link, String name) {
		print("<a class=\"btn btn-large btn-success\" href=\""+link+"\">"+name+"</a>");
		return this;
	}

	public HtmlWriter buttonInfo(String link, String name) {
		print("<a class=\"btn btn-info btn-mini\" href=\""+link+"\">"+name+"</a>");
		return this;
	}
	
	public PrintWriter out() {
		return out;
	}
	
	public HtmlWriter beginTable() {
		print("<table class=\"table table-hover\">");
		return this;
	}
	
	public HtmlWriter closeTable() {
		print("</table>");
		return this;
	}
	
	public HtmlWriter beginRow() {
		print("<tr>");
		return this;
	}
	
	public HtmlWriter closeRow() {
		print("</tr>");
		return this;
	}
	
	public HtmlWriter beginCell() {
		print("<td>");
		return this;
	}
	
	public HtmlWriter closeCell() {
		print("</td>");
		return this;
	}

	public HtmlWriter beginPart(String partHeader) {
		print("<div class=\"span6\">");
		print("<h4>"+partHeader+"</h4>");
		return this;
	}
	
	public HtmlWriter closePart() {
		print("</div>");
		return this;
	}
	
	public HtmlWriter beginHeadRow() {
		print("<thead>");
		return this;
	}

	public HtmlWriter closeHeadRow() {
		print("</thead>");
		return this;
	}

	public HtmlWriter beginHead() {
		print("<th>");
		return this;
	}

	public HtmlWriter closeHead() {
		print("</th>");
		return this;
	}

}
