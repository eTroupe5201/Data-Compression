package dataCompression;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import java.awt.CardLayout;

@SuppressWarnings("serial")
public class FileChooser extends JFrame implements ActionListener {

	JButton button;
	JFrame frame;
	String option;
	int status;
	JFileChooser fileChooser;
	String GuiInterfaceChoice;
	
	public FileChooser(String GuiInterfaceChoice) throws IOException {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close frame when complete
		try {
			frame = new JFrame("Data Compression");
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}

		fileChooser = new JFileChooser(".");//instantiate jFileChooser and set it to current directory
		JPanel panel = new JPanel();//instantiate JPanel
		this.setLayout(new CardLayout());//set the layout 
		this.add(fileChooser);//add file chooser to panel
		this.setLocationRelativeTo(null);//set to center of the screen
		this.pack();//pack, fixed double pop up
		panel.setVisible(true);//set to visible
		fileChooser.addActionListener(this);//add action listener

		switch (GuiInterfaceChoice) {//check which button was pushed on GUI
		//NOTE: in all caps because of implementation of buttons on GUI
		case "RUN LENGTH COMPRESS"://if run length compress 
			try {
				status = fileChooser.showOpenDialog(null);//get status
			
				if (status == JFileChooser.APPROVE_OPTION) {//if status is approved
					
				File RunLengthCompressFile = fileChooser.getSelectedFile().getAbsoluteFile();//get file from chooser
				
				new FileSaver(RunLengthCompressFile, GuiInterfaceChoice);
					
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close frame
				
				} else if (status == JFileChooser.CANCEL_OPTION) {// if status is equals cancel button
				
					fileChooser.cancelSelection();//exit 

				}
			} catch (FileNotFoundException e1) {
			
				e1.printStackTrace();
			}
			break;

		case "RUN LENGTH EXPAND"://if run length expand button was pushed
			status = fileChooser.showOpenDialog(null);//get status and open dialog box
			
			if (status == JFileChooser.APPROVE_OPTION) {//if the status is approve
				
				File RunLengthExpandFile = fileChooser.getSelectedFile().getAbsoluteFile();//get file from chooser
				
				new FileSaver(RunLengthExpandFile, GuiInterfaceChoice);//send file to Run Length Compress
				
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit frame safely
				
			} else if (status == JFileChooser.CANCEL_OPTION) {//if cancel button is pushed
			
				fileChooser.cancelSelection();//exit safely
			
			}
			break;

		case "HUFFMAN EXPAND"://if huffman expand button pushed on GUI
			
			status = fileChooser.showOpenDialog(null);//get status and open dialog box
			
			if (status == JFileChooser.APPROVE_OPTION) {//check status
				
				File huffmanExpandFile = fileChooser.getSelectedFile().getAbsoluteFile();//get the file from the file chooser
				
				new FileSaver(huffmanExpandFile, GuiInterfaceChoice);//send file to Run Length Compress
				
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close jFrame when complete
			
			} else if (status == JFileChooser.CANCEL_OPTION) {//if cancel is pushed 
			
				fileChooser.cancelSelection();//exit program
			}
			break;

		case "HUFFMAN COMPRESS"://if huffman compress is pushed
		
			status = fileChooser.showOpenDialog(null);//open dialog box and get the status
			
			if (status == JFileChooser.APPROVE_OPTION) {//check status to make sure appoved
			
				File HuffmanCompressFile = fileChooser.getSelectedFile().getAbsoluteFile();//get the file 
				
				new FileSaver(HuffmanCompressFile, GuiInterfaceChoice);//send file to Run Length Compress
			
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close frame
			
			} else if (status == JFileChooser.CANCEL_OPTION) {//if cancel is pushed
			
				fileChooser.cancelSelection();//exit program
			}
			break;
		case "BINARY DUMP"://if binary dump button is pushed
			
			status = fileChooser.showOpenDialog(null);//open the dialog
			
			if (status == JFileChooser.APPROVE_OPTION) {//check the status to make sure cance is not pushed
				
				File BinaryDumpFile = fileChooser.getSelectedFile().getAbsoluteFile();//get file
				
				BinaryDump.dump(BinaryDumpFile);//send the file to binary dump
				
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close frame
			
			} else if (status == JFileChooser.CANCEL_OPTION) {//if cancel is pushed
				fileChooser.cancelSelection();//exit 
			}
			break;

		default:
			break;

		}
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.getRootFrame().dispose();//handles bug
		this.dispose();
		return;
	}
}
