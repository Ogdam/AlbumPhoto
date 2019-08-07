package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import datas.Event;
import datas.Personne;
import controlers.PicView;

public class RemoveEvent extends JDialog {

	private static final long serialVersionUID = 1L;


	
	private PicView pcw;
	
	/**
	 * JTextField
	 */
	private JList<String> jl;
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
	public RemoveEvent(WindowFrame parent, String title, boolean modal, PicView pcw){
	    super(parent, title, modal);
	    this.pcw = pcw;
	    new HashMap<String, Personne>();
		this.initLayout();
	    this.setSize(400,400);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	

	
	/**
	 * ajoute du contenu au layout de la fenetre
	 */
	public void initLayout(){
        this.setLayout(new BorderLayout());
        DefaultListModel<String>  lm = new DefaultListModel<String>();
        jl = new JList<String>(lm);
        jl.setBackground(this.getBackground());
        jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for(Event event : pcw.getEvents()) {
            lm.addElement(event.getNom());
        }

        JPanel lis = new JPanel(); 
        lis.add(jl);
        JPanel panPers = new JPanel();
        panPers.setBackground(Color.WHITE);
        panPers.setBorder(this.bdPers);

        JPanel control = new JPanel();
        this.okBouton = new JButton("Valider");
        okBouton.addActionListener(new Button());

        control.add(this.okBouton);

        this.add(control,BorderLayout.NORTH);
        this.add(lis,BorderLayout.CENTER);

    }


	
	private class Button implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			pcw.retirerEvent(jl.getSelectedValue().toString());
			RemoveEvent.this.dispose();
		}
	}
}