package dataCompression;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FileSaver extends JFrame implements ActionListener{
	     JFrame frame; 
		final JFileChooser saveFile;
		JButton button;
		int status;
		
		FileSaver(String FileRequester) throws IOException{
	
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	try {
	    		frame = new JFrame("Save Dialog");
	    		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	            ex.printStackTrace();
	        }
	    	
	    	saveFile = new JFileChooser(".");
			JPanel panel = new JPanel();
			this.setLayout(new CardLayout());
		    this.add(saveFile);
			this.setLocationRelativeTo(null);
			this.pack();//avoid double pop up
			panel.setVisible(true);
			saveFile.addActionListener(this);
			
			
			
			switch(FileRequester) {
			case "RUN LENGTH COMPRESS":
				status = saveFile.showSaveDialog(null);
				File RunLengthCompressFile = saveFile.getSelectedFile().getAbsoluteFile();
      
				if (status == JFileChooser.APPROVE_OPTION) {
				
					RunLengthCompress.RecieveOutputFile(RunLengthCompressFile);
				} else if (status == JFileChooser.CANCEL_OPTION) {
					System.out.println("canceled");
				}
				break;

			case "RUN LENGTH EXPAND":
				status = saveFile.showSaveDialog(null);
		        
				File RunLengthExpandFile = saveFile.getSelectedFile().getAbsoluteFile();
		       
				if (status == JFileChooser.APPROVE_OPTION) {	
					RunLengthExpand.RecieveOutputFile(RunLengthExpandFile);
				} else if (status == JFileChooser.CANCEL_OPTION) {
					System.out.println("canceled");
				}
				break;

			case "HUFFMAN EXPAND":
				status = saveFile.showSaveDialog(null);
		        File huffmanExpandFile = saveFile.getSelectedFile().getAbsoluteFile();
		       
				if (status == JFileChooser.APPROVE_OPTION) {
					
					HuffmanExpand.RecieveOutputFile(huffmanExpandFile);
				}
				else if (status == JFileChooser.CANCEL_OPTION) {
					System.out.println("canceled");
				}
				break;

			case "HUFFMAN COMPRESS":
			
				status = saveFile.showSaveDialog(null);
		        File HuffmanCompressFile = saveFile.getSelectedFile().getAbsoluteFile();
		       
				if (status == JFileChooser.APPROVE_OPTION) {
					
					HuffmanCompress.RecieveOutputFile(HuffmanCompressFile);
				}
				else if (status == JFileChooser.CANCEL_OPTION) {
					System.out.println("canceled");
				}
				break;
	
			default:
				break;

			}
		}	

		@Override
		public void actionPerformed(ActionEvent e) {
		
		
		}
			
		}
