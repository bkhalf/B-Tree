package eg.edu.alexu.csd.filestructure.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BNode <K extends Comparable<K>, V> implements IBTreeNode<K, V> {


	private boolean leaf;
	private int numberOfChildren;
	private IBTreeNode<K, V>[] children;
	private IBTreeNode<K, V> parent;
	private List<Point<K,V>> data;

	public BNode (int numberOfChildren,IBTreeNode<K, V> Parent) {
		leaf=true;
		this.numberOfChildren = numberOfChildren;
		children = new BNode[numberOfChildren+1];
		Arrays.fill(children , null);
		data = new ArrayList<>();
		this.parent=Parent;
	}

// to be revised
	@Override
	public int getNumOfKeys() {
		return data.size();
	}

	@Override
	public void setNumOfKeys(int numOfKeys) {
		data =data.subList(0,numOfKeys);
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
		if( data.isEmpty())
			return null;
		List<K> ans = new ArrayList<>();
		for (Point<K, V> datum : data) {
			ans.add(datum.getKey());
		}
		return ans;
	}

	@Override
	public void setKeys(List<K> keys) {
		if(keys.size()<numberOfChildren && keys.size()<=data.size()) {
			for(int i=0;i<keys.size();i++) {
				data.get(i).setKey(keys.get(i));
			}
		}
	}

	@Override
	public List<V> getValues() {
		if( data.isEmpty())
			return null;
		List<V> ans = new ArrayList<>();
		for (Point<K, V> datum : data) {
			ans.add(datum.getValue());
		}
		return ans;
	}

	@Override
	public void setValues(List<V> values) {
		if(values.size()<numberOfChildren && values.size()<=data.size()) {
			for(int i=0;i<values.size();i++) {
				data.get(i).setValue(values.get(i));
			}
		}
	}

	@Override
	public List<IBTreeNode<K, V>> getChildren() {
		List<IBTreeNode<K,V>> list = new ArrayList<>();
		for(int i = 0 ; i < children.length && children[i]!= null; i++){
			list.add(children[i]);
		}
		return list;
	}

	public IBTreeNode<K,V> getChild(int index){
		return children[index];
	}
	public void addChild(IBTreeNode<K,V> node){
		int i;
		for(i=0 ; i < children.length; i++){
			if(children[i]== null)
				break;
		}
		children[i] = node;
	}
	public void addChildAfter(IBTreeNode<K,V> prev ,IBTreeNode<K,V> node){
//		int index=0;
//		for(int i=0 ; i < children.length&& children[i]!= null; i++){
//			if(children[i]== prev){
//				index = i+1;
//				break;
//			}
//		}
		int i = numberOfChildren-1;
		while (true){
			if(children[i]==prev){
				break;
			}else {
				children[i+1]=children[i];
			}
			i--;
		}
		children[i+1] = node;
	}
	public void addChild(int index,IBTreeNode<K,V> node){
		children.add(index,node);
	}
	public void removechild(IBTreeNode<K,V> node){
		children.remove(node);
	}
	@Override
	public void setChildren(List<IBTreeNode<K, V>> children) {
		children.sort(Comparator.comparing(o -> o.getKeys().get(0)));
		for(int i = 0 ; i < children.size() ; i++){
			this.children[i] = children.get(i);
		}
	}
	public List<Point<K, V>> getData() {
		return data;
	}

	public void setData(List<Point<K, V>> data) {
		this.data = data;
	}

	public void addPoint(Point<K,V> point){
		data.add(point);
		data.sort(Comparator.comparing(Point::getKey));
	}
	public void removePoint(Point<K,V> point){
		data.remove(point);
		data.sort(Comparator.comparing(Point::getKey));
	}
	public Point getpoint(int index){
		return data.get(index);
	}

	public IBTreeNode<K, V> getParent() {
		return parent;
	}

	public void setParent(IBTreeNode<K, V> parent) {
		this.parent = parent;
	}
}