package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import controlers.PicView;

@SuppressWarnings("deprecation")
public class AlbumSelector extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private PicView pvw;
	private DefaultListModel<String> lm;
	private JList<String> jl;
	
	public AlbumSelector(PicView pvw) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(200, 200));
		this.setBorder(BorderFactory.createTitledBorder("Albums"));
		
		this.lm = new DefaultListModel<String>();
		this.jl = new JList<String>(lm);
		jl.setBackground(this.getBackground());
		jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(jl);
		
		this.pvw = pvw;
		this.pvw.addObserver(this);
		
		jl.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		            // Double-click detected
		        	if(!pvw.albumIsEmpty())
		        		AlbumSelector.this.pvw.setCurrentAlbum(jl.locationToIndex(evt.getPoint()));
		        }
		    }
		});
		
		this.update(null, null);
	}

	public void update(Observable arg0, Object arg1) {
        lm.removeAllElements();
        if(!this.pvw.albumIsEmpty()) {
            for(int i = 0; i < this.pvw.getSize(); i++){
                lm.addElement(this.pvw.getNomAlbum(i));
            }
            this.jl.setSelectedIndex(this.pvw.getCurrentAlbum());
        }
    }
}
