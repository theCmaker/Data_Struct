class ListeTab implements Liste{
  protected int debut;
  protected int fin;
  protected int taille;
  protected int MAX;
  protected Object[] liste; // Organisé sous forme donnees-precedent-suivant-donnes ...

  public ListeTab (int max) {
    this.debut = 0;
    this.fin = 0;
    this.taille = 0;
    this.MAX = max;
    this.liste = new Object[3*max];
  }

  public boolean estVide() {
    return this.taille == 0;
  }

  public boolean estPleine() {
    return this.taille ==  this.MAX;
  }

  public int taille(){
    return this.taille;
  }

  public void insererDebut(Object o) {
    if (this.estPleine()) {
      System.err.println("La liste est pleine");
    }else {
      int position = 3*this.taille;
      this.liste[position] = o; // On ajoute l'élément à la case libre
      this.liste[position+1] = -1; // Il n'a pas de précédent
      if(this.estVide()){
        this.liste[position+2] = -1;
        this.fin = position;
      }else{
        this.liste[position+2] = this.debut; // Son suivant est l'ancien début
        this.liste[this.debut+1] = position; // Son précédent devient position
      }
      this.debut = position; // Le début est maintenant position
      this.taille = this.taille + 1; // On incrémente la taille
    }
  }

  public void insererFin(Object o) {
    if (this.estPleine()) {
      System.err.println("La liste est pleine");
    }else {
      int position = 3*this.taille;
      this.liste[position] = o;
      if(this.estVide()){
        this.liste[position+2] = -1;
        this.debut = position;
      }else{
        this.liste[position+1] = fin;
        this.liste[position+2] = -1;
        this.liste[this.fin+2] = position;
      }
      this.fin = position;
      this.taille = this.taille + 1;
    }
  }

  public void inserer(Object o, int i) {
    if (this.estPleine()) {
      System.err.println("La liste est pleine");
    }else {
      int cpt = 1;
      int courant = this.debut;
      int position = 3*this.taille;
      while (cpt != i-1) {
        courant = ((Integer) this.liste[courant+2]).intValue();
       cpt = cpt+1;
      }
      int suivantCourant = ((Integer) this.liste[courant+2]).intValue(); // Indice du suivant de courant
      this.liste[position] = o;
      this.liste[position+1] = courant;
      this.liste[position+2] = suivantCourant;
      this.liste[suivantCourant+1] = position;
      this.liste[courant+2] = position;
      this.taille = this.taille +1;
    }
  }

  public void supprimerDebut() {
    if (this.debut == 3*(this.taille-1)) {
      int suivantDebut = ((Integer) this.liste[this.debut+2]).intValue();
      this.liste[suivantDebut+1] = -1;
      this.debut = suivantDebut;
    }else {
      int suivantDebut = ((Integer) this.liste[this.debut+2]).intValue();
      int dernier = ((Integer) this.liste[3*(this.taille-1)]).intValue();
      int precedentDernier = ((Integer) this.liste[dernier+1]).intValue();
      this.liste[suivantDebut+1] = -1;
      this.liste[debut] = this.liste[dernier];
      this.liste[debut+1] = this.liste[dernier+1];
      this.liste[debut+2] = this.liste[dernier+2];
      this.liste[precedentDernier] = debut;
      this.debut = suivantDebut;
    }
    this.taille = this.taille -1;
  }

  public void supprimerFin() {
    if (this.fin == 3*(this.taille-1)) {
      int precedentFin = ((Integer) this.liste[this.fin+1]).intValue();
      this.liste[precedentFin+2] = -1;
      this.fin = precedentFin;
    }else {
      int precedentFin = ((Integer) this.liste[this.fin+1]).intValue();
      int dernier = ((Integer) this.liste[3*(this.taille-1)]).intValue();
      int precedentDernier = ((Integer) this.liste[dernier+1]).intValue();
      int suivantDernier = ((Integer) this.liste[dernier+2]).intValue();
      this.liste[precedentFin+2] = -1;
      this.liste[fin] = this.liste[dernier];
      this.liste[fin+1] = this.liste[dernier+1];
      this.liste[fin+2] = this.liste[dernier+2];
      this.liste[precedentDernier+2] = fin;
      this.liste[suivantDernier+1] = fin;
      this.fin = precedentFin;
    }
    this.taille = this.taille -1;
  }

  public void supprimer(int i) {
    int cpt = 1;
    int courant = debut;
    while (cpt != i) {
      courant = ((Integer) this.liste[courant+2]).intValue();
      cpt = cpt +1;
    }
    if (courant == debut) {
      this.supprimerDebut();
    }else if (courant == fin) {
      this.supprimerFin();
    }else{
      if (courant == 3*(this.taille-1)) {
        int precedentDernier = ((Integer) this.liste[courant+1]).intValue();
        int suivantDernier = ((Integer) this.liste[courant+2]).intValue();
        this.liste[precedentDernier+2] = suivantDernier;
        this.liste[suivantDernier+1] = precedentDernier;
      }else{
        int precedentCourant = ((Integer) this.liste[courant+1]).intValue();
        int suivantCourant = ((Integer) this.liste[courant+2]).intValue();
        int dernier = ((Integer) this.liste[3*(this.taille-1)]).intValue();
        int precedentDernier = ((Integer) this.liste[dernier+1]).intValue();
        int suivantDernier = ((Integer) this.liste[dernier+2]).intValue();
        this.liste[precedentCourant+2] = suivantCourant;
        this.liste[suivantCourant+1] = precedentCourant;
        this.liste[courant] = this.liste[dernier];
        this.liste[courant+1] = this.liste[dernier+1];
        this.liste[courant+2] = this.liste[dernier+2];
        this.liste[precedentDernier+2] = courant;
        this.liste[suivantDernier+1] = courant;
      }
      if (3*(this.taille-1) == this.fin) {
        this.fin = courant;
      }else if (this.taille -1 == this.debut) {
        this.debut = courant;
      }
      this.taille = this.taille -1;
    }
  }

  public Object tete() {
    return this.liste[debut];
  }

  public Object element(int i) {
    int cpt = 1;
    int courant = debut;
    while (cpt != i) {
      courant = ((Integer) this.liste[courant+2]).intValue();
      cpt = cpt +1;
    }
    return this.liste[courant];
  }

  public Object suivant(int i) {
    int cpt = 1;
    int courant = debut;
    while (cpt != i-1) {
      courant = ((Integer) this.liste[courant+2]).intValue();
      cpt = cpt+1;
    }
    return this.liste[courant+2];
  }

  public Object precedent(int i) {
    int cpt = 1;
    int courant = debut;
    while (cpt != i) {
      courant = ((Integer) this.liste[courant+2]).intValue();
      cpt = cpt+1;
    }
    return this.liste[courant+1];
  }

  public void afficher() {
    for(int i =0; i<this.liste.length;++i){
      System.out.print(this.liste[i]+" ");
    }
    System.out.println();
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    int courant = debut;
    while (courant != -1) {
      s.append(this.liste[courant]+" ");
      courant = ((Integer) this.liste[courant+2]).intValue();
    }
    s.append("\n");
    return s.toString();
  }

  public Object[] elements(){
    Object[] res = new Object[this.taille];
    int i = 0;
    int courant = debut;
    while (courant != -1) {
      res[i] = this.liste[courant];
      courant = ((Integer) this.liste[courant+2]).intValue();
      i = i+1;
    }
    return res;
  }


  public static void main (String[] args) {
    Liste l = new ListeTab(3);
    System.out.println("La liste "+(l.estVide() ?"est" :"n'est pas")+" vide");
    System.out.println("\nInsertion au début");
    l.insererDebut(new Integer(1));
    l.afficher();
    System.out.println(l);
    System.out.println("\nInsertion à la fin");
    l.insererFin(new Integer(3));
    l.afficher();
    System.out.println(l);
    System.out.println("\nInsertion au milieu");
    l.inserer(new Integer(2),2);
    l.afficher();
    System.out.println(l);
    System.out.println("La liste "+(l.estPleine() ? "est" : "n'est pas")+" pleine");
    //System.out.println("\nInsertion à la fin");
    //l.insererFin(new Integer(4));
    //l.afficher();

    System.out.println("\n Suppression");
    l.supprimer(2);
    l.afficher();
    System.out.println(l);
  }












}
