
public class Main {
    
    public int h = 0;
    public int v = 0;

    //размерность доски
    final private static int DESK_SIZE = 8;

    public static int [][] Desk;

    public static void main(String args []) {
        Desk = new int[DESK_SIZE][DESK_SIZE];
        Desk[0][0] =1;

        int cnt = 0;
        boolean ch;

        while (true) {
            ch = Check();

            if (!ch) {
                GoDown();
                continue;
            }

            if (ch && GetTopLine()==Desk.length-1) {
                cnt++;
                System.out.println("======"+String.valueOf(cnt)+"=======");
                PrintDesk(Desk);
            }
            if (!GoLeftUp()) GoDown();
        }
    }
    
    public static void PrintDesk(int [][] Desk){
        for(int [] Line:Desk){
            for (int b:Line) {
                System.out.print((b==0?"O":"*") + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static boolean CheckVrt(){
        boolean fnd = false;

        for (int i =0; i < Desk.length;i++){
            fnd = false;
            for(int j = 0; j<Desk[0].length; j++){
                if (Desk[j][i] > 0) {
                   if (fnd) {return false;}
                   else {
                       fnd = true;
                   }
                }
            }
        }
        return true;
    }

    public static boolean Check(){
        //PrintDesk(Desk);
        return CheckHrz() && CheckVrt() && CheckDgl();
    }


    public static boolean CheckHrz(){
    
        return true;
    }

    public static boolean CheckOneDgl(int [][] pDesk, int vStart, int hStart){
        int x = vStart;
        int y = hStart;
        boolean fnd = false;
        while (x<pDesk.length && y<pDesk[0].length) {
            if (pDesk[x][y] > 0) {
                if (fnd) {return false;}
                else {
                    fnd = true;
                }
            }
            x++;
            y++;
        }

        return true;
    }

    public static int [][] ReverseDesk(){
        int [][] res = new int[Desk.length][Desk[0].length];
        for (int i =0; i < Desk.length;i++){
            for (int j =0; j < Desk.length;j++){
                res[i][j] = Desk[i][Desk.length-j-1];
            }
        }
        return res;
    }

    public static boolean CheckDgl(){

        for (int i =0; i < Desk.length;i++){
            if (!CheckOneDgl(Desk,0,i)) return false;
        }

        for (int i =0; i < Desk[0].length;i++){
            if (!CheckOneDgl(Desk,i,0)) return false;
        }
        // главная дигональ
        boolean fnd = false;
        for (int i =0; i < Desk.length;i++){
            if (Desk[i][i] > 0) {
                if (fnd) {return false;}
                else {
                    fnd = true;
                }
            }
        }


        int [][] rDesk = ReverseDesk();

        for (int i =0; i < rDesk.length;i++){
            if (!CheckOneDgl(rDesk,0,i)) return false;
        }

        for (int i =0; i < rDesk[0].length;i++){
            if (!CheckOneDgl(rDesk,i,0)) return false;
        }

        // главная дигональ
        fnd = false;
        for (int i =0; i < rDesk.length;i++){
            if (rDesk[i][i] > 0) {
                if (fnd) {return false;}
                else {
                    fnd = true;
                }
            }
        }

        return true;
    }

    public static int GetTopLine(){
        for (int i = Desk.length-1; i > -1; i-- ) {
            for (int j = 0; j < Desk[i].length; j++ ){
                if (Desk[i][j] != 0) return i;
            }
        }
        return -1;
    }

    public static int GetTopCol(int LineNum){
        for (int i=0; i<Desk[LineNum].length; i++){
            if (Desk[LineNum][i] > 0) return i;
        }
        return -1;
    }

    public static boolean GoLeftUp(){
        int vv = GetTopLine();
        int hh = GetTopCol(vv);

        int LeftNode = Desk[vv][hh];

        if (LeftNode > Desk.length) return false;
        if (vv +1 > Desk[0].length-1) return false;

        Desk[vv+1][LeftNode-1]=1;

        return true;
    }

    public static void GoDown(){
        int vv = GetTopLine();
        int hh = GetTopCol(vv);

        Desk[vv][hh] = 0;
        if (vv>0){
            Desk[vv-1][GetTopCol(vv-1)]++;
        } else if (hh < Desk[0].length-1) {
            Desk[vv][hh+1] = 1;
        } else System.exit(0);
    }
}
