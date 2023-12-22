import java.util.*;

public class nextfit {

	static void NextFit(int blockSize[], int m, int processSize[], int n) {

		int allocation[] = new int[n], j = 0, t = m - 1;


		Arrays.fill(allocation, -1);


	for(int i = 0; i < n; i++){

		while (j < m){
			if(blockSize[j] >= processSize[i]){
				
				allocation[i] = j;
				
				blockSize[j] -= processSize[i];
				
				t = (j - 1) % m;
				break;
			}
			if (t == j){
				t = (j - 1) % m;
				break;
			}
			
			j = (j + 1) % m;
		}
	}

		System.out.print("\nProcess No.\tProcess Size\tBlock no.\n");
		for (int i = 0; i < n; i++) {
			System.out.print( i + 1 + "\t\t\t\t" + processSize[i]
					+ "\t\t\t\t");
			if (allocation[i] != -1) {
				System.out.print(allocation[i] + 1);
			} else {
				System.out.print("Not Allocated");
			}
			System.out.println("");
		}
	}

// Driver program
	public static void main(String[] args) {
		int blockSize[] = {5, 10, 20};
		int processSize[] = {10, 20, 5};
		int m = blockSize.length;
		int n = processSize.length;
		NextFit(blockSize, m, processSize, n);
	}
}
