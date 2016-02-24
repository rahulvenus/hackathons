package exam;

import javax.swing.*;          //This is the final package name.
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    private JLabel label = null;
    private int numClicks = 0;

    public Component createLabel() {
        final JLabel label = new JLabel("0");
        label.setText("0");
        label.setHorizontalAlignment(JLabel.RIGHT);
	this.label = label;
        return label;
    }

    public Component createButtons() {
        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createLoweredBevelBorder());
				// 5 rows 2 collums //
        pane.setLayout(new GridLayout(3,3));
	for ( int index = 0; index < 16; index ++ )	{
		JButton button = new JButton(new Integer(index).toString());
		button.addActionListener(
		  new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
				label.setText(e.getActionCommand());
		       }
                   }
                );
		pane.add(button);
	}

        return pane;
    }
    public Component createOps() {
        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createLoweredBevelBorder());
				
        pane.setLayout(new GridLayout(2, 2));
	pane.add(new Button("+"));
	pane.add(new Button("-"));
	pane.add(new Button("*"));
	pane.add(new Button("/"));

        return pane;
    }
    public Component createComponents() {
        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createLoweredBevelBorder());
				
        pane.setLayout(new FlowLayout( FlowLayout.CENTER,100,100) );
	pane.add(createLabel());
	pane.add(createButtons());
	pane.add(createOps());
	pane.add(createButtons());
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
        JFrame frame = new JFrame("Calculator");
        Calculator app = new Calculator();
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