package es.mvc.controlador;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.mvc.i18n.Mensajes;
import es.mvc.modelo.Persona;
import es.mvc.persistencia.FabricaPersonaDao;
import es.mvc.persistencia.PersonaDao;

public class Contenido extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = -2982579753763659801L;
	private Log log = LogFactory.getLog(getClass());
	private DataSource ds;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			log.info("buscando la configuración CDN");
			Context ctx = new InitialContext();
			String cdn = (String) ctx.lookup("java:comp/env/" + "jndi/CDN");
			config.getServletContext().setAttribute("cdn", cdn);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("working", Mensajes.getString("contenido.working")); //$NON-NLS-2$
		request.setAttribute("video", Mensajes.getString("contenido.video"));

		request.setAttribute("zip", Mensajes.getString("contenido.zip"));
		request.getRequestDispatcher("vistaContenido.jsp").forward(request, response);

	}
}