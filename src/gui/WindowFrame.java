package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controlers.PicView;

public class WindowFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static int Album = 0, AlbumEvent = 1, Event = 2;
	
	PicView pvw;
	
	int test = 1;
	
	public WindowFrame(PicView pvw) {
		super("PicView3000");
		this.pvw = pvw;		
		
		JPanel pane1 = new AlbumSelector(this.pvw);
		JPanel pane2 = new PhotoManager(this.pvw);
		
		this.add(pane1, BorderLayout.WEST);
		this.add(pane2, BorderLayout.CENTER);
		
		this.initMenu();
		
		this.setSize(800, 500);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	
	private void initMenu() {
		JMenuBar bar = new JMenuBar();
			JMenu menu = new JMenu("Menu"); 
			JMenuItem CreerAlbum = new JMenuItem("Creer Album");
			JMenuItem CreerAlbumEvent = new JMenuItem("Creer Album Evennementiel");
			JMenuItem SuppAlbum = new JMenuItem("Supprimer Album");
			JMenuItem CreerEvent = new JMenuItem("Creer Evenement");
			JMenuItem SuppEvent = new JMenuItem("Supprimer Evenement");
			JMenuItem SaveAll = new JMenuItem("Sauvegarde");
			menu.add(SaveAll);
			menu.add(CreerAlbum);
			menu.add(CreerAlbumEvent);
			menu.add(SuppAlbum);
			menu.add(CreerEvent);
			menu.add(SuppEvent);
			bar.add(menu);
			this.setJMenuBar(bar);
			
		//Action listener
		SaveAll.addActionListener(new Sauv());
		CreerAlbum.addActionListener(new AddButton(Album));
		CreerAlbumEvent.addActionListener(new AddButton(AlbumEvent));
		SuppAlbum.addActionListener(new DelButton(Album));
		CreerEvent.addActionListener(new AddButton(Event));
		SuppEvent.addActionListener(new DelButton(Event));
		
	}
	
	public static void main(String[] args) {
		PicView pcw = new PicView();
		new WindowFrame(pcw);
		new WindowFrame(pcw);
	}
	
	
	private class AddButton implements ActionListener{
		private int item;
		
		public AddButton(int typeItem) {
			this.item = typeItem;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			if (item == Album) {
				new EventAddAlbum(WindowFrame.this, "Creation Evenements", rootPaneCheckingEnabled, pvw);
			}
			else if (item == AlbumEvent){
				new EventAddAlbumEvent(WindowFrame.this, "Creation Evenements", rootPaneCheckingEnabled, pvw);
			}
			else if (item == Event){new AddEvent(WindowFrame.this, "Creation Evenements", rootPaneCheckingEnabled, pvw); }
		}
	}
	
	private class DelButton implements ActionListener{
		private int item;
		
		public DelButton(int typeItem) {
			this.item = typeItem;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			if (item == Album) {
				WindowFrame.this.pvw.retirerAlbum(WindowFrame.this.pvw.getCurrentAlbum());
			}
			else if (item == Event){ new RemoveEvent(WindowFrame.this, "remove Event", rootPaneCheckingEnabled, pvw);}
		}
	}
	
	private class Sauv implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			WindowFrame.this.pvw.sauvegarde();
		}
	}
}
