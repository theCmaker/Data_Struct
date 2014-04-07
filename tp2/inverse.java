import java.util.Stack;
class inverse {
  public static void main (String[] args) {
    Object[] tab = new Object[50];
    for (int i = 0;i < 50 ; ++i ) {
      tab[i] = new Integer(i);
    }

    for (int i = 0; i<tab.length; ++i) {
      System.out.print(tab[i]+" ");
    }
    System.out.println();

    Stack<Object> pile = new Stack<Object>();
    for (int i = 0; i<tab.length; ++i) {
      pile.push(tab[i]);
    }

    for (int i = 0 ;i < tab.length; ++i) {
      tab[i] = pile.pop();
    }

    for (int i = 0; i<tab.length; ++i) {
      System.out.print(tab[i]+" ");
    }
    System.out.println();

  }
}
