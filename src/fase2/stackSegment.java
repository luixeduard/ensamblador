package fase2;

import controles.constanteVal;

public class stackSegment {
	String[] data;
	String[] reData;
	constanteVal ctrlCons;
	constanteVal ctrlAuxPar;
	String textData="";
	char comi=34;
	char comiS=39;
	char cr=13;
	
	public stackSegment(String[] lineas,int noLineas, String[][] registro) {
		int i=0,c=0;
		int k=0,l=0;
		data = new String[noLineas];
		
		while(registro[4][k]!=null) {
			k++;
		}
		while(registro[8][l]!=null) {
			l++;
		}
		while(!lineas[i].toLowerCase().contains("stack segment")) {
			i++;
		}
		i++;
		while(!lineas[i].toLowerCase().contains("ends")) {
			data[c] = lineas[i];
			c++;
			i++;
		}
		for(int j=0;j<c;j++) {
			String constante="";
			String pseudo="";
			String complemento="";
			int ini=0,fin=0;
			//
			if(data[j].indexOf(" ")!=-1) {
				fin = data[j].indexOf(" ");
				pseudo=data[j].substring(0, fin);
				ini=fin;
				data[j]=data[j].replaceFirst(" ", "	");
				if(data[j].indexOf(" ")!=-1) {
					fin=data[j].indexOf(" ");
					constante=data[j].substring(ini+1, fin);
					ini=fin;
					if(data[j].indexOf(" ")!=-1) {
						fin=data[j].length();
						complemento=data[j].substring(ini+1, fin);
					}
				}
			}
			
			data[j]=data[j].replaceAll("	", " ");
			data[j]=data[j].concat(" - ");
			if(pseudo!=null && pseudo.toUpperCase().matches("DW")) {
				for(int x=0;x<k;x++) {
					if(constante!=null && constante.matches(registro[4][x])) {
						double exp1=Math.pow(2, 16)-1;
						ctrlCons = new constanteVal(constante,-1*(exp1/2),exp1);
						if(ctrlCons.getTipo()!="Fail") {
							for(int y=0;y<l;y++) {
								String auxPar = complemento.substring(complemento.indexOf("(")+1,complemento.indexOf(")"));
								ctrlAuxPar = new constanteVal(auxPar,-1*(exp1/2),exp1);
								if(complemento!=null && complemento.compareTo(registro[8][y])==0) {
									if(ctrlAuxPar.getTipo()!="Fail") {
										data[j]=data[j].concat("Valida");
									}
									else if(auxPar.startsWith(""+comi) && auxPar.endsWith(""+comi)||auxPar.startsWith(""+comiS) && auxPar.endsWith(""+comiS)) {
										data[j]=data[j].concat("Valida");
									}
								}
							}
						}
					}
				}
			}
			
			if(data[j].endsWith("Valida")==false) {
				int x1=0,x2=0,x3=0;
				if(pseudo!=null && pseudo.toUpperCase().matches("DW")) {
					x1=1;
				}
				if(x1==0) {
					data[j]=data[j].concat("¡Error en la pseudoinstruccion!");
				}
				for(int x=0;x<k;x++) {
					if(constante!=null && constante.compareTo(registro[4][x])==0) {
						x2=1;
					}
				}
				if(x2==0) {
					data[j]=data[j].concat("¡Error en la constante, revisar limite!");
				}
				for(int y=0;y<l;y++) {
					if(complemento!=null && complemento.compareTo(registro[8][y])==0) {
						x3=1;
					}
				}
				if(x3==0) {
					data[j]=data[j].concat("¡Error en DUP!");
				}
			}
		}
		i=0;
		c=0;
		while(!lineas[i].toLowerCase().contains("stack segment")) {
			i++;
		}
		lineas[i]=lineas[i].concat(" - Inicia definicion de segmento de pila");
		i++;
		while(!lineas[i].toLowerCase().contains("ends")) {
			lineas[i] = data[c];
			c++;
			i++;
		}
		lineas[i]=lineas[i].concat(" - Fin de definicion de segmento de pila");
		for(int ii=0;ii<noLineas;ii++) {
			textData=textData+""+lineas[ii]+cr+"";
		}
		reData=lineas;
	}

	public String[] getReData() {
		return reData;
	}

	public String getTextData() {
		return textData;
	}

	
}
