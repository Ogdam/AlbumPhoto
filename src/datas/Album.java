package datas;

import java.io.IOException;
import java.util.Observable;

import exception.WrongFileException;


@SuppressWarnings("deprecation")
public abstract class Album extends Observable{

	private int currentPhoto;
	protected String nom;

	public Album(String nom) {
		this.nom = nom;
	}
	
	public String getNomAlbum() {
		return this.nom;
	}
	
	public abstract boolean isEmpty();
	
	public abstract int getSize();
	
	public void ajouterPhoto(String photo) {
		if(this.isEmpty())
			this.notifyObservers();
		else
			this.notifyObservers(this.getPhoto(this.currentPhoto));
	}
	
	public void retirerPhoto(int i) {
		this.setChanged();
		if(this.currentPhoto > 0)
			this.currentPhoto--;
		if(this.isEmpty())
			this.notifyObservers();
		else
			this.notifyObservers(this.getPhoto(this.currentPhoto));
	}
	
	public abstract Photo getPhoto(int i);
	
	public abstract String getNomPhoto(int i);
	
	public abstract void load(String nomFichier) throws IOException, WrongFileException;
	
	public abstract void save();

	public int getCurrentPhoto() {
		return currentPhoto;
	}

	public void setCurrentPhoto(int currentPhoto) {
		this.currentPhoto = currentPhoto;
		this.setChanged();
		this.notifyObservers(this.getPhoto(this.getCurrentPhoto()));
	}
}
