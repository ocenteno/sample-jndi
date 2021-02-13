package es.mvc.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.net.ftp.FTP;

public class FtpPutFile extends FtpClient {

	private static final String ERROR_EN_EL_FICHERO_SUBIDO = "Error en el fichero subido\n";
	private FileItem file;

	public FtpPutFile(String server) {
		super(server);
		this.user = "administrador";
		this.pass = "administrador";
	}

	public FtpPutFile(String server, FileItem file) {
		this(server);
		this.file = file;
	}

	@SuppressWarnings("finally")
	public String put(String filename) {
		log.info("Start uploading file");
		String results = "Subiendo " + filename;
		try {
			if (filename == null || filename == "") {
				log.info(ERROR_EN_EL_FICHERO_SUBIDO);
				return ERROR_EN_EL_FICHERO_SUBIDO;
			}
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			log.info("Start uploading file");
			
			results += "Subiendo " + file.getName() + "\n";
			InputStream inputStream = file.getInputStream();
			boolean done = ftpClient.storeFile("/" + filename, inputStream);
			inputStream.close();
			if (done) {
				log.info("File is uploaded successfully.");
			}
			results += showServerReply(ftpClient);
		} catch (IOException ex) {
			log.info("Error: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return results;
		}
	}

}