import java.lang.StringBuilder;
class IllegalHourException extends Exception{};
class ex6 {
  enum Day{
    Lundi,
    Mardi,
    Mercredi,
    Jeudi,
    Vendredi,
    Samedi,
    Dimanche
  }
  class Evenement{
    String nom;
    String detail;
    Evenement(){
      this.nom = "";
      this.detail="";
    }
    Evenement(String nom, String detail){
      this.nom = nom;
      this.detail = detail;
    }
    public String toString(){
      return this.nom + " : " + this.detail;
    }
    public String shortView(){
      if (this.nom.length() >= 8){
        return this.nom.substring(0,8);
      }else{
        return (this.nom+"        ").substring(0,8);
      }
    }
  }
  Evenement[][] semaine;
  ex6(){
    this.semaine = new Evenement[10][7];
    for (int i = 0 ; i < 10 ; ++i) {
      for (int j = 0 ; j < 7 ; ++j) {
        this.semaine[i][j] = new Evenement();
      }
    }
  }
  public void add(String name, String detail, Day day, int hour) throws IllegalHourException {
    if (hour >= 8 && hour < 18){
      int dayIndex = 0;
      switch(day){
        case Lundi:
          dayIndex = 0;
          break;
        case Mardi:
          dayIndex = 1;
          break;
        case Mercredi:
          dayIndex = 2;
          break;
        case Jeudi:
          dayIndex = 3;
          break;
        case Vendredi:
          dayIndex = 4;
          break;
        case Samedi:
          dayIndex = 5;
          break;
        case Dimanche:
          dayIndex = 6;
          break;
      }
      this.semaine[hour-8][dayIndex] = new Evenement(name,detail);
    }else{
      throw new IllegalHourException();
    }
  }

  void getDay(Day day, int hour) throws IllegalHourException{
    if (hour >= 8 && hour < 18){
      int dayIndex = 0;
      switch(day){
        case Lundi:
          dayIndex = 0;
          break;
        case Mardi:
          dayIndex = 1;
          break;
        case Mercredi:
          dayIndex = 2;
          break;
        case Jeudi:
          dayIndex = 3;
          break;
        case Vendredi:
          dayIndex = 4;
          break;
        case Samedi:
          dayIndex = 5;
          break;
        case Dimanche:
          dayIndex = 6;
          break;
      }
      System.out.println(this.semaine[hour-8][dayIndex].toString());
    }else{
      throw new IllegalHourException();
    }
  }

  public String toString(){
    StringBuilder str = new StringBuilder();
    String[] days = {" Lundi  ", " Mardi  ", "Mercredi", " Jeudi  ", "Vendredi", " Samedi ", "Dimanche"};
    for (String i : days) {
      str.append("|"+i);
    }
    str.append("|\n");
    for (int i = 0 ; i < 10 ; ++i) {
      for (int j = 0 ; j < 7; ++j) {
        str.append("|"+this.semaine[i][j].shortView());
      }
      str.append("|\n");
    }
    return str.toString();
  }
  public static void main (String[] args) throws IllegalHourException{
    ex6 calendar = new ex6();
    calendar.add("Test","Detail test",Day.Lundi,8);
    calendar.add("Test","Detail test",Day.Lundi,14);
    calendar.add("Lama","Detail test",Day.Jeudi,17);
    calendar.add("Test ertyuio","Detail test",Day.Dimanche,10);
    System.out.println(calendar);
    calendar.getDay(Day.Jeudi,17);
  }
}
