package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
// TODO: Auto-generated Javadoc

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ Universidad Minuto de Dios (Bogot� - Colombia) Departamento de
 * Ingenier�a de Sistemas
 * 
 * clase para mostrar im�genes de numeros
 * 
 * Ejercicio: Puzzle.
 *
 * @author Alexis Holguin
 * @since Mayo 20, 2018
 *        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class Imagen28 extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1738187576346376072L;

	/**
	 * Instantiates a new imagen 28.
	 */
	public Imagen28() {
		setSize(200, 200);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	// Se crea un m�todo cuyo par�metro debe ser un objeto Graphics
	public void paint(Graphics grafico) {
		Dimension height = getSize();

		// Se selecciona la imagen que tenemos en el paquete de la //ruta del programa

		// ImageIcon Img = new
		// ImageIcon(getClass().getResource("src/Images/muro_laberinto.png"));
		ImageIcon img = new ImageIcon("src/images/28.png");

		// se dibuja la imagen que tenemos en el paquete Images //dentro de un panel

		grafico.drawImage(img.getImage(), 0, 0, height.width, height.height, null);

		setOpaque(false);
		super.paintComponent(grafico);
	}
}
