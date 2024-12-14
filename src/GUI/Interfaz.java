package GUI;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Analizacion.AnalisisLexicografico;
import Read.Lector;
import Separador.SeparaXComandos;
import Separador.Tratamiento;
import controles.ValSegments;
import fase1.registroFase23;
import fase2.dataSegment;
import fase2.stackSegment;
import tablaSimbolos.dataSimbolos;

public class Interfaz extends JFrame implements ActionListener {

	static String re;
	static String[] lineas;
	static String[] com;
	static String[] fase1;
	static String analizacion;
	static String[][] tablaSimbolos1;
	static String[][] tablaSimbolos2;
	public String[] lin;
	static int noLineas;
	static int lib=0;
	public JMenuBar barra = new JMenuBar();
	public JMenu menu = new JMenu("Archivo");
	public JMenu sep = new JMenu("Fase 1");
	public JMenu an = new JMenu("Fase 2");
	public JMenu acerca = new JMenu("Acerca de...");
	public JMenuItem opcion = new JMenuItem("Abrir archivo");
	public JTextPane texto = new JTextPane();
	public JScrollPane scroll = new JScrollPane(texto);
	
	public Interfaz() throws HeadlessException {
		super("Proyecto Ensambladores");
		opcion.setActionCommand("Abrir");
		opcion.addActionListener(this);
		
		menu.add(opcion);
		
		opcion = new JMenuItem("Salir");
		opcion.setActionCommand("Salir");
		opcion.addActionListener(this);
		
		menu.add(opcion);

		barra.add(menu);
		
		
		this.getContentPane().add(scroll, BorderLayout.CENTER);
		this.getContentPane().add(barra,BorderLayout.NORTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		
		opcion = new JMenuItem("Separacion");
		opcion.setActionCommand("Separacion");
		opcion.addActionListener(this);
		sep.add(opcion);
		barra.add(sep);

		opcion = new JMenuItem("Analisis");
		opcion.setActionCommand("Analisis");
		opcion.addActionListener(this);
		an.add(opcion);
		barra.add(an);
		
		opcion = new JMenuItem("Integrantes");
		opcion.setActionCommand("Acerca");
		opcion.addActionListener(this);
		acerca.add(opcion);
		barra.add(acerca);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comando = arg0.getActionCommand();
		switch(comando){
			case "Abrir":
				JFileChooser selectorArchivos = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("ASM","asm");
				selectorArchivos.setFileFilter(filter);
				selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int resultado = selectorArchivos.showOpenDialog(this);
				if(resultado == JFileChooser.APPROVE_OPTION){
					File archivo = selectorArchivos.getSelectedFile();
					Lector leer = new Lector(archivo);
					re=leer.getTexto();
					texto.setText(re);
					this.setSize(1280,720 );
					lib=1;
				}
				break;
			case "Separacion":
				if(lib==1) {
					Tratamiento separa = new Tratamiento(re);
					lineas = separa.getResul();
					noLineas =separa.getCc();
					SeparaXComandos comandos = new SeparaXComandos(lineas,noLineas);
					com = comandos.getComandos();
					analizacion = comandos.getRe();
					noLineas = comandos.getNoLin();
					texto.setText(analizacion);
					AnalisisLexicografico A1 = new AnalisisLexicografico(analizacion,noLineas,com);
					analizacion=A1.getResultado();
					texto.setText(analizacion);
					lib=2;
				}
				else {
					JOptionPane.showMessageDialog(null,"Aun no se a leido un archivo");
				}
				break;
			case "Analisis":
				if(lib==1 && lib!=2) {
					Tratamiento separa = new Tratamiento(re);
					lineas = separa.getResul();
					noLineas =separa.getCc();
					SeparaXComandos comandos = new SeparaXComandos(lineas,noLineas);
					com = comandos.getComandos();
					analizacion = comandos.getRe();
					noLineas = comandos.getNoLin();
					AnalisisLexicografico A1 = new AnalisisLexicografico(analizacion,noLineas,com);
					analizacion=A1.getResultado();
					fase1=A1.getLineas();
					lib=2;
				}
				if(lib==2) {
					Tratamiento separa = new Tratamiento(re);
					lineas = separa.getResul();
					noLineas =separa.getCc();
					registroFase23 preFase = new registroFase23(fase1);
					String registroC[][] = preFase.getRegistroC();
					
					ValSegments vs = new ValSegments(lineas);
					
					
					if(vs.launchDS()==true) {
						dataSimbolos pason1 = new dataSimbolos(lineas,noLineas,registroC,1);
						String[] auxLineasTabla;
						tablaSimbolos1 = pason1.getTablaS(); //Esta tabla solo le agregas las demas cosas 
						dataSegment paso1 = new dataSegment(lineas, noLineas, registroC);
						analizacion=paso1.getTextData();
						auxLineasTabla=paso1.getReData();
						pason1 = new dataSimbolos(auxLineasTabla,noLineas,registroC,2);
						tablaSimbolos2 = pason1.getTablaS();
						tablaSimbolosGUI win= new tablaSimbolosGUI(tablaSimbolos2);
						win.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null,"Error detectado en DATA SEGMENT o segmento inexistente");
					}
					
					if(vs.launchSS()==true) {
						stackSegment paso2 = new stackSegment(lineas, noLineas, registroC);
						analizacion=paso2.getTextData();
					}else {
						JOptionPane.showMessageDialog(null,"Error en STACK SEGMENT o segmento inexistente");
					}
					texto.setText(analizacion);
				}
				else {
					JOptionPane.showMessageDialog(null,"Aun no se a leido un archivo");
				}
				break;
			case "Acerca":
				JOptionPane.showMessageDialog(null,"Integrantes:\nPedro Jesus Ibarra Palmero\nLuis Eduardo Salas Hernández");
				break;
			case "Salir":
				System.exit(1);
		}
		
	}


}
