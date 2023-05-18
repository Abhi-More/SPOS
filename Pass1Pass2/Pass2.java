import java.io.*;
import java.util.*;

class Pass2 {
    public static void main(String args[]) throws Exception {

        String s;
        String s1[] = new String[60];
        String s2[] = new String[60];
        String s3[] = new String[60];

        FileWriter fobj1 = new FileWriter("FinalCode.txt");
        BufferedWriter buff1 = new BufferedWriter(fobj1);
        buff1.write("********* MOT *********");

        FileReader fobj2 = new FileReader("intermediate.txt");
        BufferedReader buff2 = new BufferedReader(fobj2);

        FileReader fobj3 = new FileReader("SymbolTable.txt");
        BufferedReader buff3 = new BufferedReader(fobj3);

        FileReader fobj4 = new FileReader("LiteralTable.txt");
        BufferedReader buff4 = new BufferedReader(fobj4);

        int m = 0;
        while ((s = buff2.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            while (st.hasMoreTokens()) {
                s1[m] = st.nextToken();
                m++;
            }
        }
        int m1 = 0;
        while ((s = buff3.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(s);
            while (st.hasMoreTokens()) {
                s2[m1] = st.nextToken();
                m1++;
            }
        }
        int m2 = 0;
        while ((s = buff4.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(s);
            while (st.hasMoreTokens()) {
                s3[m2] = st.nextToken();
                m2++;
            }
        }

        for (int i = 0; i < m; i++) {
            if ("AD".equals(s1[i]) && "01".equals(s1[i + 1])) {
                buff1.write("");
                buff1.newLine();
            }
            if ("RG".equals(s1[i]) || "RG".equals(s1[i]) || "RG".equals(s1[i]) || "RG".equals(s1[i])) {
                if ("AD".equals(s1[i + 1])) {
                    buff1.write("hey");
                } else {
                    buff1.write("\t" + s1[i + 1]);
                    buff1.write(" ");
                    buff1.write(" ");
                }

            }
            if ("IS".equals(s1[i])) {
                buff1.newLine();
                buff1.write(s1[i - 1] + "\t");
                buff1.write(s1[i + 1]);
            }

            if ("L".equals(s1[i])) {
                if ("IS".equals(s1[i - 4])) {

                    for (int j = 0; j < m2; j++) {
                        if (s1[i + 1].equals(s3[j])) {
                            // buff1.write("\t");
                            buff1.write(s3[j]);
                        }

                    }
                }
            }

            if ("S".equals(s1[i])) {

                for (int j = 0; j < m1; j++) {
                    if (s1[i + 1].equals(s2[j])) {
                        buff1.write(s2[j] + "\t");
                    }

                }
            }
        }
        buff1.close();
        buff2.close();
        buff3.close();
        buff4.close();
    }
}