import com.sun.xml.internal.ws.util.StringUtils;
import java.util.Arrays;
import java.util.Scanner;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zach
 */
public class framing {

    public int[] initalFrame, encodedFrame;
    private int numParityBits;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // Input the string 
        System.out.println("Type in string");
        Scanner sc = new Scanner(System.in);
        String charString = sc.nextLine();
        //System.out.println(generateEncodedFrame(charString));

        framing frame = new framing(charString);

        System.out.println(frame.encodedFrame);

    }

    public framing(String input){
        setInitialFrame(input);
    }

    public void setInitialFrame(String frame){
        initalFrame = new int[frame.length()];
        for (int i=0; i < frame.length(); i++) {
            initalFrame[i] = Integer.parseInt(frame.substring(i,i));
        }
        calculateParityBits();
    }

    public void generateEncodedFrameSkeleton(){
        //Parity bits are located at indexes, 2^n, so 1, 2, 4, 8, 16, 32, 64, etc...
        numParityBits = Integer.toBinaryString(initalFrame.length).length();
        encodedFrame = new int[initalFrame.length + numParityBits];
        int currentParity = 4;

        for (int i=2, index = 0; i < encodedFrame.length; i++){
            if (i == currentParity) {
                currentParity *= 2;
                encodedFrame[i] = 0;
            } else {
                encodedFrame[i] = initalFrame[index];
                index++;
            }
        }
    }

    public void calculateParityBits(){
        generateEncodedFrameSkeleton();
        int currentParity =1;
        for (int p=0; p < numParityBits; p++){
            for (int i=0; i < encodedFrame.length){

            }
            currentParity *= 2;
        }
    }



    public static String generateEncodedFrame(String input){
        String charString = input;

        //Create an Array
        char[] charArray;
        charArray = new char[23];


        //Put in the first character
        char first = charString.charAt(0);
        charArray[2] = first;

        int p = 3;
        int loop = 0;
        int i;

        for(i=1; i<charString.length(); i++){
            if(loop % 3 != 0){
                char next = charString.charAt(i);
                charArray[p] = next;
                p++;
                loop++;
            }else{
                p = p+1;
                char next = charString.charAt(i);
                charArray[p] = next;
                p++;
                loop++;
            }
        }

        //Print Results
        System.out.println("The string: " + charString);
        System.out.println(Arrays.toString(charArray));
        System.out.println("---------------------------------------------------------------------------------------");

        //Put the parity bit 1 values into an array//////////////////////////////////////////////////////////////////
        char[] parityArray1;
        parityArray1 = new char[11];
        parityArray1[0] = charArray[2];
        parityArray1[1] = charArray[4];
        parityArray1[2] = charArray[6];
        parityArray1[3] = charArray[8];
        parityArray1[4] = charArray[10];
        parityArray1[5] = charArray[12];
        parityArray1[6] = charArray[14];
        parityArray1[7] = charArray[16];
        parityArray1[8] = charArray[18];
        parityArray1[9] = charArray[20];
        parityArray1[10] = charArray[22];

        //Cnvert the array to a stirng
        String parity1 =String.valueOf(parityArray1);
        System.out.println("This is parity1: " + parity1);

        //Count the number of ones in the string
        char lookingFor = '1';
        int count = 0;

        for(int j=0; j < parity1.length();j++){
            if(parity1.charAt(j) == lookingFor)
                count+=1;
        }
        System.out.println(count);

        //Place the correct parity bit in the array
        if(count % 2 == 0){
            charArray[0] = '0';
        }else{
            charArray[0] = '1';
        }

        //Put the parity bit 2 values into an array/////////////////////////////////////////////////////////////////////
        char[] parityArray2;
        parityArray2 = new char[13];
        parityArray2[0] = charArray[2];
        parityArray2[1] = charArray[5];
        parityArray2[2] = charArray[6];
        parityArray2[3] = charArray[8];
        parityArray2[4] = charArray[9];
        parityArray2[5] = charArray[11];
        parityArray2[6] = charArray[12];
        parityArray2[7] = charArray[14];
        parityArray2[8] = charArray[15];
        parityArray2[9] = charArray[17];
        parityArray2[10] = charArray[18];
        parityArray2[11] = charArray[20];
        parityArray2[12] = charArray[21];

        //Cnvert the array to a stirng
        String parity2 =String.valueOf(parityArray2);
        System.out.println("This is parity2: " + parity2);

        //Count the number of ones in the string
        count = 0;

        for(int j=0; j < parity2.length();j++){
            if(parity2.charAt(j) == lookingFor)
                count+=1;
        }
        System.out.println(count);

        //Place the correct parity bit in the array
        if(count % 2 == 0){
            charArray[1] = '0';
        }else{
            charArray[1] = '1';
        }

        //Put the parity bit 3 values into an array////////////////////////////////////////////////////////////////////
        char[] parityArray3;
        parityArray3 = new char[11];
        parityArray3[0] = charArray[4];
        parityArray3[1] = charArray[5];
        parityArray3[2] = charArray[6];
        parityArray3[3] = charArray[11];
        parityArray3[4] = charArray[12];
        parityArray3[5] = charArray[13];
        parityArray3[6] = charArray[14];
        parityArray3[7] = charArray[19];
        parityArray3[8] = charArray[20];
        parityArray3[9] = charArray[21];
        parityArray3[10] = charArray[22];


        //Cnvert the array to a stirng
        String parity3 =String.valueOf(parityArray3);
        System.out.println("This is parity3: " + parity3);

        //Count the number of ones in the string
        count = 0;
        for(int j=0; j < parity3.length();j++){
            if(parity3.charAt(j) == lookingFor)
                count+=1;
        }
        System.out.println(count);

        //Place the correct parity bit in the array
        if(count % 2 == 0){
            charArray[3] = '0';
        }else{
            charArray[3] = '1';
        }

        //Put the parity bit 4 values into an array////////////////////////////////////////////////////////////////////
        char[] parityArray4;
        parityArray4 = new char[8];
        parityArray4[0] = charArray[7];
        parityArray4[1] = charArray[8];
        parityArray4[2] = charArray[9];
        parityArray4[3] = charArray[10];
        parityArray4[4] = charArray[11];
        parityArray4[5] = charArray[12];
        parityArray4[6] = charArray[13];
        parityArray4[7] = charArray[14];

        //Cnvert the array to a stirng
        String parity4 =String.valueOf(parityArray4);
        System.out.println("This is parity4: " + parity4);

        //Count the number of ones in the string
        count = 0;
        for(int j=0; j < parity4.length();j++){
            if(parity4.charAt(j) == lookingFor)
                count+=1;
        }
        System.out.println(count);

        //Place the correct parity bit in the array
        if(count % 2 == 0){
            charArray[7] = '0';
        }else{
            charArray[7] = '1';
        }

        System.out.println(Arrays.toString(charArray));

        return null;
    }
}
