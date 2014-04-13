package io.crowdcode.vehicle.web;

import io.crowdcode.vehicle.controller.VehicleController;
import io.crowdcode.vehicle.domain.EngineType;
import io.crowdcode.vehicle.dto.EngineDto;
import io.crowdcode.vehicle.dto.VehicleDto;
import io.crowdcode.vehicle.web.util.HtmlWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@WebServlet(urlPatterns = "/addvehicle")
public class AddVehicleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private WebApplicationContext context;

	private VehicleController controller;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		HtmlWriter html = new HtmlWriter(response.getWriter());
		
		html.defaultHeader().beginHtml().beginMain();
		
		String manufacturer = request.getParameter("manufacturer");
		printForm(html, manufacturer);
		printActionBar(html);
		
		html.closeMain();
		html.footer().closeHtml();
	}

	private void printActionBar(HtmlWriter html) {
		html.beginFluid();
		html.beginPart("").buttonInfo("/spring-jpa-container-web/manufacturers", "Zurück");
		html.closeFluid();
	}

	private void printForm(HtmlWriter html, String manufacturer) {
		html.beginFluid().beginPart("Add Vehicle");
		html.println("<form action=\"addvehicle\" method=\"POST\">");
			html.print("<fieldset>");
			html.println("<input type=\"hidden\" name=\"manufacturer\" value=\""+ manufacturer +"\">");
			html.println("<label>Modelname:</label>");
			html.println("<input type=\"text\" size=\"40\" name=\"modelName\">");
			html.println("<br/>");
			html.println("<label>Herstellungsdatum:</label>");
			html.println("<input type=\"text\" size=\"40\" name=\"construction\">");
			html.println("<br/>");
			html.println("<button type=\"submit\" class=\"btn\">Anlegen</button>");
			html.print("</fieldset>");
		html.println("</form>");
		html.closePart().closeFluid();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HtmlWriter html = new HtmlWriter(response.getWriter());
		html.defaultHeader().beginHtml().beginMain().beginFluid().beginPart("Add Manufacturer");

		try {
			VehicleDto vehicle = new VehicleDto();
			String modelName = request.getParameter("modelName");
			String manufacturer = request.getParameter("manufacturer");

			vehicle.setModelName(modelName);
			vehicle.setManufacturerName(manufacturer);
			EngineDto engineDto = new EngineDto();
			engineDto.setEngineType(EngineType.DIESEL);
			vehicle.setEngine(engineDto);
			
			String paramConstructionDate = request.getParameter("construction");
			Date constructionDate = new SimpleDateFormat("dd.MM.yyyy").parse(paramConstructionDate);
			vehicle.setConstructionDate(constructionDate);
			
			controller.saveOrUpdateVehicle(vehicle);
			html.print("<p class=\"text-success\">Fahrzeug "+modelName+" dem Hersteller "+manufacturer+" hinzugefügt.</p>");
		} catch (ParseException e) {
			html.print("<p class=\"text-error\"> "+e.getMessage()+"</p>");
		}
		
		html.closePart().closeFluid();
		html.beginFluid();
		html.beginPart("").buttonInfo("/spring-jpa-container-web/manufacturers", "Zurück");
		html.closeFluid();
		html.closeMain();
		
		html.footer().closeHtml();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		controller = (VehicleController) context.getBean("vehicleControllerBean");
	}

}
