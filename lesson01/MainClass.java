package YakovlevAlexandr;
/*
* @author Yakovlev Alexandr
*
*
*
* */
public class MainClass {
    public static void main(String[] args) {
        Course c = new Course(1,2,3); // Создаем полосу препятствий
        Team team = new Team(111,112,113,114,30,25,20,15); // Создаем команду
        c.doIt(team); // Просим команду пройти полосу
        team.showResults(); // Показываем результаты
        team.printInfo();

    }
}


