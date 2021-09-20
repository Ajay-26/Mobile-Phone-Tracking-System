import.java.util.*;

/*class node<Object>{
	public Object data;
	public node next;
	public void display(){
		System.out.println(this.data);
	}
	public node<Object>(Object obj){
		this.data = obj;
		this.next = ptr;
	}
}

class linkedList{
	public node<Object> head;
	public int getLength(){
		node<Object> curr = head;
		int i=0;
		while(curr != null){
			curr = curr.next;
			i++;
		}
		return i;
	}
	public boolean isEmpty{
		return (null == head);
	}
	public boolean isMember(Object obj){
		node<Object> curr = head;
		while(null != curr){
			if(curr.data == obj){
				return 1;
			}
			curr = curr.next;
		}
		return 0;
	}
	public void insertFront(Object obj){
		node<Object> curr = new node<>(obj);
		curr.next = head;
		head = curr;
		return;
	}
	public void insertRear(Object obj){
		node<Object> curr;
		if(null == head){
			curr = new node<>(obj);
			return;
		}else{
			while(null != curr.next){
				curr = curr.next;
			}
			node<Object> newNode = new node<>(obj);
			curr.next = newNode;
			return;
		}
	}
	public void delete(Object o){
		try{
			if(null == head){
				return;
			}else{
				node<Object> curr = head;
				while(curr.next != null){
					if(curr.next.data == obj){
						curr.next = curr.next.next;
					}
					curr = curr.next;
				}
				return;
			}
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
}*/

public class MySet{
	public linkedList list;
	public boolean isEmpty(){
		return this.list.isEmpty();
	}
	public boolean isMember(Object obj){
		return this.list.isMember(obj);
	}	
	public void insert(Object o){
		// inserts object o into the set, assuming insertion at rear
		if(!this.isMember(o)){		
			this.list.insertRear(o);
		}
		return;
	}
	public void delete(Object o){
		this.list.delete(o);
	}
	public MySet union(MySet a){
		// returns a set which is the union of the current set with a set a
		MySet unionSet = new MySet();
		node<Object> curr = this.list.head;
		while(null != curr){
			unionSet.insert(curr.data);
			curr = curr.next;
		}
		curr = a.list.head;
		while(null != curr){
			if(this.isMember(curr.data)){
				unionSet.insert(curr.data);
			}
			curr = curr.next;
			}
		return unionSet;
	}

	public MySet intersection(MySet a){
		MySet intersectionSet = new MySet();
		node<Object> curr = this.list.head;
		while(null != curr){
			if(a.isMember(curr.data)){
				intersectionSet.insert(curr.data);
			}
			curr = curr.next;
		}
		return intersectionSet;
	}
}
/*public class MySet<Object>{
	public node<Object> head;
	public MySet(Object o){
		head.data = o;
		head.next = null;
	}
	public boolean isEmpty(){
		//returns 0 if the set is empty
		return(null == head); 
	}
	public boolean isMember(Object o){
		// returns 1 if o is in the set
		node<Object> curr = head;
		while(null != curr){
			if(curr.data == o){
				return 1;
			}
			curr = curr.next;
		}
	}
	public void insert(Object o){
		//insets object o in the set
		if(null == head){
			node<Object> curr = new node();
			curr.next = null;
			head = curr;
			return;
		}else{
			while(curr.next != null){
				curr = curr.next;
			}
			node<Object> newNode = new node<>();
			newNode.data = o;
			newNode.next = null;
			curr.next = null;
			return;
		}


		}
	public void delete(Object o){
		//deletes object o from the set. Gives an exception if o is not in the set
		try{
			if(null == head.next){
				head = null;
				return;
			}else{
				node<Object> curr = head;
				head = head.next;
				curr = null;
				return;
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public MySet union(Myset a){
		//returns a set which is the union of the current set with a
		MySet unionSet = new MySet(head.data);
		//node prt = unionSet.head;
		node curr<Object> = head;
		while(curr != null){
			/*	ptr.data = curr.data;
			curr = curr.next;
			ptr = ptr.next;
			unionSet.insert(curr.data);
			curr = curr.next;
		}
		curr = a.head;
		while(curr != null){
			if(unionSet.isMember(curr.data)){
			/*ptr.data = curr.data;
			curr = curr.next;
			ptr = ptr.next;
			unionSet.insert(curr.data);
			curr = curr.next;
			}
		}
		return unionSet;
	}	
	public Myset intersection(Myset a){
		//returns intersection of set a with current set
		node<Object> curr = this.head;
		while(a.isMember(curr.data) && null != curr){
			curr = curr.next;
		}
		MySet intersectionSet = new MySet(curr.data);
		while(null != curr){
			if(a.isMember(curr.data)){
				intersectionSet.insert(curr.data);
			}
			curr = curr.next;
		}
		curr = a.head;
		while(null != curr){
			if(this.isMember(curr.data)){
				intersectionSet.insert(curr.data);
			}
			curr = curr.next;
		}
		return intersectionSet;
	}
}*/
