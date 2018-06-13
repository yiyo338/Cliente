package odysseyLogic;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import javazoom.jl.player.Player;

//link de descarga de la libreria: http://www.javazoom.net/javalayer/sources.html

public class MP3 {
    private String filename;
    private Player player;

    
    public void close() {
        if (player != null) player.close();
    }

    // play the MP3 file to the sound card
    public void play(String ruta) {
        this.filename = ruta;
        
        try {
            File file = new File(filename);



            InputStream is = new FileInputStream(filename);
            byte[] bytes;

            try {
                // Get the size of the file
                long length = file.length();

                // You cannot create an array using a long type.
                // It needs to be an int type.
                // Before converting to an int type, check
                // to ensure that file is not larger than Integer.MAX_VALUE.
                if (length > Integer.MAX_VALUE) {
                    // File is too large (>2GB)
                }

                // Create the byte array to hold the data
                bytes = new byte[(int)length];

                // Read in the bytes
                int offset = 0;
                int numRead = 0;
                while (offset < bytes.length
                        && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                    offset += numRead;
                }

                // Ensure all the bytes have been read in
                if (offset < bytes.length) {
                    throw new IOException("Could not completely read file " + file.getName());
                }
            }
            finally {
                // Close the input stream and return bytes
                is.close();
            }

            //byte[] bytesArray = new byte[(int) file.length()];
            byte[] bytesArray = bytes;    ///Testeo de la mitad de los bytes de la cancion

            System.out.println((int) file.length());    /// cantidad total de bytes

            FileInputStream fis = new FileInputStream(file);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();

            //for(int i = 0; i<bytesArray.length;i++){
              //  System.out.println(bytesArray[i]);
            //}

            //FileInputStream fis = new FileInputStream(filename);
            ByteArrayInputStream data = new ByteArrayInputStream(bytesArray); //cambio de byte array a un buffer para la reproduccion
            BufferedInputStream bis = new BufferedInputStream(data);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();

    }  

}