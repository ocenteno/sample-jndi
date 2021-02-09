package daw.mvc.controlador;

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

import daw.mvc.i18n.Mensajes;
import daw.mvc.modelo.Persona;
import daw.mvc.persistencia.FabricaPersonaDao;
import daw.mvc.persistencia.PersonaDao;

public class ControlPersonas extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = -115739203944836855L;
	private Log log=LogFactory.getLog(getClass());
	private DataSource ds;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			log.info("buscando el pool de conexiones");
			Context ctx=new InitialContext();
			//ds=(DataSource)ctx.lookup("java:/jdbc/poolServidor");
			ds=(DataSource) ctx.lookup("java:comp/env/"+"jdbc/cursoLDS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recibimos el comando
		String cmd=request.getParameter("cmd"); 
		
		//recibimos los objetos 
		request.getRequestDispatcher("procPersona.jsp").include(request, response); 
		Persona p=(Persona) request.getAttribute("p"); 
		
		PersonaDao dao=FabricaPersonaDao.getInstance().getDao(ds);
		
		//decodificamos el comando
		if(cmd!=null&&cmd.equalsIgnoreCase("guardar")){ 
			System.out.println(Mensajes.getString("ControlPersonas.guardando")+p); 
			dao.guardar(p);
			request.setAttribute("msg", Mensajes.getString("ControlPersonas.guardada"));  //$NON-NLS-2$
			request.setAttribute("p", p); 
		}else if("buscar".equalsIgnoreCase(cmd)){ 
			log.debug(Mensajes.getString("ControlPersonas.buscando")+p); 
			p=dao.buscar(p.getDni());
			request.setAttribute("p", p);
			request.setAttribute("msg", Mensajes.getString("ControlPersonas.buscada"));  //$NON-NLS-2$
		}else if("borrar".equalsIgnoreCase(cmd)){ 
			log.debug(Mensajes.getString("ControlPersonas.borrando")+p); 
			dao.borrar(p);
			request.setAttribute("p", new Persona());
			request.setAttribute("msg", Mensajes.getString("ControlPersonas.borrado"));  //$NON-NLS-2$
		}else if("nuevo".equalsIgnoreCase(cmd)){ 
			log.debug(Mensajes.getString("ControlPersonas.limpiando")); 
			request.setAttribute("p", new Persona());
			request.setAttribute("msg", Mensajes.getString("ControlPersonas.limpiando"));  //$NON-NLS-2$
		}
		//elegimos la siguiente vista a mostrar
		//rellenando los datos extra a visualizar
		request.setAttribute("lista", dao.listar()); 
		request.getRequestDispatcher("vistaPersonas.jsp").forward(request,response);
			
	}  	  	  	    
}