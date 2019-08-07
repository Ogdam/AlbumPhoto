package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import datas.Event;
import datas.Personne;
import controlers.PicView;
import exception.MailFormatException;

public class DialogAddEvent extends JDialog {

	private static final long serialVersionUID = 1L;


	
	private PicView pcw;
	
	/**
	 * JTextArea
	 */
	private JTextArea persArea;
	
	/**
	 * JLabel
	 */
	private JLabel nomLabel;
	private JLabel persLabelNom;
	private JLabel persLabelMail;
	
	/**
	 * JTextField
	 */
	private JTextField nom;
	private JTextField persNom;
	private JTextField persMail;
	
	/**
	 * JButton
	 */
	private JButton plus;
	private JButton moins;
	private JButton okBouton;
	private JButton cancelBouton;
	
	/**
	 * Map
	 */
	private HashMap<String, Personne> listPersonnes;
	
	/**
	 * Border
	 */
	private TitledBorder bdNom;
	private TitledBorder bdPers;
	private Border bdArea;
	
	/**
	 * Construit une boite dialogue pour l'ajout d'un evenement
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public DialogAddEvent(WindowFrame parent, String title, boolean modal){
	    super(parent, title, modal);
	    this.listPersonnes = new HashMap<String, Personne>();
	    this.initStyle();
		this.initLayout();
		this.initActions();
	    this.setSize(400,400);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	

	
	/**
	 * initie les styles des containers
	 */
	public void initStyle(){
		this.bdNom = BorderFactory.createTitledBorder("Nom de l'�v�nement");
		this.bdPers = BorderFactory.createTitledBorder("Personne(s) de l'�v�nement");
		this.bdArea = BorderFactory.createLineBorder(Color.BLACK);
	}
	
	/**
	 * ajoute du contenu au layout de la fenetre
	 */
	public void initLayout(){
		this.setLayout(new BorderLayout());
		
		JPanel infos = new JPanel();
		infos.setPreferredSize(new Dimension(380, 340));
		
		JPanel panNom = new JPanel();
	    panNom.setBackground(Color.WHITE);
	    panNom.setPreferredSize(new Dimension(380, 60));
	    panNom.setBorder(this.bdNom);
	    
	    this.nom = new JTextField();
	    this.nom.setPreferredSize(new Dimension(250, 30));
	    this.nomLabel = new JLabel("Saisir un nom :"); 
	    
	    panNom.add(this.nomLabel);
	    panNom.add(this.nom);
	    
	    JPanel panPers = new JPanel();
	    panPers.setBackground(Color.WHITE);
	    panPers.setPreferredSize(new Dimension(380, 280));
	    
	    this.persNom = new JTextField();
	    this.persMail = new JTextField();
	    this.persArea = new JTextArea();
	    
	    this.persNom.setPreferredSize(new Dimension(250, 30));
	    this.persMail.setPreferredSize(new Dimension(250, 30));
	    
	    panPers.setBorder(this.bdPers);
	    
	    this.persLabelNom = new JLabel("Saisir un nom :");
	    this.persLabelMail = new JLabel("Saisir un mail :");
	    
	    this.persArea.setEditable(false);
	    this.persArea.setBackground(Color.WHITE);
	    this.persArea.setPreferredSize(new Dimension(345, 120));
	    this.persArea.setBorder(this.bdArea);	
	    
	    this.plus = new JButton("+");
	    this.moins = new JButton("-");
	    
	    this.moins.setPreferredSize(new Dimension(170, 30));
	    this.plus.setPreferredSize(new Dimension(170, 30));
	    
	    panPers.add(this.persLabelNom);
	    panPers.add(this.persNom);
	    panPers.add(this.persLabelMail);
	    panPers.add(this.persMail);
	    panPers.add(this.plus);
	    panPers.add(this.moins);
	    panPers.add(this.persArea);

	    JPanel control = new JPanel();
	    this.okBouton = new JButton("Valider");
	    this.cancelBouton = new JButton("Annuler");
	    
	    control.add(this.okBouton);
	    control.add(this.cancelBouton);

	    infos.add(panNom);
	    infos.add(panPers);
	    this.getContentPane().add(infos,BorderLayout.CENTER);
	    this.getContentPane().add(control,BorderLayout.SOUTH);
	}
	
	/**
	 * Place les listeners sur les containers
	 */
	public void initActions(){
		this.plus.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {        
	    		try {
					Personne p = new Personne(DialogAddEvent.this.persNom.getText(),DialogAddEvent.this.persMail.getText());
					DialogAddEvent.this.listPersonnes.put(p.getMail(), p);
					DialogAddEvent.this.persArea.setText(DialogAddEvent.this.listPersonnes.toString());
				} catch (MailFormatException e) {
					System.out.println(e);
				}
	    	}      
	    });
		
		this.moins.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {        
	    		DialogAddEvent.this.listPersonnes.remove(DialogAddEvent.this.persMail.getText());
	    		DialogAddEvent.this.persArea.setText(DialogAddEvent.this.listPersonnes.toString());
	    	}      
	    });
		
		this.okBouton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
	    		if(DialogAddEvent.this.nom.getText().length()!=0) {
	    			DialogAddEvent.this.pcw.ajouterEvent(new Event(DialogAddEvent.this.nom.getText(),DialogAddEvent.this.listPersonnes));
	    		}
	    		else
					System.out.println("nom event vide");
					DialogAddEvent.this.dispose();
	    	}      
	    });
		
		this.cancelBouton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
	    		DialogAddEvent.this.dispose();
	    	}      
	    });
	} 
	
}
