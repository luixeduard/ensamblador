package Separador;

public class SeparaXComandos {
	
	char crr=13;
	char comi=34;
	char comS=39;
	char parC=41;
	//String[] dicc1 = {"db","dw","jz","jmp","bd","wd","inc","ja","jng","int","end"," proc",};
	//String[] dicc2 = {"word ","sword ","dword ", "byte "};
	String[] diccDefinitivo = {"data segment", "code segment", "stack segment", "byte ptr", "word ptr"};
	String re="";
	String text = new String();
	String backup = new String();
	String comandos[];
	Boolean flag=true;
	int ci;
	int cf;
	int noLin;

	public SeparaXComandos(String[] line, int noLineas) {
		String[] lineas = new String[noLineas];
		String cr=""+crr+"";
		String crcr=""+crr+""+crr;
		String cre=" "+crr+"";
		String milla=""+comi+"";
		char[] aux;
		lineas=line;
		for(int i=0;i<noLineas;i++) {
			for(int j=0;j<diccDefinitivo.length;j++) {
				if (lineas[i].toLowerCase().contains(diccDefinitivo[j])){
					lineas[i]=lineas[i].replaceAll(cr+diccDefinitivo[j], diccDefinitivo[j]+cr);
					i++;
				}
			}
			if(lineas[i].contains(""+comS+"")==false && lineas[i].contains(milla)==false && lineas[i].contains("[")==false && lineas[i].contains("dup(")==false && lineas[i].contains("dup (")==false) {
				lineas[i]=lineas[i].replaceAll(" ", cr);
			}
			if(lineas[i].contains(",")) {
				lineas[i]=lineas[i].replaceAll(",", cr+","+cr);
			}
				
			if (lineas[i].toLowerCase().contains("dup")){
				lineas[i]=lineas[i].replaceAll("dup", cr+"dup");
				lineas[i]=lineas[i].replaceAll("DUP", cr+"DUP");
			}
			
			if(lineas[i].contains(""+comS+"")==true) {
				lineas[i]=lineas[i].replaceFirst(" "+comS, cr+comS);
				lineas[i]=lineas[i].replaceFirst(comS+" ", comS+cr);
			}
			if(lineas[i].contains(""+comi+"")==true) {
				lineas[i]=lineas[i].replaceFirst(" "+comi, cr+comi);
				lineas[i]=lineas[i].replaceFirst(comi+" ", comi+cr);
			}
			if(lineas[i].contains("[")==true) {
				String Sin=lineas[i].substring(lineas[i].indexOf("["), lineas[i].indexOf("]")+1);
				if(Sin.contains("+")||Sin.contains("*")||Sin.contains("-")) {
					Sin=Sin.replace(" ", "");
					lineas[i]=lineas[i].substring(0, lineas[i].indexOf("["));
					lineas[i]=lineas[i].concat(Sin);
				}
			}
		}
		
		for(int i=0;i<noLineas;i++) {
			this.re=this.re+""+lineas[i]+crr;
		}
		
		
		for(int i=0;i<noLineas;i++) {
			this.re=this.re.replaceAll("	"+cr, cr);
			this.re=this.re.replaceAll(crcr, cr);
			this.re=this.re.replaceAll(cre, cr);
		}
		
		this.text=this.re;
		aux = new char[this.re.length()];
		aux = this.re.toCharArray();
		
		noLineas=0;
		for(int i=0;i<aux.length;i++) {
			if(aux[i]==crr) {
				noLineas++;
			}
		}
		
		comandos = new String[noLineas];
		
		ci=0;
		cf=0;
		for(int cc=0;cc<noLineas;cc++) {
			ci=cf;
			cf=this.text.indexOf(crr);
			this.text=this.text.replaceFirst(cr, "");
			this.backup=this.text.substring(ci, cf);
			
			this.comandos[cc]=this.backup;
		}
		
		lineas= new String[noLineas];
		lineas=this.comandos;
		
		for(int i=0;i<noLineas;i++){
			for(int j=0;j<diccDefinitivo.length;j++) {
				if(lineas[i].contains(diccDefinitivo[j])) {
					i++;
				}
			}
			if(lineas[i].contains(""+comS+"")==false && lineas[i].contains(milla)==false && lineas[i].contains("(")==false && lineas[i].contains("[")==false && lineas[i].contains("dup")==false) {
				lineas[i]=lineas[i].replaceAll(" ", cr);
			}
			else if(lineas[i].contains("[")==true) {
				lineas[i]=lineas[i].replaceAll(" ", cr);
			}
		}
		
		re="";
		
		for(int i=0;i<noLineas;i++) {
			re=re+""+lineas[i]+crr;
		}
		
		for(int i=0;i<noLineas;i++) {
			this.re=this.re.replaceAll("	"+cr, cr);
			this.re=this.re.replaceAll(crcr, cr);
			this.re=this.re.replaceAll(cre, cr);
		}
		
		this.text=this.re;
		
		aux = this.text.toCharArray();
		noLineas=0;
		
		for(int i=0;i<aux.length;i++) {
			if(aux[i]==crr) {
				noLineas++;
			}
		}
		
		
		
		comandos = new String[noLineas];
		
		ci=0;
		cf=0;
		for(int cc=0;cc<noLineas;cc++) {
			ci=cf;
			cf=this.text.indexOf(crr);
			this.text=this.text.replaceFirst(cr, "");
			this.backup=this.text.substring(ci, cf);
			this.comandos[cc]=this.backup;
		}
		
		
		this.noLin=noLineas;
		
	}
	
	public String[] getComandos() {
		return comandos;
	}

	public String getRe() {
		return re;
	}

	public int getNoLin() {
		return noLin;
	}
	

}
