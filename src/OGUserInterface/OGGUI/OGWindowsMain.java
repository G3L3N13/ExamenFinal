package OGUserInterface.OGGUI;

import javax.swing.JFrame;

import OGUserInterface.OGCustomerController.OGPanelBar;
import OGUserInterface.OGForms.OGMainPanel;

import java.awt.BorderLayout;
import java.awt.Container;

public class OGWindowsMain extends JFrame {

    public OGWindowsMain(String titleApp) {
        customizeComponent(titleApp);
    }

    private void customizeComponent(String titleApp) {
        setUndecorated(true);
        setSize(600, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Configurar el contenedor principal
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Añadir barra de título personalizada
        container.add(new OGPanelBar(this), BorderLayout.NORTH);

        // Añadir panel principal
        container.add(new OGMainPanel(), BorderLayout.CENTER);

        setVisible(true);
    }
}
