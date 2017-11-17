import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class primerpunto {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Random rd = new Random();
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		
		// ingreso de m
		System.out.println("ingrese el valor de m");
		int m= Integer.parseInt(br.readLine());
		
		int[][] matriz = new int[m][m];
		int [][] matriz2 = new int[m][m];;
		
		
		for(int i = 0; i < matriz.length ;i++) {
			for(int j = 0; j < matriz.length;j++) {
				matriz[i][j] = rd.nextInt(9)+1;
			}
		}
		for(int i = 0; i < matriz.length ;i++) {
			for(int j = 0; j < matriz.length;j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
		int k = matriz.length /2 ;
		int u = k-1,i,j;
		
		
		for(i = 0;i < k;i++) {
			for(j = u;j < k;j++) {
				matriz2[i][j] = matriz[i][j]; 
			}
			u--;
		}	
		
		u=0;
		for(i = k; i < matriz.length;i++) {
			for(j = u; j < k;j++) {
				matriz2[i][j] = matriz[i][j];  
			}
			u++;
		}
		u = k;
		for(i = 0; i < k;i++) {
			for(j = k; j <= u;j++) {
				matriz2[i][j] = matriz[i][j];  
			}
			u++;
		}
		u = matriz.length;
		for(i = k; i < matriz.length;i++) {
			for(j = k; j < u;j++) {
				matriz2[i][j] = matriz[i][j];  
			}
			u--;
		}
		
		System.out.println();
		for(i = 0; i < matriz.length ;i++) {
			for( j = 0; j < matriz.length;j++) {
				if(matriz2[i][j] == 0) 
				{
					System.out.print("  ");
				}else 
				{
					System.out.print(matriz2[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
	
}
