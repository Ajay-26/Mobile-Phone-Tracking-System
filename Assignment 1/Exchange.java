import java.util.*;

public class Exchange{
	// forms the nodes of the tree routingmaptree
	public int number;
	public Exchange parent;
	public ExchangeList listOfChildren;
	public MobilePhoneSet mobileSet;
	public Exchange getParent(){
		//returns parent of the node
		return this.parent;
	}
	public Exchange child(int i){
		//returns the child at the ith position. Indexing from 1 to numChildren
		try{
			if(null == this){
				return null;
			}
			node curr = this.listOfChildren.newExchangeList.head;
			for(int l=0; curr != null; l++){
				curr = curr.next;
				if(l == i){
					return (Exchange)curr.data;
				}
			}
			return null;
		}
		catch(Exception ex){
			System.out.println(ex);
			return null;
		}
	}
	public int numChildren(){
		//returns the number of children
		if(null == this){
			return 0;
		}
		int i =0;
		node curr = this.listOfChildren.newExchangeList.head;
		while(null != curr){
			curr = curr.next;
			i++;
		}
		return i;
	}
	public boolean isRoot(){
		//returns 1 if given node is root. returns 0 otherwise
		return(null == this.parent);
	}
	public boolean isExternal(){
		//returns 1 if given node is external node, 0 otherwise
		try{
			if(null == this){
				return true;
			}else{
				return (null == this.listOfChildren.newExchangeList.head);
			}
		}
		catch(Exception ep){
			System.out.println(ep);
			return false;
		}
	}
	public RoutingMapTree subTree(int i){
		//returns the subtree at the ith position
		RoutingMapTree newTree = new RoutingMapTree(null);
		newTree.root = this.child(i);
		return newTree;
	}
	public void addChild(Exchange b){
		this.listOfChildren.newExchangeList.insertRear(b);
		this.mobileSet.newMobile.union(b.mobileSet.newMobile);
		return;
	}
	public MobilePhoneSet residentSet(){
		//returns the resident set of the mobile phones of the exchange
		return this.mobileSet;
	}
	public Exchange(int number){
		//constructor for Exchange. unique identification for Exchange is number
		this.number = number;
	}
}
