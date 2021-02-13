package es.mvc.ftp;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import org.apache.commons.net.ftp.FTP;
 

public class FtpGetFile extends FtpClient{

	private static final String FILE_DOES_NOT_EXIST = "File does not exist\n";
	private static final String ERROR_EN_EL_FICHERO_BUSCADO = "Error en el fichero buscado\n";
	private ServletOutputStream outputStream;

	public FtpGetFile(String server, ServletOutputStream outputStream) {
		super(server);
		this.outputStream = outputStream;
	}
 
    @SuppressWarnings("finally")
	public String get(String file) {
    	String results = "";
        try {
        	if(file == null || file == "") {
                log.info(ERROR_EN_EL_FICHERO_BUSCADO);
                return ERROR_EN_EL_FICHERO_BUSCADO;
        	}
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			results = showServerReply(ftpClient);
 
            InputStream inputStream = ftpClient.retrieveFileStream(file);
 
            boolean success = ftpClient.completePendingCommand();
            if (!success) {
                log.info(FILE_DOES_NOT_EXIST);
            	results += FILE_DOES_NOT_EXIST;
            	return results;
            }
        	results += "Downloading " + file;
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream.write(bytesArray, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
 
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
    			log.info(SOMETHING_WRONG_HAPPENED);
    			log.error(ex.getMessage());
            }
            return results;
        }
    }
}