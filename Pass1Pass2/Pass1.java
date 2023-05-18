import java.io.*;
import java.util.*;

class Pass1 {
    public static void main(String args[]) throws Exception {
        int location = 0;
        int j, sym, lit, v;
        j = sym = lit = v = 0;

        FileReader fobj1 = new FileReader("Input.txt");
        BufferedReader buff1 = new BufferedReader(fobj1);

        FileWriter fobj2 = new FileWriter("SymbolTable.txt");
        BufferedWriter buff2 = new BufferedWriter(fobj2);

        FileWriter fobj3 = new FileWriter("Intermediate.txt");
        BufferedWriter buff3 = new BufferedWriter(fobj3);

        FileWriter fobj4 = new FileWriter("LiteralTable.txt");
        BufferedWriter buff4 = new BufferedWriter(fobj4);
        buff4.write("***********Literals Table**********");
        buff4.newLine();
        buff4.newLine();

        buff2.write("***********Symbol Table************");
        buff2.newLine();
        buff2.newLine();

        buff3.write("************Intermediate code*********");
        buff3.newLine();
        buff3.newLine();
        String s, sl;
        String s1[] = new String[4];
        String s2[] = new String[4];
        String s5[][] = new String[4][2];
        while ((s = buff1.readLine()) != null) {
            int flag = 0;
            int m = 0;
            StringTokenizer st = new StringTokenizer(s);
            while (st.hasMoreTokens()) {
                s1[m] = st.nextToken();
                m++;
            }
            if (s1[1].equals("START")) {

                buff3.write("\t\t  AD	 01");
                buff3.write(" 	C " + s1[2]);
                location = Integer.parseInt(s1[2]);
                buff3.newLine();
                buff3.newLine();

            } else if (s1[1].equals("END")) {
                for (int i = 1; i <= lit; i++) {
                    buff3.write(location + "  ");
                    buff3.write("\t  AD	 02");
                    buff3.newLine();
                    buff3.newLine();
                    location = location + 1;
                }
            } else {
                buff3.write(location + "  ");
                if (!s1[0].contains("-")) {
                    sym++;
                    buff2.write(sym + " " + s1[0]);
                    buff2.write(" " + location + "\n");
                }

                FileReader file5 = new FileReader("Opcode.txt");
                BufferedReader buff5 = new BufferedReader(file5);
                while ((sl = buff5.readLine()) != null) {
                    j = 0;
                    StringTokenizer se = new StringTokenizer(sl);
                    while (se.hasMoreTokens()) {
                        s2[j] = se.nextToken();
                        j++;
                    }
                    if (s1[1].equals(s2[0]) == true) {
                        for (int a = 1; a < 3; a++) {
                            buff3.write("  ");
                            buff3.write("   " + s2[a]);
                        }
                    }
                    if (s1[2].equals(s2[0]) == true) {
                        for (int a = 1; a < 2; a++) {
                            buff3.write("  ");
                            buff3.write("  " + s2[a] + " " + s2[2]);
                        }
                    }

                }

                if (s1[3].contains("=")) {
                    int i;
                    flag = 0;
                    for (i = 0; i < lit; i++) {
                        if (s5[i][1].equals(s1[3])) {
                            flag = 1;
                            break;
                        }
                    }

                    if (flag != 1) {
                        lit++;
                        s5[v][0] = Integer.toString(lit);
                        s5[v][1] = s1[3];
                        v++;
                        buff4.newLine();
                        buff4.write(" " + lit);
                        buff4.write(" " + s1[3]);
                        buff3.write(" ");
                        buff3.write(" " + "L  " + lit);
                    } else {
                        buff3.write(" ");
                        buff3.write(" " + "L  " + s5[i][0]);
                    }

                }

                else if ((s1[3]).matches("[a-zA-Z]")) {
                    buff3.write(" " + "S  " + sym);

                }
                location = location + 1;
                buff3.newLine();
                buff3.newLine();
                buff5.close();
            }
        }
        buff1.close();
        buff2.close();
        buff3.close();
        buff2.close();
        buff4.close();
    }
}
