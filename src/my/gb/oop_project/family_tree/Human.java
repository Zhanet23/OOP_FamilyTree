package my.gb.oop_project.family_tree;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Human {
    private int id;   //уникальный код человека в фамильном дерее
    private String name;
    private String secondName;
    private String middleName;    //отчество
    private LocalDate dateB;
    private LocalDate dateD;
    private Human partner;
    private List<Human> children;
    private Human mother;
    private Human father;

    /**
     * Принимает в качестве параметра переменную типа Human (человека)
     * Возвращает ФИО человека
     * возвращает данные типа StringBuilder
     */
    private StringBuilder fio (Human h) {
        StringBuilder sb = new StringBuilder();
        if (h != null) {
            sb.append("(id-").append(h.id).append(") ");
            sb.append(h.name).append(" ").append(h.middleName).append(" ");
            sb.append(h.secondName);
        }
        else sb.append(" нет данных");
        return sb;
    }

    /**
     * Принимает в качестве параметра переменную типа Human (человека)
     * Возвращает даты рождения, смерти(если человек уже умер) и возраст либо сколько лет прожил
     * возвращает данные типа StringBuilder
     */
    private StringBuilder dates (Human h) {
        LocalDate now =LocalDate.now();
        StringBuilder sb = new StringBuilder();
        if (h.dateD == null) {
            sb.append("дата рожд.: ").append(h.dateB);
            sb.append(", возраст: ").append(compare(h.dateB,now));
        }
        else {
            sb.append(h.dateB).append(" - ").append(h.dateD);
            sb.append(", прожил(а): ").append(compare(h.dateB,h.dateD));
            sb.append(" лет");
        }
        sb.append("; ");
        return sb;
    }

    // расчет возраста
    private static long compare(LocalDate first, LocalDate second) {
        return ChronoUnit.YEARS.between(first,second);
    }

    /**
     * Возвращает полную информацию о текущем человеке (возвращает тип StringBuilder
     */
    private String getFullInfAboutHuman () {
        StringBuilder sb = new StringBuilder();
        sb.append(fio(this)).append(", ");
        sb.append(dates(this));
        sb.append("\n");
        if (partner != null)  sb.append("супруг(а): ").append(fio(partner).append(", ").append(dates(partner)));
        else sb.append("супруг(а): нет данных");
        sb.append("\n");
        if (children != null) {
            sb.append("дети: ");
            for (var i: children) {
                sb.append(fio(i).append(", ").append(" ").append(dates(i)));
            }
            sb.append("\n");
        }
        else sb.append("детей нет (или нет данных),").append("\n");
        if (mother!=null) sb.append("мама: ").append(fio(mother)).append(" ").append(dates(mother));
        else sb.append("мама: данных нет");
        sb.append(", ");
        if (father!=null) sb.append("папа: ").append(fio(father)).append(" ").append(dates(father));
        else sb.append("папа: данных нет;");
        sb.append("\n");
        return   sb.toString();
    }




    public Human(int id, String name, String secondName, String middleName, LocalDate dateB, LocalDate dateD, Human partner, List<Human> children, Human mother, Human father) {
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

    // конструктор, если нет данных о партнере и детях
    public Human(int id, String name, String secondName, String middleName, LocalDate dateB, LocalDate dateD, Human mother, Human father) {
        this(id, name, secondName, middleName, dateB, dateD, null, null, mother, father);
    }

    // конструктор, если нет данных о папе
    public Human(int id, String name, String secondName, String middleName, LocalDate dateB, LocalDate dateD, Human partner, List<Human> children, Human mother) {
        this(id, name, secondName, middleName, dateB, dateD, partner, children, mother, null);
    }
    // конструктор, если нет данных о папе и маме
    public Human(int id, String name, String secondName, String middleName, LocalDate dateB, LocalDate dateD, Human partner, List<Human> children) {
        this(id, name, secondName, middleName, dateB, dateD, partner, children, null, null);
    }
    // конструктор, если нет данных о папе и маме, о детях и о партнере
    public Human(int id, String name, String secondName, String middleName, LocalDate dateB, LocalDate dateD) {
        this(id, name, secondName, middleName, dateB, dateD, null, null, null, null);
    }
    // конструктор, если нет данных о папе, партнере, детях и о дате смерти
    public Human(int id, String name, String secondName, String middleName, LocalDate dateB, Human mother) {
        this(id, name, secondName, middleName, dateB, null, null, null, mother, null);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public LocalDate getDateB() {
        return dateB;
    }

    public LocalDate getDateD() {
        return dateD;
    }

    public Human getPartner() {
        return partner;
    }

    public List<Human> getChildren() {
        return children;
    }

    public Human getMother() {
        return mother;
    }

    public static int getHumanID(Human human) {
        return human.id;
    }
    public Human getFather() {
        return father;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }

    public void setPartner(Human partner) {
        this.partner = partner;
    }

    //---------------------------------------------------------------------------
    /**
     * Принимает в качестве параметра переменную типа Human (человека)
     * Возвращает ФИО человека (типа StringBuilder)
     */
    public StringBuilder getFIO (Human h) {
        return fio(h);
    }

    /**
     * Принимает в качестве параметра переменную типа Human (человека)
     * Возвращает даты рождения, смерти(если человек уже умер) и возраст либо сколько лет прожил
     * возвращает данные типа StringBuilder
     */
    public StringBuilder getDatesOfHuman (Human h) {
        return dates(h);
    }

    @Override
    public String toString() {
        return getFullInfAboutHuman();
    }

}
