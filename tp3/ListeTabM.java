class ListeTabM implements Liste {
  protected int MAX;
  protected int taille;
  protected Object[] tDonnees; // Tableau contenant les données
  protected int[] tSuivant; // Contient les indices suivants
  protected int[] tPrecedent;
  protected int debut; // Indice du premier élément
  protected int fin;

  public ListeTabM (int max) {
    this.MAX = max;
    this.taille = 0;
    this.tDonnees = new Object[max];
    this.tSuivant = new int[max];
    this.tPrecedent = new int[max];
    this.debut = 0;
    this.fin = 0;
  }

  public boolean estVide() {
    return this.taille == 0;
  }

  public boolean estPleine(){
    return this.taille == this.MAX;
  }

  public int taille() {
    return this.taille;
  }

  public void insererDebut(Object o) {
    if (this.estPleine()) {
     System.err.println("La liste est pleine");
    }else {
      tDonnees[this.taille] = o;
      tPrecedent[this.taille] = -1;
      if (this.estVide()) {
        tSuivant[this.taille] = -1;
        this.fin = this.taille;
      }else {
        tSuivant[this.taille] = this.debut;
        tPrecedent[this.debut] = this.taille;
      }
      this.debut = this.taille;
      this.taille = this.taille +1;
    }
  }

  public void insererFin(Object o) {
    if (this.estPleine()) {
     System.err.println("La liste est pleine");
    }else {
      tDonnees[this.taille] = o;
      tSuivant[this.taille] = -1;
      if (this.estVide()) {
        tSuivant[this.taille] = -1;
      }else {
        tPrecedent[this.taille] = this.fin;
        tSuivant[this.fin] = this.taille;
      }
      this.fin = this.taille;
      this.taille = this.taille +1;
    }
  }

  public void inserer(Object o, int i ) {
    if (this.estPleine()) {
     System.err.println("La liste est pleine");
    }else {
      int cpt = 1;
      int courant = this.debut;
      while (cpt != i-1) {
        courant = tSuivant[courant];
        cpt = cpt +1;
      }
      tDonnees[this.taille] = o;
      tSuivant[this.taille] = tSuivant[courant];
      tPrecedent[tSuivant[courant]] = this.taille;
      tSuivant[courant] = this.taille;
      tPrecedent[this.taille] = courant;
      this.taille = this.taille +1;
    }
  }

  public void supprimerDebut() {
    if (this.taille -1 == debut) {
      tPrecedent[tSuivant[this.debut]] = -1;
      debut = tSuivant[this.debut];
    }else{
      int tmp = tSuivant[debut];
      tPrecedent[tSuivant[this.debut]] = -1;
      tDonnees[debut] = tDonnees[taille-1];
      tSuivant[debut] = tSuivant[taille-1];
      tPrecedent[debut] = tPrecedent[taille-1];
      tSuivant[tPrecedent[taille-1]] = debut;
      tPrecedent[tSuivant[taille-1]] = debut;
      debut = tmp;
    }
    this.taille = this.taille-1;
  }

  public void supprimerFin() {
    if (this.taille-1 == fin) {
      tSuivant[tPrecedent[this.fin]] = -1;
      fin = tPrecedent[this.fin];
    }else{
      int tmp = tPrecedent[fin];
      tSuivant[tPrecedent[this.fin]] = -1;
      tDonnees[fin] = tDonnees[taille-1];
      tSuivant[fin] = tSuivant[taille-1];
      tPrecedent[fin] = tPrecedent[taille-1];
      tSuivant[tPrecedent[taille-1]] = fin;
      tPrecedent[tSuivant[taille-1]] = fin;
      fin = tmp;
    }
    this.taille = this.taille - 1;
  }



  public void supprimer(int i) {
    if (this.estVide()) {
      System.err.println("La liste est vide");
    }else {
      int cpt = 1;
      int courant = this.debut;
      while (cpt != i) {
        courant = tSuivant[courant];
        cpt = cpt + 1;
      }
      if (courant == fin) {
        this.supprimerFin();
      }else if (courant == debut){
        this.supprimerDebut();
      }else{
        if (courant == taille-1) {
          tSuivant[tPrecedent[courant]] = tSuivant[courant];
          tPrecedent[tSuivant[courant]] = tPrecedent[courant];
        }else{
          tSuivant[tPrecedent[courant]] = tSuivant[courant];
          tPrecedent[tSuivant[courant]] = tPrecedent[courant];
          tDonnees[courant] = tDonnees[this.taille-1];
          tSuivant[courant] = tSuivant[this.taille-1];
          tPrecedent[courant] = tPrecedent[this.taille-1];
          tSuivant[tPrecedent[taille-1]] = courant;
            tPrecedent[tSuivant[taille-1]] = courant;
        }
        if (this.taille-1 == this.fin) {
          this.fin = courant;
        }else if (this.taille -1 == this.debut) {
          this.debut = courant;
        }
        this.taille = this.taille -1;
      }
    }
  }

  public Object tete() {
    return tDonnees[debut];
  }

  public Object element(int i) {
    int cpt = 1;
    int courant = this.debut;
    while (cpt != i) {
      courant = tSuivant[courant];
      cpt = cpt + 1;
    }
    return tDonnees[courant];
  }

  public Object suivant(int i) {
    int cpt = 1;
    int courant = this.debut;
    while (cpt != i+1) {
      courant = tSuivant[courant];
      cpt = cpt + 1;
    }
    return tDonnees[courant];
  }

  public Object precedent(int i) {
    int cpt = 1;
    int courant = this.debut;
    while (cpt != i-1) {
      courant = tSuivant[courant];
      cpt = cpt + 1;
    }
    return tDonnees[courant];
  }

  public void afficher(){
    System.out.print("Données : ");
    for (int k = 0; k < tDonnees.length ;k++ ) {
      System.out.print(tDonnees[k]+" ");
    }
    System.out.println();

    System.out.print("Suivant : ");
    for (int k = 0; k < tSuivant.length ;k++ ) {
      System.out.print(tSuivant[k]+" ");
    }
    System.out.println();

    System.out.print("Precedent : ");
    for (int k = 0; k < tPrecedent.length ;k++ ) {
      System.out.print(tPrecedent[k]+" ");
    }
    System.out.println();
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    int courant = this.debut;
    while (courant != -1) {
      s.append(tDonnees[courant]+" ");
      courant = tSuivant[courant];
    }
    s.append("\n");
    return s.toString();
  }

  public Object[] elements(){
    Object[] res = new Object[this.taille];
    int i = 0;
    int courant = debut;
    while (courant != -1) {
      res[i] = tDonnees[courant];
      courant = tSuivant[courant];
      i = i+1;
    }
    return res;
  }

}
