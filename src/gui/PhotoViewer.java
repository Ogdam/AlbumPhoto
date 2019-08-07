package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import datas.Album;
import datas.Photo;
import exception.PhotoNotFoundException;
import exception.WrongExtensionException;

@SuppressWarnings("deprecation")
public class PhotoViewer extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private static int avancer = 0, reculer=1, Ajout = 2, Retrait = 3 ;
	
	Album alb = null;
	
	JLabel nomPhoto = new JLabel("undefinned");
	LabelImage img = new LabelImage("pika.jpg");
	JLabel current = new JLabel("0");
	JLabel size = new JLabel("0");
	
	private JButton Avancer;
	private JButton Reculer;
	private JButton AjoutPhoto;
	private JButton RetirerPhoto;
	
	public PhotoViewer() {
		this.setLayout(new BorderLayout());
		this.add(this.nomPhoto, BorderLayout.NORTH);
		
		this.add(this.img, BorderLayout.CENTER);

		JPanel bot = new JPanel();
		Reculer = new JButton("<<");
		Avancer = new JButton(">>");
		AjoutPhoto = new JButton("Ajout");
		RetirerPhoto = new JButton("Retrait");
		
		bot.add(RetirerPhoto);
		bot.add(Reculer);
		bot.add(this.current);
		bot.add(new JLabel(" / "));
		bot.add(this.size);
		bot.add(Avancer);
		bot.add(AjoutPhoto);
		
		Reculer.addActionListener(new Button(reculer));
		Reculer.setEnabled(false);
		Avancer.addActionListener(new Button(avancer));
		AjoutPhoto.addActionListener(new Button(Ajout));
		RetirerPhoto.addActionListener(new Button(Retrait));
		
		this.add(bot, BorderLayout.SOUTH);
		update(null, null);
	}

	public void follow(Object obj) {
		Album alb = (Album)obj;
		if(obj != null){
			if(this.alb != null)
				this.alb.deleteObserver(this);
			this.alb = alb;
			this.alb.addObserver(this);
		}else
			this.alb = null;
		if(obj==null || alb.isEmpty())
			this.update(null, null);
		else
			this.update(null, alb.getPhoto(alb.getCurrentPhoto()));
	}	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 == null) {
			this.nomPhoto.setText("PAS DE PHOTO");
			this.img.setImage("nothing.png");
			this.current.setText("0");
			this.size.setText("0");
			this.Avancer.setEnabled(false);
			this.Reculer.setEnabled(false);
			this.RetirerPhoto.setEnabled(false);
		}else {
			Photo photo = null;
			if(arg1 instanceof String) {
				try {photo = new Photo((String)arg1);
				} catch (PhotoNotFoundException | WrongExtensionException e) { System.out.println(e);;}
			}else {photo = (Photo)arg1;}
			this.nomPhoto.setText(photo.getNom());
			this.img.setImage(photo.getPath());
			this.current.setText("" + (this.alb.getCurrentPhoto() +1));
			this.size.setText("" + this.alb.getSize());
			if (this.alb.getCurrentPhoto() == this.alb.getSize()-1) this.Avancer.setEnabled(false);
			if (this.alb.getCurrentPhoto() < this.alb.getSize()-1) this.Avancer.setEnabled(true);
			if (this.alb.getCurrentPhoto() == 0) this.Reculer.setEnabled(false);
			if (this.alb.getCurrentPhoto() > 0) this.Reculer.setEnabled(true);
			if(alb.getSize() > 0) this.RetirerPhoto.setEnabled(true);
			else this.RetirerPhoto.setEnabled(false);
		}
		if(null == this.alb)
			this.AjoutPhoto.setEnabled(false);
		else
			this.AjoutPhoto.setEnabled(true);
	}
	
	private void ajoutPhoto(){
		File[] file ;
		JFileChooser fc = new JFileChooser(".");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif","jpeg");
		fc.setFileFilter(filter);	   
		fc.setMultiSelectionEnabled(true) ;
		int returnVal = fc.showOpenDialog(getParent());
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFiles();	
			for(File f : file) {
				alb.ajouterPhoto(f.getPath());
			}
        }
	}
	
	private class Button implements ActionListener{
		private int item;
		
		public Button(int typeItem) {
			this.item = typeItem;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			if (item == avancer){alb.setCurrentPhoto(alb.getCurrentPhoto()+1);}
			else if (item == reculer){alb.setCurrentPhoto(alb.getCurrentPhoto()-1);}
			else if (item == Ajout){ajoutPhoto();}
			else if (item == Retrait){alb.retirerPhoto(alb.getCurrentPhoto());}
		}
	}
}
