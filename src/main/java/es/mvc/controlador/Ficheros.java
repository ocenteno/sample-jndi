package es.mvc.controlador;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPFile;

import es.mvc.ftp.FtpGetFile;
import es.mvc.ftp.FtpList;
import es.mvc.ftp.FtpPutFile;
import es.mvc.i18n.Mensajes;

public class Ficheros extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = 3358948217453242674L;
	private Log log = LogFactory.getLog(getClass());
	private String ftpServer;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			log.info("buscando la configuración FTP");
			Context ctx = new InitialContext();
			ftpServer = (String) ctx.lookup("java:comp/env/" + "jndi/FTP");
			config.getServletContext().setAttribute("ftp", ftpServer);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recibimos el comando
		String cmd = request.getParameter("cmd");
		String param = request.getParameter("parametro");
		String mensajes = "";

		// decodificamos el comando
		if (param != null && param != "") {
			if ("cd".equalsIgnoreCase(cmd)) {
				log.info(Mensajes.getString("ficheros.listando", ftpServer));
			} else if ("get".equalsIgnoreCase(cmd)) {
				log.info(Mensajes.getString("ficheros.bajando", ftpServer));
				mensajes += getFile(request, response);
			}
		}
		mensajes += listarFicheros(request);
		request.setAttribute("msg", mensajes);
		request.getRequestDispatcher("vistaFicheros.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.info(Mensajes.getString("ficheros.subiendo", ftpServer));
		String filename = request.getParameter("filename");
		ServletFileUpload uploader = new ServletFileUpload(new DiskFileItemFactory());
		uploader.setSizeMax(50 * 1024 * 1024);
		String mensajes = Mensajes.getString("ficheros.subiendo", ftpServer);
		try {
			List<FileItem> files = uploader.parseRequest(request);
			FileItem file = null;
			for (FileItem item : files) {
				if ("filename".equalsIgnoreCase(item.getFieldName()))
					filename = item.getString();
				if ("upload".equalsIgnoreCase(item.getFieldName()))
					file = item;
			}
			FtpPutFile servidor = new FtpPutFile(ftpServer, file);
			mensajes += servidor.conectar();
			mensajes += servidor.login();
			mensajes += servidor.put(filename);
		} catch (FileUploadException e) {
			mensajes += "Error al subir el fichero " + e.getMessage();
			e.printStackTrace();
		}

		mensajes += listarFicheros(request);
		request.setAttribute("msg", mensajes);
		request.getRequestDispatcher("vistaFicheros.jsp").forward(request, response);

	}

	private String getFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mensajes = "";
		String file = request.getParameter("parametro");

		FtpGetFile servidor = new FtpGetFile(ftpServer, response.getOutputStream());
		mensajes += servidor.conectar();
		mensajes += servidor.login();
		mensajes += servidor.get(file);

		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");
		return mensajes;
	}

	private String listarFicheros(HttpServletRequest request) {
		String mensajes = "";
		String param = request.getParameter("parametro");
		log.info(Mensajes.getString("ficheros.listando", ftpServer));

		FtpList servidor = new FtpList(ftpServer, param);
		mensajes += servidor.conectar();
		mensajes += servidor.login();
		FTPFile[] resultados = servidor.listar();

		request.setAttribute("lista", resultados == null ? new FTPFile[0] : resultados);
		return mensajes;
	}

}