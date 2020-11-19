
import tec.buildatree.trees.Btree.Btree;

public class test {
    public static void main(String[] args) {
        Btree b = new Btree(3);
        b.insert(8);
        b.insert(9);
        b.insert(10);
        b.insert(11);
        b.insert(15);
        //b.insert(20);
        //b.insert(17);
        //b.insert(56);
        //b.insert(34);

        b.Show();
        if (b.contains(15)) {
            System.out.println("\nfound");
        } else {
            System.out.println("\nnot found");
        }
    }


}
