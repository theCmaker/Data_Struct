interface Liste {
  public void afficher();
  public boolean estVide();
  public boolean estPleine();
  public int taille();
  public void insererDebut(Object o);
  public void insererFin(Object o);
  public void inserer(Object o, int i); // Insère à la i-eme position
  public void supprimerDebut();
  public void supprimerFin();
  public void supprimer(int i); // Supprime le i-eme élément
  public Object tete();
  public Object element(int i); // Renvoie l'élément à la position i
  public Object suivant(int i); // Renvoie l'élément i+1
  public Object precedent(int i);
  public Object[] elements(); // Renvoie un tableau d'object de la liste
}
