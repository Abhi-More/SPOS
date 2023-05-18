import java.util.*;

public class Scheduling {
    public static void main(String[] args) {
        AbhiScheduling obj = new AbhiScheduling();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n\t 1:FCFS\n\t 2:SJF\n\t 3:Priority Scheduling\n\t 4:Round Robin Scheduling");
            System.out.print("\n\t\tEnter the choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    obj.FCFS();
                    break;
                case 2:
                    obj.SJF();
                    break;
                case 3:
                    obj.Priority();
                    break;
                case 4:
                    obj.round_robin();
                    break;
                default: {
                    System.out.println("\n\t\tInvalid Choice");
                    break;
                }
            }
        } while (choice == 1 || choice == 2 || choice == 3 || choice == 4);
    }

    public void FCFS() {
        System.out.println("FCFS Scheduling");
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter the no of processes:");
        int pr = cin.nextInt();
        int Processes[] = new int[pr];
        int Burst_T[] = new int[pr];
        int Waiting_T[] = new int[pr];
        int Turnaround_T[] = new int[pr];
        System.out.println("CPU time for" + (pr) + "Processes:\n");
        for (int i = 0; i < pr; i++) {
            System.out.println("Burst time for Process [" + (i + 1) + "] :");
            Burst_T[i] = cin.nextInt();
            Processes[i] = i + 1;
        }
        Waiting_T[0] = 0;
        Turnaround_T[0] = Burst_T[0];
        int Total_WT = 0, Total_TAT = 0;
        for (int j = 1; j < pr; j++) {
            Waiting_T[j] = Waiting_T[j - 1] + Burst_T[j - 1];
            Total_WT += Waiting_T[j];
            Turnaround_T[j] = Waiting_T[j] + Burst_T[j];
            Total_TAT += Turnaround_T[j];
        }
        float Avg_TurnaroundT = (float) Total_TAT / pr;
        float Avg_WaitingT = (float) Total_WT / pr;
        System.out.println("\t\t\t\t########### FCFS Scheduling ###########\n\n");
        System.out.println("\t\tProcess\t\tBurst_time\t\tWaiting_time\t\tTurnaround_time");
        for (int k = 0; k < pr; k++) {
            System.out.println("\t\t" + (Processes[k]) + "\t\t\t" + (Burst_T[k]) + "\t\t\t" + (Waiting_T[k]) + "\t\t\t"
                    + (Turnaround_T[k]));
            System.out.println();
        }
        System.out.println("Average Waiting Time : " + Avg_WaitingT);
        System.out.println("Average Turnaround Time : " + Avg_TurnaroundT);
    }

    void SJF() {
        System.out.println("SJS Scheduling");
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter the no of processes:");
        int pr = cin.nextInt();
        int Processes[] = new int[pr];
        int Burst_T[] = new int[pr];
        int Waiting_T[] = new int[pr];
        int Turnaround_T[] = new int[pr];
        System.out.println("CPU time for" + (pr) + "Processes:\n");
        for (int i = 0; i < pr; i++) {
            System.out.println("Burst time for Process [" + (i + 1) + "] :");
            Burst_T[i] = cin.nextInt();
            Processes[i] = i + 1;
        }
        Waiting_T[0] = 0;
        Turnaround_T[0] = Burst_T[0];
        int Total_WT = 0, Total_TAT = 0;
        for (int s = 0; s < pr; s++) {
            for (int t = s + 1; t < pr; t++) {
                if (Burst_T[s] > Burst_T[t]) {
                    int temp_Burst_T = Burst_T[s];
                    Burst_T[s] = Burst_T[t];
                    Burst_T[t] = temp_Burst_T;
                    int temp_Processes = Processes[s];
                    Processes[s] = Processes[t];
                    Processes[t] = temp_Processes;
                }
            }
        }
        for (int j = 1; j < pr; j++) {
            Waiting_T[j] = Waiting_T[j - 1] + Burst_T[j - 1];
            Total_WT += Waiting_T[j];
            Turnaround_T[j] = Waiting_T[j] + Burst_T[j];
            Total_TAT += Turnaround_T[j];
        }
        float Avg_TurnaroundT = (float) Total_TAT / pr;
        float Avg_WaitingT = (float) Total_WT / pr;
        System.out.println("\t\t\t\t########### SJF Scheduling ###########\n\n");
        System.out.println("\t\tProcess\t\tBurst_time\t\tWaiting_time\t\tTurnaround_time");
        for (int k = 0; k < pr; k++) {
            System.out.println("\t\t" + (Processes[k]) + "\t\t\t" + (Burst_T[k]) + "\t\t\t" + (Waiting_T[k]) + "\t\t\t"
                    + (Turnaround_T[k]));
            System.out.println();
        }
        System.out.println("Average Waiting Time : " + Avg_WaitingT);
        System.out.println("Average Turnaround Time : " + Avg_TurnaroundT);
    }

    void Priority() {
        System.out.println("Priority Scheduling");
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter the no of processes:");
        int pr = cin.nextInt();
        int Processes[] = new int[pr];
        int Burst_T[] = new int[pr];
        int Waiting_T[] = new int[pr];
        int Turnaround_T[] = new int[pr];
        int Priority_P[] = new int[pr];
        System.out.println("CPU time for" + (pr) + "Processes:\n");
        for (int i = 0; i < pr; i++) {
            System.out.println("Burst time for Process [" + (i + 1) + "] is :");
            Burst_T[i] = cin.nextInt();
            System.out.println("Priority for Process [" + (i + 1) + "] is :");
            Priority_P[i] = cin.nextInt();
            Processes[i] = i + 1;
        }
        Waiting_T[0] = 0;
        Turnaround_T[0] = Burst_T[0];
        int Total_WT = 0, Total_TAT = 0;
        for (int s = 0; s < pr; s++) {
            for (int t = s + 1; t < pr; t++) {
                if (Priority_P[s] < Priority_P[t]) { // sort on basis of Priority
                    int temp_Burst_T = Burst_T[s];
                    Burst_T[s] = Burst_T[t];
                    Burst_T[t] = temp_Burst_T;
                    int temp_processes = Processes[s];
                    Processes[s] = Processes[t];
                    Processes[t] = temp_processes;
                    int temp_Priority = Priority_P[s];
                    Priority_P[s] = Priority_P[t];
                    Priority_P[t] = temp_Priority;
                }
            }
        }
        for (int j = 1; j < pr; j++) {
            Waiting_T[j] = Waiting_T[j - 1] + Burst_T[j - 1];
            Total_WT += Waiting_T[j];
            Turnaround_T[j] = Waiting_T[j] + Burst_T[j];
            Total_TAT += Turnaround_T[j];
        }
        float Avg_TurnaroundT = (float) Total_TAT / pr;
        float Avg_WaitingT = (float) Total_WT / pr;
        System.out.println("\t\t\t\t########### Priority Scheduling ###########\n\n");
        System.out.println("\t\tProcess\t\tBurst_time\t\tWaiting_time\t\tTurnaround_time");
        for (int k = 0; k < pr; k++) {
            System.out.println("\t\t" + (Processes[k]) + "\t\t\t" + (Burst_T[k]) + "\t\t\t" + (Waiting_T[k]) + "\t\t\t"
                    + (Turnaround_T[k]));
            System.out.println();
        }
        System.out.println("Average Waiting Time : " + Avg_WaitingT);
        System.out.println("Average Turnaround Time : " + Avg_TurnaroundT);
    }

    void round_robin() {
        Scanner cin = new Scanner(System.in);
        int pr, Quantum;
        System.out.print("\nEnter the Total number of Process :- \n");
        pr = cin.nextInt();
        int Waiting_T[] = new int[pr];
        int Burst_T[] = new int[pr];
        int R_Time[] = new int[pr];
        int Turnaround_T[] = new int[pr];
        int Processes[] = new int[pr];
        System.out.println("\nEnter the CPU Time :");
        for (int i = 0; i < pr; i++) {
            System.out.print("\tprocess P[" + (i + 1) + "] Burst Time :- ");
            Burst_T[i] = cin.nextInt();
            R_Time[i] = Burst_T[i];
            Processes[i] = i + 1;
        }
        System.out.print("\n\nEnter Quantum Time : ");
        Quantum = cin.nextInt();
        int R_P = pr;
        int i = 0;
        int time = 0;
        int Total_WT = 0, Total_TAT = 0;
        System.out.println("\n\t************ The GANTT chart for Round Robin Scheduling will be ************ \n");
        while (R_P != 0) {
            if (R_Time[i] > Quantum) {
                R_Time[i] = R_Time[i] - Quantum;
                System.out.print(" | P[" + (i + 1) + "] | ");
                time += Quantum;
                System.out.print(time);
            } else if (R_Time[i] <= Quantum && R_Time[i] > 0) {
                time += R_Time[i];
                R_Time[i] = R_Time[i] - R_Time[i];
                System.out.print(" | P[" + (i + 1) + "] | ");
                R_P--;
                Waiting_T[i] = time - Burst_T[i];
                Total_WT += Waiting_T[i];
                Turnaround_T[i] = time;
                Total_TAT += Turnaround_T[i];
                System.out.print(time);
            }
            i++;
            if (i == pr) {
                i = 0;
            }
        }
        float Avg_TurnaroundT = (float) Total_TAT / pr;
        float Avg_WaitingT = (float) Total_WT / pr;
        System.out.println("\t\t\t\t########### Round Robin Scheduling ###########\n\n");
        System.out.println("\t\tProcess\t\tBurst_time\t\tWaiting_time\t\tTurnaround_time");
        for (int k = 0; k < pr; k++) {
            System.out.println("\t\t" + (Processes[k]) + "\t\t\t" + (Burst_T[k]) + "\t\t\t" + (Waiting_T[k]) +
                    "\t\t\t" + (Turnaround_T[k]));
            System.out.println();
        }
        System.out.println("Average Waiting Time : " + Avg_WaitingT);
        System.out.println("Average Turnaround Time : " + Avg_TurnaroundT);
    }
}
