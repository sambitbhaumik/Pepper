import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLabel;
import javax.swing.text.*;
import java.io.*;
import java.util.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;
import com.sun.speech.freetts.*;

public class Main extends javax.swing.JFrame
{
    static String response="";
    static String username = "Sambit";
    static Chat chatSession;
    static Bot bot;
    static SpeechResult result;
    static Configuration configuration = new Configuration();
    static LiveSpeechRecognizer recognize;
    static String command;
    static HTMLEditorKit editor = new HTMLEditorKit();
    static HTMLDocument doc = new HTMLDocument();
    static JTextField messageBox;
    static String work = null;
    static Process p;
    static String VOICENAME = "mbrola_us1";

    static String dir = System.getProperty("user.dir");
    static File folder;
    static File[] files;
    static Random rand;
    static File file;
    static String mfile;
    static Mp3Player mp3;

    public Main()
    {
        ImageIcon siri = new ImageIcon("siri2.gif");
        ImageIcon logo = new ImageIcon("pepperlogo.png");
        setIconImage(logo.getImage());

        setTitle("Pepper Assistant");
        JLabel sri = new JLabel(siri);
        Color newsri = new Color(27,27,27);
        Color bg = new Color(40,44,53);

        JEditorPane editorPane = new JEditorPane("text/html",null);
        editorPane.setBackground(bg);

        final JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //editorScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.setPreferredSize(new Dimension(900,650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        messageBox = new JTextField(30);
        JButton ask = new JButton("<html><b>Ask</b></html>");
        ask.addActionListener(new sendMessageButtonListener());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(newsri);
        southPanel.setLayout(new GridBagLayout());

        mainPanel.add(editorScrollPane, BorderLayout.CENTER);
        mainPanel.add(sri, BorderLayout.NORTH);

        GridBagConstraints top = new GridBagConstraints();
        top.insets = new Insets(0, 10, 0, 10);
        top.anchor = GridBagConstraints.FIRST_LINE_START;
        top.fill = GridBagConstraints.HORIZONTAL;

        GridBagConstraints left = new GridBagConstraints();
        left.insets = new Insets(0, 10, 0, 10);
        left.anchor = GridBagConstraints.LINE_END;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 10);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(sri, top);
        southPanel.add(messageBox, left);
        southPanel.add(ask, right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);

        File f = new File("pepper.png");
        String s="<html><img src=\"file:"+f.toString()+"\" /><p style=\"color:white;padding:5px 10px 5px 16px;font-size:16px;\" >Howdy, Say <b color=orange >Hi Pepper</b> to get started or type something.</p></html>";

        getContentPane().setLayout(new BorderLayout());
        editorPane.setEditorKit(editor);
        editorPane.setDocument(doc);
        try {
            editor.insertHTML(doc, doc.getLength(),s,0,0,null);
        } catch (Exception e){};
        editorPane.setEditable(false);
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
    private static final boolean TRACE_MODE = false;
    static String botName = "super";

    public static String getResourcesPath()
    {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        //System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }

    class sendMessageButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (messageBox.getText().length() < 1 )
            {
                // do nothing
            }
            else
            {
                String textLine = "";
                textLine = messageBox.getText();
                try
                {
                    editor.insertHTML(doc, doc.getLength(),"<p style=\"color:Red;padding:5px 10px 0 15px;\" >Human</p>"+"\n"+"<p style=\"color:silver;padding:1px 10px 10px 15px;font-family:Arial Nova;font-size:14px;\" >"+textLine+"</p>",0,0,null);
                }catch(Exception e){};
                //chatBox.append(username + ":  " + textLine + "\n");
                messageBox.setText("");
                try {
                    if (textLine.equals("q")) {
                        System.exit(0);
                    } else
                        {
                        String request = textLine;
                        response = chatSession.multisentenceRespond(request);

                        if (textLine.equalsIgnoreCase("open browser")) {
                            response="Opening Browser";
                            speak(response);
                            work = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe";
                            p = Runtime.getRuntime().exec(work);

                        }
                        else if (textLine.equalsIgnoreCase("play some music"))
                        {
                            response="Great. Playing music";
                            System.out.println(mfile);
                            speak(response);
                            mp3.play();
                            //
                        }
                        else if (textLine.equalsIgnoreCase("stop music")) {
                            mp3.close();
                            response="Done";
                            speak(response);
                        }
                        else {
                            speak(response);
                        }
                        //messageBox.setText("");
                    }
                    //}

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            messageBox.requestFocusInWindow();
        }
    }
    public static void speak(String response)
    {
        try {
            editor.insertHTML(doc, doc.getLength(), "<p style=\"color:blue;padding:1px 10px 0 15px;\" >Pepper</p>"+"\n"+"<p color=white style=\"font-family:Arial Nova;padding:1px 10px 10px 15px;font-size:15px;\" >"+response+"</p>", 0, 0, null);
        } catch (Exception e) {
        }
        ;
        System.setProperty("freetts.voices", "de.dfki.lt.freetts.en.us.MbrolaVoiceDirectory");
        System.setProperty("mbrola.base", "C:/mbrola");
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(VOICENAME);
        voice.allocate();
        try {
            voice.speak(response);
        } catch (Exception e) {};
    }

    public static void main(String args[])throws IOException
    {
        ArrayList<String> comList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("corpus.txt"))) {
            while (br.ready()) {
                comList.add(br.readLine());
            }
        }
        //testChat chat = new testChat();
        String resourcesPath = getResourcesPath();
        //System.out.println(resourcesPath);
        MagicBooleans.trace_mode = TRACE_MODE;
        bot = new Bot("super", resourcesPath);
        chatSession = new Chat(bot);
        bot.brain.nodeStats();

        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("1457.dic");
        configuration.setLanguageModelPath("1457.lm");
        recognize = new LiveSpeechRecognizer(configuration);
        recognize.startRecognition(true);
//        chat.display();
        dir= System.getProperty("user.dir");
        dir+="/music";
        folder= new File(dir);
        files = folder.listFiles();
        rand = new Random();
        file = files[rand.nextInt(files.length)];
        mfile=file.getName();
        mp3 = new Mp3Player(file.getName());

        new Main().setVisible(true);
        while((result = recognize.getResult()) != null)
        {
            //chat.display();
            //result = recognize.getResult();
            command = result.getHypothesis();
            System.out.println(command);
            command=command.toLowerCase();
            //if (comList.contains(command.toLowerCase()))
            if(command.contains("pepper") && comList.contains(command.toLowerCase()))
            {    //Get the recognized speech
                try {
                    editor.insertHTML(doc, doc.getLength(), "<p style=\"color:red;padding:3px 10px 0 15px;\" >Human</p>"+"\n"+"<p style=\"color:silver;padding:1px 10px 10px 15px;font-family:Arial Nova;font-size:14px;\" >"+command+"</p>",0, 0, null);
                } catch (Exception e) {};
                response="Hi, Let's get started";
                speak(response);
                while((result = recognize.getResult()) != null)
                {
                    command = result.getHypothesis();
                    System.out.println(command);
                    if (comList.contains(command.toLowerCase()))
                    {
                    //System.out.println(command);
                    try {
                        editor.insertHTML(doc, doc.getLength(), "<p style=\"color:red;padding:3px 10px 0 15px;\" >Human</p>"+"\n"+"<p style=\"color:silver;padding:1px 10px 10px 15px;font-family:Arial Nova;font-size:14px;\" >"+command+"</p>", 0, 0, null);
                    } catch (Exception e) {};
                        if (command.equalsIgnoreCase("open browser")) {
                            work = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe";
                            p = Runtime.getRuntime().exec(work);
                        }
                        else if (command.equalsIgnoreCase("play some music"))
                        {
                            response="Great. Playing music";
                            System.out.println(mfile);
                            speak(response);
                            mp3.play();
                        }
                        else
                        {
                            System.out.println("AIML Response");
                            response = chatSession.multisentenceRespond(command);
                            speak(response);
                        }
                    }
                }
            }
        }
    }
}
