package eg.edu.alexu.csd.filestructure.btree;

//import eg.edu.alexu.csd.filestructure.btree.BNode.point;

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

	public void nonfullInsert(BNode x, Comparable key,Object value) {
		int i=x.getNumOfKeys();
		if(x.isLeaf())
		{
			List<K> keys=new ArrayList<K>();
			keys=x.getKeys();
			List<K> values=new ArrayList<K>();
			values=x.getValues();
			if(keys.size()<Mindegree*2-1){
			keys.add(null);
			values.add(null);}
			while(i >= 1 && key.compareTo(keys.get(i-1))<0 )//here find spot to put key.
			{
				keys.set(i,keys.get(i-1));
				values.set(i,values.get(i-1));
				i--;
			}

			keys.set(i, (K) key);
			values.set(i, (K) value);
			if(x.getNumOfKeys()==0) {
				keys.add(i, (K) key);
				values.add(i, (K) value);
			}
			else {
				keys.set(i, (K) key);
				values.set(i, (K) value);
			}
			x.setKeys(keys);
			x.setValues(values);
		}
		else {
			int j = 0;
			List<K> keys = new ArrayList<K>();
			List<K> values = new ArrayList<K>();

			if(x.getKeys()!=null){
			keys = x.getKeys();
			values=x.getValues();
			}
			while (j < x.getNumOfKeys() && key.compareTo(keys.get(j)) > 0)//find spot to recurse
			{                         //on correct child
				j++;
			}

			if (x.getChildren() != null) {
				List<BNode<K, V>> childx = new ArrayList<BNode<K, V>>();
				childx = x.getChildren();
				if (childx.get(j).getNumOfKeys() == Mindegree * 2 - 1) {
					split(x, j, childx.get(j));//call split on node x's ith child
					List<K> keysx = new ArrayList<K>();
					keysx = x.getKeys();
					List<K> valuessx = new ArrayList<K>();
					valuessx = x.getValues();
					if (key.compareTo(keysx.get(j)) > 0) {
						j++;
					}
				}
				childx = x.getChildren();
				nonfullInsert(childx.get(j), key,value);//recurse
			}
		}

	}

	@Override
	public void insert(Comparable key, Object value) {

		BNode root = (BNode) getRoot();
		if (root == null) {
			this.root = new BNode(Mindegree * 2, null);
			List<K> keys = new ArrayList<K>();
			List<K> values = new ArrayList<K>();

			keys.add((K) key);
			values.add((K) value);
			this.root.setKeys(keys);
			this.root.setValues(values);
			return;
		}

		if (root.getNumOfKeys() == Mindegree * 2 - 1) {
//			boolean ss = true;
//			if (root.getChildren() != null) {
//
//				ss = splitroot(key);
//			}
//			if (ss) {
				BNode s = new BNode(Mindegree * 2, null);
				this.root = s;
				s.setLeaf(false);
				s.setNumOfKeys(0);
				List<BNode<K, V>> childs = new ArrayList<BNode<K, V>>();
				childs.add(root);
				s.setChildren(childs);
				split(s, 0, root);
				nonfullInsert(s, key, value); //call insert method
			} else
				nonfullInsert(root, key, value);//if its not full just insert it


	}



//	public boolean splitroot(Comparable key){
//
//		List<IBTreeNode<K, V>>child=root.getChildren();
//		int k=0;
//		List<K> keys=root.getKeys();
//		while (k<root.getNumOfKeys() && key.compareTo(keys.get(k))>0) {
//			k++;
//		}
//		if(child.get(k).getNumOfKeys()==Mindegree*2-1){
//
//
//		}
//			return false;
//		}


	public void split(BNode x, int i, BNode y)
	{
		BNode z = new BNode(Mindegree*2,null);
		z.setLeaf(y.isLeaf());
		z.setNumOfKeys(Mindegree-1);
		List<K> keys=new ArrayList<K>();
		keys=y.getKeys();
		List<K> values=new ArrayList<K>();
		values=y.getValues();
		List<K> keysz=new ArrayList<K>();
		List<K> valuesz=new ArrayList<K>();

		for(int j = 0; j < Mindegree - 1; j++)
		{
			keysz.add(keys.get(j+Mindegree));
			valuesz.add(values.get(j+Mindegree));
		}
			z.setKeys(keysz);
			z.setValues(valuesz);

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

		List<K> keysx=new ArrayList<K>();
		List<K> valuesx=new ArrayList<K>();

		if(x.getKeys()!=null) {
			keysx = x.getKeys();
			valuesx=x.getValues();
		}
		if(keysx.size()<Mindegree*2-1){
		keysx.add(null);
		valuesx.add(null);}
		for(int j = x.getNumOfKeys(); j> i; j--)
		{
			keysx.set(j,keysx.get(j-1));
			valuesx.set(j,valuesx.get(j-1));
		}
		List<K> keysy=new ArrayList<K>();
		List<K> valuesy=new ArrayList<K>();

		keysy=y.getKeys();
		valuesy=y.getValues();
		keysx.set(i,keysy.get(Mindegree-1));
		valuesx.set(i,valuesy.get(Mindegree-1));
		x.setKeys(keysx);
		x.setValues(valuesx);

		for(int j = 0; j < Mindegree ; j++)
		{
			keysy.remove(Mindegree-1);
			valuesy.remove(Mindegree-1);
		}
		y.setKeys(keysy);
		y.setValues(valuesy);
		List<BNode<K, V>>childx=new ArrayList<BNode<K, V>>();
		childx=x.getChildren();
		if(childx.size()<Mindegree*2)
		childx.add(null);
		for(int j = x.getNumOfKeys()-1 ; j> i ; j--)
		{
			childx.set(j+1,childx.get(j));
//			x.child[j+1] = x.child[j]; //shift children of x
		}
		childx.set(i+1,z);
		x.setChildren(childx);


	}

	@Override
	public Object search(Comparable key) {
		if(key==null)return null;
		BNode node=root;
		V found=null;
		while(true) {
			ArrayList<K> ke=(ArrayList<K>) node.getKeys();
			ArrayList<K> va=(ArrayList<K>) node.getValues();
			ArrayList<K> ch=(ArrayList<K>) node.getChildren();
			int i=0;
			for(i=0;i<ke.size();i++) {
				int comp=key.compareTo(ke.get(i));
				if(comp==0) {
					found=(V) va.get(i);
					break;
				}else if(comp<0) {
					node=(BNode) ch.get(i);
					break;
				}
			}
			if(found==null && i==ke.size())node=(BNode) ch.get(i);
			if(found!=null||node == null) {
				break;
			}
			
		}
		if(found==null)return null;
		else return found;
		
	}

	@Override
	public boolean delete(Comparable key) {
		// TODO Auto-generated method stub
		return false;
	}
	public BNode searching(Comparable key) {
		if(key==null)return null;
		BNode node=root;
		V found=null;
		while(true) {
			ArrayList<K> ke=(ArrayList<K>) node.getKeys();
			ArrayList<K> va=(ArrayList<K>) node.getValues();
			ArrayList<K> ch=(ArrayList<K>) node.getChildren();
			int i=0;
			for(i=0;i<ke.size();i++) {
				int comp=key.compareTo(ke.get(i));
				if(comp==0) {
					found=(V) va.get(i);
					return node;
				}else if(comp<0) {
					node=(BNode) ch.get(i);
					break;
				}
			}
			if(found==null && i==ke.size())node=(BNode) ch.get(i);
			if(found!=null||node == null) {
				break;
			}
			
		}
		return null;
		
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
			for(int j = 0; j <= n.getNumOfKeys()  ; j++)//in this loop we recurse
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
