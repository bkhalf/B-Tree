package eg.edu.alexu.csd.filestructure.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BNode <K extends Comparable<K>, V> implements IBTreeNode<K, V> {

	class Point<K extends Comparable<K>, V>{
		 K key=null;
		 V value=null;
	}

	private boolean leaf;
	private int numberOfKeys;
	private int numberOfChildren;
	private BNode [] children;
	private BNode parent;
	private Point<K,V>[] data;

	public BNode (int numberOfChildren,BNode Parent) {
		numberOfKeys =0;
		leaf=true;
		this.numberOfChildren=numberOfChildren;
		children = new BNode [numberOfChildren];
		Arrays.fill(this.children, null);
		data = new Point[numberOfChildren-1];
		Arrays.fill(data,null);
		this.parent=Parent;
	}


	@Override
	public int getNumOfKeys() {
		return numberOfKeys;
	}

	@Override
	public void setNumOfKeys(int numOfKeys) {
		this.numberOfKeys =numOfKeys;
	}

	@Override
	public boolean isLeaf() {
		return leaf;
	}

	@Override
	public void setLeaf(boolean isLeaf) {
		this.leaf=isLeaf;
	}

	@Override
	public List<K> getKeys() {
		if(numberOfKeys ==0)
			return null;
		List<K>ans=new ArrayList<>();
		for(int i = 0; i< numberOfKeys; i++) {
			ans.add(data[i].key);
		}
		return ans;
	}

	@Override
	public void setKeys(List<K> keys) {
		if(keys.size()<numberOfChildren) {
			for(int i=0;i<keys.size();i++) {
				data[i]=new Point();
				data[i].key=keys.get(i);
			}
			numberOfKeys =keys.size();
		}
	}

	@Override
	public List<V> getValues() {
		if(numberOfKeys ==0)
			return null;
		List<V>ans=new ArrayList<V>();
		for(int i = 0; i< numberOfKeys; i++) {
			ans.add(data[i].value);
		}
		return ans;
	}

	@Override
	public void setValues(List<V> values) {
		if(values.size()<numberOfChildren) {
			for(int i=0;i<values.size();i++) {
				data[i].value=values.get(i);
			}
		}
	}

	@Override
	public List<IBTreeNode<K, V>> getChildren() {
		if(numberOfKeys ==0)
			return null;
		List<IBTreeNode<K, V>> ans = new ArrayList<>();
		for(int i = 0; i< numberOfChildren /*&&children[i] !=null*/; i++) {
			ans.add(children[i]);
		}
		return ans;
	}

	@Override
	public void setChildren(List<IBTreeNode<K, V>> children) {
//		are children sorted?!
		if(children.size()<=numberOfChildren) {
			for(int i=0;i<children.size();i++) {
				this.children[i]=(BNode) children.get(i);
			}
		}
	}
	public BNode getParent() {
		return parent;
	}

	public void setParent(BNode parent) {
		this.parent = parent;
	}
}
