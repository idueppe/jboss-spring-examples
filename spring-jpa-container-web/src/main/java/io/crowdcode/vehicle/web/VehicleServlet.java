package io.crowdcode.vehicle.web;

import io.crowdcode.vehicle.controller.VehicleController;
import io.crowdcode.vehicle.dto.VehicleDto;
import io.crowdcode.vehicle.web.util.HtmlWriter;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet(urlPatterns="/vehicles")
public class VehicleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private WebApplicationContext ctx;
	
	private VehicleController controller;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String manufacturer = request.getParameter("manufacturer");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HtmlWriter html = new HtmlWriter(out);
		
		html.beginHtml().defaultHeader().beginMain();
		
		printVehicleList(manufacturer, html);
		printArtionsButtons(html, manufacturer);

		html.closeMain();
		html.footer().closeHtml().out().flush();

	}

	private void printArtionsButtons(HtmlWriter html, String manufacturer) {
		html.beginFluid().beginPart("")
			.buttonInfo("/spring-jpa-container-web/addvehicle?manufacturer="+manufacturer, "Neues Fahrzeug anlegen")
			.print("&nbsp;")
			.buttonInfo("/spring-jpa-container-web/manufacturers", "Zurück")
			.closePart().closeFluid();
	}

	private void printVehicleList(String manufacturerName, HtmlWriter html) {
		
		html.beginFluid().beginPart(manufacturerName).beginTable();
		
		html.beginHeadRow();
		html.beginHead().print("#").closeHead();
		html.beginHead().print("Name").closeHead();
		html.beginHead().print("Date").closeHead();
		html.closeHeadRow();
		
		
		for (VehicleDto vehicle : controller.findVehicleByManufacturer(manufacturerName)) {
			html.beginRow()
				.beginCell().print(""+vehicle.getId()).closeCell()
				.beginCell().print(""+vehicle.getModelName()).closeCell()
				.beginCell().print(""+vehicle.getConstructionDate()).closeCell()
				.closeRow();
		}
		
		html.closeTable().closePart().closeFluid();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		controller = (VehicleController) ctx.getBean("vehicleControllerBean");
	}

}
