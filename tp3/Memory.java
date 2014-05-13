class MemoryErrorException extends Exception{};
class Memory {
  private int[] redirect;
  private int[] allocated;
  private Object[] data;
  private int length;
  private Liste free;
  private int allocLength;
  
  Memory(int capacity, int allocLength){
    this.redirect = new int[capacity];
    for (int i = 0; i < capacity; ++i) {
      this.redirect[i] = -1;
    }
    this.allocated = new int[capacity];
    this.data = new Object[capacity];
    this.free = new ListeTab(capacity);
    this.free.insererDebut(new Integer(0));
    this.length = capacity;
    this.allocLength = allocLength;
  }

  public int malloc(int n) throws MemoryErrorException{
    if (!this.free.estVide()){
      int firstFree = ((Integer) this.free.tete()).intValue();
      if (firstFree + n*this.allocLength <= this.length){
        this.free.supprimerDebut();
        this.allocated[firstFree] = n;
        int i = 0;
        while (i<this.length && this.redirect[i]!=-1) {
          ++i;
        }
        if (i == this.length){
          throw new MemoryErrorException();
        }else{
          this.redirect[i] = firstFree;
          if (firstFree + n*this.allocLength < this.length){
            this.free.insererDebut(firstFree+(n*this.allocLength));
          }
          return i;
        }
      }else{
        throw new MemoryErrorException();
      }
    }else{
      throw new MemoryErrorException();
    }
  }

  public void free(int A) throws MemoryErrorException{
    int index = this.redirect[A];
    int length = this.allocated[index];
    this.redirect[A] = -1;
    int i = index + allocated[index]*this.allocLength;
    int offset = index;
    int end;
    if (this.free.estVide()){
      end = this.length-length*this.allocLength;
    }else{
      end = ((Integer) this.free.tete()).intValue()-length*this.allocLength;
      this.free.supprimerDebut();
    }
    while (i < end) {
      int j = 0;
      while (j < this.length && this.redirect[j] != i){
        ++j;
      }
      this.allocated[offset]=this.allocated[this.redirect[j]];
      this.redirect[j]=offset;
      for (int k = i; k<i+this.allocated[i]*this.allocLength; ++k){
        this.data[offset]=this.data[k];
        offset++;
        i++;
      }
    }
    this.free.insererDebut(new Integer(end));
  }
  public static void main (String[] args) throws MemoryErrorException{
    Memory myMem = new Memory(5,1);
    int case1 = myMem.malloc(4);
    System.out.println(case1);
    int case2 = myMem.malloc(1);
    System.out.println(case2);
    myMem.free(case1);
    myMem.free(case2);
  }
}
