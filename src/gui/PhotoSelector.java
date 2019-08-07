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

import datas.Album;


@SuppressWarnings("deprecation")
public class PhotoSelector extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private Album alb;
	private DefaultListModel<String> lm;
	private JList<String> jl;
	
	public PhotoSelector() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(200, 200));
		this.setBorder(BorderFactory.createTitledBorder("Photos"));
		this.lm = new DefaultListModel<String>();
		this.jl = new JList<String>(lm);
		jl.setBackground(this.getBackground());
		jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(jl);
		

		
		jl.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		            // Double-click detected
		        	if(alb != null && !alb.isEmpty())
		        		PhotoSelector.this.alb.setCurrentPhoto(jl.locationToIndex(evt.getPoint()));
		        }
		    }
		});
		this.update(null, null);
	}
	
	public void clear() {
		
	}
	
	public void follow(Album alb) {
		if(alb == null) {
			this.clear();
		}else {
			if(this.alb != null)
				this.alb.deleteObserver(this);
			this.alb = alb;
			this.alb.addObserver(this);
		}
		this.update(null, null);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		lm.removeAllElements();
		if(alb != null) {
			for(int i = 0; i < alb.getSize(); i++) {
				lm.addElement(alb.getPhoto(i).getNom());
			}		
		}
	}
}
