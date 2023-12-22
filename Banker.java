import java.util.*;

public class Banker {
    public static void main(String[] args) {
        System.out.println("Bankers Algorithm");

        // // FOR TESTING
        // // No. of processes
        // int processes = 5;

        // // No. of resources
        // int resources = 3;
        // int[][] max = { { 7, 5, 3 },
        // { 3, 2, 2 },
        // { 9, 0, 2 },
        // { 2, 2, 2 },
        // { 4, 3, 3 }};

        // // Current allocation
        // int[][] allocation = { { 0, 1, 0 },
        // { 2, 0, 0 },
        // { 4, 0, 2 },
        // { 2, 1, 1 },
        // { 0, 0, 2 }};

        // Input setup
        Scanner s = new Scanner(System.in);
        System.out.println("Enter no. of processes: ");
        int processes = s.nextInt();
        System.out.println("Enter no. of resources: ");
        int resources = s.nextInt();

        // Declaring variables for arrays
        int[][] max = new int[processes][resources];
        int[][] allocation = new int[processes][resources];
        int[] available = new int[resources];

        // Taking inputs
        System.out.println("Enter max capacity: ");
        for (int i = 0; i < processes; i++) {
            System.out.println("For Process P" + i);
            for (int j = 0; j < resources; j++) {
                max[i][j] = s.nextInt();
            }
        }
        System.out.println("Enter current allocation: ");
        for (int i = 0; i < processes; i++) {
            System.out.println("For Process P" + i);
            for (int j = 0; j < resources; j++) {
                allocation[i][j] = s.nextInt();
            }
        }
        System.out.println("Enter currently available resources: ");
        for (int i = 0; i < resources; i++) {
            available[i] = s.nextInt();
        }

        s.close();

        // int[] available = {2,3,2};

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

        // Execution Queue
        System.out.println("\nExecution Queue:");

        // Tracking completed processes
        boolean completed[] = new boolean[processes];
        int completedCount = 0;
        int flag = 2;

        while (flag == 2) {
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
                    System.out.print("->P" + i);
                    for (int j = 0; j < resources; j++) {
                        available[j] += allocation[i][j];
                    }
                }
            }
        }

        System.out.println("\n\nALLOCATION");
        for (int i = 0; i < resources; i++) {
            System.out.print(available[i] + " ");
        }
        System.out.println();
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