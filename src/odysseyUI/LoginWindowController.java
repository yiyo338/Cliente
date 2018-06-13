/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyUI;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javazoom.jl.decoder.JavaLayerException;
import odysseyLogic.DocumentoXML;
import odysseyLogic.XML_Parser;
import odysseyLogic.ReproductorMp3;
import odysseyLogic.clientetcp;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author cris
 */
public class LoginWindowController implements Initializable {
    
    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordTextField;
    
    @FXML
    private Button registrarseBTN;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void cambiarPaginaRegistro(ActionEvent event) throws IOException {        
        Parent gui = FXMLLoader.load(getClass().getResource("RegistrationWindow.fxml"));
        Scene creacionDocs = new Scene(gui);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creacionDocs);
        window.show();
              
    }
    
    @FXML
    void validarDatos(ActionEvent event) throws ParserConfigurationException, TransformerException, TransformerConfigurationException, IOException, UnsupportedEncodingException, SAXException {       
        DocumentoXML nuevo = new DocumentoXML("comunicacion");
        nuevo.crearHijos("usuario", userTextField.getText());
        nuevo.crearHijos("contrasena", passwordTextField.getText());
        nuevo.crearHijos("codigo", "2");
        if(loginUsuario(nuevo.ConvertirXML_String())){
            Parent gui = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            Scene creacionDocs = new Scene(gui);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(creacionDocs);
            window.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error de inicio de sesión");
                    alert.setContentText("Usuario o contraseña inválidos");

                    alert.showAndWait();
        }
                               

        
    }
    
   public boolean loginUsuario(String XML) throws IOException, ParserConfigurationException, UnsupportedEncodingException, SAXException, TransformerException{
        clientetcp client = new clientetcp();
        java.net.Socket socket = client.crear();
        client.enviar(socket, XML, 1);
        System.out.println(client.getMensajeActual());
        XML_Parser parser = new XML_Parser();
        parser.parsearString(client.getMensajeActual());
        NodeList nodos = parser.by_tagName("usuario");
       
       return "true".equals(nodos.item(0).getTextContent());
   }
    
}

