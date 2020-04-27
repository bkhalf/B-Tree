package eg.edu.alexu.csd.filestructure.btree;

public class Main {
    public static void main(String[] args)
    {
        BTree s=new BTree(3);
        s.insert(7,10);
        s.insert(8,11);
        s.insert(4,12);
        s.insert(5,13);
        s.insert(6,14);
        s.insert(10,15);
        s.insert(11,16);
        s.insert(12,17);
        s.insert(13,18);
        s.insert(15,19);
        s.insert(14,100);
        s.insert(20,101);
        s.insert(30,102);
        s.insert(3,103);
        s.insert(2,104);
        s.insert(1,105);
        s.insert(0,106);
        System.out.println(s.search(7));
s.print(s.getRoot());
    }
}
