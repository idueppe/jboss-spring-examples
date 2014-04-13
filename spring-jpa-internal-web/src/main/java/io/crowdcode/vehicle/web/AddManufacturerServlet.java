package io.crowdcode.vehicle.web;

import io.crowdcode.vehicle.controller.ManufacturerController;
import io.crowdcode.vehicle.service.ManufacturerAlreadyExistsException;
import io.crowdcode.vehicle.web.util.HtmlWriter;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class ManufacturerServlet
 */
@WebServlet(urlPatterns = "/AddManufacturer")
public class AddManufacturerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private WebApplicationContext ctx;

	private ManufacturerController controller;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		HtmlWriter html = new HtmlWriter(response.getWriter());

		html.defaultHeader().beginHtml().beginMain();

		printForm(html);
		printActionBar(html);
		
		html.closeMain();
		html.footer().closeHtml();
	}


	private void printForm(HtmlWriter html) {
		html.beginFluid().beginPart("Add Manufacturer");
		html.println("<form action=\"AddManufacturer\" method=\"POST\">");
			html.print("<fieldset>");
			html.println("<label>Name:</label>");
			html.println("<input type=\"text\" size=\"40\" name=\"manufacturerName\">");
			html.println("<br/>");
			html.println("<button type=\"submit\" class=\"btn\">Anlegen</button>");
			html.print("</fieldset>");
		html.println("</form>");
		html.closePart().closeFluid();
	}
	

	private void printActionBar(HtmlWriter html) {
		html.beginFluid();
		html.beginPart("").buttonInfo("/spring-jpa-internal-web/manufacturers", "Zurück");
		html.closeFluid();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String manufacturerName = request.getParameter("manufacturerName");
		
		HtmlWriter html = new HtmlWriter(response.getWriter());
		html.defaultHeader().beginHtml().beginMain().beginFluid().beginPart("Add Manufacturer");

		try {
			controller.addManufacturer(manufacturerName);
			html.print("<p class=\"text-success\">Manufacturer "+manufacturerName+" created successfully.</p>");
		} catch (ManufacturerAlreadyExistsException e) {
			html.print("<p class=\"text-error\"> "+e.getMessage()+"</p>");
		}
		
		html.closePart().closeFluid();
		html.beginFluid();
		html.beginPart("").buttonInfo("/spring-jpa-internal-web/manufacturers", "Zurück");
		html.closeFluid();
		html.closeMain();
		
		html.footer().closeHtml();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		controller = (ManufacturerController) ctx.getBean("manufacturerControllerBean");
	}

}
