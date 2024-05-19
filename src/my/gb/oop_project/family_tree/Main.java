package my.gb.oop_project.family_tree;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Family_tree ft = new Family_tree();
        ft.addHuman(new Human(1,"Диана","В","Игоревна", LocalDate.of(2005,6,19),null,0,null,2, 3));
        ft.addHuman(new Human(2,"Жанна","В","Владимировна", LocalDate.of(1980,10,8),null, 3,List.of(1,6),4,5));
        ft.addHuman(new Human(3,"Игорь","В","Сергеевич", LocalDate.of(1974,8,7),null, 2,List.of(1,6),0,0));
        ft.addHuman(new Human(4,"Людмила","М","Александровна", LocalDate.of(1950,1,5),null, 5, List.of(2),0,0));
        ft.addHuman(new Human(5,"Владимир","М","Аркадьевич", LocalDate.of(1947,2,4),null, 4, List.of(2),7,0));
        ft.addHuman(new Human(6,"Артем","В","Игоревич", LocalDate.of(2008,10,12),null,0,null,2,3));
        ft.addHuman(new Human(7,"Нина","М","Николаевна", LocalDate.of(1920,1,20),LocalDate.of(2000,6,22),0,List.of(5),0,0));
        //ft.print();     //печать всех данных
        //ft.printParents(5); //печать данных о родителях ребенка по id ребенка
        //ft.printParents(2); //печать данных о родителях ребенка по id ребенка
        //ft.find("Жанна","Владимировна", "В"); //печать данных о человеке по его ФИО
        //ft.find("Диана","Игоревна", "В");  //печать данных о человеке по его ФИО
        //ft.find("Игорь","Сергеевич", "В");  //печать данных о человеке по его ФИО
        //ft.find("Людмила","Александровна", "М"); //печать данных о человеке по его ФИО
        //ft.find("Нина","Николаевна", "М"); //печать данных о человеке по его ФИО
        //ft.find("Владимир","Аркадьевич", "М"); //печать данных о человеке по его ФИО
    }
}
