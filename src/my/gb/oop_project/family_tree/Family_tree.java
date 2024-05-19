package my.gb.oop_project.family_tree;

import java.util.ArrayList;
import java.util.List;

public class Family_tree {
    List<Human> familyTree = new ArrayList<>();

//    public Family_tree(List<Human> familyTree) {
//        this.familyTree = familyTree;
//    }

    public void addHuman (Human human) {
        familyTree.add(human);
    }

    public void printParents (int id){      // поиск данных о маме ребенка по id ребенка
        Human child;
        child = findHumanByID(id);
        System.out.printf("данные о родителях (id = %s) %s %s %s: \n",child.id,child.name,child.middleName,child.secondName);
        if (child.mother != 0) {printFIOandBirthday(findHumanByID(child.mother).id);}
        else System.out.println("данных о маме нет");
        if (child.father != 0) {printFIOandBirthday(findHumanByID(child.father).id);}
        else System.out.println("данных о папе нет");
    }

    public Human findHumanByID (int id){      // поиск человека по id
        Human temp = null;
        for (var i : familyTree) {
            if (i.id == id) {temp = i; break;}
        }
        return temp;
    }



    @Override
    public String toString() {
        return "Family_tree{" +
                "familyTree=" + familyTree +
                '}';
    }

    public void print () {          // вывод всех данных (не упорядоченно)
        for (var i : familyTree) {
            System.out.println(i);
        }
    }

    public void find (String name, String middleName, String secondName) {  //поиск данных по имени
        Human temp = null;
        for (var i : familyTree) {
            if (i.name.equalsIgnoreCase(name) && i.middleName.equalsIgnoreCase(middleName) &&
                i.secondName.equalsIgnoreCase(secondName))
                {temp = i; break;}
        }

        if (temp != null) {    // если такой человек вообще есть в дереве
            System.out.printf("данные по %s %s %s: ", name, middleName, secondName);
            System.out.println(); printFIOandBirthday(temp.id);
            if (temp.mother != 0) {
                 System.out.print("мама: "); printFIOandBirthday(findHumanByID(temp.mother).id);}
            else System.out.println("данных о маме нет");
            if (temp.father != 0) {
                 System.out.print("папа: "); printFIOandBirthday(findHumanByID(temp.father).id);}
            else System.out.println("данных об папе нет");
            // вывод информации о супруге
            if (temp.partner != 0) {
                System.out.print("данные о супруге: ");
                printFIOandBirthday(findHumanByID(temp.partner).id);
            }
            else System.out.println("данные о супруге отсутствуют либо не замужем/не женат");
            //вывод информации о детях
            if (temp.children != null) {
              for (int i = 0; i < temp.children.size(); i++) {
                  System.out.print("ребенок: ");
                  printFIOandBirthday(findHumanByID(temp.children.get(i)).id);
              }
            }
            else System.out.println("детей нет");
        }
    }

    public void printFIOandBirthday (int id) {
        Human temp = null;
        for (var i : familyTree) {
            if (i.id == id) {temp = i; break;}
        }
        StringBuilder sb = new StringBuilder();
        sb.append(temp.name).append(" ").append(temp.middleName).append(" ").append(temp.secondName);
        sb.append(", г.р. ").append(temp.dateB);
        System.out.println(sb);
    }


}
