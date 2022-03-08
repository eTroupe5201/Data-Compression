package dataCompression;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class FileSaver extends JFrame implements ActionListener {

	JFrame frame;
	final JFileChooser saveFile;
	JButton button;
	int status;

	FileSaver(String FileRequester) throws IOException {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			frame = new JFrame("Save Dialog");
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}

		saveFile = new JFileChooser(".");
		JPanel panel = new JPanel();
		this.setLayout(new CardLayout());
		this.add(saveFile);
		this.setLocationRelativeTo(null);
		this.pack();// avoid double pop up
		panel.setVisible(true);
		saveFile.addActionListener(this);

		switch (FileRequester) {
		case "RUN LENGTH COMPRESS":
			status = saveFile.showSaveDialog(null);
			File RunLengthCompressFile = saveFile.getSelectedFile().getAbsoluteFile();
			if (status == JFileChooser.APPROVE_OPTION) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				RunLengthCompress.RecieveOutputFile(RunLengthCompressFile);
			} else if (status == JFileChooser.CANCEL_OPTION) {
				saveFile.cancelSelection();
			}
			break;

		case "RUN LENGTH EXPAND":
			status = saveFile.showSaveDialog(null);

			File RunLengthExpandFile = saveFile.getSelectedFile().getAbsoluteFile();

			if (status == JFileChooser.APPROVE_OPTION) {
				RunLengthExpand.RecieveOutputFile(RunLengthExpandFile);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			} else if (status == JFileChooser.CANCEL_OPTION) {
				saveFile.cancelSelection();
			}
			break;

		case "HUFFMAN EXPAND":
			status = saveFile.showSaveDialog(null);
			File huffmanExpandFile = saveFile.getSelectedFile().getAbsoluteFile();

			if (status == JFileChooser.APPROVE_OPTION) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				HuffmanExpand.RecieveOutputFile(huffmanExpandFile);
			} else if (status == JFileChooser.CANCEL_OPTION) {
				saveFile.cancelSelection();
			}
			break;

		case "HUFFMAN COMPRESS":

			status = saveFile.showSaveDialog(null);
			File HuffmanCompressFile = saveFile.getSelectedFile().getAbsoluteFile();

			if (status == JFileChooser.APPROVE_OPTION) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				HuffmanCompress.RecieveOutputFile(HuffmanCompressFile);
			} else if (status == JFileChooser.CANCEL_OPTION) {
				saveFile.cancelSelection();
			}
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
