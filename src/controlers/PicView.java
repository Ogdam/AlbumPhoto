package controlers;


import datas.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class PicView extends Observable{

	public static final String[] extension = {"jpg", "jpeg", "bmp", "gif", "png"};
	
	private int currentAlbum;
	private ArrayList<Album> albumList =  new ArrayList<Album>();
	private HashMap<String, Event> eventMap = new HashMap<String, Event>();
	
	/*
	 * Constructeur de PicView
	 */
	public PicView() {
		this.initEvent();
		this.initAlbum();
	}
	
	/**
	 * Retourne le nom de l'album en cours de visionnage
	 * @param i
	 * @return
	 */
	public String getNomAlbum(int i) {
		return this.albumList.get(i).getNomAlbum();
	}
	
	/**
	 * retourne la taille de l'album en cours de visionnage
	 * @return
	 */
	public int getSize() {
		return this.albumList.size();
	}
	
	/**
	 * Ajoute un album a la liste des albums
	 * @param alb
	 */
	public void ajouterAlbum(Album alb) {
		this.albumList.add(alb);
		System.out.println("adding " + alb);
		this.currentAlbum = this.albumList.size()- 1;
		this.setChanged();
		this.notifyObservers(alb);
	}
	
	/**
	 * Supprime un album de la liste des albums
	 * @param i
	 */
	public void retirerAlbum(int i) {
		this.albumList.remove(i);
		if (this.currentAlbum == this.getSize()) {
			this.currentAlbum--;
		}
		this.setChanged();
		if(this.albumIsEmpty())
			this.notifyObservers(null);
		else 
			this.notifyObservers(this.albumList.get(this.currentAlbum));
	}
	
	/**
	 * Retourne l'album a la position i de la liste 
	 * @param i
	 * @return
	 */
	public Album getAlbum(int i) {
		return this.albumList.get(i);
	}
	
	/**
	 * retourne si l'album est vide 
	 * @return
	 */
	public boolean albumIsEmpty() {
		return this.albumList.isEmpty();
	}
	
	/**
	 * Rajoute un Evenement a la liste des evenement
	 * @param evt
	 */
	public void ajouterEvent(Event evt) {
		this.eventMap.put(evt.getNom(), evt);
	}
	
	/**
	 * Retire un Evenement a la liste des evenement
	 * @param evt
	 */
	public void retirerEvent(String evt) {
		this.eventMap.remove(evt);
	}
	
	/**
	 * Retourne l'evenement nommé evt
	 * @param evt
	 * @return
	 */
	public Event getEvent(String evt) {
		return this.eventMap.get(evt);
	}
	
	/**
	 * Retourne la liste des Evenement
	 * @return
	 */
	public Collection<Event> getEvents() {
		return this.eventMap.values();
	}
	
	
	/**
	 * retroune si il y a des evenements existant
	 * @return
	 */
	public boolean eventIsEmpty() {
		return this.eventMap.isEmpty();
	}
	
	/**
	 * Retourne l'album en courant
	 * @return
	 */
	public int getCurrentAlbum() {
		return currentAlbum;
	}
	
	/**
	 * change l'album courant
	 * @param currentAlbum
	 */
	public void setCurrentAlbum(int currentAlbum) {
		this.currentAlbum = currentAlbum;
		this.setChanged();
		this.notifyObservers(this.albumList.get(this.currentAlbum));
	}
	
	/**
	 * Initialisation des albums en fonction des sauvegardes
	 */
	private void initAlbum(){
		File repertoire = new File("Sauvegarde/Album");
		File[] files = repertoire.listFiles();
		for(File f : files) {
			String path = f.getPath();
			System.out.println("chargement: " + path);
			try {
				int s =  path.indexOf("Album_");
				if(s > 0) {
					String name = path.substring(s + 6);
					s = name.indexOf(".txt");
					if(s > 0) {
						name = name.substring(0, s);
						System.out.println(name);
						this.albumList.add(new AlbumPhoto(path, name));
					}
				}else {
					s =  path.indexOf("AlbumEvent_");
					if(s > 0) {
						String name = path.substring(s + 11);
						s = name.indexOf(".txt");
						if(s > 0) {
							name = name.substring(0, s);
							System.out.println(name);
							this.albumList.add(new AlbumPhotoEvent(path, name, this.getEvent(name)));
						}
					}
				}
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 * Initialisation des Event en fonction des sauvegardes
	 */
	private void initEvent(){
		File repertoire = new File("Sauvegarde/Event");
		File[] files = repertoire.listFiles();
		for(File f : files) {
			String path = f.getPath();
			System.out.println("chargement: " + path);
			try {
				int s =  path.indexOf("Event_");
				if(s > 0) {
					String name = path.substring(s + 6);
					s = name.indexOf(".txt");
					if(s > 0) {
						name = name.substring(0, s);
						System.out.println(name);
						this.eventMap.put(name, new Event(name, path));
					}
				}
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}

	
	/**
	 * Sauvegarde les albums et evenements existant
	 */
	public void sauvegarde() {
		for(Album album : albumList ) {
			if (album instanceof AlbumPhoto) {
				AlbumPhoto a = (AlbumPhoto) album;
				System.out.println(a.getNomAlbum());
				a.save();
			} else if (album instanceof AlbumPhotoEvent) {
				AlbumPhotoEvent a = (AlbumPhotoEvent) album;
				a.save();
			}
			for(Event event : eventMap.values() ) {
				event.save();
			}
		}
	}
	
}
