package my.gb.oop_project.family_tree;

    // класс предназначен для подсчета всех людей в данной базе, даже если человек из другого древа
    public class CreaterHuman {
        public static int i;
        static {
            i = 0;
        }

        public CreaterHuman() {
            i++;
        }
    }

