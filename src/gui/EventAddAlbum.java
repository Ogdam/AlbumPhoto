package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import datas.AlbumPhoto;
import datas.Personne;
import controlers.PicView;

public class EventAddAlbum extends JDialog {

	private static final long serialVersionUID = 1L;


	
	private PicView pcw;
	
	private TextField name = new TextField();
	
	/**
	 * JButton
	 */
	private JButton okBouton;
	
	private TitledBorder bdPers;
	
	/**
	 * Construit une boite dialogue pour l'ajout d'un evenement
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public EventAddAlbum(WindowFrame parent, String title, boolean modal, PicView pcw){
	    super(parent, title, modal);
	    this.pcw = pcw;
	    new HashMap<String, Personne>();
		this.initLayout();
	    this.setSize(400,400);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	

	
	/**
	 * ajoute du contenu au layout de la fenetre
	 */
	public void initLayout(){
        this.setLayout(new BorderLayout());

        JPanel panPers = new JPanel();
        panPers.setBackground(Color.WHITE);
        panPers.setBorder(this.bdPers);

        JPanel control = new JPanel();
        this.okBouton = new JButton("Valider");
        okBouton.addActionListener(new Button());

        control.add(this.okBouton);

        this.add(name, BorderLayout.NORTH);
        this.add(control,BorderLayout.SOUTH);

    }


	
	private class Button implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			pcw.ajouterAlbum(new AlbumPhoto(name.getText()));
			EventAddAlbum.this.dispose();
		}
	}
}