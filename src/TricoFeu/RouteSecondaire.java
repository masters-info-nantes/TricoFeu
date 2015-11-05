package TricoFeu;

import java.awt.SecondaryLoop;

import javax.swing.ImageIcon;

public class RouteSecondaire extends Route {

	public RouteSecondaire(Feu feu, Object verrou) {
		super(feu,verrou);
	}

	public RouteSecondaire(Feu feu) {
		this(feu,null);
	}
	
	public void open() throws InterruptedException{
		super.open();
		System.out.println("Secondaire - feu VERT");
		TricoFeu.instance.secondaireBack.setIcon(new ImageIcon("ressources/feuS-Vert.png"));
		sleep(8000l);
		close();
	}
	
	public void close() throws InterruptedException{
		System.out.println("Secondaire - feu ORANGE");
		TricoFeu.instance.secondaireBack.setIcon(new ImageIcon("ressources/feuS-Orange.png"));
		super.close();
		System.out.println("Secondaire - feu ROUGE");
		TricoFeu.instance.secondaireBack.setIcon(new ImageIcon("ressources/feuS-Rouge.png"));
	}
	
	public void run() {
		System.out.println("Secondaire - Run");
		super.run();
	}
}
