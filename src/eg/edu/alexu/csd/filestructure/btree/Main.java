package eg.edu.alexu.csd.filestructure.btree;

public class Main {
    public static void main(String[] args)
    {
        BTree s=new BTree(2);
        for(int i = 11; i>=0; i--){
            s.insert(i,i);
        }
        s.insert(12,12);
//        s.insert(1,1);
//        s.insert(2,2);
//        s.insert(3,3);
//        s.insert(4,4);
//        s.insert(5,5);
//        s.insert(6,6);
//        s.insert(12,17);
//        s.insert(13,18);
//        s.insert(15,19);
//        s.insert(14,100);
//        s.insert(20,101);
//        s.insert(30,102);
//        s.insert(3,103);
//        s.insert(2,104);
        s.print(s.getRoot() , true);
//
//        s.insert(1,105);
//        s.insert(0,106);
//        System.out.println(s.search(7));
//        s.print(s.getRoot());
    }

}
