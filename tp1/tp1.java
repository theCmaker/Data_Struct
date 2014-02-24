class PasUnChiffre extends Exception{};

class nombreA2Chiffres{
  private int dizaines;
  private int unites; 
  
  public nombreA2Chiffres() throws PasUnChiffre{
    this.setDizaines(0);
    this.setUnites(0);
  }
  public nombreA2Chiffres(int u, int d) throws PasUnChiffre{
    this.setDizaines(d);
    this.setUnites(u);
  }
  public nombreA2Chiffres(int n) throws PasUnChiffre{
    this.setDizaines(n/10);
    this.setUnites(n%10);
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
	j = a[L-i-1]+b[l-i-1]+tmp;
	System.out.println(j);
	r.arith(j/10,j%10,false);
	tmp = r.getDizaines();
	res[L-i] = r.getUnites();
      }
      else{
	j = a[l-i-1]+b[L-i-1]+tmp;
	System.out.println(j);
	r.arith(j/10,j%10,false);
	tmp = r.getDizaines();
	res[L-i] = r.getUnites();
      }
    }
    for (i=0; i<L-l; i++){
      if(a.length == L){
	j = a[L-l-1-i]+tmp;
	System.out.println(j);
	r.arith(j/10,j%10,false);
	tmp = r.getDizaines();
	res[L-l-i] = r.getUnites();
      }
      else{
	j = b[L-l-1-i]+tmp;
	System.out.println(j);
	r.arith(j/10,j%10,false);
	tmp = r.getDizaines();
	res[L-l-i] = r.getUnites();
      }
    }
    res[0] = r.getDizaines();
    for (i=0; i<L+1; i++){
      System.out.print(res[i]);
    }
    System.out.println(" ");
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
    int[] taba = {1,2,3,4,5};
    int[] tabb = {1,2,6};
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