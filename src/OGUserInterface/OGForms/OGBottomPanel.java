package OGUserInterface.OGForms;

import OGUserInterface.OGCustomerController.OGButton;
import OGUserInterface.OGCustomerController.OGPanel;
import OGUserInterface.OGCustomerController.OGStyles;
import OGDataAccess.OGDAO.OGHormigaDAO;
import OGDataAccess.OGDTO.OGHormigaDTO;

import java.awt.*;

import javax.swing.JOptionPane;

public class OGBottomPanel extends OGPanel {

    private OGHormigueroPanel hormigueroPanel;
    private OGHormigaDAO hormigaDAO;

    public OGBottomPanel(OGHormigueroPanel hormigueroPanel) {
        super(15, OGStyles.OGCOLOR_GREEN);
        this.hormigueroPanel = hormigueroPanel;
        hormigaDAO = new OGHormigaDAO();

        OGButton buttonEliminar = new OGButton("Eliminar", false, OGStyles.OGFONT_LANGOSTIONS_SMALL);
        OGButton buttonGuardar = new OGButton("Guardar", false, OGStyles.OGFONT_LANGOSTIONS_SMALL);

        buttonEliminar.setBackground(Color.RED);  // Color de Eliminar
        buttonGuardar.setBackground(Color.GREEN);// Color de Guardar

        buttonGuardar.addActionListener(e -> guardarCambios());
        buttonEliminar.addActionListener(e -> eliminarHormiga());

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(buttonEliminar, gbc);

        gbc.gridx = 1;
        add(buttonGuardar, gbc);
    }

    private void guardarCambios() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea guardar todos los cambios realizados en el hormiguero?", 
            "Confirmación de Guardado", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            OGHormigaDTO hormiga = hormigueroPanel.obtenerHormigaSeleccionada();
            if (hormiga != null) {
                try {
                    hormigaDAO.ogUpdate(hormiga);
                    JOptionPane.showMessageDialog(this, "Cambios guardados exitosamente", "Guardar",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al guardar los cambios", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna hormiga", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void eliminarHormiga() {
        OGHormigaDTO hormiga = hormigueroPanel.obtenerHormigaSeleccionada();
        if (hormiga != null) {
            // Confirmation dialog
            int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de que desea eliminar la hormiga seleccionada?", 
                "Confirmación de Eliminación", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    hormigaDAO.ogDelete(hormiga.getIdHormiga());
                    hormigueroPanel.cargarHormigas(); 
                    JOptionPane.showMessageDialog(this, "Hormiga eliminada exitosamente", "Eliminar",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al eliminar la hormiga", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna hormiga", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}
