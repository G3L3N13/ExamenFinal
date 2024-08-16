package OGUserInterface.OGCustomerController;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;

public class OGTextBox extends JTextField {

    public OGTextBox() {
        customizeComponent();
    }

    private void customizeComponent() {
        setFont(OGStyles.OGFONT);
        setForeground(OGStyles.OGCOLOR_FONT_LIGHT);
        setCaretColor(OGStyles.OGCOLOR_CURSOR);
        setMargin(new Insets(5, 5, 5, 5));
        setBorderRect();
    }

    public void setBorderRect() {
        Border lineBorder = BorderFactory.createLineBorder(OGStyles.OGCOLOR_BORDER);
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5);
        setBorder(new CompoundBorder(lineBorder, emptyBorder));
    }
}
