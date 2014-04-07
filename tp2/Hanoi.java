import java.util.Stack;
class Hanoi {
  public static void deplacer(MyStack p1, MyStack p2){
    System.out.println(p1.peek() + " est déplacé de " + p1.getName() + " vers " + p2.getName());
    p2.push(p1.pop());
  }
  public static void hanoi(MyStack depart, MyStack pivot, MyStack arrivee, int n){
    if (n == 1) {
      deplacer(depart,arrivee);
    }else{
      hanoi(depart,arrivee,pivot,n-1);
      deplacer(depart,arrivee);
      hanoi(pivot,depart,arrivee,n-1);
    }
  }
  public static void main (String[] args) {
    MyStack depart = new MyStack("Départ");
    MyStack pivot = new MyStack("Pivot");
    MyStack arrivee = new MyStack("Arrivée");
    int n = 5;
    for (int i = n; i > 0; --i){
      depart.push(new Integer(i));
    }
    System.out.println(depart);
    hanoi(depart,pivot,arrivee,n);
    System.out.println(arrivee);
  }
}
