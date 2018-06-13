/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyLogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author cris
 */
public class DocumentoXML {
    	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private Document doc;

    public Document getDoc() {
        return doc;
    }
        private Element rootElement;
        
            public DocumentoXML(String nombrePeticion) throws ParserConfigurationException{
            this.docFactory = DocumentBuilderFactory.newInstance();
            this.docBuilder = docFactory.newDocumentBuilder();
            this.doc = docBuilder.newDocument();
            this.rootElement = doc.createElement(nombrePeticion);
            doc.appendChild(rootElement);            
            
        }
        
        public String ConvertirXML_String() throws TransformerConfigurationException, TransformerException, IOException{
            	TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                StringWriter writer = new StringWriter();
                transformer.transform(new DOMSource(doc), new StreamResult(writer));
                String output = writer.getBuffer().toString().replaceAll("\n|\r", "");          
   
		System.out.println("File saved! " + output);
                return output;
        }
        
        
        public void crearHijos(String NombreElemento, String Atributo){
                Element nuevo = doc.createElement(NombreElemento);
		nuevo.appendChild(doc.createTextNode(Atributo));
		rootElement.appendChild(nuevo);
        }
}
