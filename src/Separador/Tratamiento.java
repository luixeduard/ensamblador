package Separador;

public class Tratamiento {
	String text = new String();
	String[] resul = new String[1000];
	String backup = new String();
	Boolean flag=true;
	int ci=0;
	int cf=0;
	char crr=13;
	int cc=0;
	
	public Tratamiento(String texto) {
		super();
		String cr=""+crr+"";
		this.text=texto;
		this.text=this.text.concat(cr);
		this.text=this.text.replaceAll(cr+""+cr+""+cr+"", cr);//Quitamos los tres enter seguidos por 1
		this.text=this.text.replaceAll(cr+""+cr+"", cr);//Quitamos los dos enter seguidos por 1
		while(flag == true) {//Mientras no sea cierto
			ci=cf;//Iniciamos cada linea
			cf=this.text.indexOf(crr);//Buscamos el primer enter
			this.text=this.text.replaceFirst(cr, " ");//Solo para que no se estanque indexOf y siempre vaya al primer enter
			this.backup=this.text.substring(ci, cf);//Copiamos en una variable auxiliar
			this.resul[cc]=this.backup;//copiamos la linea en los resultados
			if(this.backup.length()>0) {//Si tiene algo backup se le sumara al contador cc
				cc++;
			}
			if(cf+1==this.text.length()) {
				flag=false;
			}
		}
		for(int j=0;j<cc;j++) {
			for(int i=0;i<cc;i++) {
				if(this.resul[i].contains("			")) {
					this.resul[i]=this.resul[i].replaceAll("			", " ");//Si tiene tres tab
				}
				if(this.resul[i].contains("		")) {
					this.resul[i]=this.resul[i].replaceAll("		", " ");//Si tiene dos tab
				}
				if(this.resul[i].contains("   ")) {
					this.resul[i]=this.resul[i].replaceAll("   ", " ");//Si tiene tres espacios
				}
				if(this.resul[i].contains("  ")) {
					this.resul[i]=this.resul[i].replaceAll("  ", " ");//Si tiene dos espacios
				}
				if(this.resul[i].contains(", ")) {//Si tiene una coma seguido de un espacio
					this.resul[i]=this.resul[i].replaceAll(", ", ",");
				}
				if(this.resul[i].contains(" ,")) {//Si tiene una coma seguido de un espacio
					this.resul[i]=this.resul[i].replaceAll(" ,", ",");
				}
				if(this.resul[i].contains(" )")) {//Si tiene una coma seguido de un espacio
					this.resul[i]=this.resul[i].replace(" )", ")");
				}
				if(this.resul[i].contains("( ")) {//Si tiene una coma seguido de un espacio
					this.resul[i]=this.resul[i].replace("( ", "(");
				}
				if(this.resul[i].contains(" (")) {//Si tiene una coma seguido de un espacio
					this.resul[i]=this.resul[i].replace(" (", "(");
				}
				if(this.resul[i].contains(" ]")) {//Si tiene una coma seguido de un espacio
					this.resul[i]=this.resul[i].replace(" ]", "]");
				}
				if(this.resul[i].contains("[ ")) {//Si tiene una coma seguido de un espacio
					this.resul[i]=this.resul[i].replace("[ ", "[");
				}
				if(this.resul[i].startsWith(" ")) {//Si tiene un espacio al principio
					this.resul[i]=this.resul[i].replaceFirst(" ", "");
				}
				else if(this.resul[i].startsWith("	")) {//Si empieza con un tabulador
					this.resul[i]=this.resul[i].replaceFirst("	", "");
				}
			}
		}
	}

	public String[] getResul() {
		return resul;
	}

	public int getCc() {
		return cc;
	}
	
	
}
