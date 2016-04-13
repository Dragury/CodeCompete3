/**
 * Created by elliot on 13/04/2016.
 */

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Download {
    public static void main(String[] args){
        if(args.length == 0){
            //If no URL was supplied
            System.out.println("Usage: Java Download <URL of file to download>");
            System.out.println("HTTP Only");
        } else {
            //If URL was supplied
            String URLToDownload = args[0];
            URL url = null; //initialise to null to stop java from complaining :)
            try {
                url = new URL(URLToDownload);
            } catch (MalformedURLException murle){
                System.out.println("Invalid URL");
                System.exit(1);
            }

            //Choose a save location
            JFileChooser fc = new JFileChooser();
            File file = null; //initialise to null to stop java from complaining :)

            if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                //use the chosen file location
                file = fc.getSelectedFile();
            }
            else{
                //quit if a save location wasn't chosen
                System.out.println("No File was chosen");
                System.exit(0);
            }
            FileOutputStream fos = null; //shut up java
            try {
                //try and open the file
                fos = new FileOutputStream(file);
            } catch(FileNotFoundException fnfe){}
            try {
                //open the stream from the file on the server
                InputStream in = url.openStream();

                //create a byte buffer for saving
                byte[] buffer = new byte[1024];
                //track the number of bytes read so far
                int bytesRead = 0;
                //while there are still bytes to read.
                while ((bytesRead = in.read(buffer)) != -1) {
                    //write the read bytes from the buffer to the file
                    fos.write(buffer,0,bytesRead);
                }
                //close the In/Output streams
                fos.close();
                in.close();
            } catch(IOException ioe){
                //complain if something went wrong.
                System.out.println("Failed to read/write file");
            }
        }
    }
}
