class TableauDynamique {
  Object[] tab; // Tableau qui va contenir les données
  int taille; // taille du tableau (nombre d'élément réellement insérés)
  int pasIncrementation; //Indique le pas d'incrémentation du tableau lorsqu'on tente d'ajouter un élément alors que le tableau est plein. (10 par défaut)

  public TableauDynamique(int t) {
    this.tab = new Object[t];
    this.taille = 0;
    this.pasIncrementation = 10;
  }

  public TableauDynamique(int t, int pas){
    this(t);
    this.pasIncrementation = pas;
  }

  public Object get(int i){
    if (i > this.size()) {
      throw new IllegalArgumentException("\n Indice invalide");
    }
    return this.tab[i];
  }

  public int size() {
    return this.taille;
  }

  public void add(Object val) {
    if (this.size() < this.tab.length) { // Si on a encore de la place, on ajoute l'élément et on incrémente la taille
      this.tab[this.size()] = val;
      taille++;
    }
    else { // Sinon, on doit copier les éléments dans un plus grand tableau avant de pouvoir ajouter
      Object[] nvTab = new Object[this.size() + this.pasIncrementation];
      for (int i = 0; i < this.tab.length; ++i) {
        nvTab[i] = this.get(i);
      }
      nvTab[this.size()] = val;
      taille++;
      this.tab = nvTab;
    }
  }

  public boolean contains(Object val) {
    boolean res = false;
    int j = 0;
    while (j < this.size() && !res) {
      if (this.get(j) == val) {
        res = true;
      }
      j++;
    }
    return res;
  }

  public void remove(Object val) {
    int j = 0;
    boolean res = false;
    while (j < this.size() && !res) {
      if (this.get(j) == val) {
        res = true;
      }
      j++;
    }
    if (res) { // Si on a trouvé l'élément, on doit décaler d'un cran vers la gauche tous les éléments du tableau à partir de l'indice de l'élément (inclus).
      for (int i = j-1; i < this.size() -1; ++i) {
        this.tab[i] = this.get(i+1);
      }
      this.taille--;
    }
  }

  public void reverse() {
    Object tmp;
    for (int i = 0; i<this.size()/2; ++i) {
      tmp = this.get(this.size()-1-i);
      this.tab[this.size()-1-i] = this.get(i);
      this.tab[i] = tmp;
    }
  }

  @Override
  public String toString() {
    StringBuilder s= new StringBuilder("Le tableau contient "+this.size()+" éléments : ");
    for (int i=0; i < this.size();++i) {
      s.append(this.get(i)+" ");
    }
    return s.toString();
  }
}
