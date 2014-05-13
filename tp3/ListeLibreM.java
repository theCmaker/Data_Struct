class ListeLibreM extends ListeTabM{
  private ListeTabM libre;

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
    this.tPrecedent[position] = -1;
    if (this.estVide()) {
      this.liste[position] = o;
      this.tSuivant[position] = -1;
    }else {
      this.liste[position] = o;
      this.tSuivant[position] = this.debut;
      this.tPrecedent[debut] = position;
    }
    this.taille = this.taille+1;
  }

  public void liberer(int i) { // Libère la i-eme cellule de la mémoire
    if (i == this.debut) {
      this.supprimerDebut();
    }else if (i == this.fin) {
      this.supprimerFin();
    }else {
      int precedent = this.tPrecedent[position];
      int suivant = this.tSuivant[position]
      this.tSuivant[precedent] = suivant;
      this.tPrecedent[suivant] = precedent;
    }
    this.libre.insererDebut(new Integer(i));
  }
}