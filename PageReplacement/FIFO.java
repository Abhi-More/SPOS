import java.util.Scanner;

public class FIFO {
    int Counter;
    int MaxPages;
    int[][] Ref_String;
    int[] MemBuffer;
    Scanner Sc = new Scanner(System.in);

    void Reference_String() {
        System.out.println("Enter No Of Processes : ");
        Counter = Sc.nextInt();
        Ref_String = new int[Counter][2];
        System.out.println("Enter Max Memory Page Buffer Size : ");
        MaxPages = Sc.nextInt();
        MemBuffer = new int[MaxPages];
        for (int j = 0; j < MaxPages; j++) {
            MemBuffer[j] = -1;
        }
        System.out.println("Enter Processes in Reference String : ");
        for (int i = 0; i < Counter; i++) {
            Ref_String[i][0] = Sc.nextInt();
            Ref_String[i][1] = -1;
        }
    }

    void rfifo() {
        for (int i = 0; i < Counter; i++) {
            if (!isExists(Ref_String[i][0])) {
                getMemLoc(Ref_String[i][0]);
                Ref_String[i][1] = 1;
            } else {
                Ref_String[i][1] = 0;
            }
            System.out.println(" Ref_String : " + Ref_String[i][0]);
            for (int j = 0; j < MaxPages; j++) {
                System.out.println(" M[" + j + "]:" + MemBuffer[j]);
            }
        }
    }

    void displayHitFault() {
        System.out.println("Hit Counter : " + getHitCounter());
        float HitRatio = Float.parseFloat(Integer.toString(getHitCounter())) /
                Float.parseFloat(Integer.toString(Counter));
        System.out.println("Hit Ratio : " + HitRatio);
        System.out.println("Fault Counter : " + getFaultCounter());
        float FaultRatio = Float.parseFloat(Integer.toString(getFaultCounter()))
                / Float.parseFloat(Integer.toString(Counter));
        System.out.println("Fault Ratio : " + FaultRatio);
    }

    boolean isExists(int num) {
        for (int j = 0; j < MaxPages; j++) {
            if (MemBuffer[j] == num)
                return true;
        }
        return false;
    }

    void getMemLoc(int num) {
        int j = 0;
        for (; j < MaxPages - 1; j++) {
            MemBuffer[j] = MemBuffer[j + 1];
        }
        MemBuffer[j] = num;
    }

    int getHitCounter() {
        int hitCounter = 0;
        for (int i = 0; i < Counter; i++) {
            if (Ref_String[i][1] == 0) {
                hitCounter++;
            }
        }
        return hitCounter;
    }

    int getFaultCounter() {
        int faultCounter = 0;
        for (int i = 0; i < Counter; i++) {
            if (Ref_String[i][1] == 1) {
                faultCounter++;
            }
        }
        return faultCounter;
    }

    public static void main(String[] args) {
        System.out.println("FIFO Page Replacement Algorithm:");
        FIFO fifo = new FIFO();
        fifo.Reference_String();
        fifo.rfifo();
        fifo.displayHitFault();
    }
}
