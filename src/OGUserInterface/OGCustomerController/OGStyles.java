package OGUserInterface.OGCustomerController;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class OGStyles {
    public static final Color OGBACKGROUND_COLOR = new Color(245, 245, 245); // Fondo claro
    public static final Color OGCOLOR_FONT = new Color(0, 0, 0); // Color de fuente negro
    public static final Color OGCOLOR_FONT_LIGHT = new Color(120, 120, 120); // Color de fuente más claro
    public static final Color OGCOLOR_CURSOR = new Color(0, 0, 0); // Color del cursor
    public static final Color OGCOLOR_BORDER = new Color(180, 180, 180); // Color del borde claro

    // Colores Naturaleza
    public static final Color OGCOLOR_FOREST_GREEN = new Color(34, 139, 34); // Verde bosque
    public static final Color OGCOLOR_SAND = new Color(194, 178, 128); // Color arena
    public static final Color OGCOLOR_EARTH_BROWN = new Color(139, 69, 19); // Marrón tierra
    public static final Color OGCOLOR_SKY_BLUE = new Color(135, 206, 235); // Azul cielo

    // Colores Azules Oscuro
    public static final Color OGCOLOR_BLUE1 = new Color(13, 27, 42);
    public static final Color OGCOLOR_BLUE2 = new Color(27, 38, 59);
    public static final Color OGCOLOR_BLUE3 = new Color(65, 90, 119);
    public static final Color OGCOLOR_BLUE4 = new Color(119, 141, 169);
    public static final Color OGCOLOR_BLUE5 = new Color(224, 225, 221);

    // Colores Morados
    public static final Color OGCOLOR_PURPLE1 = new Color(75, 0, 130); // Índigo
    public static final Color OGCOLOR_PURPLE2 = new Color(128, 0, 128); // Púrpura
    public static final Color OGCOLOR_PURPLE3 = new Color(147, 112, 219); // Púrpura medio
    public static final Color OGCOLOR_PURPLE4 = new Color(186, 85, 211); // Orquídea media
    public static final Color OGCOLOR_PURPLE5 = new Color(216, 191, 216); // Orquídea claro

    // Colores Botones
    public static final Color OGCOLOR_YELLOW = new Color(249, 254, 125);
    public static final Color OGCOLOR_RED = new Color(254, 104, 104);
    public static final Color OGCOLOR_GREEN = new Color(182, 254, 104);

    // Colores de Peligro/Advertencia
    public static final Color OGCOLOR_WARNING_ORANGE = new Color(255, 165, 0); // Naranja de advertencia
    public static final Color OGCOLOR_DANGER_RED = new Color(220, 20, 60); // Rojo peligro
    public static final Color OGCOLOR_CAUTION_YELLOW = new Color(255, 255, 0); // Amarillo precaución

    // Colores Metálicos
    public static final Color OGCOLOR_SILVER = new Color(192, 192, 192); // Plata
    public static final Color OGCOLOR_GOLD = new Color(255, 215, 0); // Oro
    public static final Color OGCOLOR_BRONZE = new Color(205, 127, 50); // Bronce

    public static final Font OGFONT = new Font("Arial", Font.PLAIN, 18); // Fuente más grande
    public static final Font OGFONT_BOLD = new Font("Arial", Font.BOLD, 18);
    public static final Font OGFONT_BOLD_SMALL = new Font("Arial", Font.BOLD, 9);

    // Método para cargar una fuente personalizada
    public static Font loadCustomFont(String path, float size) {
        try {
            InputStream is = OGStyles.class.getResourceAsStream(path);
            if (is == null) {
                throw new IOException("Resource not found: " + path);
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, (int) size); // Fuente por defecto en caso de error
        }
    }

    // Fuente personalizada cargada desde el archivo AmidoneGrotesk.ttf
    public static final Font OGFONT_AMIDONEGROTESK = loadCustomFont(
            "/OGUserInterface/OGResources/Fonts/AmidoneGrotesk.ttf", 14f);
    public static final Font OGFONT_ANGELACINDY = loadCustomFont(
            "/OGUserInterface/OGResources/Fonts/AngelaCindysttar.otf", 14f);
    public static final Font OGFONT_DESTRUCTION = loadCustomFont(
            "/OGUserInterface/OGResources/Fonts/DestructionManual.otf", 20f);
    public static final Font OGFONT_LANGOSTIONS = loadCustomFont("/OGUserInterface/OGResources/Fonts/LANGOSTIONS.ttf",
            14f);
    public static final Font OGFONT_LANGOSTIONS_SMALL = loadCustomFont(
            "/OGUserInterface/OGResources/Fonts/LANGOSTIONS.ttf", 11f);

    public static final Cursor OGCURSOR_HAND = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor OGCURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

    public static final URL URL_ICON_ANT = OGStyles.class
            .getResource("/OGUserInterface/OGResources/Icons/iconAnt3.png");
    public static final URL URL_LOGO_ANT = OGStyles.class
            .getResource("/OGUserInterface/OGResources/Logos/logoHormiga2.png");

    public static final CompoundBorder OGCreateBorderRect() {
        return BorderFactory.createCompoundBorder(new LineBorder(OGCOLOR_BORDER), new EmptyBorder(5, 5, 5, 5));
    }

    public static final void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "👾 ECUFAUNA", JOptionPane.INFORMATION_MESSAGE);
    }

    public static final void showMsgError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "☣️ ECUFAUNA", JOptionPane.OK_OPTION);
    }

    public static final boolean showConfirmYesNo(String msg) {
        return (JOptionPane.showConfirmDialog(null, msg, "❓ ECUFAUNA",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
}
