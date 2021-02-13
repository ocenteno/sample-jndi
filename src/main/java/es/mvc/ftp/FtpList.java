package es.mvc.ftp;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPFile;

public class FtpList extends FtpClient {

	static final String OOPS_SOMETHING_WRONG_HAPPENED = "Oops! Something wrong happened";
	private String directorio = "/";
	
	public FtpList(String server) {
		super(server);
	}

	public FtpList(String server, String directorio) {
		this(server);
		if (directorio != null && directorio != "")
			this.directorio = directorio;
	}

	public FTPFile[] listar() {
		try {
			// Lists files and directories
			return ftpClient.listFiles(directorio);

		} catch (IOException ex) {
			log.info(OOPS_SOMETHING_WRONG_HAPPENED);
			log.error(ex.getMessage());
		} finally {
			// logs out and disconnects from server
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				log.error(ex.getMessage());
			}
		}
		return null;
	}
}