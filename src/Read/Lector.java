package Read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JOptionPane;

public class Lector {
	
	private String texto = "";
	private char Cr=13;
	public String re="";
	private int i=0;
	
	public Lector(File e) {
		
		try{
			FileReader lector=new FileReader(e);
			BufferedReader contenido=new BufferedReader(lector);
			while((texto=contenido.readLine())!=null){
				if(texto.contains(";")!=true) {
					if(i==0) {
						re=re+""+texto;
					}
					else {
						re=re+""+Cr+""+texto;
					}
				}
				else {
					if(i==0) {
						re=re+""+texto.substring(0, texto.indexOf(";"));
					}
					else {
						re=re+""+Cr+""+texto.substring(0, texto.indexOf(";"));
					}
				}
					i++;
			}
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(null,"Error al leer archivo");
		}
	}

	public String getTexto() {
		return re;
	}
	
}
