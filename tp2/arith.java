import java.util.Stack;
import java.util.Vector;
class arith {
  public static StringBuilder cleanSpaces(StringBuilder s){
    StringBuilder sb = new StringBuilder(s);
    for(int i=0; i<sb.length(); ++i){
      if (sb.charAt(i)==' '){
        int j = i+1;
        while(j < sb.length() && sb.charAt(j) == ' '){j++;}
        sb.delete(i+1,j);
      }
    }
    return sb;
  }

  public static StringBuilder getPolo(StringBuilder s){
    if (s.length() == 0) {
      return s;
    }
    s.deleteCharAt(0); //Supprimer la première parenthèse
    StringBuilder g = null;
    StringBuilder d = null;
    Character o = null;
    if (s.charAt(0) == '(') {
      g = getPolo(s);//récupère opérande gauche
    }else{
      g = stringOfInt(s);
      s.delete(0,g.length());
    }
    o = s.charAt(0);//récupère opérateur
    s.deleteCharAt(0);
    if (s.charAt(0) == '(') {
      d = getPolo(s);//récupère opérande droite
    }else{
      d = stringOfInt(s);
      s.delete(0,d.length());
    }
    s.deleteCharAt(0); //Supprimer la parenthèse fermante
    StringBuilder res = new StringBuilder(g);
    res.append(" "+d.toString());
    res.append(" "+o.toString()+" ");
    return cleanSpaces(res);
  }
  public static StringBuilder stringOfInt(StringBuilder s){
    int i = 0;
    while (s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != '*' && s.charAt(i) != '/' && s.charAt(i) != '%' && s.charAt(i) != ')') {
      ++i;
    }
    return new StringBuilder(s.substring(0,i));
  }
  public static int evalPolo(StringBuilder s){
    Stack<Integer> p = new Stack<Integer>();
    Vector<Integer> spaces = new Vector<Integer>();
    for (int i=0; i<s.length(); ++i){
      if (s.charAt(i) == ' '){
        spaces.add(i);
      }
    }
    char c;
    int espace;
    int i = 0;
    while (i < s.length()){
      espace = spaces.get(0);
      spaces.remove(0);
      c = s.charAt(i);
      if (c <= '9' && c >= '0'){
        p.push(Integer.parseInt(s.substring(i,espace)));
        i = espace+1;
      }else{
        int x = p.pop();
        int y = p.pop();
        switch(c){
          case '+':
            p.push(x+y);
            break;
          case '-':
            p.push(x-y);
            break;
          case '*':
            p.push(x*y);
            break;
          case '/':
            p.push(x/y);
            break;
          case '%':
            p.push(x%y);
            break;
        }
        i = espace+1;
      }
    }
    return p.pop();
  }
  public static int evalExp(StringBuilder s, boolean polo){
    return (polo)? evalPolo(s) : evalPolo(getPolo(s)); //Si polo est à true, s est en notation polonaise sinon en notation infixe.
  }
  public static void main (String[] args) {
    StringBuilder test1 = new StringBuilder("3 5 2 3 * + * 4 + 2 * ");
    StringBuilder test2 = new StringBuilder("(((3*(5+(2*3)))+4)*2)");
    System.out.println(test1 + " : " + evalExp(test1,true));
    System.out.println(test2 + " : " + evalExp(test2,false));
  }
}
