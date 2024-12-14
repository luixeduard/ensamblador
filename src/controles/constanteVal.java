package controles;

public class constanteVal {

	private String tipo = "Dec";
	private int status;
	
	public constanteVal(String numero) {
		int base = 10;
		
		if(numero.toUpperCase().endsWith("H")){
			base = 16;
			this.tipo = "Hex";
		}
		if(numero.toUpperCase().endsWith("B")){
			base = 2;
			this.tipo = "Bin";
		}
		
		maxMin observador = new maxMin(numero,base);
		
		if(observador.getStatus()!=0) {
			this.tipo="Fail";
		}
		this.status = observador.getStatus();
	}
	
	public constanteVal(String numero, double min, double max) {
		int base = 10;
		
		if(numero.toUpperCase().contains("H")){
			base = 16;
			this.tipo = "Hex";
		}
		if(numero.toUpperCase().contains("B")){
			base = 2;
			this.tipo = "Bin";
		}
		
		maxMin observador = new maxMin(numero,base,max,min);
		
		if(observador.getStatus()!=0) {
			this.tipo="Fail";
		}
		this.status = observador.getStatus();
	}

	public int getStatus() {
		return status;
	}

	public String getTipo() {
		return tipo;
	}
}
