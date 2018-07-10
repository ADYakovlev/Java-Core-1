package YakovlevAlexandr;

public class Team {
//    public String nameTeam="Russia";
    public String name;
    public int power;
    int[][] team = new int[2][4];
    public void setPower(int p){
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < p ; j++) {
                team[1][j]--;
            }
        }
    }

    public Team(int name1, int name2, int name3, int name4, int power1, int power2, int power3, int power4) {
        team[0][0]=name1;
        team[0][1]=name2;
        team[0][2]=name3;
        team[0][3]=name4;
        team[1][0]=power1;
        team[1][1]=power2;
        team[1][2]=power3;
        team[1][2]=power4;
    }

    public void showResults() {
        System.out.println("Done: ");
        for (int i = 0; i < 4; i++) {
            if (team[0][i]>0) {
                System.out.print(team[0][i]+" ");
            }
        }
    }
    public void printInfo() {
        System.out.println("\nAll team: ");
        for (int i = 0; i <2 ; i++) {
            for (int j = 0; j <4 ; j++) {
                System.out.print(team[i][j]+" ");
            }
        }
    }

}
