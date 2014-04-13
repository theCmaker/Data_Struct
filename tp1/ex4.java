import java.util.ArrayList;
//Tri Fusion

class ex4{
  public static ArrayList<Integer> fusionSort2(ArrayList<Integer> t1, ArrayList<Integer> t2){ //Sans tableaux intermédiaires
    int indexT1 = 0;
    int indexT2 = 0;
    ArrayList<Integer> res = new ArrayList<Integer>(); 
    for(int i = 0; i < t1.size()+t2.size(); ++i){
      if (indexT1 < t1.size() && indexT2 < t2.size()){ //il reste des cases non parcourues dans les 2 tableaux
	Integer min = (t1.get(indexT1).compareTo(t2.get(indexT2)) == -1)? t1.get(indexT1) : t2.get(indexT2);
	if (min == t1.get(indexT1)){
	  indexT1++;
	}else{
	  indexT2++;
	}
	res.add(min);
      }else if (indexT1 < t1.size()){ //le second tableau a été parcouru
	res.add(t1.get(indexT1));
	indexT1++;
      }else{ //le premier tableau a été parcouru
	res.add(t2.get(indexT2));
	indexT2++;
      }
    }
    return res;
  }
  
  public static ArrayList<Integer> fusionSort1(ArrayList<Integer> t1, ArrayList<Integer> t2){ //Avec tableaux intermédiaires
    ArrayList<Integer> T1 = new ArrayList<Integer>(t1);
    ArrayList<Integer> T2 = new ArrayList<Integer>(t2);
    ArrayList<Integer> res = new ArrayList<Integer>();
    for(int i = 0; i < t1.size()+t2.size(); ++i){
      if(T1.size() != 0 && T2.size() != 0){ //Les deux tableaux ne sont pas vides
        Integer min = (T1.get(0).compareTo(T2.get(0)) == -1)? T1.get(0) : T2.get(0);
        if (min == T1.get(0)){
          T1.remove(0);
        }else{
          T2.remove(0);
        }
        res.add(min);
      }else if(T1.size() != 0){ //Le premier tableau n'est pas vide
	res.add(T1.remove(0));
      }else{ //Le second tableau n'est pas vide
	res.add(T2.remove(0));
      }
    }
    return res;
  }
  
  public static void main (String[] args) {
    ArrayList<Integer> t1 = new ArrayList<Integer>();
    ArrayList<Integer> t2 = new ArrayList<Integer>();
    t1.add(2); t1.add(3); t1.add(7); t1.add(9); t1.add(11);
    t2.add(1); t2.add(4); t2.add(5); t2.add(8); t2.add(12);
    System.out.println("T1 : "+t1);
    System.out.println("T2 : "+t2);
    System.out.println("Tri Fusion 1 : "+fusionSort1(t1,t2));
    System.out.println("Tri Fusion 2 : "+fusionSort2(t1,t2));
    
  }
}
