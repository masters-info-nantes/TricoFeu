package TricoFeu;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

class WindowEventHandler extends WindowAdapter {
	  public void windowClosing(WindowEvent evt) {
	    System.exit(0);
	  }
}

@SuppressWarnings("serial")
public class TricoFeu extends JFrame{
	
	JLabel principaleBack;
	JLabel secondaireBack;
	static TricoFeu instance;
	
	public TricoFeu(){
		instance = this;
		
		Intersection intersection = new Intersection();
		
		//Partie Graphique
		this.setVisible(true);
		this.setBackground(Color.BLACK);
		this.setSize(600, 600);
		//this.setResizable(false);
		this.setTitle("TricoFeu");
        this.addWindowListener(new WindowEventHandler());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        principaleBack = new JLabel();
        principaleBack.setIcon(new ImageIcon("ressources/feuP-Vert.png"));
        this.add(principaleBack);
        principaleBack.setBounds(0, 0,600,600);
        
        secondaireBack = new JLabel();
        secondaireBack.setIcon(new ImageIcon("ressources/feuS-Rouge.png"));
        this.add(secondaireBack);
        secondaireBack.setBounds(0, 0,600,600);
        
        JLabel background = new JLabel();
    	background.setIcon(new ImageIcon("ressources/road.jpg"));
        this.add(background);
        background.setBounds(0, 0,600,600);
        
		//lance le traffic
		intersection.start();
		
		Thread test = new Thread() {
			//boolean voiture = false;
			public void run() {
				try {
					sleep(3000l);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				while(true) {
					synchronized (intersection.getRouteMineur()) {
						System.out.println("");
						System.out.println("TricoFeu notify");
						intersection.getRouteMineur().notify();
					}
					
					try {
						sleep(45000l);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		};
		test.start();
	}

	public static void main(String[] args) {
		new TricoFeu();
	}

}
