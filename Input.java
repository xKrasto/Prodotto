/*
 * The MIT License
 *
 * Copyright 2017 Alex/xKrasto.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package cassa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Alex
 */
public class Input {

    public static InputStreamReader inp = new InputStreamReader(System.in);
    public static BufferedReader tastiera = new BufferedReader(inp);

    public static String reqString(String s1) {
        boolean done = false;
        String s = "Ops";
        do {
            try {
                System.out.print(s1);
                s = tastiera.readLine();
                done = true;
            } catch (IOException e) {
                System.err.println("Errore nell'inserimento");
                done = false;
            }
        } while (!done);
        return s;
    }

    public static int reqInt(String s) {

        int i = 0;
        boolean done = false;
        do {
            try {
                System.out.print(s);
                i = Integer.parseInt(tastiera.readLine());
                done = true;
            } catch (IOException | NumberFormatException e) {
                System.err.println("Errore nell'inserimento");
                done = false;
            }
        } while (!done);
        return i;
    }

    public static char reqChar(String s) {
        boolean done = false;
        char c = 'a';
        do {
            try {
                System.out.print(s);
                c = tastiera.readLine().charAt(0);
                done = true;
            } catch (IOException e) {
                System.err.println("Errore nell'inserimento");
                done = false;
            }
        } while (!done);
        return c;
    }

    public static double reqDouble(String s) {
        boolean done = false;
        double d = 0;
        do {
            try {
                System.out.print(s);
                d = Double.parseDouble(tastiera.readLine());
                done = true;
            } catch (IOException | NumberFormatException e) {
                System.err.println("Errore nell'inserimento!");
                done = false;
            }
        } while (!done);
        return d;
    }

    public static float reqFloat(String s) {

        boolean done = false;
        Float f = 0f;
        do {
            try {
                System.out.print(s);
                f = Float.parseFloat(tastiera.readLine());
                done = true;
            } catch (IOException | NumberFormatException e) {
                System.err.println("Errore nell'inserimento");
                done = false;
            }
        } while (!done);
        return f;
    }

    public static boolean reqBoolean(String s) {
        boolean done = false, d = false, duePunti = false;
        char temp;
        if (s.endsWith(":") || s.endsWith(": ")) {
            s.replace(':', ' ');
            duePunti = true;

        }
        do {
            try {
                if (duePunti) {
                    System.out.println(s + "(S/N): ");
                } else {
                    System.out.println(s + " (S/N) ");
                }
                temp = tastiera.readLine().toUpperCase().charAt(0);
                switch (temp) {
                    case 'T':
                    case 'Y':
                    case 'S':
                        d = true;
                        done = true;
                        break;
                    case 'F':
                    case 'f':
                        d = false;
                        done = true;
                        break;
                    default:
                        throw new Exception();

                }
            } catch (Exception e) {
                System.err.print("Errore nell'inserimento!");
                done = false;
            }
        } while (!done);
        return d;
    }

    public static long reqLong(String s) {
        boolean done = false;
        long l = 0;
        do {
            try {
                System.out.print(s);
                l = Long.valueOf(tastiera.readLine());
                done = true;
            } catch (IOException | NumberFormatException e) {
                System.err.println("Errore nell'inserimento");
                done = false;
            }
        } while (!done);
        return l;
    }
}
