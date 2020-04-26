package eg.edu.alexu.csd.filestructure.btree;

import eg.edu.alexu.csd.filestructure.btree.BNode.point;

public class BTree<K extends Comparable<K>, V> implements IBTree <K,V> {
	
	public BNode root;

	@Override
	public int getMinimumDegree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IBTreeNode getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Comparable key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object search(Comparable key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Comparable key) {
		// TODO Auto-generated method stub
		return false;
	}

}
