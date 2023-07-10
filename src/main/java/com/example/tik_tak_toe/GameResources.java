package com.example.tik_tak_toe;

import javafx.scene.image.Image;

import java.io.File;

public class GameResources {
    public final static Image CROSS     = new Image(getPath("tiles\\cross.png"));
    public final static Image CROSS_WIN = new Image(getPath("tiles\\cross_win.png"));
    public final static Image TOE       = new Image(getPath("tiles\\toe.png"));
    public final static Image TOE_WIN   = new Image(getPath("tiles\\toe_win.png"));

    /**
     * Gets the path to the file in the resources folder
     * @param name name of the file
     * @return path to the file in the resources folder
     */
    private static String getPath(String name) {
        String absolutePath = new File("").getAbsolutePath();
        return absolutePath + "\\src\\main\\resources\\" + name;
    }
}
