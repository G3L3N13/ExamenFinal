package OGUserInterface.OGForms;

import javax.swing.*;

import OGUserInterface.OGCustomerController.OGButton;
import OGUserInterface.OGCustomerController.OGStyles;

import java.awt.*;

public class OGGridPanel extends JPanel {

    public OGGridPanel() {
        setLayout(new BorderLayout());

        JLabel logoLabel = new JLabel(new ImageIcon(OGStyles.URL_ICON_ANT));
        add(logoLabel, BorderLayout.WEST);

     
        JPanel grid = new JPanel(new GridLayout(3, 4, 5, 5));
        for (int i = 0; i < 12; i++) { 
            grid.add(new JLabel(""));
        }
        add(grid, BorderLayout.CENTER);

    
        OGButton createAntButton = new OGButton("Crear hormiga larva", false, OGStyles.OGFONT_LANGOSTIONS);
        add(createAntButton, BorderLayout.EAST);
            
    }
}
