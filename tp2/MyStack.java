import java.util.Stack;
class MyStack extends Stack<Integer> {
  private String name;
  MyStack(String name){
    super();
    this.name = name;
  }
  public String getName(){
    return this.name;
  }
}
