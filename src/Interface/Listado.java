/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author MAX
 */
public class Listado {
    
public Listado(){
    
}
        public void listar() throws IOException{
            
        
        FTPClient client = new FTPClient();
        boolean result;
        try 
        {
            
        String ftp = "192.99.37.129"; 
        String user = "gps_ftp@manuelfiestasweb.com";
        String password = "Ftp*2017";
            
                client.connect("192.99.37.129");
                result = client.login(user,password);
                //client.changeWorkingDirectory("c:/FTP");
                if (result == true) 
                {
                        System.out.println("Logged in Successfully !");
                }
                else 
                {
                        System.out.println("Login failed !");
                        return;
                }
                client.enterLocalPassiveMode();
                FTPFile[] files = client.listFiles();
                System.out.println("Files and directories on Ftp Server directory : ");
                for (FTPFile file : files) 
                {
                        System.out.println(file.getName());
                }

        }
 
        catch (FTPConnectionClosedException e) 
        {
                System.out.println(e);
        }
        finally 
        {
        try 
        {
        client.disconnect();
        }
        catch (FTPConnectionClosedException e) 
        {
                        System.out.println(e);
        }
        }
        }
}
