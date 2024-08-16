package OGUserInterface.OGForms;

import OGUserInterface.OGCustomerController.OGButton;
import OGUserInterface.OGCustomerController.OGComboBox;
import OGUserInterface.OGCustomerController.OGPanel;
import OGUserInterface.OGCustomerController.OGStyles;
import OGDataAccess.OGDataHelper.OGDataHelper;
import OGDataAccess.OGDAO.OGGenoAlimentoDAO;
import OGDataAccess.OGDAO.OGHormigaDAO;
import OGDataAccess.OGDAO.OGIngestaNativaDAO;
import OGDataAccess.OGDTO.OGGenoAlimentoDTO;
import OGDataAccess.OGDTO.OGHormigaDTO;
import OGDataAccess.OGDTO.OGIngestaNativaDTO;

import java.util.List;
import javax.swing.JOptionPane;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OGActionPanel extends OGPanel {

    private OGHormigaDAO hormigaDAO;
    private OGComboBox genoAlimentoComboBox;
    private OGComboBox ingestaNativaComboBox;
    private OGHormigueroPanel hormigueroPanel;

    public OGActionPanel(OGHormigueroPanel hormigueroPanel) {
        super(20, OGStyles.OGCOLOR_BORDER); 
        this.hormigueroPanel = hormigueroPanel;

       
        hormigaDAO = new OGHormigaDAO();

        OGIngestaNativaDAO ingestaDAO = new OGIngestaNativaDAO();
        OGGenoAlimentoDAO genoAlimentoDAO = new OGGenoAlimentoDAO();

        List<String> ingestaItems;
        List<String> genoAlimentoItems;

        try {
            ingestaItems = ingestaDAO.ogReadAll().stream().map(ingesta -> ingesta.getNombre()).toList();
            genoAlimentoItems = genoAlimentoDAO.ogReadAll().stream().map(genoAlimento -> genoAlimento.getNombre())
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            ingestaItems = List.of("Error al cargar");
            genoAlimentoItems = List.of("Error al cargar");
        }

        genoAlimentoComboBox = new OGComboBox(genoAlimentoItems, "GenoAlimento");
        ingestaNativaComboBox = new OGComboBox(ingestaItems, "IngestaNativa");

        OGButton buttonAlimentarGA = new OGButton("Alimentar con Genoalimento", false,
                OGStyles.OGFONT_LANGOSTIONS_SMALL);
        OGButton buttonAlimentarIN = new OGButton("Alimentar con Ingesta Nativa", false,
                OGStyles.OGFONT_LANGOSTIONS_SMALL);

        buttonAlimentarGA.setBackground(OGStyles.OGCOLOR_PURPLE1); //Color de Alimentar con Genoalimento
        buttonAlimentarIN.setBackground(OGStyles.OGCOLOR_PURPLE1); // Color de Alimentar con Ingesta Nativa

        buttonAlimentarGA.addActionListener(e -> alimentarHormiga("GenoAlimento"));
        buttonAlimentarIN.addActionListener(e -> alimentarHormiga("IngestaNativa"));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(genoAlimentoComboBox, gbc);

        gbc.gridx = 1;
        add(buttonAlimentarGA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(ingestaNativaComboBox, gbc);

        gbc.gridx = 1;
        add(buttonAlimentarIN, gbc);
    }

    private void alimentarHormiga(String tipoAlimento) {
        OGHormigaDTO hormiga = hormigueroPanel.obtenerHormigaSeleccionada();
        if (hormiga == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una hormiga antes de alimentarla.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener el id del alimento seleccionado y actualizar la hormiga
        if (tipoAlimento.equals("GenoAlimento")) {
            int idGenoAlimento = obtenerIdGenoAlimento(genoAlimentoComboBox.getSelectedItem().toString());
            hormiga.setIdGenoAlimento(idGenoAlimento);
        } else if (tipoAlimento.equals("IngestaNativa")) {
            int idIngestaNativa = obtenerIdIngestaNativa(ingestaNativaComboBox.getSelectedItem().toString());
            hormiga.setIdIngestaNativa(idIngestaNativa);
        }

        // Actualizar la base de datos
        try {
            hormigaDAO.ogUpdate(hormiga);
            hormigueroPanel.actualizarHormigaEnTabla(hormiga); // Opcional: Refrescar la tabla
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el alimento de la hormiga", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private int obtenerIdGenoAlimento(String nombreGenoAlimento) {
        OGGenoAlimentoDAO genoAlimentoDAO = new OGGenoAlimentoDAO();
        try {
            List<OGGenoAlimentoDTO> genoAlimentos = genoAlimentoDAO.ogReadAll();
            for (OGGenoAlimentoDTO genoAlimento : genoAlimentos) {
                if (genoAlimento.getNombre().equals(nombreGenoAlimento)) {
                    return genoAlimento.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Valor predeterminado en caso de no encontrar el alimento
    }

    private int obtenerIdIngestaNativa(String nombreIngestaNativa) {
        OGIngestaNativaDAO ingestaNativaDAO = new OGIngestaNativaDAO();
        try {
            List<OGIngestaNativaDTO> ingestaNativas = ingestaNativaDAO.ogReadAll();
            for (OGIngestaNativaDTO ingestaNativa : ingestaNativas) {
                if (ingestaNativa.getNombre().equals(nombreIngestaNativa)) {
                    return ingestaNativa.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Valor predeterminado en caso de no encontrar el alimento
    }

}
