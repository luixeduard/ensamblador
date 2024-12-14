package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

public class tablaSimbolosGUI extends JFrame implements ActionListener{

	JTable tablaText;
	JScrollPane scroll;
	String[] columnNames = {"Simbolo", "Tipo", "Valor", "Tamaño"};
	
	public tablaSimbolosGUI(String tabla[][])  {
		
		tablaText = new JTable(tabla,columnNames);
		scroll = new JScrollPane(tablaText);
		
		this.getContentPane().add(scroll, BorderLayout.CENTER);
		this.setSize(720, 720);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
