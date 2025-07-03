import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.event.*;
import com.sun.speech.freetts.*;
import java.io.*;
public class TTS extends JFrame
{
    JTextArea chatBox;
    String VOICENAME="kevin16";
    JFrame window =new JFrame("TTS");
    JButton speak;
    //System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    TTS()
    {
        //initComponents();
        window.setLayout(null);
        speak=new JButton("Speak");
        speak.setBounds(300,100,100,30);
        speak.addActionListener(new speakListener());
        window.add(speak);

        chatBox = new JTextArea(100,25);
        chatBox.setBounds(5,10,200,200);
        chatBox.setEditable(true);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        //chatBox.setSize(200,200);
        window.add(chatBox);

        window.setSize(470,300);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    class speakListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            System.setProperty("freetts.voices","de.dfki.lt.freetts.en.us.MbrolaVoiceDirectory");
            //System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            System.setProperty("mbrola.base", "C:/mbrola");
            Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice("mbrola_en1");
            voice.allocate();
            try {
                voice.speak(chatBox.getText());
            }catch (Exception e){};
        }
    }

    public static void main(String[] args)
    {
        TTS t=new TTS();
    }
}