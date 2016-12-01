/**
 * Used to decodeFrame Hamming Strings
 * @author Adam
 */

class HammingDecode {

    private int[] encodedFrame;
    private int numParityBits;

    /**
     * The main constructor, requires an initial string so no
     * errors are thrown
     * @param frame A frame to be decoded
     * @throws NumberFormatException In case an invalid string is given
     */
    public HammingDecode(String frame) throws NumberFormatException {
        setFrame(frame);
    }

    /**
     * Used to set the initial frame.
     * This converts the string into an int array and checks to see
     * if the string is valid.
     * @param frame The frame to be decoded
     * @throws NumberFormatException In case an invalid string is input
     */
    public void setFrame(String frame) throws NumberFormatException {
        encodedFrame = new int[frame.length()];
        for (int i=0; i < frame.length(); i++) {
            encodedFrame[i] = Integer.parseInt(frame.substring(i,i+1));
            if (!(encodedFrame[i] == 0 || encodedFrame[i]  == 1)){
                throw new NumberFormatException();
            }
        }
    }

    /**
     * Decodes the that has be set using setFrame
     * @return Decoded String
     */
    public String decodeFrame(){
        calculateNumParityBits();
        return calculateDecodedString();
    }

    /**
     * Takes an encoded string and decodes it
     * @param frame Hamming encoded string
     * @return Decoded string
     */
    public String decodeFrame(String frame){
        try {
            setFrame(frame);
        } catch (Exception e){
            return "";
        }
        return decodeFrame();
    }

    /**
     * Calculates the number of parity bits by iterating over the array
     * and adding one each time a position is reached
     */
    public void calculateNumParityBits(){
        int parityNum = 1;
        numParityBits = 1;
        for (int i=0; i < encodedFrame.length -1; i++){
            if (i+1 == parityNum) {
                parityNum *= 2;
                numParityBits++;
            }
        }
        numParityBits--;
    }


    /**
     * Calculates the decoded string
     * Basically checks the string for errors, corrects them
     * and converts the array into a string
     * @return String of decoded bits
     */
    public String calculateDecodedString(){
        int parityIndex = 1;
        int errorIndex = 0;


        /*
            Checks to see if there is any error present in the string.
            If an error is detected, its position can be calculated by
            adding the parity positions that indicated an error together
         */
        for (int p = 1; p <= numParityBits; p++){
            int parityValue = 0;

            //Determine the parity bit's value
            for (int i = parityIndex; i < encodedFrame.length; i++){
                if (((i-parityIndex+1)/parityIndex)%2 == 0){
                    parityValue += encodedFrame[i];
                }
            }

            //Compare the parity bit's value to the parity bit encoded in the string
            if (encodedFrame[parityIndex - 1] != (parityValue) % 2){
                errorIndex += parityIndex;
            }

            parityIndex *= 2;
        }

        //Flip the bit at the error position
        if (errorIndex > 0) {
            encodedFrame[errorIndex - 1] = (encodedFrame[errorIndex - 1] + 1) % 2;
        }

        String out = "";
        int currentParity = 1;

        //Picks out the data bits and puts them in a string
        for (int i = 0; i < encodedFrame.length; i++){
            if (i + 1 == currentParity) {
                currentParity *= 2;
            } else {
				out += encodedFrame[i];
            }
        }

        return out;
    }

}