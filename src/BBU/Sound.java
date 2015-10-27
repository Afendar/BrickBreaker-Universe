/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBU;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author portable mickael
 */
public class Sound {
    
    public static Sound brick = new Sound("sfx/brick.wav");
    public static Sound player = new Sound("sfx/player.wav");
    public static Sound loose = new Sound("sfx/loose.wav");
    
    public String path;
    
    private Sound(String path)
    {
        this.path = path;
    }
    
    public void play()
    {
        try{
            InputStream in = new FileInputStream(path);
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
