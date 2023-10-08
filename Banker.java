public class Banker {
    public static void main(String[] args) {
        System.out.println("Bankers Algorithm");
        // No. of processes
        int processes = 5;

        // No. of resources
        int resources = 3;
        int[][] max = { { 7, 5, 3 }, 
        { 3, 2, 2 }, 
        { 9, 0, 2 }, 
        { 2, 2, 2 }, 
        { 4, 3, 3 }};

        // Current allocation
        int[][] allocation = { { 0, 1, 0 }, 
        { 2, 0, 0 }, 
        { 4, 0, 2 }, 
        { 2, 1, 1 }, 
        { 0, 0, 2 }};

        int[] available = {2,3,2};

        // Calculating need matrix
        int[][] need = new int[processes][resources];
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        // Checking need matrix
        System.out.println("NEED MATRIX");
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                System.out.print(need[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        // Tracking completed processes
        boolean completed[] = new boolean[processes];
        int completedCount = 0;
        int flag = 2;
        
        while(flag == 2) {
            // Main loop\
            flag = 1;
            for (int i = 0; i < processes; i++) {
                // Exit if all processes executed
                if (completedCount == processes) {
                    break;
                }
                if (isSmall(need[i], available) && completed[i] == false) {
                    flag = 2;
                    completed[i] = true;
                    completedCount++;
                    System.out.println("ADDED FOR PROCESS " + i);
                    for (int j = 0; j < resources; j++) {
                        available[j] += allocation[i][j];
                    }
                }           
            }
        }

        System.out.println("ALLOCATION");
        for (int i = 0; i < resources; i++) {
            System.out.print(available[i]);
            System.out.println();
        }
    }

    public static boolean isSmall(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                return false;
            }
        }
        return true;
    }
}