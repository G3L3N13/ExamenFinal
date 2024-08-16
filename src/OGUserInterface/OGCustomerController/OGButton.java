package OGUserInterface.OGCustomerController;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OGButton extends JButton implements MouseListener {

    private boolean isRound;
    private Color hoverBackgroundColor = new Color(128, 128, 128, 128); // Gris con 50% de transparencia
    private boolean isHovering = false; // Variable para rastrear si el ratón está encima
    private Color originalForegroundColor; // Variable para guardar el color de texto original

    public OGButton(String text, boolean isRound, Font customFont) {
        this.isRound = isRound;
        customizeComponent(text, customFont);
        //setBackground(Color.BLUE);  //Cambia el color de todos los botones

    }

    private void customizeComponent(String text, Font customFont) {
        setText(text);
        setFont(customFont); // Usar la fuente personalizada pasada como parámetro
        originalForegroundColor = Color.WHITE; // Guardar el color de texto original
        setForeground(originalForegroundColor);
        setOpaque(false); // Asegura que no se pinte el fondo rectangular
        setFocusPainted(false);
        setBorderPainted(false); // Desactivar el borde pintado
        setContentAreaFilled(false); // No pintar el área del contenido
        setCursor(OGStyles.OGCURSOR_HAND);
        addMouseListener(this);

        if (isRound) {
            setPreferredSize(new Dimension(40, 40)); // Tamaño ajustado a 40x40 píxeles para botones redondos
        } else {
            setPreferredSize(new Dimension(200, 40)); // Tamaño ajustado para botones cuadrados más grandes
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Habilitar anti-aliasing para bordes suaves
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Pintar el color de fondo
        g2.setColor(getBackground()); // Pinta todos los botones
        if (isRound) {
            g2.fillOval(0, 0, getWidth(), getHeight());
        } else {
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        // Si el ratón está encima, pintar el color semitransparente
        if (isHovering) {
            g2.setColor(hoverBackgroundColor);
            if (isRound) {
                g2.fillOval(0, 0, getWidth(), getHeight());
            } else {
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        }

        // Pintar el texto centrado en el botón
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);

        // No llamamos a super.paintComponent(g) después de dibujar el texto
    }

    // Eliminar o comentar el método paintBorder para no dibujar ningún borde
    /*
     * @Override
     * protected void paintBorder(Graphics g) {
     * // No se dibuja borde
     * }
     */

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isHovering = true; // Indicar que el ratón está sobre el botón
        setForeground(Color.BLACK); // Cambiar el color del texto a negro
        repaint(); // Volver a dibujar el botón
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isHovering = false; // Indicar que el ratón ha salido del botón
        setForeground(originalForegroundColor); // Restaurar el color de texto original
        repaint(); // Volver a dibujar el botón
    }
}
