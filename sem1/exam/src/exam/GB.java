package exam;


import javax.swing.*;          	//This is the final package name.
import java.awt.*;
import java.awt.event.*;

public class GB {

    public Component createComponents() {
	JButton button;
	JPanel contentPane = new JPanel();
	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	contentPane.setLayout(gridbag);
	c.fill = GridBagConstraints.HORIZONTAL; 
	   
	button = new JButton("Button 1");
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 0;
	gridbag.setConstraints(button, c);
	contentPane.add(button);

	button = new JButton("2");
	c.weightx = 4;
	c.gridx = 1;
	c.gridy = 0;
	gridbag.setConstraints(button, c);
	contentPane.add(button);

	button = new JButton("Button 3");
	c.weightx = 8;
	c.gridx = 2;
	c.gridy = 0;
	gridbag.setConstraints(button, c);
	contentPane.add(button);

	button = new JButton("Long-Named Button 4");
	c.ipady = 40;      	//make this component tall
	c.weightx = 0.0;
	c.insets = new Insets(20,0,0,0);  	//top padding
	c.gridwidth = 3;
	c.gridx = 0;
	c.gridy = 1;
	gridbag.setConstraints(button, c);
	contentPane.add(button);

	button = new JButton("Button 5");
	c.ipady = 0;       	//reset to default
	c.weighty = 1.0;   	//request any extra vertical space
	c.anchor = GridBagConstraints.SOUTH; 	//bottom of space
	c.insets = new Insets(10,0,0,0);  	//top padding
	c.gridx = 1;       	//aligned with button 2
	c.gridwidth = 2;   	//2 columns wide
	c.gridy = 2;       	//third row
	gridbag.setConstraints(button, c);
	contentPane.add(button);
	    return contentPane;
    }

    public static void main(String[] args) {

	String lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
	
        try {
		UIManager.setLookAndFeel( lookAndFeel);
        } catch (Exception e) { }

        JFrame frame = new JFrame("GB");
        GB app = new GB();
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