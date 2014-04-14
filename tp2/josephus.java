import java.util.LinkedList;

class josephus {
  josephus(){ //Génération de noms par défaut
    char[] array = {'a','e','i','o','u','y'};
    int k = 0;
    String[] names = new String[array.length*array.length];
    for (char i : array) {
      for (char j : array){
        names[k] = "t"+i+"t"+j;
        k++;
      }
    }
    exec(names,4);//Exécution
  }
  josephus(String[] names, int m){
    exec(names,m);
  }
  public static void exec (String[] names, int m){
    LinkedList<String> vivants = new LinkedList<String>();//Liste des vivants
    for (int i = 0 ; i < names.length ; ++i) {//ajout des personnes depuis le tableau
      vivants.add(names[i]);
    }
    while (vivants.size() != 0){//rotation de la file
      for (int i = 0 ; i < m-1 ; ++i) {
        vivants.add(vivants.remove());
      }
      System.out.println("Tué : " + vivants.remove());//suppression de la personne à tuer.
    }
  }
  public static void main (String[] args) {
    String[] names = {"toto","titi","tata","tutu","tete"};
    josephus test1 = new josephus(names, 3);
    josephus test2 = new josephus();
  }
}
