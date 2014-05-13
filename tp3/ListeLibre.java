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
      this.liste.insererDebut(new Integer(i));
    }
  
  public void densifierListe(){
    int[] carac = new int[this.MAX*3];
    Object[] elts = this.elements();
    int[] tabLibre = new int[this.taille];
    int[] tabOcc = new int[this.taille];
    int courant = debut;
    int debutLibre = 0;
    int debutOcc = 0;
    for (int i = 0; i<carac.length; ++i) {
      carac[i] = 0;
    }
    for (int i =0; i<elts.length; ++i) {
      tabLibre[i] = -1;
      tabOcc[i] = -1;
    }
    for (int i =0; i<tabLibre.length; ++i) { // On construit le tableau caractérisque des données
      int tmp = ((Integer) tabLibre[i]).intValue();
      carac[tmp] = 1;
    }
    for (int i = 0; i<this.taille; ++i) { //tabLibre contient les indices libres < this.taille
      if (carac[i] == 1) {
        tabLibre[debutLibre] = i;
        debutLibre = debutLibre +1;
      }
    }
    for (int i = this.taille;i<carac.length;++i ) { //tabOcc contient les indices occupées >= this.taille
      if (carac[i] == 0 && (i% this.taille) == 0) {
        tabOcc[i] = i;
        debutOcc = debutOcc +1;
      }
    }
    int j = 0;
    while (j < this.taille && tabLibre[j] != -1) { // On swap les positions des données
      int anciennePos = tabOcc[j];
      int nouvellePos = tabLibre[j];
      int precedentAnciennePos = ((Integer) this.liste[anciennePos+1]).intValue();
      int suivantAnciennePos = ((Integer) this.liste[anciennePos+2]).intValue();
      this.liste[nouvellePos] = this.liste[anciennePos];
      this.liste[nouvellePos+1] = this.liste[anciennePos+1];
      this.liste[nouvellePos+2] = this.liste[anciennePos+2];
      this.liste[precedentAnciennePos+2] = nouvellePos;
      this.liste[suivantAnciennePos+1] = nouvellePos;
      if (anciennePos == this.debut) {
        this.debut = nouvellePos;
      }
      if (anciennePos == this.fin) {
        this.fin = nouvellePos;
      }
    }
    // On remet la liste des cases libres à jour
    while (this.libre.estVide() == false) {
      this.libre.supprimerDebut();
    }
    for (int i = 3*this.taille; i< this.MAX*3; i = i+3 ) {
      this.libre.insererDebut(new Integer(i));
    }
  }
}
