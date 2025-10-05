/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
/**
 *
 * @author lucelia
 */
public class TextFieldUtils {
    public static void estilizarCampo(JTextField campo) {
        campo.setBorder(new RoundedBorder(10, Color.WHITE)); // borda arredondada branca
        campo.setBackground(new Color(255, 255, 255)); // fundo branco
        campo.setForeground(Color.BLACK); // texto preto
        campo.setCaretColor(Color.BLACK); // cursor preto
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setMargin(new Insets(5, 10, 5, 10)); // espaço interno (top, left, bottom, right)
    }

    // Borda arredondada
    static class RoundedBorder extends AbstractBorder {
        private int radius;
        private Color cor;
        public RoundedBorder(int radius, Color cor) {
            this.radius = radius;
            this.cor = cor;
        }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(cor);
            g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}
