
/**
 *
 * @author Adam
 */
import java.util.*;

class HammingCheck {
	public static void main(String args[]) {
        String in = "1101001100110101";
        HammingEncode encode = new HammingEncode(in);
        System.out.println(receive(nomString("011100110011001110101"),encode.getNumParityBits()));
    }

    private static int[] nomString(String input){
        int[] out = new int[input.length()];
        for (int i=0; i < input.length(); i++){
            out[i] = Integer.parseInt(input.substring(i,i+1));
        }
        return out;
    }
	
	static String receive(int a[], int parity_count) {
		// This is the receiver code. It receives a Hamming code in array 'a'.
		// We also require the number of parity bits added to the original data.
		// Now it must detect the error and correct it, if any.
		
		int power;
		// We shall use the value stored in 'power' to find the correct bits to check for parity.
		
		int parity[] = new int[parity_count];
		// 'parity' array will store the values of the parity checks.
		
		String errorLocation = new String();
		// 'errorLocation' string will be used to store the integer value of error location.
		
		for(power=0 ; power < parity_count ; power++) {
		// Need to check the parities the same no of times as the no of parity bits added.
			for(int i=0 ; i < a.length ; i++) {
				// Extracting the bit from 2^(power):
				int k = i+1;
				String s = Integer.toBinaryString(k);
				int bit = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
				if(bit == 1) {
					if(a[i] == 1) {
						parity[power] = (parity[power]+1)%2;
					}
				}
			}
			errorLocation  = parity[power] + errorLocation ;
		}

		// This gives us the parity check equation values.
		// Check if there is a single bit error and then correct it.
		int error_location = Integer.parseInt(errorLocation, 2);
		if(error_location != 0) {
			a[error_location-1] = (a[error_location-1]+1)%2;
			for(int i=0 ; i < a.length ; i++) {
			}
		}

		// Extract the original data from the received (and corrected) code:
        String out = "";
        power = parity_count-1;
		for(int i=a.length ; i > 0 ; i--) {
			if(Math.pow(2, power) != i) {
                out += a[i-1];
			}
			else {
				power--;
			}
		}

        //Reverse the output because it was printing out of order for some reason.
        return new StringBuilder(out).reverse().toString();
	}
}