package dataCompression;

import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class GuiUserInterface extends JFrame implements ActionListener{
	JFrame frame;
	String choice;
	JButton button;
	String name;
	
	GuiUserInterface() throws IOException {

		try {
			frame = new JFrame("GUI Options");
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");//set the look, catch any exception
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		Container contentPane = frame.getContentPane();//add the frame to a container 
		
		//make buttons for each option
		JButton BinaryDump= new JButton("BINARY DUMP"); 
		JButton RunLengthCompress = new JButton("RUN LENGTH COMPRESS");
		JButton RunLengthExpand = new JButton("RUN LENGTH EXPAND");
		JButton HuffmanCompress = new JButton("HUFFMAN COMPRESS");
		JButton HuffmanExpand = new JButton("HUFFMAN EXPAND");
		
		//add all of the buttons to the contentPane
		contentPane.add(BinaryDump);
		contentPane.add(RunLengthCompress);
		contentPane.add(RunLengthExpand);
		contentPane.add(HuffmanCompress);
		contentPane.add(HuffmanExpand);

		//set the font for each button
		Font font = new Font("ARIAL", Font.PLAIN, 14);
		BinaryDump.setFont(font);
		RunLengthCompress.setFont(font);
		RunLengthExpand.setFont(font);
		HuffmanCompress.setFont(font);
		HuffmanExpand.setFont(font);
		
		this.add(contentPane);
		this.setLocationRelativeTo(null);//Center
		this.pack();
		this.setSize(450, 500);//size of GUI
		this.setVisible(true);
		
		//add action listeners for each button
		BinaryDump.addActionListener(this);
		RunLengthExpand.addActionListener(this);
		RunLengthCompress.addActionListener(this);
		HuffmanExpand.addActionListener(this);
		HuffmanCompress.addActionListener(this);

		//set layout, center, and exit when complete
		frame.setLayout(new GridLayout(5, 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	
		
	}
	
	 protected void processWindowEvent(WindowEvent e) {
         if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
         }
      }
	@Override
	public void actionPerformed(ActionEvent e) {
		//Get the test of the button to send to file chooser
		button = (JButton) e.getSource();
		//JOptionPane.getRootFrame().dispose();
		this.dispose();
		try {
			
			//figure out for cancel without throwing exception	
			name = button.getText();//get the name of the button pushed by user
			if(name == "BINARY DUMP" || name == "RUN LENGTH COMPRESS" || name == "RUN LENGTH EXPAND" || name == "HUFFMAN COMPRESS" || name == "HUFFMAN EXPAND" ) {
				new FileChooser(name);//send to the file chooser
				}else {
					processWindowEvent(null); // fix the last bug
					}
				
			
			
		} catch (IllegalArgumentException e1) {
		//e1.printStackTrace();
		} catch (Exception e1) {
		//	e1.printStackTrace();
		}
	}

}

