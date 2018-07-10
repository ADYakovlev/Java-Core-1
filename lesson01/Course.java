package YakovlevAlexandr;

public class Course {
    int[] course = new int[3];

    public Course(int a, int b,int c) {
        course[0]=a;
        course[1]=b;
        course[2]=c;
    }

    public void doIt(Team team){
        for (int i = 0; i < course.length; i++) {
            team.setPower(course[i]);
        }
    }
}
