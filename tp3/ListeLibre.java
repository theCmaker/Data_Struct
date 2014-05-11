class ListeLibre extends ListeTab{
  private ListeTab libre;

  public ListeLibre (int max) {
    super(max);
    this.libre = new ListeTab(max);
    for (int i=0;i<max;++i ) {
      this.libre.insererDebut(new Integer(i));
    }
  }

	public void allouer(Object o) { // Alloue une cellule pour stocker l'objet o
			int position = ((Integer) this.libre.tete()).intValue();
			this.libre.supprimerDebut();
			this.liste[position+1] = -1;
			if (this.estVide()) {
				this.liste[position] = o;
				this.liste[position+2] = -1;
			}else {
				this.liste[position] = o;
				this.liste[position+2] = this.debut;
				this.liste[this.debut+1] = position;
			}
			this.taille = this.taille+1;
		}

		public void liberer(int i) { // Libère la i-eme cellule de la mémoire
			int position = 3*i;
			if (position == this.debut) {
				this.supprimerDebut();
			}else if (position == this.fin) {
				this.supprimerFin();
			}else {
				int precedent = ((Integer) this.liste[position+1]).intValue();
				int suivant = ((Integer) this.liste[position+2]).intValue();
				this.liste[precedent+2] = suivant;
				this.liste[suivant+1] = precedent;
			}
		}
  
  public void densifierListe(){
		int premiereLibre = ((Integer) this.libre.tete()).intValue();
		Object o = null;
		while(premiereLibre < this.taille){
			o = this.liste[3*(this.taille-1)]; // On prend l'élément le plus à droite
			this.liberer(this.taille-1); // On libère La cellule
			this.allouer(o); // On ajoute à première libre
			premiereLibre = ((Integer) this.libre.tete()).intValue(); // On actualise la nouvelle tête
  }
}
