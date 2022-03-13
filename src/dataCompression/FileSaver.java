package dataCompression;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class FileSaver extends JFrame implements ActionListener {

	JFrame frame;
	final JFileChooser saveFile; 
	int status;//check if approved or canceled

	
	FileSaver(File InputFile, String FileRequester) throws IOException { //accept string for switch statement to enable ability to check where it was called from
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close frame when finished
		
		try {
			frame = new JFrame("Save Dialog"); 
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); //set look
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException 
				| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace(); // print stack if exception
		}

		saveFile = new JFileChooser("."); //instantiate new JFileChooser set to current directory
		
		JPanel panel = new JPanel();  //instantiate panel
		
		this.setLayout(new CardLayout()); //set layout
		
		this.add(saveFile); //add JChooser
		
		this.setLocationRelativeTo(null); //set to middle of the screen
		
		this.pack();// pack jChooser and JPanel, bug to avoid multiple popups
		
		panel.setVisible(true); //make visible
		
		saveFile.addActionListener(this);

		switch (FileRequester) { // check which class needs file
		
		case "RUN LENGTH COMPRESS": //if RUN LENGTH COMPRESS
		
			status = saveFile.showSaveDialog(null); // open save dialog
			
			if (status == JFileChooser.APPROVE_OPTION) { //if not canceled 
				
				File RunLengthCompressFile = saveFile.getSelectedFile().getAbsoluteFile(); //get absolute file name from save dialog
				
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when file is sent
				
				RunLengthCompress.compress(InputFile, RunLengthCompressFile);
			
			} else if (status == JFileChooser.CANCEL_OPTION) {//if cancel button is pushed
			
				saveFile.cancelSelection(); //cancel the FileChooser, close frame, exit program
			}
			break;

		case "RUN LENGTH EXPAND": //if run length needs the file
		
			status = saveFile.showSaveDialog(null);//get status
			
			if (status == JFileChooser.APPROVE_OPTION) {//check status and if approved
			
				File RunLengthExpandFile = saveFile.getSelectedFile().getAbsoluteFile(); //get file absolute file name
				
				RunLengthExpand.expand(InputFile, RunLengthExpandFile);
				
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close jFrame
		
			} else if (status == JFileChooser.CANCEL_OPTION) {//if cancel button is pushed
			
				saveFile.cancelSelection();//cancel everything and exit program
			}
			break;

		case "HUFFMAN EXPAND"://If HUffman Expand needs the file

			status = saveFile.showSaveDialog(null); //get status and open save dialog
			
			if (status == JFileChooser.APPROVE_OPTION) {//if check status is not set to cancel
			
				File huffmanExpandFile = saveFile.getSelectedFile().getAbsoluteFile();//get file to send to huffman expand
				
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close the frame when finished
				
				HuffmanExpand.expand(InputFile, huffmanExpandFile);
		
			} else if (status == JFileChooser.CANCEL_OPTION) {//if cancel status is selected
			
				saveFile.cancelSelection();//safely exit
			}
			break;

		case "HUFFMAN COMPRESS"://if huffman compress needs the file
			
			status = saveFile.showSaveDialog(null);//open save dialog and get status
			
			if (status == JFileChooser.APPROVE_OPTION) {//if not canceled
			
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close safely after sending file
				
				File HuffmanCompressFile = saveFile.getSelectedFile().getAbsoluteFile();//get file that was selected

				HuffmanCompress.compress(InputFile, HuffmanCompressFile);
				
			} else if (status == JFileChooser.CANCEL_OPTION) {//if cancel button is pushed
			
				saveFile.cancelSelection();//safely exit program
			}
			break;
		
		default:
			break;

		}
		
		//JOptionPane.getRootFrame().dispose();
		this.dispose();//nope
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//added to see if it helped and it didnt 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JOptionPane.getRootFrame().dispose();
		//this.dispose();
		
	
	}
}