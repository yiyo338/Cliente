/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyLogic;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author bamdres16
 */
public class Song_item {
   private String album;
   private String song_name;
   private String artist;


    public Song_item(String album, String song_name, String artist) {
        this.album = album;
        this.song_name = song_name;
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

  
   
}
