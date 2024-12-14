package controles;

public class maxMin {
	private int numero;
	//private double numero2;
	private int status;
	
	public maxMin(String n, int base){
		
		n = n.toUpperCase().replaceAll("H", "");
		n = n.toUpperCase().replaceAll("B", "");
		
		int num;
		int max = 256;
		int min = -128;
		
		try {
			num = Integer.parseInt(n,base);
			this.numero = num;
			
			if(num>max) {
				this.status = 1;
			}
			if(num<min) {
				this.status = -1;
			}
			
			if(num<=max && num>=min) {
				this.status = 0;
			}
			
		}catch (NumberFormatException e) {
			this.status = 4;
		}
		
	}
	
	public maxMin(String n, int base, double max, double min) {
		
		n = n.toUpperCase().replaceAll("H", "");
		n = n.toUpperCase().replaceAll("B", "");
		
		double num;
		
		try {
			num = Integer.parseInt(n,base);
			
			if(num<=max && num>=min) {
				this.status = 0;
			}
			
			if(num>max) this.status = 1;
			if(num<min) this.status = -1;
			
			num = Integer.parseInt(n,base);
			//this.numero2 = num;
			if(num<max && num>min) {
			this.status = 0;
			}
			if(num>max) this.status = 1;
			if(num<min) this.status = -1;
			
		}catch (NumberFormatException e) {
			this.status = 4;
		}
		
	}
	
	public int getStatus() {
		return status;
	}

	public int getNumero() {
		return numero;
	}
	
}
