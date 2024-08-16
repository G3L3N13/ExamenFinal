package OGUserInterface.OGForms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import OGUserInterface.OGCustomerController.OGButton;
import OGUserInterface.OGCustomerController.OGPanel;
import OGUserInterface.OGCustomerController.OGStyles;
import OGBusinessLogic.OGHormigaBL;
import OGDataAccess.OGDAO.OGHormigaDAO;
import OGDataAccess.OGDTO.OGHormigaDTO;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;


public class OGHormigueroPanel extends OGPanel {

    private JTable tablaHormigas;
    private OGHormigaDAO hormigaDAO;
    private OGHormigaBL hormigaBL; // Declara una instancia de OGHormigaBL


    public OGHormigueroPanel() {
        super(20, OGStyles.OGCOLOR_GREEN); // Mantiene el fondo del panel principal
        hormigaDAO = new OGHormigaDAO();
        hormigaBL = new OGHormigaBL(); // Instancia OGHormigaBL
        

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.05;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel logoLabel = new JLabel(new ImageIcon(OGStyles.URL_ICON_ANT));
        add(logoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel titleLabel = new JLabel("  Hormiguero virtual", JLabel.LEFT);
        titleLabel.setFont(OGStyles.OGFONT_LANGOSTIONS);
        add(titleLabel, gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.EAST;
        OGButton createAntButton = new OGButton("Crear hormiga larva", false, OGStyles.OGFONT_LANGOSTIONS_SMALL);
        createAntButton.setPreferredSize(new Dimension(150, 25));
        createAntButton.setBackground(OGStyles.OGCOLOR_PURPLE1); 
        add(createAntButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        tablaHormigas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaHormigas);
        add(scrollPane, gbc);

        cargarHormigas();
        createAntButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de crear una Hormiga larva?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                
            }
        });
    }
    public boolean crearHormigaLarva(String nombre) throws Exception {
        
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("El nombre de la hormiga larva es obligatorio.");
        }
    
        
        OGHormigaDTO hormigaLarva = new OGHormigaDTO();
        hormigaLarva.setTipoHormiga("Larva");
        hormigaLarva.setNombre(nombre);
        hormigaLarva.setSexo("Asexual");
        hormigaLarva.setEstado("VIVA");
        hormigaLarva.setUbicacion(generarUbicacionAleatoria()); 
        
        return hormigaDAO.ogCreate(hormigaLarva);
    }

    public void cargarHormigas() {
        try {
            List<OGHormigaDTO> hormigas = hormigaDAO.ogReadAll();
            DefaultTableModel model = new DefaultTableModel(
                new String[] { "Región", "Tipo Hormiga", "Provincia", "Sexo", "GenoAlimento", "Ingesta Nativa", "Estado" }, 0
            );
    
            for (OGHormigaDTO hormiga : hormigas) {
                String nombreSexo = hormigaBL.obtenerSexo(hormiga.getIdSexo());
                String nombreGenoAlimento = hormigaBL.obtenerGenoAlimento(hormiga.getIdGenoAlimento());
                String nombreIngestaNativa = hormigaBL.obtenerNombreIngestaNativa(hormiga.getIdIngestaNativa());
                String nombreProvincia = hormigaBL.obtenerProvincia(hormiga.getIdProvincia());
                String nombreRegion = hormigaBL.obtenerRegion(hormiga.getIdProvincia());
                String tipoHormiga = hormiga.getTipoHormiga();
                String estado = hormiga.getEstado();
    
                // Agregar una fila al modelo de la tabla con los valores obtenidos
                model.addRow(new Object[] { nombreRegion, tipoHormiga, nombreProvincia, nombreSexo, nombreGenoAlimento, nombreIngestaNativa, estado });
            }
    
            // Establecer el modelo en la tabla
            tablaHormigas.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar hormigas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar hormigas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public OGHormigaDTO obtenerHormigaSeleccionada() {
        int selectedRow = tablaHormigas.getSelectedRow();
        if (selectedRow != -1) {
            String nombreHormiga = (String) tablaHormigas.getValueAt(selectedRow, 0);
            try {
                return hormigaDAO.ogReadAll().stream()
                        .filter(h -> h.getNombre().equals(nombreHormiga))
                        .findFirst().orElse(null);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al obtener la hormiga seleccionada", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public void actualizarHormigaEnTabla(OGHormigaDTO hormiga) {
        int selectedRow = tablaHormigas.getSelectedRow();
        try {
            tablaHormigas.setValueAt(hormigaBL.obtenerGenoAlimento(hormiga.getIdGenoAlimento()),
                    selectedRow, 2);
            tablaHormigas.setValueAt(hormigaBL.obtenerNombreIngestaNativa(hormiga.getIdIngestaNativa()),
                    selectedRow, 3);
            tablaHormigas.setValueAt(hormigaBL.obtenerSexo(hormiga.getIdSexo()), selectedRow, 1);
            tablaHormigas.setValueAt(hormigaBL.obtenerProvincia(hormiga.getIdProvincia()), selectedRow, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
