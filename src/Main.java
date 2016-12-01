import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by brandonbeckwith on 11/17/16.
 *
 * Main Class for running everything else
 */
public class Main {

    private static JFrame frame = null;


    public static void main (String[] args){
        System.out.println("Hello World");
        run();
    }

    public static void test(int max){
        HammingEncode encode = new HammingEncode("101");
        HammingDecode decode = new HammingDecode("101");
        for (int i=1; i <= max; i++){
            String code = generate(i);

            String encodedCode = encode.encodeFrame(code);
            String decodedCode = decode.decodeFrame(encodedCode);
            String corruptedCode = corrupt(encodedCode);
            String fixedCode = decode.decodeFrame(corruptedCode);


            System.out.println("============================================================================================================================");
            System.out.println("Initial: " + code + "\n" + "Encoded: " + encodedCode + "\n" + "Decoded: " + decodedCode + "\n" + "Corrupted: "+ corruptedCode + "\n" + "Corrected: " + fixedCode);

            if (!decodedCode.equals(code)){
                System.out.println("Failed! 'decode'     {" + i +"}");
                break;
            } else if (!fixedCode.equals(code)) {
                System.out.println("Failed! 'fix'     {" + i +"}");
                break;
            } else {
                System.out.println("Passed!                {" + i + "}");
            }
        }
        System.out.println("============================================================================================================================");
        System.out.println("All tests passed!");
    }

    private static String corrupt(String frame){
        int pos = (int) (frame.length() * Math.random());
        StringBuilder out = new StringBuilder(frame);
        if (frame.charAt(pos) == '0'){
            out.setCharAt(pos, '1');
        } else {
            out.setCharAt(pos, '0');
        }
        return out.toString();
    }

    private static String generate(int length){
        Random rand = new Random();
        String out = "";
        for (int i=0; i < length; i++){
            out += (rand.nextInt(5) % 2);
        }
        return out;
    }

    public static void run(){
        Scanner scan = new Scanner(System.in);
        info();

        while (true){
            System.out.println("Please input the number of the task you wish to run.");
            System.out.print("Task Number: ");
            int num = scan.nextInt();
            scan.nextLine();
            switch (num){
                case 1: hamEncode(scan); break;
                case 2: hamDecode(scan); break;
                case 3: CRC(scan); break;
                case 4: test(100); break;
                case 5:
                    if (frame == null || !frame.isVisible()){
                        openGUI();
                    }
                    break;
                case 6: System.out.println("Done!"); return;
                default : info();break;
            }
        }
    }

    private static void info(){
        System.out.println("==============================Info==============================");
        System.out.println("-The number inside the '( )' is used to select the desired task-");
        System.out.println("(1) Hamming encode");
        System.out.println("(2) Hamming decode");
        System.out.println("(3) CRC encode");
        System.out.println("(4) Run auto tests");
        System.out.println("(5) Open GUI");
        System.out.println("(6) Exit");
        printBreak();
    }

    private static void hamEncode(Scanner scan){
        printBreak();
        System.out.print("Input frame to encode: ");
        String in = scan.nextLine();
        System.out.println("Encoded string: " + new HammingEncode(in).toString());
    }

    private static void hamDecode(Scanner scan){
        printBreak();
        System.out.print("Input frame to decode: ");
        String in = scan.nextLine();
        System.out.println("Decoded string: " + new HammingDecode(in).toString());
    }

    private static void CRC(Scanner scan){
        printBreak();
        System.out.print("Input CRC frame to encode: ");
        String frame = scan.nextLine();
        System.out.print("Input CRC generator code: ");
        String generator = scan.nextLine();
        System.out.println("Encoded string: " + new CRC(frame, generator));
    }

    private static void openGUI(){
        System.out.println("Opening the GUI...");
        frame = new JFrame("Networking Project");
        frame.setContentPane(new MainGUI().MainJPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        System.out.println("Done!");
        printBreak();
    }

    private static void printBreak(){
        System.out.println("================================================================");
    }
}
