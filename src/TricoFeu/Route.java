package TricoFeu;

import TricoFeu.Feu.EtatFeu;

public class Route extends Thread{
	
	private Feu feu;
	private Object ptSynchro;
	
	public Route(Feu feu,Object ptSynchro){
		this.feu = feu;
		this.ptSynchro = ptSynchro;
	}
	
	public Feu getFeu() {
		return feu;
	}

	public void setFeu(Feu feu) {
		this.feu = feu;
	}
	
	public void run(){
		while(true){
			synchronized (this) {
                try {
                	System.out.println(getClass().getName()+" start wait");
                    wait();
                    System.out.println(getClass().getName()+"  end  wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
	            //lance la simulation du traffic
	            try {
					action();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	// Lance le traffic sur la route = feu vert
	public void open() throws InterruptedException {
		synchronized (ptSynchro) {
			ptSynchro.notify();
		}
		synchronized (this) {
			wait();
		}
		feu.setEtat(EtatFeu.Vert);
	}
	
	// ferme le traffic de la route = feu rouge
	public void close() throws InterruptedException{
		feu.setEtat(EtatFeu.Orange);
		sleep(2000l);
		feu.setEtat(EtatFeu.Rouge);
		synchronized (ptSynchro) {
			ptSynchro.notify();
		}
		
	}

	public void action() throws InterruptedException {
		//System.out.println(getClass().getName()+" action");
		if(feu.getEtat() == EtatFeu.Vert) {
			this.close();
		} else {
			this.open();
		}
	}
	
	public Object getPointSynchro() {
		return this.ptSynchro;
	}
	
	public void setPointSynchro(Object ptSynchro) {
		this.ptSynchro = ptSynchro;
	}
}
