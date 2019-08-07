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
import exception.WrongExtensionException;
import exception.WrongFileException;
import datas.Photo;

public class AlbumPhoto extends Album {
	
	private ArrayList<Photo> listePhoto;
	
	/**
	 * creer un Album Photo
	 * @param nom
	 */
	public AlbumPhoto(String nom) {
		super(nom);
		this.listePhoto = new ArrayList<Photo>();
	}
	
	/**
	 * Creer un album photo numeroté
	 * @param nomFichier
	 * @param i
	 */
	public AlbumPhoto(String nomFichier, String nomAlbum) {
		super(nomAlbum);
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
		return this.listePhoto.size();
	}
	
	@Override
	/**
	 * ajoute des photos a l'album
	 * @param photo
	 */
	public void ajouterPhoto(String photo) {
		try {
			Photo p = new Photo(photo);
			this.listePhoto.add(p);
			super.ajouterPhoto(photo);
		} catch (PhotoNotFoundException | WrongExtensionException e) {
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
			this.listePhoto = new ArrayList<Photo>();
			while((ligne = bIn.readLine()) != null) {
				ajouterPhoto(ligne);
			}
		}finally {
			try {
				if (bIn != null)
					bIn.close();
			}catch(IOException e){ System.out.println(e);
			}
		}
	}

	/**
	 * sauvegarde un Album Photo
	 */
	public void save() {
		BufferedWriter bOut = null;
		try {
			bOut = new BufferedWriter(new FileWriter("Sauvegarde/Album/Album_"+this.getNomAlbum()+".txt"));
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
