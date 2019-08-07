package datas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exception.PhotoNotFoundException;
import exception.WrongEventException;
import exception.WrongExtensionException;
import exception.WrongFileException;
import datas.PhotoEvent;
import datas.Photo;

public class AlbumPhotoEvent extends Album{

	private Event evenement;
	private ArrayList<PhotoEvent> listePhoto = new ArrayList<PhotoEvent>();
	
	
	public AlbumPhotoEvent(String nom, Event event) {
		super(nom);
		this.evenement = event;
		this.listePhoto = new ArrayList<PhotoEvent>(); 
	}
	
	public AlbumPhotoEvent(String nomFichier, String nomAlbum, Event event){
		super(nomAlbum);
		this.evenement = event;
		try {
			this.load(nomFichier);
		} catch (IOException | WrongFileException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Retourne la taille de l'album
	 */
	@Override
	public int getSize() {
		return listePhoto.size();
	}
	
	/**
	 * Retourne l'evenement de l'album
	 * @return evenement
	 */
	public Event getEvenement() {
		return evenement;
	}

	/**
	 * ajoute des photos a l'album
	 * @param photo
	 */
	@Override
	public void ajouterPhoto(String photo) {
		try {
			PhotoEvent p = new PhotoEvent(photo,evenement);
			this.listePhoto.add(p);
			super.ajouterPhoto(photo);
		}catch(PhotoNotFoundException | WrongExtensionException | WrongEventException e) {
			System.out.println(e);
		}
	}

	/**
	 * Retire un photo de l'album
	 * @param i
	 */
	@Override
	public void retirerPhoto(int i) {
		this.listePhoto.remove(i);
		super.retirerPhoto(i);
	}

	/**
	 * retourne une photo de l'album a l'amplacement i
	 * @param i
	 */
	@Override
	public Photo getPhoto(int i) {
		return this.listePhoto.get(i);
	}

	/**
	 * retourne le nom de la photo de l'emplacement i
	 * @param i
	 */
	@Override
	public String getNomPhoto(int i) {
		return this.listePhoto.get(i).getNom();
	}
	
	/**
	 * to String de la fonction AlbumPhoto
	 */
	public String toString(){
		String s = new String(this.nom+"\n");
		for(Photo p : this.listePhoto){
			s += p.toString()+"\n";
		}
		return s;
	}

	/**
	 * Charge un album grace a un fichier
	 */
	@Override
	public void load(String nomFichier) throws IOException, WrongFileException {
		BufferedReader bIn = null;
		try {
			bIn = new BufferedReader(new FileReader(new File(nomFichier)));
			String ligne = bIn.readLine();
			if(!this.nom.equals(ligne))
				throw new WrongFileException(nomFichier, this.nom);
			this.listePhoto = new ArrayList<PhotoEvent>();
			while((ligne = bIn.readLine()) != null) {
				ajouterPhoto(ligne);
			}
		}finally {
			try {
				if(bIn != null)
					bIn.close();
			}catch(IOException e){ 
				System.out.println(e);
			}
		}
	}

	/**
	 * sauvegarde un Album Photo
	 */
	@Override
	public void save() {
		BufferedWriter bOut = null;
		try {
			bOut = new BufferedWriter(new FileWriter("Sauvegarde/Album/AlbumEvent_"+this.nom+".txt"));
			bOut.write(this.toString());
		}catch(FileNotFoundException e){ System.out.println(e);
		}catch(IOException e) { System.out.println(e);
		}finally {
			try {
				bOut.close();
			}catch(IOException e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * retroune si l'album est vide
	 */
	@Override
	public boolean isEmpty() {
		return this.listePhoto.isEmpty();
	}

}
