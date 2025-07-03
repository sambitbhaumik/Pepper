//Icon icon = new ImageIcon("E:\\editicon.PNG");
  //      JButton button7 = new JButton(icon);
import java.awt.*;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.event.*;
public class mic extends JFrame implements ActionListener
{

    JFrame f=new JFrame();
    JTextField t;
    JButton b;
    String input,com;
    int n;
    Icon icon;

    static SpeechResult result;
    static Configuration configuration = new Configuration();
    static LiveSpeechRecognizer recognize;
    //Image img = ImageIO.read(getClass().getResource("mic.png"));
    mic()
    {
        ImageIcon h = new ImageIcon("mic.png");
        Image img = h.getImage();
        t=new JTextField();
        t.setBounds(20,100,150,30);
        t.setEditable(false);
        Image newimg = img.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        b = new JButton(newIcon);
        //b= new JButton(new ImageIcon(((new ImageIcon("preview.gif")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

        //b.setIcon(icon);
        b.addActionListener(this);
        b.setBounds(100,300,60,60);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        f.add(b);
        f.add(t);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == b)
        {
            ImageIcon icon = new ImageIcon("siri2.gif");

            JOptionPane.showMessageDialog(null, "Say something...","Ask Robot", JOptionPane.INFORMATION_MESSAGE, icon);

            //input=JOptionPane.showInputDialog(this,"Say something... ");
            try {
                recognize = new LiveSpeechRecognizer(configuration);

                String command = "";
                //SpeechResult result;


                //LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);
                recognize.startRecognition(true);

               while((result = recognize.getResult()) != null) {
                    //chat.display();
                    //result = recognize.getResult();
                    command = result.getHypothesis();
                    System.out.println(command);
                    t.setText(command);
                    break;
                }
            }catch (Exception e){};


        }
    }
    public static void main(String[] args)
    {
        new mic();
        configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("0609.dic");
        configuration.setLanguageModelPath("0609.lm");
        recognize=null;
    }
}