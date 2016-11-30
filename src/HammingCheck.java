/**
 *
 * @author Adam
 */

class HammingCheck {

    private int[] encodedFrame, decodedFrame;
    private int numParityBits;

	public static void main(String args[]) {
        String in = "110011101111";
        HammingEncode encode = new HammingEncode(in);
        System.out.println(calculateNumParityBits("0 1 1 1 001 1 0011001 1 10101"));
        HammingCheck check = new HammingCheck();
        check.setInitialFrame(in);
        System.out.println(check.calculateOriginal());
    }

    //Add a method to calculate the number of parity bits in the inputted string

    private static int[] nomString(String input){
        int[] out = new int[input.length()];
        for (int i=0; i < input.length(); i++){
            out[i] = Integer.parseInt(input.substring(i,i+1));
            System.out.print(out[i]);
        }
        System.out.println("======");
        return out;
    }

    /**
     * Used to set the initial frame.
     * This converts the string into an int array and checks to see
     * if the string is valid.
     * @param frame The frame to be encoded
     * @throws NumberFormatException In case an invalid string is input
     */
    public void setInitialFrame(String frame) throws NumberFormatException {
        encodedFrame = new int[frame.length()];
        for (int i=0; i < frame.length(); i++) {
            encodedFrame[i] = Integer.parseInt(frame.substring(i,i+1));
            if (!(encodedFrame[i] == 0 || encodedFrame[i]  == 1)){
                throw new NumberFormatException();
            }
        }
    }

    public static String decode(String frame){
        for (char c: frame.toCharArray()){
            if (!(c == '1' || c =='0')){
                return "";
            }
        }
        return HammingCheck.receive(nomString(frame),calculateNumParityBits(frame));
    }

    /**
     * Calculates the number of parity bits in the encoded string
     */
    private static int calculateNumParityBits(String in){
        int parityIndex = 1;
        int parityNum = 1;

        for (int i=0; i < in.length() -1; i++){
            if (i+1 == parityNum) {
                parityNum *= 2;
                parityIndex++;
            }
        }
        return parityIndex;
    }

    public void newCalculateParityBits(){
        int parityNum = 1;
        numParityBits = 1;
        for (int i=0; i < encodedFrame.length -1; i++){
            if (i+1 == parityNum) {
                parityNum *= 2;
                numParityBits++;
            }
        }
    }


    public String calculateOriginal(){
        newCalculateParityBits();
        int parityIndex = 1;
        int errorIndex = 0;
        for (int p = 1; p < numParityBits; p++){

            int parityValue = 0;

            for (int i = parityIndex - 1; i < encodedFrame.length; i++){
                if (((i-parityIndex+1)/parityIndex)%2 == 0){
                    parityValue += encodedFrame[i];
                }
            }

            System.out.println(parityValue);
            if (encodedFrame[parityIndex - 1] != (parityValue) % 2){
                System.out.println("Error detected!");
                errorIndex += parityIndex;
            }

            parityIndex *= 2;
        }

        System.out.println(errorIndex);

        if (errorIndex > 0) {
            encodedFrame[errorIndex - 1] = (encodedFrame[errorIndex - 1] + 1) % 2;
        }
        String out = "";
        int currentParity = 1;
        for (int i = 0; i < encodedFrame.length; i++){
            if (i + 1 == currentParity) {
                currentParity *= 2;
            } else {
                out += encodedFrame[i];
            }
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