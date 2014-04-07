class PasUnChiffre extends Exception{};

class nombreA2Chiffres{
  private int dizaines;
  private int unites; 
  
  public nombreA2Chiffres() throws PasUnChiffre{
    this(0,0);
  }
  public nombreA2Chiffres(int u, int d) throws PasUnChiffre{
    this.dizaines = d;
    this.unites = u;
  }
  public nombreA2Chiffres(int n) throws PasUnChiffre{
    this(n/10, n%10);
  }
  private void setUnites(int u) throws PasUnChiffre{
    if (u<10){
      this.unites = u;
    }
    else{
      throw new PasUnChiffre();
    }
  }
  private void setDizaines(int d) throws PasUnChiffre{
    if (d<10){
      this.dizaines = d;
    }
    else{
      throw new PasUnChiffre();
    }
  }
  public int getUnites(){
    return this.unites;
  }
  public int getDizaines(){
    return this.dizaines;
  }
  public void arith (int x, int y, boolean b) throws PasUnChiffre{
    if (x<10 && y<10)
    {
      if (b){
	this.setDizaines((x*y)/10);
	this.setUnites((x*y)%10);
      }
      else{
	this.setDizaines((x+y)/10);
	this.setUnites((x+y)%10);
      }
    }
    else{
      throw new PasUnChiffre();
    }
  }
  public String toString(){
    return "Dizaines : "+this.dizaines+" ; Unités : "+this.unites;
  }
  public static void somme (int[] a, int[] b) throws PasUnChiffre{
    nombreA2Chiffres r = new nombreA2Chiffres();
    int l = Math.min(a.length, b.length);
    int L = Math.max(a.length, b.length);
    int[] res = new int[L+1];
    int tmp = 0;
    int i, j;
    for (i=0; i<l; i++){
      if(a.length == L){
	r.arith(a[L-i-1],b[l-i-1],false);
	r.setUnites(r.getUnites()+tmp);
	if(r.getUnites()>=10){
	  r.setDizaines(r.getDizaines()+1);
	  r.setUnites(r.getUnites()%10);
	}
	tmp = r.getDizaines();
	res[L-i] = r.getUnites();
      }
      else{
	r.arith(b[L-i-1],a[l-i-1],false);
	r.setUnites(r.getUnites()+tmp);
	if(r.getUnites()>=10){
	  r.setDizaines(r.getDizaines()+1);
	  r.setUnites(r.getUnites()%10);
	}
	tmp = r.getDizaines();
	res[L-i] = r.getUnites();
      }
    }
    for (i=0; i<L-l; i++){
      if(a.length == L){
	r.arith(a[L-l-1-i],tmp,false);
	tmp = r.getDizaines();
	res[L-l-i] = r.getUnites();
      }
      else{
	r.arith(b[L-l-1-i],tmp,false);
	tmp = r.getDizaines();
	res[L-l-i] = r.getUnites();
      }
    }
    res[0] = r.getDizaines();
    for (i=0; i<L+1; i++){
      System.out.print(res[i]);
    }
    System.out.println("");
  }
}

public class ex1{
  public static void main(String[] args) throws PasUnChiffre{
    System.out.println("Hello World!");
    int a = 2;
    int b = 8;
    int c = 12;
//     int[] taba = new int[5];
//     int[] tabb = new int[3];
    int[] taba = {9,9,9,4,3};
    int[] tabb = {9,9,9};
    nombreA2Chiffres.somme(taba,tabb);
    nombreA2Chiffres d = new nombreA2Chiffres();
    nombreA2Chiffres e = new nombreA2Chiffres();
    nombreA2Chiffres f = new nombreA2Chiffres();
    d.arith(a,b,false);
    System.out.println(d);
    e.arith(a,b,true);
    System.out.println(e);
    try{
      f.arith(a,c,false);
    }
    catch(PasUnChiffre exc){
      System.out.println("Erreur d'argument");
    }
    System.out.println(f);
  }
}