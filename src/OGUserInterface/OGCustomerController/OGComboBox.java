package OGUserInterface.OGCustomerController;

import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

public class OGComboBox extends JComboBox<String> {

    public OGComboBox(String[] items) {
        super(items);
        customizeComponent();

    }

    public OGComboBox(List<String> items, String mmNombreComboBox) {
        super();
        // Agregar el elemento por defecto al inicio
        addItem(mmNombreComboBox);

        // Agregar el resto de los elementos
        for (String item : items) {
            addItem(item);
        }

        // Personalizar el componente
        customizeComponent();
    }

    private void customizeComponent() {
        setFont(OGStyles.OGFONT_LANGOSTIONS_SMALL);
        setForeground(OGStyles.OGCOLOR_FONT);
        // Ajustar el tamaño preferido del ComboBox
        setPreferredSize(new Dimension(150, 30)); // Puedes ajustar estas dimensiones según tus necesidades
        setBackground(Color.YELLOW); // Pinta el boton de GeoAlimento y IngestaNativa
        //setBorder(MMStyles.MMCreateBorderRect()); // Bordes redondeados
        setSelectedIndex(0);
    }

    @Override
    public void setSelectedIndex(int index) {
        // Evitar la selección del primer elemento (el texto por defecto)
        if (index == 0) {
            return;
        }
        super.setSelectedIndex(index);
    }
}
