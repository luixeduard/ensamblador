package fase2;

import controles.constanteVal;

public class dataSegment {
	String[] data;
	String[] reData;
	String textData="";
	char comi=34;
	char comiS=39;
	char cr=13;
	

	public dataSegment(String[] lineas,int noLineas, String[][] registro) {
		int i=0,d=0,c=0;
		data = new String[noLineas];
		int k=0,l=0,mm=0;
		for(int  m=0;m<noLineas;m++) {
			if(lineas[m].endsWith(" ")) {
				lineas[m]=lineas[m].concat("^");
				lineas[m]=lineas[m].replace(" ^", "");
			}
		}
		
		while(registro[2][k]!=null) {
			k++;
		}
		while(registro[4][l]!=null) {
			l++;
		}
		while(registro[8][mm]!=null) {
			mm++;
		}
		
		while(!lineas[i].toLowerCase().contains("data segment")) {
			i++;
		}
		i++;
		while(!lineas[i].toLowerCase().contains("ends")) {
			data[c] = lineas[i];
			c++;
			i++;
		}
		
		for(int j=0;j<c;j++) {
			String simbolo="";
			String pseudo="";
			String complemento="";
			int ini=0,fin=0;
			if(data[j].indexOf(" ")!=-1) {
				fin = data[j].indexOf(" ");
				simbolo=data[j].substring(0, fin);
				ini=fin;
				data[j]=data[j].replaceFirst(" ", "	");
				if(data[j].indexOf(" ")!=-1) {
					fin=data[j].indexOf(" ");
					pseudo=data[j].substring(ini+1, fin);
					ini=fin;
					if(data[j].indexOf(" ")!=-1) {
						fin=data[j].length();
						complemento=data[j].substring(ini+1, fin);
					}
				}
			}
			data[j]=data[j].replaceAll("	", " ");
			data[j]=data[j].concat(" - ");

			constanteVal ctrlCons;
			constanteVal ctrlCons2;
			constanteVal ctrlCons3;
			
			if(data[j].contains("dup(")==true && complemento.contains(" ")){
				String complemento1=complemento.substring(0, complemento.indexOf(" "));
				String complemento2=complemento.substring(complemento.indexOf(" ")+1, complemento.indexOf(")")+1);
				String auxDUP=complemento2.substring(complemento2.indexOf("(")+1, complemento2.indexOf(")"));
				for(int x=0;x<k;x++) {
					if(simbolo!=null && simbolo.matches(registro[2][x])) {
						if(pseudo!=null && (pseudo.toUpperCase().matches("DB")||pseudo.toUpperCase().matches("DW"))) {
							for(int y=0;y<l;y++) {
								if(complemento1!=null && complemento1.compareTo(registro[4][y])==0){
									double exp1=Math.pow(2, 16)-1;
									ctrlCons3 = new constanteVal(complemento1,1,exp1);
									if(ctrlCons3.getTipo()!="Fail") {
										for(int z=0;z<mm;z++) {
											if(complemento2!=null && complemento2.compareTo(registro[8][z])==0) {
												ctrlCons = new constanteVal(auxDUP,-1*(exp1/2),exp1);
												ctrlCons2 = new constanteVal(auxDUP);
												//System.out.println(auxDUP);
												if(auxDUP.startsWith(""+comiS)&&auxDUP.endsWith(""+comiS)) {
													data[j]=data[j].concat("Valida");
												}
												else if(ctrlCons2.getTipo()!="Fail" && pseudo.toUpperCase().matches("DB")) {
													data[j]=data[j].concat("Valida");
												}
												else if(ctrlCons.getTipo()!="Fail" && pseudo.toUpperCase().matches("DW")) {
													data[j]=data[j].concat("Valida");
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			else {
				for(int x=0;x<k;x++) {
					if(simbolo!=null && simbolo.matches(registro[2][x])) {
						if(pseudo!=null && (pseudo.toUpperCase().matches("DB")||pseudo.toUpperCase().matches("DW")||pseudo.toUpperCase().matches("EQU"))) {
							for(int y=0;y<l;y++) {
								if(complemento!=null && complemento.compareTo(registro[4][y])==0){
									
									if(pseudo.toUpperCase().matches("DB")&&(complemento.startsWith(""+comi) && complemento.endsWith(""+comi)||complemento.startsWith(""+comiS) && complemento.endsWith(""+comiS))) {
										data[j]=data[j].concat("Valida");
									}
									else if(pseudo.toUpperCase().matches("DB")) {
										ctrlCons = new constanteVal(complemento);
										if(ctrlCons.getTipo()!="Fail") {
											data[j]=data[j].concat("Valida");
										}
									}
									else if(pseudo.toUpperCase().matches("DW")) {
										double exp1=Math.pow(2, 16)-1;
										ctrlCons = new constanteVal(complemento,-1*(exp1/2),exp1);
										if(ctrlCons.getTipo()!="Fail") {
											data[j]=data[j].concat("Valida");
										}
									}
									else if(pseudo.toUpperCase().matches("EQU")) {
										double exp1=Math.pow(2, 16)-1;
										ctrlCons = new constanteVal(complemento,-1*(exp1/2),exp1);
										if(ctrlCons.getTipo()!="Fail") {
											data[j]=data[j].concat("Valida");
										}
									}
									
								}
							}
						}
					}
				}
			}
			
			if(data[j].contains("dup(")==true) {
				double exp1=Math.pow(2, 16)-1;
				String complemento1="";
				String complemento2="";
				String auxDUP="";
				if(complemento.contains(" ")) {
					complemento1=complemento.substring(0, complemento.indexOf(" "));
					complemento2=complemento.substring(complemento.indexOf(" ")+1, complemento.indexOf(")")+1);
					auxDUP=complemento2.substring(complemento2.indexOf("(")+1, complemento2.indexOf(")"));
				}
				if(data[j].endsWith("Valida")==false) {
					int x1=0,x2=0,x3=0,x4=0,x5=0;
					for(int x=0;x<k;x++) {
						if(simbolo!=null && simbolo.compareTo(registro[2][x])==0) {
							x1=1;
						}
					}
					if(x1==0) {
						data[j]=data[j].concat("¡Error en el simbolo!");
					}
					if(pseudo!=null && (pseudo.toUpperCase().matches("DB") || pseudo.toUpperCase().matches("DW"))) {
						x2=1;
					}
					if(x2==0) {
						data[j]=data[j].concat("¡Se esperaba DB | DW!");
					}
					for(int y=0;y<l;y++) {
						ctrlCons = new constanteVal(complemento1,1,exp1);
						if(complemento1!=null && complemento1.compareTo(registro[4][y])==0) {
							if(ctrlCons.getTipo()!="Fail") {
								x3=1;
							}
						}
					}
					if(x3==0) {
						data[j]=data[j].concat("¡Error en el primer rango!");
					}
					for(int z=0;z<mm;z++) {
						if(complemento2!=null && complemento2.compareTo(registro[8][z])==0) {
							x4=1;
						}
					}
					if(x4==0) {
						data[j]=data[j].concat("¡Error en la declaracion de DUP!");
					}
					ctrlCons = new constanteVal(auxDUP,-1*(exp1/2),exp1);
					ctrlCons2 = new constanteVal(auxDUP);
					if(auxDUP.startsWith(""+comiS)&&auxDUP.endsWith(""+comiS)) {
						x5=1;
					}
					else if(ctrlCons2.getTipo()!="Fail" && pseudo.toUpperCase().matches("DB")) {
						x5=1;
					}
					else if(ctrlCons.getTipo()!="Fail" && pseudo.toUpperCase().matches("DW")) {
						x5=1;
					}
					if(x5==0) {
						data[j]=data[j].concat("¡Error en la constante de DUP!");
					}
				}
			}
			else {
				if(data[j].endsWith("Valida")==false) {
					int x1=0,x2=0,x3=0;
					for(int x=0;x<k;x++) {
						if(simbolo!=null && simbolo.compareTo(registro[2][x])==0) {
							x1=1;
						}
					}
					if(x1==0) {
						data[j]=data[j].concat("¡Error en el simbolo!");
					}
					if(pseudo!=null && (pseudo.toUpperCase().matches("DB") || pseudo.toUpperCase().matches("DW") || pseudo.toUpperCase().matches("EQU"))) {
						x2=1;
					}
					if(x2==0) {
						data[j]=data[j].concat("¡Se esperaba DB | DW | EQU!");
					}
					for(int y=0;y<l;y++) {
						if(complemento!=null && complemento.compareTo(registro[4][y])==0) {
							x3=1;
						}
					}
					double exp1=Math.pow(2, 16)-1;
					constanteVal ctrlCon = new constanteVal(complemento,-1*(exp1/2),exp1);
					/*if(x3==0) {
						data[j]=data[j].concat("¡Error en el complemento!");
					}*/
					System.out.println(ctrlCon.getStatus());
					if(ctrlCon.getTipo()=="Fail" && ctrlCon.getStatus()==4 && complemento.startsWith(comi+"")==false && complemento.endsWith(comi+"")==false) {
						data[j]=data[j].concat("¡Error se esperaba una constante!");
					}
					else if(data[j].endsWith(" ") && pseudo!=null && pseudo.toUpperCase().matches("DW")!=true) {
						data[j]=data[j].concat("¡Error en el rango de la constante!");
					}
					else if(data[j].endsWith(" ") && pseudo!=null && pseudo.toUpperCase().matches("DW")==true && complemento.startsWith(comi+"")&& complemento.endsWith(comi+"")) {
						data[j]=data[j].concat("¡Error no puede tener DW una constante cadena de caracteres!");
					}
					else if(data[j].endsWith(" ")) {
						data[j]=data[j].concat("¡Error en el rango de la constante!");
					}
				}
			}
			
		}
		i=0;
		c=0;
		while(!lineas[i].toLowerCase().contains("data segment")) {
			i++;
		}
		lineas[i]=lineas[i].concat(" - Inicia definicion de segmento de datos");
		i++;
		while(!lineas[i].toLowerCase().contains("ends")) {
			lineas[i] = data[c];
			c++;
			i++;
		}
		lineas[i]=lineas[i].concat(" - Fin de definicion de segmento de datos");

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


