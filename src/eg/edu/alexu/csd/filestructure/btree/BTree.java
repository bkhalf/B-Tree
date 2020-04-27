package eg.edu.alexu.csd.filestructure.btree;

import eg.edu.alexu.csd.filestructure.btree.BNode.point;

import java.util.ArrayList;
import java.util.List;

public class BTree<K extends Comparable<K>, V> implements IBTree <K,V> {
	private BNode root=null;
	private int Mindegree;

	public BTree( int mindegree) {
		this.Mindegree = mindegree;
	}

	@Override
	public int getMinimumDegree() {
		return Mindegree;
	}

	@Override
	public IBTreeNode getRoot() {
		return root;
	}

	public void nonfullInsert(BNode x, Comparable key) {
		int i=x.getNumOfKeys();
		if(x.isLeaf())
		{
			List<K> keys=new ArrayList<K>();
			keys=x.getKeys();
			keys.add(null);
			while(i >= 1 && key.compareTo(keys.get(i-1))<0 )//here find spot to put key.
			{
				keys.set(i,keys.get(i-1));
				i--;
			}

			keys.set(i, (K) key);

			if(x.getNumOfKeys()==0)
			    keys.add(i, (K) key);
			else
				keys.set(i, (K) key);

			x.setKeys(keys);
		}
		else {
			int j = 0;
			List<K> keys = new ArrayList<K>();
			if(x.getKeys()!=null)
			keys = x.getKeys();
			while (j < x.getNumOfKeys() && key.compareTo(keys.get(j)) > 0)//find spot to recurse
			{                         //on correct child
				j++;
			}

			if (x.getChildren() != null) {
				List<BNode<K, V>> childx = new ArrayList<BNode<K, V>>();
				childx = x.getChildren();
				if (childx.get(j-1).getNumOfKeys() == Mindegree * 2 - 1) {
					split(x, j, childx.get(j));//call split on node x's ith child
					List<K> keysx = new ArrayList<K>();
					keysx = x.getKeys();
					if (key.compareTo(keysx.get(j)) > 0) {
						j++;
					}
				}

				nonfullInsert(childx.get(j-1), key);//recurse
			}
		}

	}

	@Override
	public void insert(Comparable key, Object value) {

		BNode root= (BNode) getRoot();
		if(root==null){
			this.root =new BNode(Mindegree*2,null);
			List<K> keys=new ArrayList<K>();
			keys.add((K) key);
			this.root.setKeys(keys);
			return;
		}

		if(root.getNumOfKeys()==Mindegree*2-1){
			BNode s=new BNode(Mindegree*2,null);
			this.root=s;
			s.setLeaf(false);
			s.setNumOfKeys(0);

			s.children[0]=root;
			split(s,0,root);
			nonfullInsert(s, key); //call insert method
		}
		else
			nonfullInsert(root,key);//if its not full just insert it
		}




	public void split(BNode x, int i, BNode y)
	{
		BNode z = new BNode(Mindegree*2,null);
		z.setLeaf(y.isLeaf());
		z.setNumOfKeys(Mindegree-1);
		List<K> keys=new ArrayList<K>();
		keys=y.getKeys();
		List<K> keysz=new ArrayList<K>();
		for(int j = 0; j < Mindegree - 1; j++)
		{
			keysz.add(keys.get(j+Mindegree));
		}
			z.setKeys(keysz);

		if(!y.isLeaf())//if not leaf we have to reassign child nodes.
		{
			List<IBTreeNode<K, V>>child=new ArrayList<IBTreeNode<K, V>>();
			child=y.getChildren();

			for(int k = 0; k < Mindegree; k++)
			{
//				child.set()
				child.remove(Mindegree);
//				z.child[k] = y.child[k+order]; //reassing child of y
			}
			z.setChildren(child);

		}
		y.setNumOfKeys(Mindegree);


		List<BNode<K, V>>childx=new ArrayList<BNode<K, V>>();
		childx=x.getChildren();
		BNode zz=z;
		BNode yy=y;
		if (childx==null){
				List<BNode<K, V>>childxx=new ArrayList<BNode<K, V>>();
				childxx.add(zz);
				childxx.add(yy);
				x.setChildren(childxx);
			}
			else{
		for(int j = x.getNumOfKeys() ; j> i ; j--)
		{
			childx.set(j+1,childx.get(j));
//			x.child[j+1] = x.child[j]; //shift children of x
		}
		childx.set(i+1,z);
		x.setChildren(childx);}
		List<K> keysx=new ArrayList<K>();
			if(x.getKeys()!=null)
				keysx=x.getKeys();
		keysx.add(null);
		for(int j = x.getNumOfKeys(); j> i; j--)
		{
			keysx.set(j+1,keysx.get(j));
		}
		List<K> keysy=new ArrayList<K>();
		keysy=y.getKeys();
		keysx.set(i,keysy.get(Mindegree-1));
		keysy.remove(Mindegree-1);
        x.setKeys(keysx);
//		for(int j = 0; j < Mindegree - 1; j++)
//		{
//			keysy.remove(Mindegree);
//		}
		y.setKeys(keysy);

	}

	@Override
	public Object search(Comparable key) {
		if(key==null)return null;
		BNode node=root;
		point found=null;
		while(true) {
			ArrayList<K> ke=(ArrayList<K>) node.getKeys();
			int i=0;
			for(i=0;i<ke.size();i++) {
				int comp=key.compareTo(ke.get(i));
				if(comp==0) {
					found=node.data[i];
					break;
				}else if(comp<0) {
					node=node.children[i];
					break;
				}
			}
			if(found!=null && i==ke.size())node=node.children[i+1];
			if(found!=null||node == null) {
				break;
			}
			
		}
		if(found==null)return null;
		else return found.value;
		
	}

	@Override
	public boolean delete(Comparable key) {
		// TODO Auto-generated method stub
		return false;
	}
	public void print(IBTreeNode<K,V>  n)
	{
		List<K> keysn=new ArrayList<K>();
		keysn=n.getKeys();

		for(int i = 0; i < n.getNumOfKeys(); i++)
		{
			System.out.print(keysn.get(i)+" " );//this part prints root node
		}

		if(!n.isLeaf())//this is called when root is not leaf;
		{
			for(int j = 0; j < n.getNumOfKeys()  ; j++)//in this loop we recurse
			{				  //to print out tree in
				List<IBTreeNode<K, V>>childn=new ArrayList<IBTreeNode<K, V>>();
				childn=n.getChildren();
				if(childn.get(j) != null) //preorder fashion.
				{			  //going from left most
					System.out.println();	  //child to right most
					print(childn.get(j));     //child.
				}
			}
		}
	}


}
