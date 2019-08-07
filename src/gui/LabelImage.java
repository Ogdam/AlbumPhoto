package gui;

import java.awt.*;
import javax.swing.*;

/** classe autonome permettant d'afficher une image dnas un JLabel
Besoin de compléter le paintComponent pour que l'image s'affiche à la taille du JLable de façon dynamique
**/
public class LabelImage extends JLabel{

	private static final long serialVersionUID = 1L;
	private Image im;
    
    public LabelImage(String nomImage) {
        super();
        ImageIcon icon = new ImageIcon(nomImage);
        this.im = icon.getImage();
        this.setIcon(icon);
    }
    
    public LabelImage(){
        super();
    }
    
    public void setImage(String nomImage) {
        ImageIcon icon = new ImageIcon(nomImage);
        this.im = icon.getImage();
        this.setIcon(icon);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.im, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
}