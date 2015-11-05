package TricoFeu;

import javax.swing.ImageIcon;

public class RoutePrincipale extends Route {

	public RoutePrincipale(Feu feu, Object verrou) {
		super(feu,verrou);
	}
	
	public RoutePrincipale(Feu feu) {
		this(feu,null);
	}
	
	public void open() throws InterruptedException{
		super.open();
		System.out.println("Principale - feu VERT");
		TricoFeu.instance.principaleBack.setIcon(new ImageIcon("ressources/feuP-Vert.png"));
	}
	
	public void close() throws InterruptedException{
		System.out.println("Principale - feu ORANGE");
		TricoFeu.instance.principaleBack.setIcon(new ImageIcon("ressources/feuP-Orange.png"));
		super.close();
		System.out.println("Principale - feu ROUGE");
		TricoFeu.instance.principaleBack.setIcon(new ImageIcon("ressources/feuP-Rouge.png"));
		sleep(10000l);
		synchronized (this) {
			notify();
		}
	}

	public void run() {
		System.out.println("Principale - Run");
		super.run();
	}
}
