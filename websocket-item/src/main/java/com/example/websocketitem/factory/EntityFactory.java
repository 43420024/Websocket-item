package com.example.websocketitem.factory;

import com.example.websocketitem.model.AlbumPicture;
import com.example.websocketitem.model.Relationship;


public class EntityFactory {
    public static AlbumPicture createAlbumPicture(){
        return new AlbumPicture();
    }

    public static Relationship createRelationship(){return new Relationship();}
}
