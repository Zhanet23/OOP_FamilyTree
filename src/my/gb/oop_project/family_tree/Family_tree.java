package my.gb.oop_project.family_tree;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import static my.gb.oop_project.family_tree.Human.compare;

public class Family_tree {
    private List<Human> familyTree = new ArrayList<>();

    private void addHuman (Human human) {
        familyTree.add(human);
    }

    /**
     * поиск человека по его ФИО,
     * возвращает данные типа Human
     */
    private Human findByFIO (String name, String middleName, String secondName) {
        Human temp = null;
        for (var i : familyTree) {
            if (i.getName().equalsIgnoreCase(name) && i.getMiddleName().equalsIgnoreCase(middleName) &&
                    i.getSecondName().equalsIgnoreCase(secondName))
            {temp = i; break;}
        }
        return temp;
    }

    /**
     * поиск человека по его id,
     * возвращает данные типа Human
     */
    private Human findByID (int id){
        Human temp = null;
        for (var i : familyTree) {
            if (i.getId() == id) {temp = i; break;}
        }
        return temp;
    }

    /**
     * возвращает ФИО, д.рожд и смерти(если человек умер уже), и возраст человека,
     * на вход принимает переменную типа Human,
     * возвращает данные типа StringBuilder
     */
    private StringBuilder getFio_Birthday_Age (Human human) {
        LocalDate now = LocalDate.now();
        StringBuilder sb = new StringBuilder();
        if (human != null) {
            //sb.append(human.getName()).append(" ").append(human.getMiddleName()).append(" ").append(human.getSecondName());
            sb.append(human.fio(human));
            sb.append(", ");
            sb.append(human.dates(human));
//            if (human.getDateD() == null) {
//                sb.append(", дата рожд.: ").append(human.getDateB());
//                sb.append(", возраст: ").append(compare(human.getDateB(), now));
//            } else {
//                sb.append(human.getDateB()).append(" - ").append(human.getDateD());
//                sb.append(", прожил(а): ").append(compare(human.getDateB(), human.getDateD())).append(" лет");
//            }
        }
        return sb;
    }

    // ---------------------- public methods -------------------------------------------
    /**
     * Добавляет данные о человеке в базу данных
     */
    public void add (Human human) {
        addHuman(human);
    }

    /**
     * поиск данных(ФИО, даты рожд/смерти(если уже умер) и возраст) о родителях ребенка по id ребенка,
     * возвращает данные типа StringBuilder
     */
    public StringBuilder findParentsByID (int id){
        Human child; StringBuilder sb = new StringBuilder();
        child = findByID(id); // ищем в базе человека по id, для которого хотим напечатать инф о его родителях
        if (child != null) {
            sb.append("Данные о родителях ").append(child.fio(child)).append(":\n");
            if (child.getMother() != null) {
                sb.append("мама: ").append(getFio_Birthday_Age(child.getMother())).append("\n");
            } else sb.append("данных о маме нет, ");

            if (child.getFather() != null) {
                sb.append("папа: ").append(getFio_Birthday_Age(child.getFather()));
            } else sb.append("данных о папе нет");
        }
        else sb.append("нет данных о человеке c id = ").append(id);
        return sb;
    }

    /**
     * поиск данных(ФИО, даты рожд/смерти(если уже умер) и возраст) о родителях ребенка по ФИО ребенка,
     * возвращает данные типа StringBuilder
     */
    public StringBuilder findParentsByFIO (String name, String middleName, String secondName){
        StringBuilder sb = new StringBuilder();
        Human child = findByFIO(name,middleName, secondName);
        if (child != null)  sb.append(findParentsByID (child.getId()));
        else {
            sb.append("человека с таким ФИО: ").append(name).append(" ");
            sb.append(middleName).append(" ").append(secondName).append(", ");
            sb.append("нет в базе");
        }
        return sb;
    }

    public StringBuilder findHumanByFIO(String name, String middleName, String secondName) {
         StringBuilder sb = new StringBuilder();
         Human human;
         human = findByFIO (name, middleName, secondName);
         if (human != null) { //человек есть в базе
             sb.append(human);
         }
         else sb.append("нет данных о человеке: ").append(name).append(" ").
                 append(middleName).append(" ").append(secondName);
         return sb;
    }


    //поиск детей по ID родителя
    public StringBuilder findChildrenByIDParent (int id){      // поиск человека по id
        Human temp = findByID(id); //нашли родителя по id
        StringBuilder sb = new StringBuilder();
        if (temp != null) {  //если родитель есть в базе
            if (temp.getChildren() != null) {  //если у него есть дети
                sb.append("дети: ");
                for (var i: temp.getChildren()) {
                    sb.append(getFio_Birthday_Age(i).append(", "));
                }
                sb.append("\n");
            }
            else sb.append("детей нет (или нет данных)"); //если детей нет
        }
        return sb;
     }

     //поиск детей по ФИО родителя
     public StringBuilder findChildrenByFIOParent (String name, String middleName, String secondName) {
         StringBuilder sb = new StringBuilder();
         Human parent = findByFIO(name, middleName,secondName); //сначала ищем родителя по заданному ФИО в базе
         if (parent != null) {
             sb.append("Информация о детях для введенного человека: ");
             sb.append(parent.fio(parent)).append("\n");
             sb.append(findChildrenByIDParent(parent.getId())); //получаем инф по id найденного родителя
         }
         else sb.append("такого человека нет в базе"); //если детей нет
         return sb;
     }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var i : familyTree) {
            sb.append(i); sb.append("\n");
        }
        return sb.toString();

    }

}
