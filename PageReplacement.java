import java.util.*;

public class PageReplacement {
    public static void FIFO(int pages[], int frame[]) {
        int pageHit = 0, pageMiss = 0, ptr = 0;
        for (int i = 0; i < frame.length; i++) {
            frame[i] = -1;
        }
        int flag = 0;
        for (int i = 0; i < pages.length; i++) 
        {
            flag = 1;
            int page = pages[i];
            for (int j = 0; j < frame.length; j++) {
                if (frame[j] == page) 
                {
                    flag = 0;
                    pageHit++;
                }
            }
            if (flag == 1) {
                frame[ptr] = page;
                pageMiss++;
                ptr++;
                if (ptr == frame.length)
                    ptr = 0;
            }

            System.out.print("Frame[" + (i + 1) + "]");
            for (int k = 0; k < frame.length; k++) {
                System.out.print("\t" + frame[k]);
            }
            System.out.println();
        }
        System.out.println("\nNumber of PageHit: " + pageHit);
        System.out.println("Number of PageMiss(Page Fault): " + pageMiss);
        float pagemiss = pageMiss, noofpages = pages.length;
        System.out.println("Page fault ratio: " + pagemiss / noofpages);
    }

    public static void LRU(int page[], int frame[]) 
    {
        for (int i = 0; i < frame.length; i++)
            frame[i] = -1;

        int flag = 0, pageHit = 0, pageMiss = 0;
        int array[] = new int[frame.length];

        for (int i = 0; i < frame.length; i++)
            array[i] = 0;

        int j =0;
        for (int i = 0; i < page.length; i++) 
        {
            flag = 0;
            for (int x = 0; x < frame.length; x++)
            {
                if (frame[x] == page[i]) 
                {
                    flag = 1;
                    pageHit++;
                    array[x] = 0;
                }
            }
            if (flag == 0)
            {
                if (frame[j] == -1)
                {
                    frame[j] = page[i];
                    pageMiss++;
                    j = (j+1)%frame.length;
                } 
                else 
                {
                    int max = array[0], loc = 0;
                    pageMiss++;
                    for (int b = 0; b < frame.length; b++)
                    {
                        if (array[b] > max) 
                        {
                            max = array[b];
                            loc = b;
                        }
                    }
                    frame[loc] = page[i];
                    array[loc] = 0;
                    j = (j+1)%frame.length;
                }
            }

            for (int a = 0; a < frame.length; a++) 
            {
                if (frame[a] == -1)
                    array[a] = 0;
                else
                    array[a]++;
            }

            System.out.print("Frame[" + (i + 1) + "]: ");
            for (int k = 0; k < frame.length; k++)
                System.out.print(" " + frame[k]);
            System.out.println();

        }
        System.out.println("\nNumber of PageHit: " + pageHit);
        System.out.println("Number of PageMiss(Page Fault): " + pageMiss);
        float pagemiss = pageMiss, noofpages = page.length;
        System.out.println("Page fault ratio: " + pagemiss / noofpages);
    }

    public static void OPT(int pages[], int frame[])
    {
        int pageHit = 0, pageMiss = 0;
        int array[] = new int[pages.length];
        for (int i = 0; i < frame.length; i++) 
        {
            frame[i] = -1;
        }

        int flag1, flag2, flag3 = 0, position = 0, max;

        for (int i = 0; i < pages.length; ++i) 
        {
            flag1 = flag2 = 0;
            for (int j = 0; j < frame.length; ++j) {
                if (frame[j] == pages[i]) {
                    pageHit++;
                    flag1 = flag2 = 1;
                    break;
                }
            }
            if (flag1 == 0)
            {
                for (int j = 0; j < frame.length; ++j)
                {
                    if (frame[j] == -1)
                    {
                        pageMiss++;
                        frame[j] = pages[i];
                        flag2 = 1;
                        break;
                    }
                }
            }

            if (flag2 == 0) 
            {
                flag3 = 0;
                for (int j = 0; j < frame.length; ++j) 
                {
                    array[j] = -1;
                    for (int k = i + 1; k < pages.length; ++k) 
                    {
                        if (frame[j] == pages[k]) {
                            // pageHit++;
                            array[j] = k;
                            break;
                        }
                    }
                }
                for (int j = 0; j < frame.length; ++j) 
                {
                    if (array[j] == -1) 
                    {
                        position = j;
                        flag3 = 1;
                        break;
                    }
                }
                if (flag3 == 0)
                {
                    max = array[0];
                    position = 0;
                    for (int j = 1; j < frame.length; ++j) {
                        if (array[j] > max) {
                            max = array[j];
                            position = j;
                        }
                    }
                }
                frame[position] = pages[i];
                pageMiss++;
            }

            System.out.print("Frame[" + (i + 1) + "]");
            for (int k = 0; k < frame.length; k++)
            {
                System.out.print("\t" + frame[k]);
            }
            System.out.println();
        }
        System.out.println("\nNumber of PageHit: " + pageHit);
        System.out.println("Number of PageMiss(Page Fault): " + pageMiss);
        float pagemiss = pageMiss, noofpages = pages.length;
        System.out.println("Page fault ratio: " + pagemiss / noofpages);
    }

    public static void main(String[] args) {
        System.out.println("Page Replacement Algorithms");
        Scanner sc = new Scanner(System.in);
        char dsc;
        System.out.println("Enter size of frame: ");
        int framesize = sc.nextInt();
        
        System.out.println("Enter Length of Reference String Pages: ");
        int n = sc.nextInt();

        int refStr[] = new int[n];

        System.out.println("Enter Reference String: ");
        for (int i = 0; i < refStr.length; i++) {
            refStr[i] = sc.nextInt();
        }
        int frame[] = new int[framesize];
        System.out.print("\nrefStr: ");
        for (int i = 0; i < refStr.length; i++) {
            System.out.print(" " + refStr[i]);
        }
        System.out.print("\nFrame: ");
        for (int i = 0; i < frame.length; i++) {
            frame[i] = -1;
            System.out.print(" " + frame[i]);
        }

        do 
        {
            System.out.println("\n*****Menu*****\n1:FIFO\n2:LRU\n3:OPT\n4:Exit\n******");
            System.out.println("Enter a choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    FIFO(refStr, frame);
                    break;
                case 2:
                    LRU(refStr, frame);
                    break;
                case 3:
                    OPT(refStr, frame);
                    break;
                case 4:
                    return;
            }
            System.out.println("Do you want to continue(y/n): ");
            dsc = sc.next().charAt(0);
            if (dsc == 'n')
                return;
        } while (dsc == 'y');
        sc.close();
    }
}
