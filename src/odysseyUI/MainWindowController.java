/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyUI;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import odysseyLogic.Cancion;
import odysseyLogic.DocumentoXML;
import odysseyLogic.MP3;
import odysseyLogic.ReproductorMp3;
import odysseyLogic.Song_item;
import odysseyLogic.XML_Parser;
import odysseyLogic.clientetcp;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author cris
 */
public class MainWindowController implements Initializable {
    @FXML
    private TextField userTextField;

    @FXML
    private Button ingresarBTN;

    @FXML
    private Button registrarseBTN;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ImageView imageLogo;
        
    @FXML
    private Label cancion;
    
    
    @FXML
    private ListView<String> listaCanciones;
       
    @FXML Button stop;
    @FXML Button start;
    @FXML Button resume;
    @FXML Button pause;

    @FXML private Media md;
    @FXML Label init;
    @FXML Label end;
    
            
    private ReproductorMp3 repro = ReproductorMp3.getSingletonInstance();
    
    public String ruta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {try {
        SincronizarDatos();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        resume.setDisable(true);
        stop.setDisable(true);
        pause.setDisable(true);
        
        
    }

    
    
    @FXML
    void EliminarCancion(ActionEvent event) {
        
    }
    
    void SincronizarDatos() throws ParserConfigurationException, ParserConfigurationException, IOException, TransformerException, UnsupportedEncodingException, SAXException {
        DocumentoXML nuevo = new DocumentoXML("comunicacion");
        nuevo.crearHijos("codigo", "4");
        clientetcp cliente = new clientetcp();
        java.net.Socket socketcito = cliente.crear();    
        cliente.enviar(socketcito, nuevo.ConvertirXML_String(), 1);  
        XML_Parser parse = new XML_Parser(); 
        System.out.println("soy este: " + cliente.getMensajeActual());
        parse.parsearString(cliente.getMensajeActual());

        NodeList nombres = parse.by_tagName("nombre");
        NodeList artistas = parse.by_tagName("artista");
        NodeList albums = parse.by_tagName("album");
        getSongs(artistas, albums, nombres);
    }        
   
    @FXML
    void seleccionDeCancion(ActionEvent event) throws IOException {
            Parent gui = FXMLLoader.load(getClass().getResource("seleccionCancion.fxml"));
            Scene creacionDocs = new Scene(gui);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(creacionDocs);
            window.show();
    }
        
    void streaming() throws ParserConfigurationException, IOException, TransformerException, UnsupportedEncodingException, SAXException{ 
        repro.streaming(md);
    }

    @FXML
    void Pause(ActionEvent event) {
        resume.setDisable(false);
        repro.Pause();
    }

    @FXML
    void Play(ActionEvent event) throws ParserConfigurationException, IOException, TransformerException, UnsupportedEncodingException, SAXException  {
        resume.setDisable(true);
        stop.setDisable(false);
        pause.setDisable(false);
        
        streaming();
        //repro.Play(ruta);
    }

    @FXML
    void Resume(ActionEvent event) {
        repro.Resume();
    }

    @FXML
    void Stop(ActionEvent event) {
        
        repro.Stop();
    }
    public void getSongs (NodeList artistas,NodeList albums, NodeList nombres){
        ObservableList<String> items =FXCollections.observableArrayList();
        for(int x = 0; x < artistas.getLength(); x++){
            String item = nombres.item(x).getTextContent()+"-"+artistas.item(x).getTextContent()+"-"+albums.item(x).getTextContent();
            items.add(item);
        }
        listaCanciones.setItems(items);
        
    }   
    
    @FXML
    void prueba(ActionEvent event) {
        try {
            SincronizarDatos();
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

    @FXML
    void repro(ActionEvent event) throws ParserConfigurationException, IOException, TransformerException {
        String x = listaCanciones.getSelectionModel().getSelectedItem();
        String nombre = "";
        int y = 0;
        while(x.charAt(y) != '-'){
            nombre = nombre + x.charAt(y);
            y++;
        }
        System.out.println("Nombre: " + nombre);
        DocumentoXML nuevo = new DocumentoXML("comunicacion");
        nuevo.crearHijos("codigo", "5");
        nuevo.crearHijos("nombre", nombre);
        clientetcp cliente = new clientetcp();
        java.net.Socket socketcito = cliente.crear();    
        cliente.enviar(socketcito, nuevo.ConvertirXML_String(), 5);  
        cancion.setText("Cancion actual: " + nombre);
    }    
}
