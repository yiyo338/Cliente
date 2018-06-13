package odysseyUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import odysseyLogic.Base64_;
import odysseyLogic.ConversorMP3_ByteArray;
import org.apache.commons.codec.binary.Base64;
import odysseyLogic.DocumentoXML;
import odysseyLogic.MP3;
import odysseyLogic.XML_Parser;
import odysseyLogic.ReproductorMp3;
import odysseyLogic.clientetcp;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class Odyssey extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       /* DocumentoXML n = new DocumentoXML("Comunicacion");
        n.crearHijos("Codigo", "00");
        String msj = n.ConvertirXML_String();
        
        
       clientetcp client = new clientetcp();
		java.net.Socket socket = client.crear();
                client.enviar(socket, msj);
                
		//client.recibir(socket);
                System.out.println(client.getMensajeActual());
                XML_Parser NUEVO = new XML_Parser();
                
                Base64_ nu = new Base64_();
                //String p = NUEVO.DepurarXML(client.getMensajeActual());
                NUEVO.parsearString(client.getMensajeActual());
                
                NodeList lista = NUEVO.by_tagName("mBytes");
                byte[] m = Base64.decodeBase64(lista.item(0).getTextContent());
         
                FileUtils.writeByteArrayToFile(new File("pruebadeamor666.mp3"), m);
                MP3 N = new MP3();
                N.play("/home/cris/NetBeansProjects/Odissey_client-Java--master/pruebadeamor666.mp3");
*/
                       
                
        
        
        Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
 
