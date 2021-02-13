package es.mvc.ftp;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import es.mvc.i18n.Mensajes;

public class FtpClient {

	protected static final String SOMETHING_WRONG_HAPPENED = "Something wrong happened";
	protected Log log = LogFactory.getLog(getClass());
	private int port = 21;
	protected String user = "registrado";
	protected String pass = "registrado";
	private String directorio = "/";
	private String server;
	protected FTPClient ftpClient = new FTPClient();

	public FtpClient(String server) {
		this.server = server;
	}

	public FtpClient(String server, String directorio) {
		this(server);
		if (directorio != null)
			this.directorio = directorio;
	}

	public String login() {
		String results = null;
		try {
			boolean success = ftpClient.login(user, pass);
			results = showServerReply(ftpClient);
			if (!success) {
				log.info("Could not login to the server");
				return Mensajes.getString("ficheros.login.error", user);
			}
		} catch (IOException e) {
			log.info(SOMETHING_WRONG_HAPPENED);
			log.error(e.getMessage());
		}
		return results;
	}

	public String conectar() {
		String results = null;
		try {
			ftpClient.connect(server, port);
			results = showServerReply(ftpClient);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				log.info("Connection failed");
				return Mensajes.getString("ficheros.conexion.error", server);
			}
		} catch (SocketException e) {
			log.info(SOMETHING_WRONG_HAPPENED);
			log.error(e.getMessage());
	
		} catch (IOException e) {
			log.info(SOMETHING_WRONG_HAPPENED);
			log.error(e.getMessage());
		}
	
		return results;
	}

	protected String showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		String result = "";
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				log.info(aReply);
				result += aReply + "\n";
			}
		}
		return result;
	}
}