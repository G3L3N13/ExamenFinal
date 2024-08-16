package OGUserInterface.OGForms;

import java.awt.*;

import javax.swing.JLabel;

import OGUserInterface.OGCustomerController.OGPanel;
import OGUserInterface.OGCustomerController.OGTextBox;
import OGUserInterface.OGCustomerController.OGStyles;

public class OGCedulaPanel extends OGPanel {

    public String ogNombre = "Gelen Ortiz";
    public String ogCedula = "175458263-1";

    public OGCedulaPanel() {
        // Configuración del panel con bordes redondeados y fondo transparente
        super(20, OGStyles.OGCOLOR_BORDER); // Color panel de cedula y nombre

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Aumentar márgenes

        // Alineación y ajustes para el campo "Cedula"
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel cedulaLabel = new JLabel("Cedula del alumno:");
        cedulaLabel.setFont(OGStyles.OGFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        add(cedulaLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        OGTextBox cedulaField = new OGTextBox();
        cedulaField.setFont(OGStyles.OGFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        cedulaField.setText(ogCedula); // Inicializar con el valor de la cédula
        cedulaField.setEditable(false); // Deshabilitar el campo de edición
        cedulaField.setPreferredSize(new Dimension(200, 25)); // Ajustar tamaño
        add(cedulaField, gbc);

        // Alineación y ajustes para el campo "Nombres"
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel nombresLabel = new JLabel("Nombres completos del alumno:");
        nombresLabel.setFont(OGStyles.OGFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        add(nombresLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        OGTextBox nombresField = new OGTextBox();
        nombresField.setFont(OGStyles.OGFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        nombresField.setText(ogNombre); // Inicializar con el valor del nombre
        nombresField.setEditable(false); // Deshabilitar el campo de edición
        nombresField.setPreferredSize(new Dimension(200, 25)); // Ajustar tamaño
        add(nombresField, gbc);
    }
}
