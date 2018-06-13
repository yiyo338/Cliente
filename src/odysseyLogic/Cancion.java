/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyLogic;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author cris
 */
public class Cancion {
    
    private final SimpleStringProperty Nombre;
    private final SimpleStringProperty Artista;
    private final SimpleStringProperty Album;
    
    public Cancion(String nombre,String artista, String album){
        this.Nombre = new SimpleStringProperty(nombre);
        this.Artista = new SimpleStringProperty(artista);
        this.Album = new SimpleStringProperty(album);
    }

    public SimpleStringProperty getNombre() {
        return Nombre;
    }

    public SimpleStringProperty getArtista() {
        return Artista;
    }

    public SimpleStringProperty getAlbum() {
        return Album;
    }
    
}
