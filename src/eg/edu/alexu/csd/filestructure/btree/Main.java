package eg.edu.alexu.csd.filestructure.btree;

public class Main {
    public static void main(String[] args)
    {
        BTree s=new BTree(3);
        s.insert(7,10);
        s.insert(8,10);
        s.insert(4,10);
        s.insert(5,10);
        s.insert(6,10);
        s.insert(10,10);


s.print(s.getRoot());
    }
}
