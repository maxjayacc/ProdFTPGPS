package Interface;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author visel
 */
public class Cheksum {


    public void IntegridadArchivo(String name, String filep) {
try {
        FTPClient client = new FTPClient();
        String ftp = "192.99.37.129"; 
        String user = "gps_ftp@manuelfiestasweb.com";
        String password = "Ftp*2017";
        FileInputStream fis = null;
        BufferedInputStream in = null;

            // Conactando al servidor
            client.connect(ftp);
      
            // Logueado un usuario (true = pudo conectarse, false = no pudo
            // conectarse)
            
            boolean login = client.login(user, password);
            boolean storeFile = false;
            
            System.out.println("Autentificación: " + login);
           
            
            client.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
            client.setFileTransferMode(FTP.BINARY_FILE_TYPE);
//   
            client.enterLocalPassiveMode();

            String archivoRemoto = "/"+name;
            File downloadFile = new File("C:/Archivos/"+name);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile));
            boolean success = client.retrieveFile(archivoRemoto, outputStream1);
            outputStream1.close();
 
            if (success) {
                System.out.println("Archivo #1 has been descargado correctamente.");
            }
            
            File remotofile = new File("C:/Archivos/"+name);
           // System.out.println(filep);
            String remotoCRC = Integer.toHexString((int) FileUtils.checksumCRC32(remotofile)).toUpperCase();
            System.out.println("REMOTO "  + remotoCRC);
            
            
            File file = new File(filep);
            System.out.println(name);
            //Obtiene el CRC del archivo antes de subir al FTP
            String localCRC = Integer.toHexString((int) FileUtils.checksumCRC32(file)).toUpperCase();
            System.out.println(localCRC);
            System.out.println("LOCAL "  + localCRC);
            
            if(remotoCRC.equals(localCRC)) {
                System.out.println("NO SE MODIFICO LA INTEGRIDAD DEL DOCUMENTO ");
                 JOptionPane.showMessageDialog(null, "No se modificó la integridad del documento");
            }else {
                System.out.println("SE MODIFICO EL DOCUMENTO");
            }
                    
                    
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}