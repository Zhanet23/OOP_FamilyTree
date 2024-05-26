package my.gb.oop_project.family_tree;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Human extends CreaterHuman {

    private int id;  //уникальный идентификатор человека (инициализация при создании человека)
    private String name;
    private String secondName;
    private String middleName;
    Gender gender;
    private LocalDate dateB;
    private LocalDate dateD;
    private Human spouse;
    private List<Human> children = new ArrayList<>();
    private Human mother;
    private Human father;

    //------------------------------- КОНСТРУКТОРЫ ---------------------------------------------------------
    public Human() {
        id = i;
    }
    public Human(String name, String secondName, String middleName, Gender gender,LocalDate dateB, LocalDate dateD, Human spouse, List<Human> children, Human mother, Human father) {

        this();
        this.name = name; this.secondName = secondName; this.middleName = middleName;
        this.gender = gender;
        this.dateB = dateB; this.dateD = dateD;
        this.spouse = spouse;
        this.children = children;
        this.mother = mother; this.father = father;
    }

    // конструктор, если нет данных о супруге и детях
    public Human(String name, String secondName, String middleName, Gender gender,LocalDate dateB, LocalDate dateD, Human mother, Human father) {
        this(name, secondName, middleName, gender,dateB, dateD, null, null, mother, father);
    }

    // конструктор, если нет данных о папе
    public Human(String name, String secondName, String middleName, Gender gender,LocalDate dateB, LocalDate dateD, Human spouse, List<Human> children, Human mother) {
        this(name, secondName, middleName, gender,dateB, dateD, spouse, children, mother, null);
    }
    // конструктор, если нет данных о папе и маме
    public Human(String name, String secondName, String middleName, Gender gender,LocalDate dateB, LocalDate dateD, Human spouse, List<Human> children) {
        this(name, secondName, middleName, gender,dateB, dateD, spouse, children, null, null);
    }
    // конструктор, если нет данных о папе и маме, о детях и о партнере
    public Human(String name, String secondName, String middleName, Gender gender,LocalDate dateB, LocalDate dateD) {
        this(name, secondName, middleName, gender,dateB, dateD, null, null, null, null);
    }
    // конструктор, если нет данных о папе, партнере, детях и о дате смерти
    public Human(String name, String secondName, String middleName, Gender gender,LocalDate dateB, Human mother) {
        this(name, secondName, middleName, gender,dateB, null, null, null, mother, null);
    }
    //----------------------------------------------------------------------------------------------------

    //----------------------------приватные методы--------------------------------------------------------
    /**
     * --->(тип Human) ---> id и ФИО человека (тип StringBuilder)
     */
    private StringBuilder getFullName (Human h) {
        StringBuilder sb = new StringBuilder();
        if (h != null) {
            sb.append("(id-").append(h.id).append(") ");
            sb.append(h.name).append(" ").append(h.middleName).append(" ").append(h.secondName);
        }
        else sb.append(" нет данных");
        return sb;
    }

    /**
     * Принимает в качестве параметра переменную типа Human (человека)
     * Возвращает даты рождения, смерти(если человек уже умер) и возраст либо сколько лет прожил
     * возвращает данные типа StringBuilder
     */
    private StringBuilder getDates (Human h) {
        LocalDate now =LocalDate.now(); StringBuilder sb = new StringBuilder();
        int age = age(h);
        if (h.dateD == null) {
            sb.append("дата рожд.: ").append(h.dateB).append(", возраст: ").append(age);
        }
        else {
            sb.append(h.dateB).append(" - ").append(h.dateD).append(", прожил(а): ");
            sb.append(age).append(" лет");
        }
        sb.append("; ");
        return sb;
    }
    private int age (Human h) {
        int age; LocalDate now =LocalDate.now();
        if (h.dateD == null) {
            age = (int) compare(h.dateB,now);
        }
        else {
            age = (int) compare(h.dateB,h.dateD);
        }
        return age;

    }

    // расчет возраста
    private static long compare(LocalDate first, LocalDate second) {
        return ChronoUnit.YEARS.between(first,second);
    }

    /**
     * -возвращает id, ФИО и даты детей рассматриваемого человека (возвращает тип StringBuilder)
     */
    private StringBuilder getChildren(Human h) {
        StringBuilder sb = new StringBuilder();
        if (h.children != null) {
            sb.append("дети: ");
            for (var i: h.children) {
                sb.append(getFullName(i).append(", ").append(" ").append(getDates(i)));
            }
            sb.append("\n");
        }
        else sb.append("детей нет (или нет данных),").append("\n");
        return sb;
    }



    /**
     * -возвращает id, ФИО и даты родителей рассматриваемого человека (возвращает тип StringBuilder)
     */
    private StringBuilder getInfOfParents(Human h) {
        StringBuilder sb = new StringBuilder();
        sb.append("информация о родителях: \n");
        if (h.mother!=null) sb.append("мама: ").append(getFullName(h.mother)).append(" ").append(getDates(h.mother)).append("\n");
        else sb.append("мама: данных нет\n");

        if (h.father!=null) sb.append("папа: ").append(getFullName(h.father)).append(" ").append(getDates(h.father));
        else sb.append("папа: данных нет");
        sb.append("\n");
        return   sb;
    }

     /**
     * Возвращает полную информацию о текущем человеке (возвращает тип StringBuilder)
     */
    private String getFullInfAboutHuman () {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------------------------").append("\n");
        sb.append(getFullName(this)).append(", ").append(getDates(this));
        String t = "";
        if (String.valueOf(getGender()).equals("Male")) t = "м";
        else t = "ж";
        sb.append(", ").append(t).append("\n");

        if (spouse != null)  sb.append("супруг(а): ").append(getFullName(spouse).append(", ").append(getDates(spouse)));
        else sb.append("супруг(а): нет данных");
        sb.append("\n");

        sb.append(getChildren(this));
        sb.append(getInfOfParents(this));
        sb.append("\n");
        return   sb.toString();
    }

    //----------------------------------getters-------------------------------------------------
    public int getId() {return id;}
    public String getName() {return name;}
    public String getSecondName() {return secondName;}
    public String getMiddleName() {return middleName;}
    public LocalDate getDateB() {return dateB;}
    public LocalDate getDateD() {return dateD;}
    public Human getSpouse() {return spouse;}
    public Human getMother() {return mother;}
    public Human getFather() {return father;}
    public List<Human> getChildren() {return children;}

    public Gender getGender() {return gender;}
    private StringBuilder Name (Human h){
        StringBuilder sb = new StringBuilder();
        sb.append(h.getName()).append(" ").append(h.getMiddleName()).append(" ").append(h.getSecondName());
        return sb;
    }
    public StringBuilder getNames (Human h){
        return Name(h);
    }
    //------------------------------------------------------------------------------------------
    //-------------------------------setters----------------------------------------------------
    //public void setChildren(List<Human> children) {
    //    this.children = children;
    //}

    //TODO сделать метод в fam tree this - delete
    public void setSpouse(Human spouse) {
        this.spouse = spouse;
    }
    //------------------------------------------------------------------------------------------------

    public int getAge (Human h) {
        return age(h);
    }
    public Human(List<Human> children) {
        this.children = children;
    }

    public void addChild (Human h){
        if (this.children!=null) {if (!this.children.contains(h)) this.children.add(h);}
        else {
            this.children = new ArrayList<>();
            this.children.add(h);
        }
    }

    public void addMother (Human h){
        if (this.mother != h) {
            this.mother = h;
        }
    }
    public void addFather (Human h){
        if (this.father != h) {
            this.father = h;
        }
    }


    /**
     * Принимает в качестве параметра переменную типа Human (человека)
     * Возвращает ФИО человека и его id (типа StringBuilder)
     */
    public StringBuilder getFIO (Human h) { return getFullName(h);}

    /**
     * Принимает в качестве параметра переменную типа Human (человека)
     * Возвращает даты рождения, смерти(если человек уже умер) и возраст либо сколько лет прожил
     * возвращает данные типа StringBuilder
     */
    public StringBuilder getDatesOfHuman (Human h) {return getDates(h);}

    @Override
    public String toString() {
        return getFullInfAboutHuman();
    }

    @Override
    public boolean equals(Object o) {   //если совпадают все поля, кроме id
        if (this == o) return true;
        if (!(o instanceof Human human)) return false;
        return Objects.equals(getName(), human.getName()) && Objects.equals(getSecondName(), human.getSecondName()) && Objects.equals(getMiddleName(), human.getMiddleName()) && Objects.equals(getDateB(), human.getDateB()) && Objects.equals(getDateD(), human.getDateD()) && Objects.equals(getSpouse(), human.getSpouse()) && Objects.equals(getChildren(), human.getChildren()) && Objects.equals(getMother(), human.getMother()) && Objects.equals(getFather(), human.getFather());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSecondName(), getMiddleName(), getDateB(), getDateD(), getSpouse(), getChildren(), getMother(), getFather());
    }
}
