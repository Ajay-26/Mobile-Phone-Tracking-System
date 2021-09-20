import java.util.*;

class node<Object>{
	public Object data;
	public node next;
	public void display(){
		System.out.println(this.data);
	}
	public void setData(Object obj){
		this.data = obj;
	}
	public node (Object obj){
		this.data = obj;
		this.next = null;
	}
	public Object getData(){
		return(this.data);
	}
}

public class linkedList{
	public node<Object> head;
	//public int length;
	public int getLength(){
		node<Object> curr = head;
		int i=0;
		while(curr != null){
			curr = curr.next;
			i++;
		}
		return i;
	}
	public boolean isEmpty(){
		return (null == head);
	}
	public boolean isMember(Object obj){
		node<Object> curr = head;
		while(null != curr){
			if(curr.data == obj){
				return true;
			}
			curr = curr.next;
		}
		return false;
	}
	public void insertFront(Object obj){
		node<Object> curr = new node<>(obj);
		curr.next = head;
		head = curr;
		return;
	}
	public void insertRear(Object obj){
		node<Object> curr new node<>(obj);
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
					if(curr.next.data == o){
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
}
