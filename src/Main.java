import java.util.Random;

/**
 * Created by brandonbeckwith on 11/17/16.
 */
public class Main {
    public static void main (String[] args){
        System.out.println("Hello World");

    }

    public static void test(int max){
        HammingEncode encode = new HammingEncode("101");
        for (int i=10; i < max; i++){
            String code = "11111111";
            boolean fail = false;
            System.out.println(code);
            String encodedCode = encode.encodeFrame(code);
            System.out.println(encodedCode);
            String decodedCode = "";
            System.out.println("{d}" + decodedCode);
            //String corruptedCode = corrupt(code);
            //System.out.println(corruptedCode);
            //String fixedCode = HammingDecode.decode(corruptedCode);
            //System.out.println(fixedCode);
            if (!decodedCode.equals(code)){
                fail = true;
            }

            if (fail) {
                System.out.println("Failed{" + i + "}");
                //System.out.println(code + "\n" + encodedCode + "\n" + decodedCode + "\n" + corruptedCode + "\n" + fixedCode);
                break;
            }


        }
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
        String out = "1";
        for (int i=0; i < length; i++){
            out += (rand.nextInt(5) % 2);
        }
        return out;
    }
}
