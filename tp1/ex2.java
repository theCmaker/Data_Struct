import java.util.*;

public class ex2{
  public static void main(String[] args){
    int n=40, m=7, i, j;
    LinkedList<Integer> vivants = new LinkedList<Integer>();
    LinkedList<Integer> morts = new LinkedList<Integer>();
    for (i=0; i<n; i++){
      vivants.add(i, i);
    }
    i = 0;
    while (!vivants.isEmpty()){
      j = vivants.get((i+m)%vivants.size());
      i = (i+m)%vivants.size();
      morts.addLast(j);
      vivants.remove(i);
    }
    ListIterator litr = morts.listIterator();
    while(litr.hasNext()){
      Object element = litr.next();
      System.out.println("Tué : "+element);
    }
    //Note : on tue en comptant les sauts effectués entre les personnes. Lorsqu'une personne se fait tuer, son emplacement disparaît et les index des personnes suivantes sont mis à jour.
  }
}