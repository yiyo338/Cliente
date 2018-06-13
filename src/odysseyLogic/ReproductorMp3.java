/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyLogic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.apache.commons.codec.binary.Base64;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Observable;
import javafx.beans.InvalidationListener;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import odysseyUI.MainWindowController;
import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

public class ReproductorMp3{
    
    private static ReproductorMp3 instancia;
    private FileInputStream inputStream;
    private BufferedInputStream bufferedInputStream;
    private Media media;
    private MediaPlayer mp;
    private long pausedAt, endsAt;
    private String songPath;
    private Thread streamc;
    private  Slider slide;
    private double current_time = 0.0;
    private ReproductorMp3(){
        
    }
    
    public static ReproductorMp3 getSingletonInstance() {
        if (instancia == null){
            instancia = new ReproductorMp3();
        }
        else{
            System.out.println("No se puede crear el objeto");
            
        }
        return instancia;
        
    }
    
    
    public void Stop(){
        System.out.println("Stopped song...");
        stop_music();
        endsAt = pausedAt = 0;
    }

    public void Play(String ruta){
        System.out.println("Playing song...");
        play_music(ruta);
        
    }

    public  void Pause(){
        System.out.println("Paused song...");
        pause_music();
    }

    public void Resume(){
        resume_music();
    }

    public void slider_drag(){

    }

    private void stop_music(){
       mp.stop();
       streamc.stop();
    }
    
public void streaming (Media m) throws ParserConfigurationException, IOException, TransformerException, UnsupportedEncodingException, SAXException{
        this.media = m;
        
        
        DocumentoXML stream = new DocumentoXML("comunicacion");
        stream.crearHijos("codigo", "0");
        stream.crearHijos("chunk", "0");
        clientetcp client = new clientetcp();
        java.net.Socket socket = client.crear();
        client.enviar(socket, stream.ConvertirXML_String(), 0);
        System.out.println(client.getMensajeActual());
        XML_Parser parser = new XML_Parser();
        parser.parsearString(client.getMensajeActual());

        String limite = parser.by_tagName("limite").item(0).getTextContent();
        int x = Integer.parseInt(limite);
        streamc = new Thread(){
            @Override
            public void run() {
                int y = 0; 
                while(y < x){
                    DocumentoXML nuevo;
                    try {
                        nuevo = new DocumentoXML("comunicacion");
                        nuevo.crearHijos("codigo", "0");
                        nuevo.crearHijos("chunk", ""+y);
                        clientetcp cliente = new clientetcp();
                        java.net.Socket socketcito = client.crear();    
                        cliente.enviar(socketcito, nuevo.ConvertirXML_String(), 0);  
                        XML_Parser parse = new XML_Parser(); 
                        parse.parsearString(cliente.getMensajeActual());
                        byte[] j = Base64.decodeBase64(parse.by_tagName("mBytes").item(0).getTextContent());

                        FileUtils.writeByteArrayToFile(new File("chunk.mp3"), j);
                        Play("/home/cris/NetBeansProjects/Odyssey-Cliente-master/chunk.mp3");
                        while(mp.getCurrentTime().compareTo(mp.getTotalDuration()) == -1);
                        current_time+=mp.getTotalDuration().toSeconds();
                        mp.pause();
                        y++;                        
                    } catch (ParserConfigurationException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (TransformerException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SAXException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
  
                }                
            }
        };
        streamc.setDaemon(true);
        streamc.start();
    }
    private void play_music(String path){
       
            media = new Media ("file:///"+path);
            mp = new MediaPlayer(media);
            mp.play();
        
        
        
    }

    private void pause_music(){
      mp.pause();
      
      
    }

    private void resume_music(){
       mp.play();
       
    }

    private void setSliderPosition(){
        
    }


    public FileInputStream getInputStream() {
        return inputStream;
    }

    public BufferedInputStream getBufferedInputStream() {
        return bufferedInputStream;
    }

   

    public long getPausedAt() {
        return pausedAt;
    }

    public long getEndsAt() {
        return endsAt;
    }

    public String getSongPath() {
        return songPath;
    }

   
    
}