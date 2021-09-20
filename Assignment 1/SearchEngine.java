import java.util.*;

class node<t>{
	public t data;
	public node next;
	public node(t data){
		this.data = data;
		this.next = null;
	}
}
class myLinkedList<t>{
	public node<t> head;
	public myLinkedList(){
		head = null;
	}
	public boolean isEmpty(){
		return (null == head);
	}
	public boolean isMember(t item){
		node<t> curr = head;
		while(null != curr){
			if(item == curr.data){
				return true;
			}
			curr = curr.next;
		}
		if(null == curr){
			return false;
		}
	}
	public void insertFront(t item){
		node<t> newHead = new node<t>(item);
		if(this.isEmpty()){
			head = newHead;
			return;
		}else{
			newHead.next = this.head;
			head = newHead;
			return;
		}
	}
	public void insertRear(t item){
		node<t> newNode = new node<t>(item);
		if(null == head){
			head = newNode;
			return;
		}else if(null == head.next){
			head.next = newNode;
			return;
		}else{
			node<t> curr = head;
			while(curr.next != null){
				curr = curr.next;
			}
			curr.next = newNode;
			return;
		}
	}
	public void deleteFront(){
		if(null == head){
			return;
		}else if(null == head.next){
			head = null;
		}else{
			node<t> curr = head;
			head = head.next;
			curr = null;
			return;
		}
	}
	public void deleteRear(){
		if(null == head){
			return;
		}else if(null == head.next){
			head = null;
			return;
		}else{
			node<t> curr = head;
			while(curr.next != null){
				curr = curr.next;
			}
			curr = null;
			return;
		}
	}
}

class mySet<X>{
	public X element;

}

class Position{
   	public page p;
   	public position i;
}

class wordEntry{

}

class pageIndex{
	public void addPositionForWord(String str, Position P){

	}
	public myLinkedList<wordEntry> getWordEntries(){

	}
}

class pageEntry{
	public pageEntry(String pageName){

	}
	public pageIndex getPageIndex(){

	}
}

class myHashTable{
	private int getHashIndex(String str){

	}
	public void addPositionForWord(WordEntry w){

	}
}

class invertedPageIndex{
	public void addPage(pageEntry p){

	}
	public mySet<pageEntry> getPagesWhichContainWord(String str){

	}
}

public class SearchEngine{
	public SearchEngine(){

	}
	public void performAction(String actionMessage){

	}
}