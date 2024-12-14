package tablaSimbolos;

public class dataSimbolos {

	String[] data;
	String[] reData;
	String[][] tablaS;
	String textData="";
	char comi=34;
	char comiS=39;
	char cr=13;
	int taba=0;
	
	public dataSimbolos(String[] lineas,int noLineas, String[][] registro, int caso) {
		int i=0, c=0;
		int k=0;
		data = new String[noLineas];
		
		for(int  m=0;m<noLineas;m++) {
			if(lineas[m].endsWith(" ")) {
				lineas[m]=lineas[m].concat("^");
				lineas[m]=lineas[m].replace(" ^", "");
			}
		}
		
		while(registro[2][k]!=null) {
			k++;
		}
		
		while(!lineas[i].toLowerCase().contains("data segment")) {
			i++;
		}
		i++;
		while(!lineas[i].toLowerCase().contains("ends")) {
			if(caso==1) {
				data[c] = lineas[i];
				c++;
			}
			else if(caso==2) {
				if(lineas[i].toLowerCase().endsWith("valida")) {
					data[c] = lineas[i];
					c++;
				}
			}
			i++;
		}
		tablaS = new String[c][4];
		
		for(int j=0;j<c;j++) {
			String simbolo="";
			String tipo="";
			String valor="";
			int ini=0,fin=0;
			if(data[j].indexOf(" ")!=-1) {
				fin = data[j].indexOf(" ");
				simbolo=data[j].substring(0, fin);
				ini=fin;
				data[j]=data[j].replaceFirst(" ", "	");
				if(data[j].indexOf(" ")!=-1) {
					fin=data[j].indexOf(" ");
					tipo=data[j].substring(ini+1, fin);
					ini=fin;
					if(data[j].indexOf(" ")!=-1) {
						if(caso==1) {
							fin=data[j].length();
							valor=data[j].substring(ini+1, fin);
						}
						else if(caso==2) {
							fin=data[j].indexOf(" - Valida");
							valor=data[j].substring(ini+1, fin);
						}
					}
				}
			}
			data[j]=data[j].replaceAll("	", " ");
			data[j]=data[j].concat(" - ");
		
			//System.out.println(simbolo+"--"+tipo+"--"+valor);
			for(int x=0;x<k;x++) {
				if(simbolo!=null && simbolo.matches(registro[2][x])) {
					if(tipo.toLowerCase().matches("db")||tipo.toLowerCase().matches("dw")||tipo.toLowerCase().matches("equ")) {
						tablaS[taba][0]=simbolo;
						tablaS[taba][1]=tipo;
						tablaS[taba][2]=valor;
						if(tipo.toLowerCase().matches("db")) {
							tablaS[taba][3]="Byte";
							tablaS[taba][1]="Variable";
						}
						else if(tipo.toLowerCase().matches("dw")) {
							tablaS[taba][3]="Palabra";
							tablaS[taba][1]="Variable";
						}
						else if(tipo.toLowerCase().matches("equ")) {
							tablaS[taba][3]="Constante";
							tablaS[taba][1]="Constante";
						}
						
						taba++;
						//System.out.println(tablaS[0][taba]+"-"+tablaS[1][taba]+"-"+tablaS[2][taba]+"-"+tablaS[3][taba]);
					}
				}
			}
		}
	}

	public String[][] getTablaS() {
		return tablaS;
	}
	
}
