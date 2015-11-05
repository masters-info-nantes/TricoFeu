package TricoFeu;

import TricoFeu.Feu.EtatFeu;

public class Intersection extends Thread{

	private RoutePrincipale routeMajeur;
	private RouteSecondaire routeMineur;
	
	public Intersection() {
		this.routeMajeur = new RoutePrincipale(new Feu(EtatFeu.Vert));
		this.routeMineur = new RouteSecondaire(new Feu(EtatFeu.Rouge),routeMajeur);
		this.routeMajeur.setPointSynchro(routeMineur);
	}

	public void run() {
		super.run();
		
		//lancement des routes
		routeMajeur.start();
        routeMineur.start();

        //synchronisation des routes
        synchronized (routeMajeur) {
            try {
				routeMajeur.open();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            routeMajeur.notify();
        }
        
        try {
            routeMajeur.join();
            routeMineur.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	public RoutePrincipale getRouteMajeur() {
		return routeMajeur;
	}
	
	public RouteSecondaire getRouteMineur() {
		return routeMineur;
	}
	
	

}
