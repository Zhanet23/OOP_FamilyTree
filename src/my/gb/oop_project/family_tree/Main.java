package my.gb.oop_project.family_tree;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Family_tree ft = new Family_tree();
        Human h1 = new Human(1,"Нина","М","Николаевна", LocalDate.of(1920,1,20),LocalDate.of(2000,6,22));
        ft.add(h1);
        Human h2 = new Human(2,"Аркадий","М","Александрович", LocalDate.of(1924,11,17),LocalDate.of(1996,3,15));
        ft.add(h2); h1.setPartner(h2); h2.setPartner(h1); //запись информации о партнере

        Human h3 = new Human(3,"Елизавета","К","Петровна", LocalDate.of(1925,3,30),LocalDate.of(1975,5,15));
        ft.add(h3);
        Human h4 = new Human(4,"Александр","К","Михайлович", LocalDate.of(1927,7,10),LocalDate.of(1987,12,28));
        ft.add(h4); h3.setPartner(h4); h4.setPartner(h3); //запись информации о партнере

        Human h5 = new Human(5,"Владимир","М","Аркадьевич", LocalDate.of(1947,2,4),null,h1,h2);
        ft.add(h5);
        Human h6 = new Human(6,"Людмила","М","Александровна", LocalDate.of(1950,1,5),null,h3,h4);
        ft.add(h6); h5.setPartner(h6); h6.setPartner(h5); //запись информации о партнере

        Human h7 = new Human(7,"Евдокия","В","Федоровна", LocalDate.of(1948,9,16),LocalDate.of(2004,8,8));
        ft.add(h7);
        Human h8 = new Human(8,"Сергей","В","Алексеевич", LocalDate.of(1946,12,27),LocalDate.of(2022,4,8));
        ft.add(h8); h7.setPartner(h8); h8.setPartner(h7); //запись информации о партнере

        Human h9 = new Human(9,"Игорь","В","Сергеевич", LocalDate.of(1974,8,7),null,h7,h8);
        ft.add(h9);
        Human h10 = new Human(10,"Людмила","П","Сергеевна", LocalDate.of(1976,3,20),null,h7,h8);
        ft.add(h10);
        Human h11 = new Human(11,"Жанна","В","Владимировна", LocalDate.of(1980,10,8),null,h6,h5);
        ft.add(h11); h9.setPartner(h11); h11.setPartner(h9); //запись информации о партнере

        Human h12 = new Human(12,"Диана","В","Игоревна", LocalDate.of(2005,6,19),null,h11, h9);
        ft.add(h12);
        Human h13 = new Human(13,"Максим","В","Игоревич", LocalDate.of(2008,10,10), h11);
        ft.add(h13);
        // заполнение данных о детях
        h9.setChildren(List.of(h12,h13)); h11.setChildren(List.of(h12,h13)); // Игорь и Жанна -> Диана и Артем
        h7.setChildren(List.of(h9,h10)); h8.setChildren(List.of(h9,h10)); // Евдокия и Сергей -> Игорь, Людмила
        h5.setChildren(List.of(h11)); h6.setChildren(List.of(h11)); // Владимир и Людмила -> Жанна
        h1.setChildren(List.of(h5)); h2.setChildren(List.of(h5)); // Аркадий и Нина -> Владимир

        //-------------------------функционал------------------------------------------------
        //System.out.println(ft);  //печать всех данных в базе

        //печать данных о родителях ребенка по id ребенка
        //System.out.println(ft.findParentsByID(1));
        //System.out.println(ft.findParentsByID(11));
        //System.out.println(ft.findParentsByID(110)); ////такого человека нет в базе

        //печать данных о родителях ребенка по ФИО ребенка
        //System.out.println(ft.findParentsByFIO("Владимир", "Аркадьевич", "М"));
        //System.out.println(ft.findParentsByFIO("Евдокия", "Федоровна", "В")); //данных о родиелях нет
        //System.out.println(ft.findParentsByFIO("Анна", "Васильевна", "А"));//такого человека нет в базе

        //печать данных о человеке по ФИО
        //System.out.println(ft.findHumanByFIO("Диана", "Игоревна", "В"));
        //System.out.println(ft.findHumanByFIO("Максим", "Игоревич", "В"));
        //System.out.println(ft.findHumanByFIO("Жанна", "Владимировна", "В"));
        //System.out.println(ft.findHumanByFIO("Сергей", "Алексеевич", "В"));
        //System.out.println(ft.findHumanByFIO("Алексей", "Васильевич", "Д"));//несуществующий человек в базе


        //печать информации о детях по ФИО родителя
        //System.out.println(ft.findChildrenByFIOParent("Диана", "Игоревна", "В"));
        //System.out.println(ft.findChildrenByFIOParent("Сергей", "Алексеевич", "В"));
        //System.out.println(ft.findChildrenByFIOParent("Алексей", "Сергеевич", "В"));

        //печать информации о детях по id родителя
        //System.out.println(ft.findChildrenByIDParent(10));
        //System.out.println(ft.findChildrenByIDParent(6));


    }
}
