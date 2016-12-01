import java.util.Random;

/**
 * Created by brandonbeckwith on 11/17/16.
 *
 * Main Class for testing everything else
 */
public class Main {
    public static void main (String[] args){
        System.out.println("Hello World");
        test(64);
    }

    public static void test(int max){
        HammingEncode encode = new HammingEncode("101");
        HammingDecode decode = new HammingDecode("101");
        for (int i=1; i <= max; i++){
            String code = generate(i);
            boolean fail = false;

            String encodedCode = encode.encodeFrame(code);
            String decodedCode = decode.decodeFrame(encodedCode);
            String corruptedCode = corrupt(encodedCode);
            String fixedCode = decode.decodeFrame(corruptedCode);

            if (!decodedCode.equals(code)){
                fail = true;
            }

            if (!fixedCode.equals(code)) {
                fail = true;
            }

            System.out.println("============================================================================================================================");
            System.out.println(code + "\n" + encodedCode + "\n" + decodedCode + "\n" + corruptedCode + "\n" + fixedCode);

            if (fail) {
                System.out.println("Failed!     {" + i + "}");
                break;
            } else {
                System.out.println("Passed!     {" + i + "}");
            }


        }
    }

    private static String corrupt(String frame){
        int pos = (int) (frame.length() * Math.random());
        System.out.println("Pos" + pos);
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
}
