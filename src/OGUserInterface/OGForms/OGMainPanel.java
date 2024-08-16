package OGUserInterface.OGForms;

import javax.swing.*;

import OGUserInterface.OGCustomerController.OGStyles;

import java.awt.*;

public class OGMainPanel extends JPanel {

    public OGMainPanel() {
        setBackground(OGStyles.OGCOLOR_GREEN);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;

        OGHormigueroPanel hormigueroPanel = new OGHormigueroPanel();
        OGActionPanel actionPanel = new OGActionPanel(hormigueroPanel);
        OGBottomPanel bottomPanel = new OGBottomPanel(hormigueroPanel);

       
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new OGCedulaPanel(), gbc);

       
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0.6;
        gbc.anchor = GridBagConstraints.CENTER;
        add(hormigueroPanel, gbc);

        
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(actionPanel, gbc);

        
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(bottomPanel, gbc);
    }
}
