package my.gb.oop_project.family_tree;

import java.io.Console;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Family_tree ft = new Family_tree();
        Family_tree ft2 = new Family_tree();
        Human h1 = new Human("Нина","М","Николаевна", Gender.Female,
                LocalDate.of(1920,1,20),LocalDate.of(2000,6,22));
        System.out.println(ft.add(h1));

        Human h2 = new Human("Аркадий","М","Александрович", Gender.Male,
                LocalDate.of(1924,11,17),LocalDate.of(1996,3,15));
        System.out.println(ft.add(h2));
        h1.setSpouse(h2); h2.setSpouse(h1); //запись информации о партнере

        Human h3 = new Human("Елизавета","К","Петровна", Gender.Female,
                LocalDate.of(1925,3,30),LocalDate.of(1975,5,15));
        System.out.println(ft.add(h3));

        Human h4 = new Human("Александр","К","Михайлович", Gender.Male,
                LocalDate.of(1927,7,10),LocalDate.of(1987,12,28));
        System.out.println(ft.add(h4));
        h3.setSpouse(h4); h4.setSpouse(h3); //запись информации о партнере

        Human h5 = new Human("Владимир","М","Аркадьевич", Gender.Male,
                LocalDate.of(1947,2,4),null,h1,h2);
        System.out.println(ft.add(h5));

        Human h6 = new Human("Людмила","М","Александровна", Gender.Female,
                LocalDate.of(1950,1,5),null,h3,h4);
        System.out.println(ft.add(h6));
        h5.setSpouse(h6); h6.setSpouse(h5); //запись информации о партнере

        Human h7 = new Human("Евдокия","В","Федоровна", Gender.Female,
                LocalDate.of(1948,9,16),LocalDate.of(2004,8,8));
        System.out.println(ft.add(h7));

        Human h8 = new Human("Сергей","В","Алексеевич", Gender.Male,
                LocalDate.of(1946,12,27),LocalDate.of(2022,4,8));
        System.out.println(ft.add(h8));
        h7.setSpouse(h8); h8.setSpouse(h7); //запись информации о партнере

        Human h9 = new Human("Игорь","В","Сергеевич", Gender.Male,
                LocalDate.of(1974,8,7),null,h7,h8);
        System.out.println(ft.add(h9));

        Human h10 = new Human("Людмила","П","Сергеевна", Gender.Female,
                LocalDate.of(1976,3,20),null,h7,h8);
        System.out.println(ft.add(h10));

        Human h11 = new Human("Жанна","В","Владимировна", Gender.Female,
                LocalDate.of(1980,10,8),null,h6,h5);
        System.out.println(ft.add(h11));
        h9.setSpouse(h11); h11.setSpouse(h9);//запись информации о партнере

        Human h12 = new Human("Диана","В","Игоревна", Gender.Female,
                LocalDate.of(2005,6,19),null,h11, h9);
        System.out.println(ft.add(h12));

        Human h13 = new Human("Максим","В","Игоревич", Gender.Male,
                LocalDate.of(2008,10,10), null,h11,h9);
        System.out.println(ft.add(h13));

        Human h14 = new Human("Николай","М","Алексеевич", Gender.Male,
                LocalDate.of(1900,6,2),
                LocalDate.of(1961,6,22),null,List.of(h1));
        System.out.println(ft.add(h14));

        Human h15 = new Human("Илья","К","Иванович", Gender.Male,
                LocalDate.of(2019,7,15), h11);
        System.out.println(ft.add(h15));

        Human h16 = new Human("Илья","К","Иванович", Gender.Male,
                LocalDate.of(2019,7,15), h11);
        System.out.println(ft.add(h16));

//        Human h17 = new Human("Тарас","В","Владимирович", Gender.Male,LocalDate.of(2005,3,25),null,null,null,null);
//        ft2.add(h17);   //другое древо
//        //System.out.println(h17);
//        h12.setSpouse(h15);h14.setSpouse(h12);System.out.println(ft.add(h17));


        //-------------------------функционал------------------------------------------------
        // 1) печать всех данных в базе (не сортировано)
        //System.out.println(ft);

        // 2) печать всех данных в базе (сортировка по возрастанию года рождения)
        //System.out.println(ft.printSorted());

        // 3)печать данных о родителях ребенка по id ребенка
        //System.out.println(ft.findParentsByID(1)); //только отец Николай Алексеевич
        //System.out.println(ft.findParentsByID(11));
        //System.out.println(ft.findParentsByID(15));
        //System.out.println(ft.findParentsByID(110)); ////такого человека нет в базе

        // 4) печать данных о родителях ребенка по ФИО ребенка
        //System.out.println(ft.findParentsByFIO("Владимир", "Аркадьевич", "М")); //Нина и Аркадий
        //System.out.println(ft.findParentsByFIO("Евдокия", "Федоровна", "В")); //данных о родиелях нет
        //System.out.println(ft.findParentsByFIO("Анна", "Васильевна", "А"));//такого человека нет в базе

        // 5) печать всех данных о человеке по ФИО
        //System.out.println(ft.findHumanByFIO("Диана", "Игоревна", "В"));
        //System.out.println(ft.findHumanByFIO("Максим", "Игоревич", "В"));
        //System.out.println(ft.findHumanByFIO("Жанна", "Владимировна", "В"));
        //System.out.println(ft.findHumanByFIO("Сергей", "Алексеевич", "В"));
        //System.out.println(ft.findHumanByFIO("Алексей", "Васильевич", "Д"));//несуществующий человек в базе

        // 6) печать информации о детях по ФИО родителя
        //System.out.println(ft.findChildrenByFIOParent("Диана", "Игоревна", "В"));
        //System.out.println(ft.findChildrenByFIOParent("Сергей", "Алексеевич", "В"));
        //System.out.println(ft.findChildrenByFIOParent("Алексей", "Сергеевич", "В"));

        // 7) печать информации о детях по id родителя
        //System.out.println(ft.findChildrenByIDParent(11));
        //System.out.println(ft.findChildrenByIDParent(6));

        // 8) печать информации о братьях/сестрах человека по его id
        //System.out.println(ft.getSublins(15));
        //System.out.println(ft.getSublins(12));
        //System.out.println(ft.getSublins(9));
        //System.out.println(ft.getSublins(11));
        //System.out.println(ft.getSublins(111)); //id такого нет в базе


    }
}
