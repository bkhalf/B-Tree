package eg.edu.alexu.csd.filestructure.btree;

public class Main {
    public static void main(String[] args)
    {
        BTree s=new BTree(3);
for(int i=0;i<30;i++){
    s.insert(i,i);
}
        s.print(s.getRoot() , true);
        s.delete(8);
        s.print(s.getRoot() , true);
    }

}
