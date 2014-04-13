package io.crowdcode.vehicle.web;

import io.crowdcode.vehicle.controller.ManufacturerController;
import io.crowdcode.vehicle.dto.ManufacturerDto;
import io.crowdcode.vehicle.web.util.HtmlWriter;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class ManufacturerServlet
 */
@WebServlet(urlPatterns="/manufacturers")
public class ManufacturerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private WebApplicationContext ctx;
	
	private ManufacturerController controller;
	
	private Integer servletCounter = 0;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HtmlWriter html = new HtmlWriter(response.getWriter());

		html.beginHtml().defaultHeader().beginMain();
		
		html.println("<hr><small>counter "+increaseCounter(request)+"");
		html.println("servlet counter "+(++servletCounter)+"</small></hr>");
		
		printManufacturerTable(html);
		printActionBar(html);
		
		html.closeMain();
		html.footer().closeHtml().out().flush();
	}

	private Integer increaseCounter(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Integer counter = (Integer) session.getAttribute("counter");
		if (counter == null) {
			counter =Integer.valueOf(0);
		}
		session.setAttribute("counter", ++counter);
		return counter;
	}

	private void printActionBar(HtmlWriter html) {
		html.beginFluid();
		html.beginPart("")
			.buttonInfo("AddManufacturer", "Add Manufacturer")
			.print("&nbsp;")
			.buttonInfo("/spring-jpa-container-web", "Zurück");
		html.closeFluid();
	}

	private void printManufacturerTable(HtmlWriter html) {
		html.beginFluid();
		html.beginPart("Manufacturer");
		html.beginTable();
		
		printManufacturerHeaders(html);
		printManufacturerRows(html);
		
		html.closeTable().closePart();
		html.closeFluid();
	}

	private void printManufacturerHeaders(HtmlWriter html) {
		html.beginHeadRow();
		html.beginHead().print("#").closeHead();
		html.beginHead().print("Name").closeHead();
		html.beginHead().print("Action").closeHead();
		html.closeHeadRow();
	}

	private void printManufacturerRows(HtmlWriter html) {
		for (ManufacturerDto manufacturer : controller.allManufactures()) {
			html.beginRow();
			html.beginCell().print(""+manufacturer.getId()).closeCell();
			html.beginCell().print(""+manufacturer.getName()).closeCell();
			html.beginCell().buttonInfo("vehicles?manufacturer="+manufacturer.getName(),"Details").closeCell();
			html.closeRow();
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		controller = (ManufacturerController) ctx.getBean("manufacturerControllerBean");
	}

}