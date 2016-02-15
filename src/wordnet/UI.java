package wordnet;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;

public class UI
{
	String inputPath = "";
    // set up the file chooser
    final static JFileChooser jfc = new JFileChooser(" ");
	
	public String getInputString(){
		return jfc.getSelectedFile().getPath();
		
	}
	
    static void readin(String fn, JTextComponent pane)
    {
        try
        {
            FileReader fr = new FileReader(fn);
            pane.read(fr, null);
            fr.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }

    public static void main(String args[])
    {
        final JFrame result= new JFrame("Result");
        result.setVisible(false);
        final JTextArea result_area=new JTextArea();
        final JFrame frame = new JFrame("File selector");
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        // set up the text pane, JTextPane

        final JTextComponent textpane = new JTextArea();

        // set up a scroll pane for the text pane

        final JScrollPane pane = new JScrollPane(textpane);
        pane.setPreferredSize(new Dimension(600, 600));



        // final JLabel elapsed = new JLabel("Elapsed time: ");

        JButton compute=new JButton("Check Reviews");
        JButton filebutton = new JButton("Choose File");
        filebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (jfc.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION)
                        return;
                File f = jfc.getSelectedFile();

                // record the current time and read the file

                frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                readin(f.toString(), textpane);

                // wait for read to complete and update time

                frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
             }
        });
        compute.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            try {

              
                Main.s1();
                if(POSTag.negativePercentage>POSTag.positivePercentage)
                {
                    result_area.append("The user reviews show that there is a negative opinion among people and the percentage of dislike is" +(-POSTag.negativePercentage) );
                    result_area.setEditable(false);
                    result.getContentPane().add(result_area);
                    result.pack();
                    result.setVisible(true);
                }
                else
                {
                    result_area.append("the user reviews show that there is a positive opinion among people and the percentage of like is" +(+POSTag.positivePercentage));
                    result_area.setEditable(false);
                    result.getContentPane().add(result_area);
                    result.pack();
                    result.setVisible(true);
                }
                
            
            } catch (MalformedURLException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }

        }) ;

        JPanel buttonpanel = new JPanel();
        buttonpanel.add(filebutton);
        buttonpanel.add(compute);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add("North", buttonpanel);
        panel.add("Center", pane);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

}