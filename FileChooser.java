package dataCompression;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.awt.CardLayout;

public class FileChooser extends JFrame implements ActionListener{
    
	JButton button;
	JFrame frame;
	String option;
	int status;
	JFileChooser fileChooser;
	
	public FileChooser(String choice) throws IOException{
		try {
		String GuiInterfaceChoice = choice;
		FileChooserChoice(GuiInterfaceChoice);
		} catch(IllegalArgumentException e1) {
			System.out.println("Illegal Arg Caught");
		}
		}
	
	public void FileChooserChoice(String GuiInterfaceChoice) throws IOException{
	
	
    	
		try {
    		 frame = new JFrame("Data Compression");
    		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
	    
		fileChooser = new JFileChooser(".");
	 
//		JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
		
		//jComboBox.setBounds(80, 50, 140, 20);
		JPanel panel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new CardLayout());
 
	this.add(fileChooser);
	
		this.setLocationRelativeTo(null);
	
		this.pack();
		panel.setVisible(true);
	
		fileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Action");

			}
		});


			switch(GuiInterfaceChoice){
				case "Run Length Compress":
					try {
						 status = fileChooser.showOpenDialog(null);

						if (status == JFileChooser.APPROVE_OPTION) {

							File currentFile = fileChooser.getSelectedFile().getAbsoluteFile();

							System.out.println("In File Chooser: " + currentFile.getParent());
							System.out.println(currentFile.getName());

						RunLengthCompress.compress(currentFile);
			} else if (status == JFileChooser.CANCEL_OPTION) {
				System.out.println("canceled");}}
catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
					
				case "Run Length Expand":
					status = fileChooser.showOpenDialog(null);

					if (status == JFileChooser.APPROVE_OPTION) {

						File currentFile = fileChooser.getSelectedFile().getAbsoluteFile();

						System.out.println("In File Chooser: " + currentFile.getParent());
						System.out.println(currentFile.getName());

					RunLengthExpand.expand(currentFile);		

			} else if (status == JFileChooser.CANCEL_OPTION) {
				System.out.println("canceled");}

					break;
					
				case "Huffman Expand":
					 status = fileChooser.showOpenDialog(null);

					if (status == JFileChooser.APPROVE_OPTION) {

						File currentFile = fileChooser.getSelectedFile().getAbsoluteFile();

						System.out.println("In File Chooser: " + currentFile.getParent());
						System.out.println(currentFile.getName());

					Huffman.expand(currentFile);}		

	else if (status == JFileChooser.CANCEL_OPTION) {
		System.out.println("canceled");}

					break;
					
				case "Huffman Compress":
					status = fileChooser.showOpenDialog(null);

					if (status == JFileChooser.APPROVE_OPTION) {

						File currentFile = fileChooser.getSelectedFile().getAbsoluteFile();

						System.out.println("In File Chooser: " + currentFile.getParent());
						System.out.println(currentFile.getName());

					Huffman.compress(currentFile);}		

	 else if (status == JFileChooser.CANCEL_OPTION) {
		System.out.println("canceled");}

					break;
			
				case "Binary Dump":
					 status = fileChooser.showOpenDialog(null);

					if (status == JFileChooser.APPROVE_OPTION) {

						File currentFile = fileChooser.getSelectedFile().getAbsoluteFile();

						System.out.println("In File Chooser: " + currentFile.getParent());
						System.out.println(currentFile.getName());

				BinaryDump.BinaryDumpFile(currentFile);		}

			else if (status == JFileChooser.CANCEL_OPTION) {
				System.out.println("canceled");}
					break;
					
				default:
					break;
	
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
		
	}
