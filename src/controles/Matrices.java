package controles;

public class Matrices {
	
	public Matrices() {
	}
	
	public boolean contains(String subString, String[][] matrix) {
		boolean existe = false;
		int n = matrix.length;
		int m = matrix[0].length;
		
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<m;j++) {
				
				if(matrix[i][j]!= null) {
					if(matrix[i][j].compareTo(subString)==0) {
						existe = true;
					}
				}
				
			}
		}
		
		return existe;
	}
}
