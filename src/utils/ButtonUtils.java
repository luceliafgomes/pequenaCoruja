/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author lucelia
 */
public class ButtonUtils {
    public static void estilizarBotao(JButton button) {
       // Cor normal e hover
        Color corNormal = new Color(0xC8A2C8);
        Color corHover = corNormal.darker();

        button.setBackground(corNormal);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(false);

        // Bordas arredondadas
        button.setBorder(new RoundedBorder(25));

        // Cursor de mão
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Sombra e anti-aliasing
        button.setContentAreaFilled(false);
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fundo
                g2.setColor(button.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 25, 25);

                // Sombra sutil
                g2.setColor(new Color(0, 0, 0, 40));
                g2.fillRoundRect(2, 2, c.getWidth()-4, c.getHeight()-4, 25, 25);

                super.paint(g, c);
            }
        });

        // Efeito hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(corHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(corNormal);
            }
        });
    }

    // Classe para borda arredondada
    static class RoundedBorder extends AbstractBorder {
        private int radius;
        RoundedBorder(int radius) { this.radius = radius; }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground());
            g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}
