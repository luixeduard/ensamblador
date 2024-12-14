package Analizacion;

public class AnalisisLexicografico {
	String An2;//Para ponerle enfrente que tipo de dato es
	String An1;//Para analisis personal
	char crr=13;
	String[] diccInst = {"AAD", "CMPSW","LAHF", "PUSHF","LEA","DIV","OR","TEST","JC","JMP","JP","JNL"};
	String[] diccVarPseu = {"EQU","DB","DW","DWORD","SWORD","WORD","BYTE","SDWORD"};
	String[] diccPseu = {"ASSUME","PROC","END","ENDS","ENDP"};
	String[] diccPseuSeg = {" SEGMENT","OFFSET","DUP("};
	String[] diccReg = {"AL","CL","DL","BL","AH","CH","DH","BH","AX","CX","DX","BX","SP","BP","SI","DI","EAX","ECX","EDX","EBX","ESP","EBP","ESI","EDI","SS","DS","CS","ES"};
	String[] lineas;
	String resultado="";

	public AnalisisLexicografico(String Analizar,int l,String[] Comandd) {
		this.lineas = Comandd;
		this.An2 = Analizar;
		String cr=" "+crr+"";
		String c=""+crr+"";
		char comi = 34;
		char comiS = 39;
		String milla = ""+comi+"";
		String millaS = ""+comiS+"";
		
		An2=An2.replaceAll(cr, c);
		An1=An2;
		An2=An2.replaceAll(c, cr);
		

		for(int i=0;i<l;i++) {
			for(int j=0;j<diccInst.length;j++) {
				if(this.lineas[i].toUpperCase().matches(this.diccInst[j])) {
					this.lineas[i]=this.lineas[i].concat(" - Valida:Instruccion");
				}
			}
		}
		for(int i=0;i<l;i++) {
			for(int j=0;j<diccReg.length;j++) {
				if(this.lineas[i].toUpperCase().matches(this.diccReg[j])) {
					this.lineas[i]=this.lineas[i].concat(" - Valida:Registro");
				}
			}
		}
		for(int i=0;i<l;i++) {
			for(int j=0;j<diccPseuSeg.length;j++) {
				if(this.lineas[i].toUpperCase().contains(this.diccPseuSeg[j])) {
					this.lineas[i]=this.lineas[i].concat(" - Valida:Pseudoinstruccion");
				}
			}
		}
		for(int i=0;i<l;i++) {
			for(int j=0;j<diccVarPseu.length;j++) {
				if(this.lineas[i].toUpperCase().contains(this.diccVarPseu[j]) && this.lineas[i].contains("-")==false) {
					this.lineas[i]=this.lineas[i].concat(" - Valida:Pseudoinstruccion");
				}
			}
		}
		/*for(int i=0;i<l;i++) {
			for(int j=0;j<diccVarPseu.length;j++) {
				if(i!=l-1) {
					if(this.lineas[i+1].toUpperCase().contains(this.diccVarPseu[j]) && this.lineas[i].contains("-")==false) {
						this.lineas[i]=this.lineas[i].concat(" - Valida:Variable");
					}
				}
			}
		}*/
		for(int i=0;i<l;i++) {
			for(int j=0;j<diccPseu.length;j++) {
				if(this.lineas[i].toUpperCase().matches(this.diccPseu[j])&& this.lineas[i].contains("-")==false) {
					this.lineas[i]=this.lineas[i].concat(" - Valida:Pseudoinstruccion");
				}
			}
		}
		
		for(int i=0;i<l;i++) {
			if(this.lineas[i].toUpperCase().matches(",")) {
				this.lineas[i]=this.lineas[i].concat(" - Valida:Separador");
			}
		}
		
		for(int i=0;i<l;i++) {
			if(this.lineas[i].toUpperCase().endsWith("H")) {
				int cont=0;
				char[] auxH = this.lineas[i].toUpperCase().substring(0, this.lineas[i].length()-1).toCharArray();
				for(int j=0;j<auxH.length;j++) {
					if((auxH[j]>64 && auxH[j]<71) || (auxH[j]>47 && auxH[j]<58)) {
						cont++;
					}
					if(cont==j+1 && j==auxH.length-1) {
						this.lineas[i]=this.lineas[i].concat(" - Valida:Constante");
					}
				}
			}
			else if(this.lineas[i].matches("[0-1]+[B]")||this.lineas[i].matches("[0-1]+[b]")) {
				this.lineas[i]=this.lineas[i].concat(" - Valida:Constante");
			}
			else if(this.lineas[i].matches("[0-9]+[0-9]")||this.lineas[i].matches("[0-9]")) {
				this.lineas[i]=this.lineas[i].concat(" - Valida:Constante");
			}
			else if(this.lineas[i].replace("-","").matches("[0-9]")||this.lineas[i].replace("-","").matches("[0-9]+[0-9]")) {
				this.lineas[i]=this.lineas[i].concat(" - Valida:Constante");
			}
		}
		/*for(int i=0;i<l;i++) {
			if(this.lineas[i].contains(":")==true && this.lineas[i].contains("-")==false){
				this.lineas[i]=this.lineas[i].concat(" - Valida:Variable");
			}
		}*/
		for(int i=0;i<l;i++) {
			char[] auxiliar = this.lineas[i].toUpperCase().toCharArray();
			if((auxiliar[0]>64 && auxiliar[0]<90)&& this.lineas[i].length()<=10 && this.lineas[i].contains("-")==false){
				this.lineas[i]=this.lineas[i].concat(" - Valida:Símbolo");
			}
			else if(this.lineas[i].contains("[")==true && (auxiliar[0]>64 && auxiliar[0]<90) && this.lineas[i].indexOf("[")<=10 && this.lineas[i].contains("-")==false){
				this.lineas[i]=this.lineas[i].concat(" - Valida:Símbolo");
			}
		}
		for(int i=0;i<l;i++) {
			char[] auxiliar = this.lineas[i].toUpperCase().toCharArray();
			if(auxiliar[0]==63&& this.lineas[i].length()==1){
				this.lineas[i]=this.lineas[i].concat(" - Valida:Caracter especial");
			}
		}
		for(int i=0;i<l;i++) {
			if((this.lineas[i].endsWith(milla)&&this.lineas[i].startsWith(milla))||(this.lineas[i].endsWith(millaS)&&this.lineas[i].startsWith(millaS))) {
				this.lineas[i]=this.lineas[i].concat(" - Valida:Constante");
			}
			else if(this.lineas[i].contains("Valida:")==false){
				this.lineas[i]=this.lineas[i].concat(" - No reconocida");
			}
		}
		
		for(int i=0;i<l;i++) {
			String aux;
			String aux11;
			String aux12;
			if(this.lineas[i].contains("[") && this.lineas[i].contains("]")) {
				int ini=this.lineas[i].indexOf("[")+1;
				int fin=this.lineas[i].indexOf("]");
				aux = new String(this.lineas[i].substring(ini, fin));
				boolean fl=true;
				if(aux.contains("+")||aux.contains("*")||aux.contains("-")) {
					char[] auxChar = aux.toUpperCase().toCharArray();
					int ini2=0;
					int fin2=0;
					ini=0;
					fin=aux.length();
					while(fl==true) {
						ini=ini2;
						if(aux.toString().contains("+")==true||aux.toString().contains("-")==true||aux.toString().contains("*")==true) {
							if(aux.contains("+")) {
								fin2=aux.indexOf("+");
							}
							else if(aux.contains("*")) {
								fin2=aux.indexOf("*");
							}
							else if(aux.contains("-")) {
								fin2=aux.indexOf("-");
							}
							aux11 = aux.substring(0, fin2);
							fin2++;
							aux12 = aux.substring(fin2, fin);
							aux=aux11+" "+aux12;
							ini2=fin2;
							fin2--;
						}
						else if(aux.toString().contains("+")==false && aux.toString().contains("-")==false && aux.toString().contains("*")==false) {
							fin2=fin;
							fl=false;
						}
						if(aux.substring(ini, fin2).matches("[0-9]")||aux.substring(ini, fin2).matches("[0-9]+[0-9]")) {
							this.lineas[i]=this.lineas[i].concat(", Constante");
						}
						else if(aux.substring(ini, fin2).toUpperCase().endsWith("H")) {
							int cont=0;
							String aaa = aux.substring(ini, fin2).toUpperCase();
							char[] auxH = aaa.substring(0, aaa.length()-1).toCharArray();
							for(int j=0;j<auxH.length;j++) {
								if((auxH[j]>64 && auxH[j]<71) || (auxH[j]>47 && auxH[j]<58)) {
									cont++;
								}
								if(cont==j+1 && j==auxH.length-1) {
									this.lineas[i]=this.lineas[i].concat(", Constante");
								}
							}
						}
						else if(aux.substring(ini, fin2).matches("[0-1]+[b]")||aux.substring(ini, fin2).matches("[0-1]+[B]")) {
							this.lineas[i]=this.lineas[i].concat(", Constante");
						}
						else if(aux.substring(ini, fin2).matches("-+[0-9]")) {
							this.lineas[i]=this.lineas[i].concat(", Constante");
						}
						else if((auxChar[ini]>64 && auxChar[ini]<90)&& aux.substring(ini, fin2).length()<10){
							this.lineas[i]=this.lineas[i].concat(", Simbolo");
						}
						for(int j=0;j<this.diccReg.length;j++) {
							if(aux.substring(ini, fin2).toUpperCase().matches(diccReg[j])) {
								this.lineas[i]=this.lineas[i].concat(", Registro");
							}
						}
					}
				}
				else {
					char[] auxChar = aux.toUpperCase().toCharArray();
					if(this.lineas[i].substring(ini, fin).matches("[0-9]")||this.lineas[i].substring(ini, fin).matches("[0-9]+[0-9]")) {
						this.lineas[i]=this.lineas[i].concat(", Constante");
					}
					else if(this.lineas[i].substring(ini, fin).toUpperCase().endsWith("H")) {
						int cont=0;
						char[] auxH = this.lineas[i].toUpperCase().substring(ini, fin-1).toCharArray();
						for(int j=0;j<auxH.length;j++) {
							if((auxH[j]>64 && auxH[j]<71) || (auxH[j]>47 && auxH[j]<58)) {
								cont++;
							}
							if(cont==j+1 && j==auxH.length-1) {
								this.lineas[i]=this.lineas[i].concat(", Constante");
							}
						}
					}
					else if(this.lineas[i].substring(ini, fin).matches("[0-1]+[b]")||this.lineas[i].substring(ini, fin).matches("[0-1]+[B]")) {
						this.lineas[i]=this.lineas[i].concat(", Constante");
					}
					else if(this.lineas[i].substring(ini, fin).matches("-+[0-9]")) {
						this.lineas[i]=this.lineas[i].concat(", Constante");
					}
					else if((auxChar[0]>64 && auxChar[0]<90)&& this.lineas[i].length()<10){
						this.lineas[i]=this.lineas[i].concat(", Simbolo");
					}
					else if(auxChar[0]==63) {
						this.lineas[i]=this.lineas[i].concat(", Caracter especial");
					}
					for(int j=0;j<this.diccReg.length;j++) {
						if(this.lineas[i].substring(ini, fin).toUpperCase().matches(diccReg[j])) {
							this.lineas[i]=this.lineas[i].concat(", Registro");
						}
					}
				}
			}
		}
		
		for(int i=0;i<l;i++) {
			int ini=0;
			int fin=0;
			if(this.lineas[i].contains("(") && this.lineas[i].contains(")")) {
				ini=this.lineas[i].indexOf("(")+1;
				fin=this.lineas[i].indexOf(")");
				String extr = this.lineas[i].substring(ini, fin);
				while(extr.endsWith(" ")) {
					extr.concat("¿");
					extr = extr.replace(" ¿", "");
				}
				while(extr.startsWith(" ")) {
					extr = extr.replaceFirst(" ", "");
				}
				if(extr.matches("[0-9]")) {
					this.lineas[i]=this.lineas[i].concat(", Constante");
				}
				else if(extr.matches("[0-9]+[0-9]")) {
					this.lineas[i]=this.lineas[i].concat(", Constante");
				}
				else if(extr.replace("-","").matches("[0-9]")||extr.replace("-","").matches("[0-9]+[0-9]")) {
					this.lineas[i]=this.lineas[i].concat(", Constante");
				}
				else if(extr.toUpperCase().endsWith("H")) {
					int cont=0;
					char[] auxH = extr.substring(0, extr.length()-1).toCharArray();
					for(int j=0;j<auxH.length;j++) {
						if((auxH[j]>64 && auxH[j]<71) || (auxH[j]>47 && auxH[j]<58)) {
							cont++;
						}
						if(cont==j+1 && j==auxH.length-1) {
							this.lineas[i]=this.lineas[i].concat(", Constante");
						}
					}
				}
				else if(extr.matches("[0-1]+[b]")||extr.matches("[0-1]+[B]")) {
					this.lineas[i]=this.lineas[i].concat(", Constante");
				}
				else if((extr.endsWith(milla)&&extr.startsWith(milla))||(extr.endsWith(millaS)&&extr.startsWith(millaS))) {
					this.lineas[i]=this.lineas[i].concat(", Constante");
				}
			}
		}
		
		
		
		for(int i=0;i<l;i++) {
			resultado=resultado+""+this.lineas[i]+crr;
		}
	}

	public String getResultado() {
		return resultado;
	}

	public String[] getLineas() {
		return lineas;
	}
	
}
