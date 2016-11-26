import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField CRCFrameOutput;
    private CRC CRCEncoder;

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
        CRCFrameInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                updateCRC();
            }
        });
        CRCGeneratorInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                updateCRC();
            }
        });
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
