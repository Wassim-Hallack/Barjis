public class Cell {
    private int i, j, tmp;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getTmp() {
        return tmp;
    }

    public void setTmp(int tmp) {
        this.tmp = tmp;
    }

    public Cell(int i, int j, int tmp) {
        this.i = i;
        this.j = j;
        this.tmp = tmp;
    }
}
