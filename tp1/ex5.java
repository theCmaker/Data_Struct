import java.util.ArrayList;

class ex5{
  public static void q1(String s){
    int e = 0, t = 0, eol = 0;
    for (int i = 0; i < s.length(); ++i){
      if (s.charAt(i) == ' '){
	++e;
      }else if (s.charAt(i) == '\t'){
	++t;
      }else if (s.charAt(i) == '\n'){
	++eol;
      }
    }
    System.out.println("Spaces : "+e+"\nTabs : "+t+"\nEnds of line : "+eol);
  }
  public static String q2(String s){
    StringBuilder sb = new StringBuilder(s);
    for(int i=0; i<sb.length(); ++i){
      if (sb.charAt(i)==' '){
	int j = i+1;
	while(j < sb.length() && sb.charAt(j) == ' '){j++;}
	sb.delete(i+1,j);
      }
    }
    return sb.toString();
  }
  public static int q3(String s, char c){
    int cpt;
    int e = 0, t = 0, eol = 0;
    for (int i = 0; i < s.length(); ++i){
      if (s.charAt(i) == ' '){
	++e;
      }else if (s.charAt(i) == '\t'){
	++t;
      }else if (s.charAt(i) == '\n'){
	++eol;
      }
    }
    return (c == 'c')?s.length():(c =='l')?eol+1:e+1;
  }
  public static void q4(String s){
    int length = 0;
    String str = q2(s);//nettoie les espaces multiples
    ArrayList<Integer> carac = new ArrayList<Integer>(str.length()+1);//tableau contenant le nombre de mots de longueur l'indice de la case
    System.out.println("length : "+carac.size());
    for (int i = 0; i <= str.length(); ++i){ //initialisation du tableau
      carac.add(0);
    }
    for (int i = 0; i < str.length(); ++i){ //parcours de la chaîne et indexage du nombre de lettres
      if (str.charAt(i) == ' '){
	carac.set(length, carac.get(length)+1);
	length = 0;
      }else{
	length++;
      }
    }
    if (length != 0){ //dernier mot ? (chaîne non terminée par une espace)
      carac.set(length, carac.get(length)+1);
    }
    //Calculs pour histogramme
    int last = carac.size()-1; //dernier baton de l'histogramme
    while (last >= 0 && carac.get(last) == 0){
      last--;
    }
    int max = 0;
    for (int i = 0; i < carac.size(); ++i){ //valeur maximum pour le diagramme.
      max = (carac.get(i) > max)? carac.get(i) : max;
    }
    char[][] hist = new char[max+2][last+1];
    for (int i = 0; i <= last; ++i){//abscisses avec valeurs
      hist[max][i] = '-';
      hist[max+1][i] = (char) (i+48);
    }
    for (int i = 0; i <= last; ++i){
      for (int j = 0; j < max; ++j){
	hist[j][i] = (max-carac.get(i) > j)? ' ' : '#';
      }
    }
    for (int i = 0; i <= max+1; ++i){
      for (int j = 0; j <= last; ++j){
	System.out.print(hist[i][j]);
      }
      System.out.println();
    }
    
  }
  
  
  public static void main(String[] args){
    System.out.println(q2("Patate    toto"));
    q4("Pat ate toto Bonjour Silence t tam tom tyt");
    
  }
}