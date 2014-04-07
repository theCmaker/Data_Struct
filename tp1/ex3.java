import java.util.Vector;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;
enum Dir{LEFT,RIGHT};

// class RubanPleinException extends Exception;
class ForbiddenMoveLeftException extends Exception{};
class RubanPleinException extends Exception{};
class ForbiddenMoveRightException extends Exception{};

class Instruction{
  private int n1; //n : test avec q
  private int n2; //n' : valeur à affecter à q en cas de test positif
  private char c1; //c : test valeur courante
  private char c2; //c' : caractère à écrire si test positif
  private Dir d; //d : sens de déplacement
  Instruction(char c1, int n1, int n2, char c2, Dir d){
    if (n1 > 1 && n2 > 1 && (c1 == '0' || c1 == '1' || c1 == 'B' || c1 == '#') && (c2 == '0' || c2 == '1' || c2 == 'B' || c2 == '#')){
      this.n1 = n1;
      this.n2 = n2;
      this.c1 = c1;
      this.c2 = c2;
      this.d = d;
    }else{
      throw new IllegalArgumentException("Ce n'est pas une instruction valide");
    }
  }
  public int getN1(){
    return this.n1;
  }
  public int getN2(){
    return this.n2;
  }
  public char getC1(){
    return this.c1;
  }
  public char getC2(){
    return this.c2;
  }
  public Dir getD(){
    return this.d;
  }
}

class Programme{
  private Vector<Instruction> instr; //tableau d'instructions
  private char[] ruban; //ruban de la machine de taille MAX
  private final int MAX;
  private int q; //état courant du programme
  Programme(int MAX, char[] ruban){
    this(MAX);
    if (ruban.length>MAX){
      throw new IllegalArgumentException("La taille du ruban excède la taille maximale !");
    }else{
      for (int i = 0; i<ruban.length; i++){
	this.ruban[i] = ruban[i];
      }
    }
  }
  Programme(int MAX){
    this.MAX = MAX;
    this.instr = new Vector<Instruction>();
    this.q = 2;
    this.ruban = new char[this.MAX];
    for (int i = 0; i<ruban.length; i++){
      this.ruban[i] = 0;
    }
  }
  Programme(){
    this(256);
  }
  Programme(int MAX, String path){
    this(MAX);
    Scanner s = null;
    try{
      s = new Scanner(new BufferedReader(new FileReader(path)));
    }
    catch(IOException e){
      System.err.println("IO Exception : "+e.getMessage())
    }
    finally{
      if (s != null){
	s.close();
      }
    }
    
  }
  public boolean setInstructions(Vector<Instruction> instr){
    boolean res = false;
    if (instr != null){
      this.instr = instr;
      res = true;
    }
    return res;
  }
  public void setQ(int q){
    this.q = q;
  }
  public void executer() throws ForbiddenMoveLeftException, ForbiddenMoveRightException, RubanPleinException{
    int i = 0; //compteur
    int index = 0;//position sur le ruban
    while(i<this.instr.size()){
      if (index == ruban.length){
	throw new RubanPleinException();
      }else{
	if (this.ruban[index] == this.instr.get(i).getC1() && this.q == this.instr.get(i).getN1()){
	  this.q = this.instr.get(i).getN2();
	  this.ruban[index] = this.instr.get(i).getC2();
	  if(this.instr.get(i).getD() == Dir.LEFT){
	    if(index > 0){
	      index--;
	    }else{
	      throw new ForbiddenMoveLeftException();
	    }
	  }else{
	    if(index < ruban.length-1){
	      index++;
	    }else{
	      throw new ForbiddenMoveRightException();
	    }
	  }
	  i = 0;
	}else{
	  i++;
	}
      }
    }
  }
}