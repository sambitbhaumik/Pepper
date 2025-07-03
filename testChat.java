import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
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

public class testChat
{
    String appname = "RoboBop";
    JFrame window = new JFrame(appname);
    JButton ask;

    static JTextArea chatBox;
    static String response="";
    static String username = "Sambit";
    JTextField usernameChooser;
    JFrame preFrame;
    static Chat chatSession;
    static Bot bot;
    static SpeechResult result;
    static Configuration configuration = new Configuration();
    static LiveSpeechRecognizer recognize;
    static String command;
    static ArrayList<String> comList = new ArrayList<>();

    String VOICENAME = "kevin16";

    public static void main(String[] args) throws IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader("corpus.txt"))) {
            while (br.ready()) {
                comList.add(br.readLine());
            }
        }
        testChat chat = new testChat();
        String resourcesPath = getResourcesPath();
        //System.out.println(resourcesPath);
        MagicBooleans.trace_mode = TRACE_MODE;
        bot = new Bot("super", resourcesPath);
        chatSession = new Chat(bot);
        bot.brain.nodeStats();

        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("0609.dic");
        configuration.setLanguageModelPath("0609.lm");
        recognize = new LiveSpeechRecognizer(configuration);
        recognize.startRecognition(true);
        chat.display();
        while((result = recognize.getResult()) != null) {
            //chat.display();
            result = recognize.getResult();
            command = result.getHypothesis();
            System.out.println(command);
            if (comList.contains(command.toLowerCase())) {    //Get the recognized speech
                String work = null;
                Process p;
                System.out.println("Here");
                //Some Extra Commands from my Corpus File
                                /*if(command.equalsIgnoreCase("open browser")) {
                                    work = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe www.google.com/";
                                }
                                else if(command.equalsIgnoreCase("Hello"))
                                    System.out.println(command);
                                if(work != null) {
                                    p = Runtime.getRuntime().exec(work);
                                }*/

                //if (MagicBooleans.trace_mode)
                //  chatBox.append("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
                //if (command.equalsIgnoreCase("Hello")) {
                chatBox.append(username + ":  " + command + "\n");
                response = chatSession.multisentenceRespond(command);
                    /*while (response.contains("&lt;"))
                        response = response.replace("&lt;", "<");
                    while (response.contains("&gt;"))
                        response = response.replace("&gt;", ">");*/
                chatBox.append("Robot:  " + response + "\n");
            }
        }
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

    public void display()
    {
        /*String resourcesPath = getResourcesPath();
        //System.out.println(resourcesPath);
        MagicBooleans.trace_mode = TRACE_MODE;
        bot = new Bot("super", resourcesPath);
        chatSession = new Chat(bot);
        bot.brain.nodeStats();*/

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        RoundedJTextField messageBox = new RoundedJTextField(30);
        ask = new JButton("<html><b><u>S</u>end Message</b></html>");
        ask.addActionListener(new sendMessageButtonListener());

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        //chatBox.setFont(new Font("Arial Nova", Font.BOLD, 15));
        chatBox.setBackground(Color.BLACK);
        chatBox.setForeground(Color.WHITE);
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(ask, right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);

        window.add(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(470, 300);
        window.setVisible(true);
    }


    class sendMessageButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (messageBox.getText().length() < 1 )
            {
                // do nothing
                /*//while ((result = recognize.getResult()) != null) {
                if(comList.contains(command.toLowerCase())) {    //Get the recognized speech
                    String work = null;
                    Process p;
                    System.out.println("Here");
                    //Some Extra Commands from my Corpus File
                                /*if(command.equalsIgnoreCase("open browser")) {
                                    work = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe www.google.com/";
                                }
                                else if(command.equalsIgnoreCase("Hello"))
                                    System.out.println(command);
                                if(work != null) {
                                    p = Runtime.getRuntime().exec(work);
                                }

                    //if (MagicBooleans.trace_mode)
                    //  chatBox.append("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
                    //if (command.equalsIgnoreCase("Hello")) {

                        chatBox.append(username + ":  " + command + "\n");
                        response = chatSession.multisentenceRespond(command);
                    /*while (response.contains("&lt;"))
                        response = response.replace("&lt;", "<");
                    while (response.contains("&gt;"))
                        response = response.replace("&gt;", ">");
                        chatBox.append("Robot:  " + response + "\n");
                        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
                        Voice voice;
                        VoiceManager vm = VoiceManager.getInstance();
                        //voice = vm.getVoice(VOICENAME);
                        //voice.allocate();
                        /*try {
                            voice.speak(response);
                          } catch (Exception e) {};*/
                        //messageBox.setText("");
                    //}*/
                //}
            }
            else
                {
                String textLine = "";
                textLine = messageBox.getText();
                chatBox.setFont(new Font("Arial Nova", Font.PLAIN, 15));
                chatBox.append(username + ":  " + textLine + "\n");
                messageBox.setText("");
                try {
                    if (textLine.equals("q")) {
                        System.exit(0);
                    } else {
                        String request = textLine;

                        //Check if recognizer recognized the speech
                           /* while ((result = recognize.getResult()) != null) {

                                //Get the recognized speech
                                String command = result.getHypothesis();
                                String work = null;
                                Process p;

                                //Some Extra Commands from my Corpus File
                                /*if(command.equalsIgnoreCase("open browser")) {
                                    work = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe www.google.com/";
                                }
                                else if(command.equalsIgnoreCase("Hello"))
                                    System.out.println(command);
                                if(work != null) {
                                    p = Runtime.getRuntime().exec(work);
                                }*/

                        if (MagicBooleans.trace_mode)
                            chatBox.append("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
                        response = chatSession.multisentenceRespond(request);
                        while (response.contains("&lt;"))
                            response = response.replace("&lt;", "<");
                        while (response.contains("&gt;"))
                            response = response.replace("&gt;", ">");
                        chatBox.setFont(new Font("Arial Nova", Font.PLAIN, 15));
                        chatBox.append("Robot:  " + response + "\n");

                        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
                        Voice voice;
                        VoiceManager vm = VoiceManager.getInstance();
                        voice = vm.getVoice(VOICENAME);
                        voice.allocate();
                            /*try {
                                voice.speak(response);
                            }catch (Exception e){};*/
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
}
class RoundedJTextField extends JTextField
{
    private Shape shape;

    public RoundedJTextField(int size)
    {
        super(size);
        setOpaque(false);
    }

    protected void paintComponent(Graphics g)
    {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g)
    {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    }

    public boolean contains(int x, int y)
    {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }
}