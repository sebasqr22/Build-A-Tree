public class test {
    public static void main(String[] args) {
        SplayTree ar = new SplayTree();
        Btree arr = new Btree(3);

        System.out.println("Comenzando");
        ar.Insert(34);
        ar.Insert(23);
        ar.Insert(1);
        ar.Insert(222);
        ar.Insert(333);
        ar.Insert(234);

        ar.recorrer(ar.getRoot());

        System.out.println(ar.getSize());
        System.out.println("------------------------------------------------");

        arr.insert(8);
        arr.insert(9);
        arr.insert(10);
        arr.insert(11);
        arr.insert(15);
        arr.insert(20);
        arr.insert(17);

        arr.Show();

    }

}
