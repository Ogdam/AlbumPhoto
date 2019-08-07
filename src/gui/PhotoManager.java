package gui;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import datas.Album;
import controlers.PicView;

@SuppressWarnings("deprecation")
public class PhotoManager extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private PhotoSelector selecter = new PhotoSelector();
	private PhotoViewer view = new PhotoViewer();
	
	public PhotoManager(PicView pcV) {
		pcV.addObserver(this);
		this.setLayout(new BorderLayout());
		
		this.add(this.view, BorderLayout.CENTER);
		
		this.add(this.selecter, BorderLayout.EAST);
		
		
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		this.selecter.follow((Album)arg1);
		this.view.follow(arg1);
	}
	
}
