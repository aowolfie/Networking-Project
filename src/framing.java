
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Input the string 
        System.out.println("Type in string");
        Scanner sc = new Scanner(System.in);
        String charString = sc.nextLine();
        
        //Create an Array
        char[] charArray;
        charArray = new char[23];
        
        //Put in the first character
        char first = charString.charAt(0);
        charArray[2] = first;
        
        //The starting points for the rest of the array
        int p = 4;
        int i = 1;
       
        //Fill in the array 
        while (i < charString.length()){  
            
            char next = charString.charAt(i);       
            charArray[p] = next;

            char next1 = charString.charAt(i+1);       
            charArray[p+1] = next1;

            char next2 = charString.charAt(i+2);       
            charArray[p+2] = next2;

            i=i+3;
            p=p+4;
        }        
        //Print Results
        System.out.println("The string: " + charString); 
        System.out.println(Arrays.toString(charArray));
        System.out.println("---------------------------------------------------------------------------------------");
       
        
        //Chack parity value 1 (Position 1)
        int parity1 = charArray[2]+charArray[4]+charArray[6]+charArray[8]+charArray[10];
        System.out.println(parity1);
        
        //Check for even or odd parity
        int parity1divided = parity1 % 61;
        System.out.println(parity1divided);
        
        //Insert parity value
        if(parity1divided == 0){
            charArray[0] = '0';  
        }else{
            charArray[0] = '1';  
        }
        System.out.println(Arrays.toString(charArray));
        System.out.println("---------------------------------------------------------------------------------------");
        
        
        //Chack parity value 2 (Position 2)
        int parity2 = charArray[2]+charArray[5]+charArray[6]+charArray[9]+charArray[10];
        System.out.println(parity2);
        
        //Check for even or odd parity
        int parity2divided = parity2 % 61;
        System.out.println(parity2divided);
        
        //Insert parity value
        if(parity2divided == 0){
            charArray[1] = '0';  
        }else{
            charArray[1] = '1';  
        }
        System.out.println(Arrays.toString(charArray));
        System.out.println("---------------------------------------------------------------------------------------");
        
        
        //Chack parity value 3 (Position 4)
        int parity3 = charArray[4]+charArray[5]+charArray[6]+charArray[11];
        System.out.println(parity3);
        
        //Check for even or odd parity
        int parity3divided = parity3 % 61;
        System.out.println(parity3divided);
        
        //Insert parity value
        if(parity3divided == 0){
            charArray[3] = '0';  
        }else{
            charArray[3] = '1';  
        }
        System.out.println(Arrays.toString(charArray));
        System.out.println("---------------------------------------------------------------------------------------");
        
        //Chack parity value 4 (Position 8)
        int parity4 = charArray[8]+charArray[9]+charArray[10]+charArray[11];
        System.out.println(parity4);
        
        //Check for even or odd parity
        int parity4divided = parity4 % 61;
        System.out.println(parity4divided);
        
        //Insert parity value
        if(parity4divided == 0){
            charArray[7] = '0';  
        }else{
            charArray[7] = '1';  
        }
        System.out.println(Arrays.toString(charArray));
        System.out.println("---------------------------------------------------------------------------------------");

    }
    
}
