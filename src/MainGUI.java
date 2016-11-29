import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Created by Brandon on 11/26/2016.
 *
 * Used to display a GUI for easy easy distribution and data entry.
 * Note this may only work with the IntelliJ IDEA IDE
 */
public class MainGUI {
    private JTextField CRCFrameInput;
    private JTextField CRCGeneratorInput;
    private JPanel MainJPanel;
    private JTextField hammingDecodeOutput;
    private JTextField hammingDecodeInput;
    private JTextField hammingEncodeOutput;
    private JTextField hammingEncodeInput;
    private JTextField CRCFrameOutput;
    private CRC CRCEncoder;
    private HammingEncode hammingEncoder;

    /**
     * Used to launch the GUI
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Networking Project");
        frame.setContentPane(new MainGUI().MainJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * Constructor
     */
    public MainGUI() {

        CRCEncoder = new CRC(CRCFrameInput.getText(), CRCGeneratorInput.getText());
        updateCRC();

        hammingEncoder = new HammingEncode(hammingEncodeInput.getText());
        hammingEncodeOutput.setText(hammingEncoder.encodeFrame());

        CRCFrameInput.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                updateCRC();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                updateCRC();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                updateCRC();
            }
        });

        CRCGeneratorInput.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                updateCRC();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                updateCRC();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                updateCRC();
            }
        });

        hammingEncodeInput.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                updateHammingEncode();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                updateHammingEncode();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                updateHammingEncode();
            }
        });

        hammingDecodeInput.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                updateHammingEncode();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                updateHammingEncode();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                updateHammingEncode();
            }
        });


    }

    /**
     * Helper method that is used to update the Hamming Encode textField
     */
    private void updateHammingEncode(){
        String code = hammingEncoder.encodeFrame(hammingEncodeInput.getText());
        if (code.equals("")){
          code = "ERROR!";
        }
        hammingEncodeOutput.setText(code);
    }

    /**
     * Helper method that is used to update the Hamming Decode textField
     */
    private void updateHammingDecode(){
        /*
           Place update code here
           hammingDecodeInput is the input
           hammingDecodeOutput is the output
         */
    }

    /**
     * Helper method that is used to update the CRC textFields
     */
    private void updateCRC(){
        try {
            CRCEncoder.setGenerator(CRCGeneratorInput.getText());
            CRCEncoder.setInput(CRCFrameInput.getText());
            CRCFrameOutput.setText(CRCEncoder.generateFrame());
        } catch (Exception e){
            CRCFrameOutput.setText("ERROR!");
        }
    }
}
