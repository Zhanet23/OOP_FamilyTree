package my.gb.oop_project.family_tree;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Human {
    int id;   //уникальный код человека в фамильном дерее
    String name;
    String secondName;
    String middleName;    //отчество
    LocalDate dateB;
    LocalDate dateD;
    int partner;
    List<Integer> children;
    int mother;
    int father;

    public Human(int id, String name, String secondName, String middleName, LocalDate dateB, LocalDate dateD, int partner,List<Integer> children, int mother, int father) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.middleName = middleName;
        this.dateB = dateB;
        this.dateD = dateD;
        this.partner = partner;
        this.children = children;
        this.mother = mother;
        this.father = father;
    }

    @Override
    public String toString() {
        LocalDate now =LocalDate.now();

        StringBuilder sb = new StringBuilder();
        sb.append(id).append(" - ");
        sb.append(name).append(" ").append(middleName).append(" ").append(secondName).append(", ");
        if (dateD == null) {
            sb.append("дата рожд.: ").append(dateB);
            sb.append(", возраст: ").append(compare(dateB,now));
        }
        else {
            sb.append(dateB).append(" - ").append(dateD);
            sb.append(", прожил(а): ").append(compare(dateB,dateD));
        }
        if (partner != 0)  sb.append(", супруг(а): ").append(partner);
        else sb.append("супруг(а): нет данных ");
        if (children != null) sb.append(", дети: ").append(children);
        sb.append(", мама: ").append(mother);
        sb.append(", папа: ").append(father);
        return   sb.toString();
    }

    public static long compare(LocalDate first, LocalDate second) {
        return ChronoUnit.YEARS.between(first,second);
    }





}
