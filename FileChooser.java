package dataCompression;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import java.awt.CardLayout;

public class FileChooser extends JFrame implements ActionListener {

	JButton button;
	JFrame frame;
	String option;
	int status;
	JFileChooser fileChooser;
	String GuiInterfaceChoice;

	public FileChooser(String GuiInterfaceChoice) throws IOException {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			frame = new JFrame("Data Compression");
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}

		fileChooser = new JFileChooser(".");
		JPanel panel = new JPanel();
		this.setLayout(new CardLayout());
		this.add(fileChooser);
		this.setLocationRelativeTo(null);
		this.pack();// avoid double pop up
		panel.setVisible(true);
		fileChooser.addActionListener(this);

		switch (GuiInterfaceChoice) {
		case "RUN LENGTH COMPRESS":
			try {
				status = fileChooser.showOpenDialog(null);
				File RunLengthCompressFile = fileChooser.getSelectedFile().getAbsoluteFile();
				if (status == JFileChooser.APPROVE_OPTION) {
					RunLengthCompress.compress(RunLengthCompressFile);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} else if (status == JFileChooser.CANCEL_OPTION) {
					fileChooser.cancelSelection();

				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			break;

		case "RUN LENGTH EXPAND":
			status = fileChooser.showOpenDialog(null);
			File RunLengthExpandFile = fileChooser.getSelectedFile().getAbsoluteFile();
			if (status == JFileChooser.APPROVE_OPTION) {
				RunLengthExpand.expand(RunLengthExpandFile);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			} else if (status == JFileChooser.CANCEL_OPTION) {
				fileChooser.cancelSelection();
			}
			break;

		case "HUFFMAN EXPAND":
			status = fileChooser.showOpenDialog(null);
			File huffmanExpandFile = fileChooser.getSelectedFile().getAbsoluteFile();
			if (status == JFileChooser.APPROVE_OPTION) {
				HuffmanExpand.expand(huffmanExpandFile);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			} else if (status == JFileChooser.CANCEL_OPTION) {
				fileChooser.cancelSelection();
			}
			break;

		case "HUFFMAN COMPRESS":
			status = fileChooser.showOpenDialog(null);
			File HuffmanCompressFile = fileChooser.getSelectedFile().getAbsoluteFile();
			if (status == JFileChooser.APPROVE_OPTION) {
				HuffmanCompress.compress(HuffmanCompressFile);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			} else if (status == JFileChooser.CANCEL_OPTION) {
				fileChooser.cancelSelection();
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
		this.dispose();
		return;
	}
}
