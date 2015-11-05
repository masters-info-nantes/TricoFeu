package TricoFeu;

public class Feu {

	public static enum EtatFeu{
		Vert,
		Orange,
		Rouge
	};
	
	private EtatFeu etat;
	
	public Feu(EtatFeu etat) {
		this.setEtat(etat);
	}

	public EtatFeu getEtat() {
		return etat;
	}

	public void setEtat(EtatFeu etat) {
		this.etat = etat;
	}

}
