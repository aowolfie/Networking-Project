/**
 * Created by brandonbeckwith on 11/24/16.
 *
 * A simple class that CRC Error Detection
 */
public class CRC  {

    private String input, generator;
    private int genInt;


    //Temporary main method used for testing
    public static void main(String[] args){
        CRC test = new CRC("1101011011011100","1101110111011101");
        System.out.println(test.generateFrame());
    }


    /**
     * Constructs a new CRC object
     * @param frame The frame to be encoded
     * @param generator The generator to be used in the encoding
     * @throws IllegalArgumentException Thrown for invalid generators, generator must have a leading 1
     */
    public CRC(String frame, String generator) throws IllegalArgumentException {
        setInput(frame);
        setGenerator(generator);
    }

    /**
     * Sets the frame
     * @param frame The frame to be encoded
     */
    public void setInput(String frame){
        this.input = frame;
    }

    /**
     * Helper method, generates the frame with the correct number of appended zeros
     * @return Padded frame
     */
    private String genFrame(){
       return input + new String(new char[generator.length()-1]).replace("\0","0");
    }

    /**
     * Sets the generator string and converts it an integer for easy XOR
     * @param generator The generator string
     * @throws IllegalArgumentException Thrown in case a generator with a leading 0 is passed
     */
    public void setGenerator(String generator) throws IllegalArgumentException{
        if (generator.charAt(0) != '1') {
            throw new IllegalArgumentException("Leading number must be 1");
        }
        this.generator = generator;
        genInt = Integer.parseInt(generator, 2);
    }

    /**
     * Calculate the CRC code that is appended to the input
     * @return The CRC code
     */
    public String calculateCode(){
        String frame = genFrame();

        String subFrame = frame.substring(0,generator.length()-1);
        for (int i = generator.length()-1; i < frame.length(); i++){
            subFrame += frame.charAt(i);
            if (subFrame.charAt(0) == '0'){
                subFrame = subFrame.substring(1,subFrame.length());
            } else {
                int temp = Integer.parseInt(subFrame, 2);
                temp = temp ^ genInt;
                subFrame = Integer.toBinaryString(temp);
                while (subFrame.length() < generator.length() - 1) {
                    subFrame = "0" + subFrame;
                }
            }
        }
        return subFrame;
    }

    /**
     * Generates the entire frame
     * @return the encoded frame
     */
    public String generateFrame(){
        return input + calculateCode();
    }

}