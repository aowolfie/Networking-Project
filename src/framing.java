import java.util.ArrayList;

/**
 *
 * @author Zach
 */
public class framing {

    private Integer[] encodedFrame;
    private int[] initialFrame;
    private int numParityBits;

    //Testing main method
    public static void main(String[] args) {
        framing frame = new framing("11111111");
        System.out.println(frame.encodeFrame());
    }

    /**
     * Requires input because so we have values to return
     * @param input The frame to be encoded
     * @throws NumberFormatException In case an invalid string is input
     */
    public framing(String input) throws NumberFormatException{
        setInitialFrame(input);
        encodeFrame();
    }

    /**
     * Used to set the initial frame.
     * This converts the string into an int array and checks to see
     * if the string is valid.
     * @param frame The frame to be encoded
     * @throws NumberFormatException In case an invalid string is input
     */
    public void setInitialFrame(String frame) throws NumberFormatException {
        Integer.parseInt(frame, 2);
        initialFrame = new int[frame.length()];
        for (int i=0; i < frame.length(); i++) {
            initialFrame[i] = Integer.parseInt(frame.substring(i,i+1));
        }
    }

    /**
     * Uses the inputted frame to generate the encoded frame skeleton
     */
    private void generateEncodedFrameSkeleton(){
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(0);
        numParityBits = 1;
        int nextParity = 2;
        for (int i = 0; i < initialFrame.length; i++){
            if (i + numParityBits + 1 == nextParity) {
                temp.add(0);
                numParityBits++;
                nextParity *= 2;
            }
            temp.add(initialFrame[i]);
        }
        encodedFrame = temp.toArray(new Integer[temp.size()]);
    }

    /**
     * Using the generated frame skeleton this calculates the parity bits
     */
    private void calculateParityBits(){
        int parityIndex = 1;

        for (int p = 1; p <= numParityBits; p++){

            int parityValue = 0;

            for (int i = parityIndex - 1; i < encodedFrame.length; i++){
                if (((i-parityIndex+1)/parityIndex)%2 == 0){
                    parityValue += encodedFrame[i];
                }
            }

            encodedFrame[parityIndex - 1] = (parityValue) % 2;
            parityIndex *= 2;
        }
    }

    /**
     * Encodes the frame and returns the value
     * @return The encoded frame
     */
    public String encodeFrame(){
        generateEncodedFrameSkeleton();
        calculateParityBits();
        return toString();
    }

    /**
     * Encodes the given frame and returns the encoded string
     * @param frame The frame to be encoded
     * @return The encoded frame
     */
    public String encodeFrame(String frame){
        try {
            setInitialFrame(frame);
        } catch (NumberFormatException e){
            return "";
        }
        return encodeFrame();
    }

    /**
     * Converts our encoded int array to a string
     * @return The encoded frame as a String
     */
    @Override
    public String toString() {
        String out = "";
        for (int i: encodedFrame){
            out += i;
        }
        return out;
    }
}
