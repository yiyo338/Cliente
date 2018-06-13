/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import odysseyLogic.DocumentoXML;
import odysseyLogic.clientetcp;

/**
 * FXML Controller class
 *
 * @author cris
 */
public class SeleccionCancionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    @FXML
    private TextField nombre;

    @FXML
    private TextField artista;

    @FXML
    private TextField album;

    @FXML
    private Label mensaje;
    
    private String ruta;

    @FXML
    void seleccionar(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        fileChooser.setTitle("Open Resource File");
        this.ruta = selectedFile.getPath();
        mensaje.setText("Seleccionada");
    }
    
    @FXML
    void listo(ActionEvent event) throws ParserConfigurationException, IOException, TransformerException {
        DocumentoXML nueva = new DocumentoXML("comunicacion");
        nueva.crearHijos("codigo", "3");
        nueva.crearHijos("nombre", nombre.getText());
        nueva.crearHijos("artista", artista.getText());
        nueva.crearHijos("album", album.getText());
        nueva.crearHijos("path", ruta);
        
        clientetcp client = new clientetcp();
        java.net.Socket socket = client.crear();
        client.enviar(socket, nueva.ConvertirXML_String(), 1);
        
        Parent gui = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Scene creacionDocs = new Scene(gui);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creacionDocs);
        window.show();        
    }    

}
