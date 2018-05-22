package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Imagen33 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 132941042246207598L;
	public Imagen33() {
		setSize(200,200);
	}
	// Se crea un m�todo cuyo par�metro debe ser un objeto Graphics
		public void paint(Graphics grafico) {
			Dimension height = getSize();

			// Se selecciona la imagen que tenemos en el paquete de la //ruta del programa

			//ImageIcon Img = new ImageIcon(getClass().getResource("src/Images/muro_laberinto.png"));
			ImageIcon img = new ImageIcon("src/images/33.png");

			// se dibuja la imagen que tenemos en el paquete Images //dentro de un panel

			grafico.drawImage(img.getImage(), 0, 0, height.width, height.height, null);

			setOpaque(false);
			super.paintComponent(grafico);
		}
}