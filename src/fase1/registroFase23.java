package fase1;

import controles.Matrices;

public class registroFase23 {
	String[][] registroC;
	Matrices s = new Matrices();
	String auxIni;
	String auxFin;
	int j=0,k=0,l=0,m=0,n=0,o=0,p=0,q=0;

	public registroFase23(String[] analisis) {
		registroC = new String[9][analisis.length];
		for(int i=0;i<analisis.length;i++) {
			auxIni=analisis[i].substring(0, analisis[i].indexOf(" -"));
			if(analisis[i].contains("No reconocida")) {
				auxFin=analisis[i].substring(analisis[i].indexOf("-")+1,analisis[i].length());
			}
			else {
				auxFin=analisis[i].substring(analisis[i].indexOf(":")+1,analisis[i].length());
			}
			
			switch(auxFin) {
			case "Pseudoinstruccion":
				if(!s.contains(auxIni, registroC)) {
					registroC[0][j] = auxIni;
					j++;
				}
				break;
			case "Símbolo":
				if(!s.contains(auxIni, registroC)) {
					registroC[2][k] = auxIni;
					k++;
				}
				break;
			case "Constante":
				if(!s.contains(auxIni, registroC)) {
					registroC[4][l] = auxIni;
					l++;
				}
				break;
			case "Separador":
				if(!s.contains(auxIni, registroC)) {
					registroC[6][m] = auxIni;
					m++;
				}
				break;
			case "Registro":
				if(!s.contains(auxIni, registroC)) {
					registroC[1][n] = auxIni;
					n++;
				}
				break;
			case "Caracter especial":
				if(!s.contains(auxIni, registroC)) {
					registroC[3][o] = auxIni;
					o++;
				}
				break;
			case "No reconocida":
				if(!s.contains(auxIni, registroC)) {
					registroC[7][p] = auxIni;
					p++;
				}
				break;
			case "Pseudoinstruccion, Constante":
				if(!s.contains(auxIni, registroC)) {
					registroC[8][q] = auxIni;
					q++;
				}
				break;
			}
		}
	}

	public String[][] getRegistroC() {
		return registroC;
	}
	
}
