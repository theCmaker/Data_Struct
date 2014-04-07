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
    int index = 0;
    String str = q2(s);//nettoie les espaces multiples
    int[] carac = new int[str.length()];
    for (int i = 0; i < carac.size(); ++i){
      carac[i] = 0;
    }
    for (int i = 0; i < str.length(); ++i){
      if (str.charAt(i) == ' '){
	carac[i-index]++;
	index = i+1;
      }
    }
  }
  
  
  public static void main(String[] args){
    System.out.println(q2("Patate    toto"));
    
  }
}