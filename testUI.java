import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.*;
import javax.swing.text.html.*;

public class testUI extends JFrame
{
    testUI()
    {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JEditorPane htmlTextPane = new JEditorPane();
        JScrollPane scrollPane = new JScrollPane(htmlTextPane);
        htmlTextPane.setEditorKit(new PreWrapHTMLEditorKit());
        htmlTextPane.setBounds(10,10,100,100);
        htmlTextPane.setEditable(false);
        htmlTextPane.setContentType("text/html");
        //htmlTextPane.setLineWrap(true);
        try {htmlTextPane.setText("<html>Could not load jakfkjasfasjf" +
                    "safjbajsfjas" +
                    "sfajbfkjabsfja" +
                    "fsajhfakjhfka" +
                    "fsajkfkjashkf" +
                    "sfafjkashkfsa" +
                    "flsajkasfksa" +
                    "faskfkjasbfjka" +
                    "fas" +
                    "vjbdkjbvskjv" +
                    "salfjkvdsjkbskjvs" +
                    "aakfsjaskjfasva</html>");
        }catch (Exception e){};
        scrollPane.setViewportView(htmlTextPane);
        /*final JScrollPane scrollPane = new JScrollPanehtmlTextPane);
        scrollPane.setHorizontalScrollBar(null);
                //,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scrollPane);*/
        //scrollPane.setViewportViewhtmlTextPane);
        //addhtmlTextPane;
        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(scrollPane);
        this.setSize(300, 200);
        /*try {
            editor.read(reader, pane.getDocument(), pane.getDocument().getLength());
        } catch (Exception e){};*/

    }
        public static void main(String[] args)
    {
        new testUI();
    }
}
class PreWrapHTMLEditorKit extends HTMLEditorKit {
    ViewFactory viewFactory = new HTMLFactory() {
        @Override
        public View create(Element elem) {
            AttributeSet attrs = elem.getAttributes();
            Object elementName = attrs
                    .getAttribute(AbstractDocument.ElementNameAttribute);
            Object o = (elementName != null) ? null : attrs
                    .getAttribute(StyleConstants.NameAttribute);
            if (o instanceof HTML.Tag) {
                HTML.Tag kind = (HTML.Tag) o;
                if (kind == HTML.Tag.IMPLIED) {
                    return new javax.swing.text.html.ParagraphView(elem);
                }
            }
            return super.create(elem);
        }
    };

    @Override
    public ViewFactory getViewFactory() {
        return this.viewFactory;
    }
}