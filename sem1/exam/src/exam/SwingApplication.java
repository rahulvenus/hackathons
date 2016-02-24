package exam;

import javax.swing.*;          //This is the final package name.
import java.awt.*;
import java.awt.event.*;

public class SwingApplication {
    private static String labelPrefix = "So many clicks: ";
    private int numClicks = 0;

    public Component createComponents() {
        final JLabel label = new JLabel(labelPrefix + "0    ");

        JButton button = new JButton("Swing button!");
        
        button.addActionListener(
		new ActionListener() {
                       public void actionPerformed(ActionEvent e) {
                           numClicks++;
                           label.setText(labelPrefix + numClicks);
			   System.out.println("getActionCommand: " + 
				e.getActionCommand());
			   System.out.println("getModifiers: " + 
				e.getModifiers());
			   System.out.println("paramString: " + 
				e.paramString());
                       }
                }
            );
        label.setLabelFor(button);

        /*
         * An easy way to put space between a top-level container
         * and its contents is to put the contents in a JPanel
         * that has an "empty" border.
         */
        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createEmptyBorder(
                                        30, //top
                                        30, //left
                                        10, //bottom
                                        30) //right
                                        );
				// rows column
        //pane.setLayout(new GridLayout(4, 1));
        pane.setLayout(new FlowLayout());
        pane.add(button);
        pane.add( new JFileChooser()) ;
        pane.add(new JCheckBox("not me", true) );
        pane.add(label);

        return pane;
    }

    public static void main(String[] args) {
	String lookAndFeel;

	lookAndFeel=UIManager.getCrossPlatformLookAndFeelClassName();
	if ( args.length == 1 )
	{
		if ( args[0].equals("motif") )
		   lookAndFeel =
			"com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		if ( args[0].equals("metal") )
		   lookAndFeel =
			"javax.swing.plaf.metal.MetalLookAndFeel";
		else if ( args[0].equals("system") )
		   lookAndFeel=
			UIManager.getSystemLookAndFeelClassName() ;
	}
        try {
            UIManager.setLookAndFeel( lookAndFeel);
        } catch (Exception e) { }

        //Create the top-level container and add contents to it.
        JFrame frame = new JFrame("SwingApplication");
        SwingApplication app = new SwingApplication();
        Component contents = app.createComponents();
        frame.getContentPane().add(contents);

        //Finish setting up the frame, and show it.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
}