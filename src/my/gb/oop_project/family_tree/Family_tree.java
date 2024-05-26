package my.gb.oop_project.family_tree;

import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

public class Family_tree {
    private static List<Human> familyTree;

    //-----------------конструкторы----------------------------------------------------------
    public Family_tree() {
        this(new ArrayList<>());
    }
    public Family_tree(List<Human> familyTree) {
        this.familyTree = familyTree;
    }
    //----------------------------------------------------------------------------------------

    private StringBuilder addHuman (Human h) {
        StringBuilder sb = new StringBuilder();
        if (h == null) sb.append("не введен человек");
        else {
            if (!familyTree.contains(h)) {
                familyTree.add(h);
                sb.append("человек успешно добавлен в древо");
                // если у человека есть информация о родителях, то добавим и родителям информацию о ребенке
                if (h.getMother() != null) {h.getMother().addChild(h);}
                if (h.getFather() != null) {h.getFather().addChild(h);}
                //если вводится информация о старшем родственнике и известны дети у него
                // то надо добавить детям информацию о родителе
                if (h.getChildren() != null) {addParentToChildren(h);}
            }
            else sb.append("абсолютно идентичная информация о человеке ").append(h.getNames(h).append(" уже имеется в базе, ").
                    append("код - ").append(findByFIO(h.getName(),h.getMiddleName(),h.getSecondName()).getId()));
            }
        return sb;
    }

    // возвращает отсортированное древо по возрастанию года рождения людей. Возвращает StringBuilder
    private StringBuilder printSort (){
        StringBuilder sb = new StringBuilder();
        Comparator<Human> cc = new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return o1.getDateB().getYear() - o2.getDateB().getYear();
            }
        };
        familyTree.stream().sorted(cc).forEach(sb::append);
        return sb;
    }

    // используется в методе addHuman
    private void addParentToChildren (Human h){
        //добавляем отца
        if (String.valueOf(h.getGender()).equals("Male")) {
            for (Human child : h.getChildren()) { child.addFather(h);}
        }
        else {
            for (Human child : h.getChildren()) {child.addMother(h);}
        }
    }

    /**
     * Без входящих параметров. Возвращает всю информацию из базы (данные типа StringBuilder)
     * отсортировано по id
     */
    private String FullInfAboutTree () {
        StringBuilder sb = new StringBuilder();
        for (var i : familyTree) { sb.append(i); sb.append("\n");}
        return sb.toString();
    }

    /**
     * поиск человека по его ФИО, возвращает данные типа Human
     */
    private Human findByFIO (String name, String middleName, String secondName) {
        Human tempHuman = null;
        for (var i : familyTree) {
            if (i.getName().equalsIgnoreCase(name) && i.getMiddleName().equalsIgnoreCase(middleName) &&
                    i.getSecondName().equalsIgnoreCase(secondName))
            {tempHuman = i; break;}
        }
        return tempHuman;
    }

    /**
     * поиск человека по его id, возвращает данные типа Human
     */
    private Human findByID (int id){
        Human tempHuman = null;
        for (var i : familyTree) {
            if (i.getId() == id) {tempHuman = i; break;}
        }
        return tempHuman;
    }

    /**
     * возвращает ФИО, д.рожд и смерти(если человек умер уже), и возраст человека,
     * на вход принимает переменную типа Human, возвращает данные типа StringBuilder
     */
    private StringBuilder getFio_Dates (Human human) {
        LocalDate now = LocalDate.now();
        StringBuilder sb = new StringBuilder();
        if (human != null) {
            sb.append(human.getFIO(human)).append(", ").append(human.getDatesOfHuman(human));
        }
        return sb;
    }

    private StringBuilder findParentsByID_ft (int id){
        Human child; StringBuilder sb = new StringBuilder();
        child = findByID(id); // ищем в базе человека по id, для которого хотим напечатать инф о его родителях
        if (child != null) {
            sb.append("Данные о родителях ").append(child.getFIO(child)).append(":\n");
            if (child.getMother() != null) {
                sb.append("мама: ").append(getFio_Dates(child.getMother())).append("\n");
            } else sb.append("данных о маме нет, ");

            if (child.getFather() != null) {
                sb.append("папа: ").append(getFio_Dates(child.getFather()));
            } else sb.append("данных о папе нет");
        }
        else sb.append("нет данных о человеке c id = ").append(id);
        return sb;
    }

    private StringBuilder findParentsByFIO_ft (String name, String middleName, String secondName){
        StringBuilder sb = new StringBuilder();
        Human child = findByFIO(name,middleName, secondName);
        if (child != null)  sb.append(findParentsByID_ft (child.getId()));
        else {
            sb.append("человека с таким ФИО: ").append(name).append(" ");
            sb.append(middleName).append(" ").append(secondName).append(", ");
            sb.append("нет в базе");
        }
        return sb;
    }

    // выдает полную информацию о человеке по его фио
    private StringBuilder findHumanByFIO_ft(String name, String middleName, String secondName) {
        StringBuilder sb = new StringBuilder(); Human human;
        human = findByFIO (name, middleName, secondName);
        if (human != null) { //человек есть в базе
            sb.append(human);
            // добавим информацию о братьях/сестрах
            sb.append("информация о братьях / сестрах:").append("\n");
            List<Human> sublins = new ArrayList<>(); sublins = getSubl(human.getId());
            if (!sublins.isEmpty()) {
                for (var i : sublins) {
                    sb.append(i.getFIO(i)).append(", ").append(i.getDatesOfHuman(i)).append("\n");
                }
            }
            else sb.append("нет или нет данных");
        }
        else sb.append("нет данных о человеке: ").append(name).append(" ").
                append(middleName).append(" ").append(secondName);
        return sb;
    }

    private StringBuilder findChildrenByIDParent_ft (int id){      // поиск человека по id
        Human temp = findByID(id); //нашли родителя по id
        StringBuilder sb = new StringBuilder();
        if (temp != null) {  //если родитель есть в базе
            sb.append("Информация о детях для введенного человека: ");
            sb.append(temp.getFIO(temp)).append("\n");
            if (temp.getChildren() != null) {  //если у него есть дети
                sb.append("дети: ");
                for (var i: temp.getChildren()) {
                    sb.append(getFio_Dates(i).append(", "));
                }
                sb.append("\n");
            }
            else sb.append("детей нет (или нет данных)"); //если детей нет
        }
        return sb;
    }

    private StringBuilder findChildrenByFIOParent_ft (String name, String middleName, String secondName) {
        StringBuilder sb = new StringBuilder();
        Human parent = findByFIO(name, middleName,secondName); //сначала ищем родителя по заданному ФИО в базе
        if (parent != null) {
            sb.append(findChildrenByIDParent(parent.getId())); //получаем инф по id найденного родителя
        }
        else sb.append("такого человека нет в базе");
        return sb;
    }

    private List<Human> getParents (Human h){
        List<Human> parents = new ArrayList<>();
        if (h.getMother() != null) parents.add(h.getMother());
        if (h.getFather() != null ) parents.add(h.getFather());
        return parents;
    }

    // вызов из метода getSublins
    private StringBuilder sublins (int id) { //родных и сводных
        StringBuilder sb = new StringBuilder();
        Human h = findByID(id); //получили человека, для которого ищем сестер/братьев
        if (h == null) sb.append("нет человека с таким id в базе");
        else {
            List<Human> sublins = new ArrayList<>();
            sb.append("\n").append("информация о братьях/сестрах для человека: ").append(h.getFIO(h)).append("\n");
            sublins = getSubl(id); //получили список братьев и сестер
            if (!sublins.isEmpty()) {
                for (var i : sublins) {
                    String t; int idh =i.getId();
                    String str = String.valueOf(findByID(idh).getGender());
                    if (str.equals("Male")) t = "брат"; else t = "сестра";
                    sb.append(t).append(":\n");
                    Human s = findByID(idh);
                    sb.append(s.getFIO(s)).append(", ").append(s.getDatesOfHuman(s)).append("\n");
                }
            }
            else sb.append("братьев / сестер нет (или нет информации)");
        }
        return sb;
    }

    //возвращает список sublins List<Human>
    private List<Human> getSubl (int id) { //родных и сводных
        List<Human> sublins = new ArrayList<>(); //инициация списка sublins
        Human h = findByID(id); //получили человека, для которого ищем сестер/братьев

        if (h != null) {
            // set - чтобы дети не повторялись, двое родителей - т.к. могут быть siblins сводные
            // в set нельзя добавить Human, тк equals по всем полям, кроме id
            //это для того, чтобы не добавлялась абсолютно идентичная инф о человеке, а id считается автоматически
            Set<Integer> sub = new HashSet<>();
            for (Human par : getParents(h)){ //для каждого родителя
                for (Human child : par.getChildren()) { //по списку детей родителя
                    if (h.getId() != child.getId()) sub.add(child.getId());
                }
            }
            // в set находятся id братьев и сестер
            if (!sub.isEmpty()) { // если set не пустой
                for (int i : sub) { sublins.add(findByID(i));}
            }
        }
        return sublins;
    }

















    // ---------------------- public methods -------------------------------------------
    /**
     * Выдает отсортированное дерево по возрастанию года рождения людей
     */
     public StringBuilder printSorted () {
        return printSort();
    }


    /**
     * Ищет данные о братьях/сестрах чнловека по его id
     */
    public StringBuilder getSublins (int id) {
        return sublins(id);
    }
    /**
     * Добавляет данные о человеке в базу данных
     */
    public StringBuilder add (Human human) {return addHuman(human);}

    /**
     * поиск данных(ФИО, даты рожд/смерти(если уже умер) и возраст) о родителях ребенка по id ребенка,
     * возвращает данные типа StringBuilder
     */
    public StringBuilder findParentsByID (int id){
        return findParentsByID_ft(id);
    }

    /**
     * поиск данных(ФИО, даты рожд/смерти(если уже умер) и возраст) о родителях ребенка по ФИО ребенка,
     * возвращает данные типа StringBuilder
     */
    public StringBuilder findParentsByFIO (String name, String middleName, String secondName){
        return findParentsByFIO_ft(name,middleName,secondName);
    }

    /**
     * поиск данных по ФИО человека, возвращает данные типа StringBuilder
     */
    public StringBuilder findHumanByFIO(String name, String middleName, String secondName) {
        return findHumanByFIO_ft(name, middleName, secondName);
    }

    //поиск детей по ID родителя
    public StringBuilder findChildrenByIDParent (int id){      // поиск человека по id
        return findChildrenByIDParent_ft(id);
    }

     //поиск детей по ФИО родителя
     public StringBuilder findChildrenByFIOParent (String name, String middleName, String secondName) {
        return findChildrenByFIOParent_ft(name, middleName, secondName);
     }

    @Override
    public String toString() {return FullInfAboutTree();}

}
