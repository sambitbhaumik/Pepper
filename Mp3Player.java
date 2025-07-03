import java.io.*;
import java.util.*;
import javazoom.jl.player.Player;


public class Mp3Player {

    private String filename;
    private Player player;


    public Mp3Player(String filename) {
 this.filename = filename;
    }


    public void play() {
 try {
            BufferedInputStream buffer = new BufferedInputStream(
    new FileInputStream(filename));
     player = new Player(buffer);
     player.play();
 }
 catch (Exception e) {

     System.out.println(e);
 }

    }
    public void close()
    {
        try {
            /*BufferedInputStream buffer = new BufferedInputStream(
                    new FileInputStream(filename));
            player = new Player(buffer);*/
            player.close();
        }
        catch (Exception e) {

            System.out.println(e);
        }
    }

    public static void main(String[] args) {
      String dir= System.getProperty("user.dir");
      dir+="/music";

File folder= new File(dir);

File[] files = folder.listFiles();

Random rand = new Random();

File file = files[rand.nextInt(files.length)];
String mfile=file.getName();
System.out.println(mfile);
 Mp3Player mp3 = new Mp3Player(file.getName());
 mp3.play();

    }

}