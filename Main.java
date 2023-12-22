import java.util.*;

public class Main {
	public static int[] sort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp;
					temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	};

	public static void main(String[] args) {
		// Taking inputs
		int[] pid = { 1, 2, 3, 4, 5 };
		int[] arrival = { 4, 4, 0, 3, 8 };
		int[] burst = { 5, 10, 2, 1, 4 };

		int[] completion = { 0, 0, 0, 0, 0 };
		int[] wait = { 0, 0, 0, 0, 0 };
		int[] turnaround = { 0, 0, 0, 0, 0 };

		System.out.println("\nBEFORE SORT\n");
		System.out.println("PID AT BT");
		for (int i = 0; i < arrival.length; i++) {
			System.out.println(pid[i] + "   " + arrival[i] + "  " + burst[i]);
		}

		// Sorting
		for (int i = 0; i < arrival.length - 1; i++) {
			for (int j = 0; j < arrival.length - 1; j++) {
				if (arrival[j] > arrival[j + 1]) {
					int temp;

					// Modifying arrival
					temp = arrival[j + 1];
					arrival[j + 1] = arrival[j];
					arrival[j] = temp;

					// Modifying pid
					temp = pid[j + 1];
					pid[j + 1] = pid[j];
					pid[j] = temp;

					// Modifying burst
					temp = burst[j + 1];
					burst[j + 1] = burst[j];
					burst[j] = temp;
				}
			}
		}

		// Print test
		System.out.println("\nAFTER SORT\n");
		System.out.println("PID AT BT");
		for (int i = 0; i < arrival.length; i++) {
			System.out.println(pid[i] + "   " + arrival[i] + "  " + burst[i]);
		}

		// Computing values
		completion[0] = arrival[0] + burst[0];
		for (int i = 1; i < arrival.length; i++) {
			if (arrival[i] > completion[i - 1]) {
				completion[i] = burst[i] + arrival[i];
			} else {
				completion[i] = burst[i] + completion[i - 1];
			}
		}

		float totalwt = 0;
		float totaltat = 0;

		for (int i = 0; i < arrival.length; i++) {
			turnaround[i] = completion[i] - arrival[i];
			wait[i] = turnaround[i] - burst[i];
			totalwt += wait[i];
			totaltat += turnaround[i];
		}

		System.out.println("\nFINAL RESULT\n");
		System.out.println("PID AT BT CT WT TAT");
		for (int i = 0; i < arrival.length; i++) {
			System.out.println(
					pid[i] + "   " + arrival[i] + "  " + burst[i] + "  " + completion[i] + "  " + wait[i] + "  " + turnaround[i]);
		}

		// Printing averages
		System.out.println("Avg. Waiting: " + totalwt / 5 + "  Avg. TAT: " + totaltat / 5);
	}
}
