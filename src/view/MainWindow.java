package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import model.Sign;
import model.Table;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -4507758962341993549L;
	private JPanel contentPane;
	private JTextArea textArea;

	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 11));
		textArea.setBounds(10, 11, 380, 389);
		contentPane.add(textArea);
	}

	public void redrawTable(Table t) {
		String s = "";
		for (int i = 0; i < t.getTableSize(); i++) {
			for (int j = 0; j < t.getTableSize(); j++) {
				Sign sign = t.getSign(i, j);
				if (sign.getSign().equals(Sign.EMPTY)) {
					s += "  ";
				} else if (sign.getSign().equals(Sign.X)) {
					s += "X ";
				} else {
					s += "O ";
				}
			}
			s+="\n";
		}
		textArea.setText(s);
	}
}
