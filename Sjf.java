import java.util.*;

public class Sjf {
    public static void main(String[] arge) {
        System.out.println("SJF PREEMPTIVE");
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter no. of processes: ");
        int process_count = sc.nextInt();

        // // Main data structures
        int[] burst = new int[process_count];
        int[] arrival = new int[process_count];

        // Additional data structures
        int[] timeline = new int[50]; // Keeps track of executions
        int completed_count = 0; // Counting completed prjocesses

        // Getting user input
        for (int i = 0; i < process_count; i++) {
            System.out.println("Arrival of P" + i + ": ");
            arrival[i] = sc.nextInt();
            System.out.println("Burst of P" + i + ": ");
            burst[i] = sc.nextInt();
        }

        sc.close();

        // Testing values
        // int process_count = 5;
        // int[] burst = { 6, 2, 8, 3, 4 };
        // int[] arrival = { 2, 5, 1, 0, 4 };

        int[] completion_time = new int[process_count];
        int[] wait_time = new int[process_count];
        int[] turnaround_time = new int[process_count];
        int[] original_burst = burst.clone();
        float turnaround_total = 0;
        float wait_total = 0;

        // Repeat until all processes are completed
        int current_index = 0;
        while (completed_count < process_count) {

            // Getting smallest available process
            int smallest_index = smallest_available(burst, current_index, arrival);

            // Adding process to timeline
            if (smallest_index == -1) {
                // No process available
                timeline[current_index] = -1;
                current_index++;
                continue;
            } else {
                // Process available
                timeline[current_index] = smallest_index;
                burst[smallest_index]--;
            }

            // Checking if process finished
            if (burst[smallest_index] == 0) {
                completed_count++;
                completion_time[smallest_index] = current_index + 1;
                burst[smallest_index] = 5000;
            }

            current_index++;
        }

        // Computing Wait time & Turnaround time
        for (int i = 0; i < process_count; i++) {
            turnaround_time[i] = completion_time[i] - arrival[i];
            wait_time[i] = turnaround_time[i] - original_burst[i];
            turnaround_total += turnaround_time[i];
            wait_total += wait_time[i];
        }

        // Printing Output
        System.out.println("Pid\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < process_count; i++) {
            System.out.print("P" + i + "\t" + arrival[i] + "\t" + original_burst[i] + "\t" + completion_time[i] + "\t"
                    + turnaround_time[i] + "\t" + wait_time[i] + "\n");
        }
        System.out.println("\nGantt Chart: ");
        for (int i = 0; i < current_index; i++) {
            if (timeline[i] != -1) {
                System.out.print("->P" + timeline[i]);
            } else {
                System.out.print("->X");
            }
        }
        System.out.println(
                "\n\nAVG TAT: " + turnaround_total / process_count + "  AVG WT: " + wait_total / process_count);
    }

    public static int smallest_available(int[] burst, int current_time, int[] arrival) {
        int index = 0;
        boolean found = false;
        for (int i = 0; i < burst.length; i++) {
            // System.out.println(burst[index] + " " + burst[i] + " " + arrival[i] + " " +
            // current_time);
            if (burst[index] >= burst[i] && arrival[i] <= current_time && burst[i] != -1) {
                index = i;
                found = true;
            }
        }

        if (found)
            return index;
        else
            return -1;
    }
}