package controles;

public class ValSegments {
	private int dsP = -1;		//Data segment pointer
	private int dsEP = -1;		//Ends data segment pointer
	private int ssP = -1;		//Stack segment pointer
	private int ssEP = -1;		//Ends Stack segment pointer
	
	
	public ValSegments(String[] lineas) {
		this.DSSearch(lineas);
		this.SSSearch(lineas);
	}

	private void DSSearch(String[] lineas) {
		int i = 0;
		while(lineas[i]!=null) {
			if(lineas[i].toUpperCase().compareTo("DATA SEGMENT")==0 && this.dsP == -1) {
				this.dsP = i;
			}
			if(lineas[i].toUpperCase().compareTo("ENDS")==0 && this.dsP != -1) {
				this.dsEP = i;
				break;
			}
			
			if(lineas[i].toUpperCase().compareTo("STACK SEGMENT")==0) {
				this.dsEP = -1;
				break;
			}
			
			i++;
		}
	}
	
	private void SSSearch(String[] lineas) {
		int i = 0;
		while(lineas[i]!=null) {
			if(lineas[i].toUpperCase().compareTo("STACK SEGMENT")==0 && this.ssP == -1) {
				this.ssP = i;
			}
			if(lineas[i].toUpperCase().compareTo("ENDS")==0 && this.ssP != -1) {
				this.ssEP = i;
				break;
			}
			
			if(lineas[i].toUpperCase().compareTo("CODE SEGMENT")==0) {
				this.ssEP = -1;
				break;
			}
			
			i++;
		}
	}
	
	public boolean launchDS() {
		if(this.dsEP != -1 && this.dsP !=- 1) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean launchSS() {
		if(this.ssP != -1 && this.ssEP != -1) {
			return true;
		}else {
			return false;
		}
	}

	public int getDsP() {
		return dsP;
	}


	public int getDsEP() {
		return dsEP;
	}


	public int getSsP() {
		return ssP;
	}


	public int getSsEP() {
		return ssEP;
	}
}
