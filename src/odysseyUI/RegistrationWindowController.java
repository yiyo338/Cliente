/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import odysseyLogic.DocumentoXML;
import odysseyLogic.XML_Parser;
import odysseyLogic.clientetcp;

/**
 * FXML Controller class
 *
 * @author cris
 */
public class RegistrationWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private CheckBox Rock;

    @FXML
    private CheckBox Electro;

    @FXML
    private CheckBox HipHop;

    @FXML
    private CheckBox Metal;

    @FXML
    private CheckBox Reggaeton;

    @FXML
    private CheckBox Rap;
    
    @FXML
    private TextField UsuarioTextField;

    @FXML
    private PasswordField PasswordTextField;

    @FXML
    private PasswordField PasswordTextField2;
    
    @FXML
    private TextField NombreTextField;
    

    @FXML
    private Button VolverBTN;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    void cambiarPaginaLogin(ActionEvent event) throws IOException {
        Parent gui = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
        Scene creacionDocs = new Scene(gui);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(creacionDocs);
        window.show();
    }
    @FXML
    void registrar(ActionEvent event) throws ParserConfigurationException, IOException, TransformerException {
        if(UsuarioTextField.getText().length() != 0 & NombreTextField.getText().length() != 0){
            if(PasswordTextField.getText().length() >= 8){
                if(PasswordTextField.getText().equals(PasswordTextField2.getText())){
                    DocumentoXML registro = new DocumentoXML("comunicacion");
                    registro.crearHijos("codigo", "1");
                    registro.crearHijos("usuario", UsuarioTextField.getText());
                    registro.crearHijos("nombre", NombreTextField.getText());
                    registro.crearHijos("contrasena", PasswordTextField.getText());
                    registro = verificarGéneros(registro);
                    System.out.println(registro.ConvertirXML_String());
                    clientetcp client = new clientetcp();
                    java.net.Socket socket = client.crear();
                    client.enviar(socket, registro.ConvertirXML_String(),1);
                    System.out.println(client.getMensajeActual());                        
                }else{
                    //Colocar ventana emergente
                    System.out.println(1);
                }
            }else{
                System.out.println(2);
                //Colocar ventana emergente
            }
        }else{
            System.out.println(3);
            //Colocar ventana emergente
        }
        
    }        
 
    
    public DocumentoXML verificarGéneros(DocumentoXML xml){
        String generos = ""; 
        if(Rock.isSelected()){
            generos = generos + "+rock+";
        }
        if(Electro.isSelected()){
            generos = generos + "+electronica+";                    
        }
        if(HipHop.isSelected()){
            generos = generos + "+hiphop+";          
        }         
        if(Metal.isSelected()){
            generos = generos + "+metal+";           
        }
        if(Reggaeton.isSelected()){
           generos = generos + "+reggaeton+";           
        }        
        if(Rap.isSelected()){
            generos = generos + "+rap+";            
        }
        xml.crearHijos("generos", generos);
        return xml;
    }
    
}
