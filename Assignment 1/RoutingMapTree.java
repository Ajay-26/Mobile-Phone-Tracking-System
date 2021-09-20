import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class RoutingMapTree{

	public RoutingMapTree() {
		//
		this.root = new Exchange(0);
	}
	public static void main ( String args [])
	{
		BufferedReader br = null;
		RoutingMapTree r = new RoutingMapTree();

		try {
			String actionString;
			br = new BufferedReader(new FileReader("actions.txt"));

			while ((actionString = br.readLine()) != null) {
				r.performAction(actionString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public String performAction(String actionMessage) {
	//	System.out.println(actionMessage);
        String actionWord = "";
		int i=0;
		while(actionMessage.charAt(i) != ' '){
			actionWord = actionWord + actionMessage.substring(i,i+1);
			i++;
		}
		

		if(actionWord.equals("addExchange")){
			int a = (int)(actionMessage.charAt(i+1)) - 48;
			int b = (int)(actionMessage.charAt(i+3)) - 48;
			Exchange myNode = findExchange(a);
			try{
				if(null == myNode){
				 //   System.out.println("myNode is null");
					throw new Exception();
				}else{
					Exchange newChild = new Exchange(b);
					//System.out.println("Code at line 30");
					myNode.addChild(newChild);
					newChild.parent = myNode;
			//		System.out.println(myNode.child(2).number);
			//	    System.out.println("Code at line 31");
			//		System.out.println(myNode.numChild());
					return "";
				}
			}catch(Exception en){
			//	System.out.println(en);
			}
		
		}else if(actionWord.equals("queryNthChild")){
		    //System.out.println(actionMessage);
			int a = (int)(actionMessage.charAt(i+1)) - 48;
			int b = (int)(actionMessage.charAt(i+3)) - 48;
			Exchange myNode = findExchange(a);
			try{
    			if(null == myNode){
    				throw new Exception("Error- No " + b + " child with identifier " + a + "\n");
    			}else{
				    //System.out.println(myNode.child(b).number);
				    String y = actionMessage + ": "+ (myNode.child(b).number);
				    return y;
			    }
			}
			catch(Exception ex){
			    System.out.println("Error- No " + b + " child with identifier " + a);
			    return "";
			}
		
		}else if(actionWord.equals("switchOnMobile")){
		    i = i+1;
		    String inputInt = "";
		    while(actionMessage.charAt(i) != ' '){
		        inputInt = inputInt + actionMessage.substring(i,i+1);
		        i++;
		    }
		    
		    int a =Integer.parseInt(inputInt);
		    int b = Integer.parseInt(actionMessage.substring(i+1,actionMessage.length()));
		//	System.out.println("Numbers a and b are " + a + " " + b);
		//	node curr = this.root.residentSet().newMobile.list.head;
			try{
				/*while(curr != null){
					if(a == ((MobilePhone)(curr.data)).number){
						this.switchOn(((MobilePhone)curr.data),((MobilePhone)(curr.data)).location());
						return "";
					}
					curr = curr.next;
				}*/
				Exchange myNode = findExchange(b);
				if(myNode == null){
				    throw new Exception("Error-No exchange with identifier " + b);
				}else{
				    MobilePhone newPhone = new MobilePhone(a);
				    newPhone.baseStation = myNode;
				    newPhone.status = false;
				    newPhone.baseStation = myNode;
				    this.switchOn(newPhone,myNode);
				    return "";
				}
			}
			catch(Exception en){
				System.out.println("Error-No exchange with identifier " + b);
				return "";
			}
						
		}else if(actionWord.equals("queryMobilePhoneSet")){
			int a = (int)(actionMessage.charAt(i+1)) - 48;
			Exchange myNode = findExchange(a);
			if(null == myNode){
			    return "";
			}
			String answer = "";
			if(myNode.residentSet().newMobile.isEmpty()){
			    //System.out.println("Empty");
			    return answer;
			}
			node curr = myNode.residentSet().newMobile.list.head; 
			while(curr != null){
			    if(curr.next != null){
				    answer = answer + (((MobilePhone)(curr.data)).number + ", ");
			    }else{
			        answer = answer + (((MobilePhone)(curr.data)).number);
			    }
			//	System.out.print((((MobilePhone)(curr.data)).number + " "));
				curr = curr.next;
			}
			System.out.print("\n");
			return actionMessage + ": " + answer;
		}else if(actionWord.equals("switchOffMobile")){
		    i = i+1;
		    String inputInt = "";
		    while(i< actionMessage.length()){
		        inputInt = inputInt + actionMessage.substring(i,i+1);
		        i++;
		    }
		    i--;
		    int a =Integer.parseInt(inputInt);
		   // System.out.println("Number is " + a);
		//	int a = (int)(actionMessage.charAt(i+1)) - 48;
			node curr = this.root.residentSet().newMobile.list.head;
			try{
				while(curr != null){
					if(a == ((MobilePhone)(curr.data)).number){
					   // System.out.println("Found it");
						this.switchOff((MobilePhone)(curr.data));
						return "";
					}
					curr = curr.next;
				}
				throw new Exception("Error-No mobile phone with identifier " + a);
			}
			catch(Exception en){
				//System.out.println(" Error - No mobile phone with identifier " + a);
				return "";
			}
		}else if(actionWord.equals("findPhone")){
			int a = Integer.parseInt(actionMessage.substring(i+1,actionMessage.length()));
			try{
				MobilePhone targetMobile = this.root.getTargetPhone(a);
				if(null == targetMobile){
					throw new Exception();
				}
				Exchange targetExchange = targetMobile.baseStation;
				//System.out.println(actionMessage + " - " + targetExchange.number);
				return actionMessage + " - " + Integer.toString(a);
			}
			catch(Exception en){
				//System.out.println(actionMessage + " - " + "Error - Mobile Phone of identifier " + actionMessage.substring(i+1,actionMessage.length()) + " is switched off.");
				return (actionMessage + " - " + "Error - Mobile Phone of identifier" + actionMessage.substring(i+1,actionMessage.length()) + " is switched off.");
			}
		}
		else if(actionWord.equals("lowestRouter")){
			int a = (int)(actionMessage.charAt(i+1)) - 48;
			int b = (int)(actionMessage.charAt(i+3)) - 48;
			try{
				Exchange e1 = findExchange(a);
				Exchange e2 = findExchange(b);
				if(this.lowestRouter(e1,e2) == null){
					throw new Exception();
				}else{
					//System.out.println(actionMessage + " - " + this.lowestRouter(e1,e2).number);
					return actionMessage + " - " + Integer.toString(this.lowestRouter(e1,e2).number);
				}
			}
			catch(Exception ex){
				return "";
			}
		}else if(actionWord.equals("findCallPath")){
			i = i+1;
			//System.out.println(actionMessage + " ");
		    String inputInt = "";
		    while(actionMessage.charAt(i) != ' '){
		        inputInt = inputInt + actionMessage.substring(i,i+1);
		        i++;
		    }
		    int a =Integer.parseInt(inputInt);
		    int b = Integer.parseInt(actionMessage.substring(i+1,actionMessage.length()));
		    try{
		    	MobilePhone m1 = this.root.getTargetPhone(a);
		    	MobilePhone m2 = this.root.getTargetPhone(b);
		    	if(!(m1.status)){
		    		//System.out.println("Error - Mobile phone with identifier " + a + " is switched off");
		    		return "Error - Mobile phone with identifier " + a + " is switched off";
		    	}else if(!(m2.status)){
		    		//System.out.println("Error - Mobile phone with identifier " + b + " is switched off");
		    		return "Error - Mobile phone with identifier " + b + " is switched off";
		    	}
		    	node curr = this.routeCall(m1,m2).newExchangeList.head;
		    	String answer = "";
		    	while(curr != null){
		    		//System.out.print(((Exchange)curr.data).number + " ");
		    		if(curr.next != null){
		    			answer = answer + Integer.toString(((Exchange)curr.data).number) + " " ;
		    		}else{
		    			answer = answer + Integer.toString(((Exchange)curr.data).number);
		    		}
		    		curr = curr.next;
		    	}
		    	if(answer == ""){
		    		throw new Exception();
		    	}
		    	//System.out.println(actionMessage + " - " + answer + " \n");
		    	return actionMessage + " - " + answer;
		    }
		    catch(Exception en){
		    	return("Exception");
		    }
		}else if(actionWord.equals("movePhone")){
			i = i+1;
			//System.out.println(actionMessage);
		    String inputInt = "";
		    while(actionMessage.charAt(i) != ' '){
		        inputInt = inputInt + actionMessage.substring(i,i+1);
		        i++;
		    }
		    
		    int a =Integer.parseInt(inputInt);
		    int b = Integer.parseInt(actionMessage.substring(i+1,actionMessage.length()));
		    try{
		    	MobilePhone m1 = this.root.getTargetPhone(a);
		    	//System.out.println(m1.baseStation.number);
		    	if(m1 == null){
		    		throw new Exception("Mobile phone with number " + a + " is unavailable.");
		    		//return "Mobile phone with number " + a + " is unavailable.";
		    	}
		    	this.findExchange(b);
		    	if(null == this.findExchange(b)){
		    		throw new Exception("Exchange with number " + b + " does not exist.");
		    		//return "Exchange with number " + b + " does not exist.";
		    	}
		    	this.movePhone(m1,this.findExchange(b));
		    	//System.out.println(m1.baseStation.number);
		    	return "";
		    }
		    catch(Exception en){
		    	return "";
		    }
		}
	
		// return the answer here (as mentioned in answers.txt)
		return actionMessage;
	}
	public Exchange root;
	public boolean isEmpty(){
		//checks whether routing map tree is empty. returns 1 if empty, 0 otherwise
		return (null == this.root); 
	}
	public boolean containsNode(Exchange b){
		//returns 1 if node b is present. 0 otherwise
		if(null == this.root){
			return false;
		}else if(this.root.isExternal()){
			return(b == this.root);
		}else{
			if(b == this.root){
				return true;
			}else{
				node curr = this.root.listOfChildren.newExchangeList.head; // problematic
				while(curr != null){
					if((Exchange)curr.data == b){	
						return true;
					}	
					curr = curr.next;
				}
				return false;
			}
		}
	}
	/*public boolean isExternal(){
		//returns 1 if node has no children. 0 otherwise
		return (null == this.root.listOfChildren.head);
	} Redundant for a tree, makes more sense for a node*/
	public void switchOn(MobilePhone a, Exchange b){
		//switches on a mobile phone with status off. registers a with station b
		try{
			if(a.status){
				throw new Exception();
			}else{
				a.status = true;
				/*b.mobileSet.newMobile.insert(a);
				Exchange curr = b;
				while(null != curr){
					curr.mobileSet.newMobile.insert(a);
					curr = curr.parent;
				}*/
				b.insertMobile(b,a);
				return;
			}
		}
		catch(Exception ea){
		//	System.out.println(ea);
			return;
		}
	}
	public void switchOff(MobilePhone a){
		//switches off a mobile phone a. Entire routing map has to be adjusted properly
		try{
			if(!a.status){
				throw new Exception();
			}else{
				a.status = false;
				Exchange loc = a.baseStation;
				loc.mobileSet.newMobile.delete(a);
				Exchange curr = loc;
				while(null != curr){
					curr.mobileSet.newMobile.delete(a);
					//System.out.println("Executes");
					curr = curr.parent;
				}
				return;
			}
		}
		catch(Exception eb){
			//	System.out.println(eb);
				return;
		}		
	}

	public Exchange findExchange(int i){
	    //returns exchange with number i
	   if(null == this.root){
	     //  System.out.println("Case 1");
	       return null;
	   }else if(this.root.isExternal()){
	       if(this.root.number ==i){
	           //System.out.println("Case 2-found");
	           return this.root;
	       }else{
	           //System.out.println("Mistake- this.root.number somehow became " +this.root.number);
	           //System.out.println("Case 2-not found");
	           return null;
	       }
	   }else{
	       if(this.root.number == i){
	         //  System.out.println("Case 3- root itself");
	           return this.root;
	       }
	       for(int y=0; y< this.root.numChildren(); y++){
	           // System.out.println("Case 3- not found");
	           if(null != this.root.subTree(y).findExchange(i)){
	               return this.root.subTree(y).findExchange(i);
	           }
	       }
	   }
	       return null;
	}
	public Exchange findPhone(MobilePhone m){
		//given a mobile phone it returns its level 0 area exchange
		return m.baseStation;
	}
	/*public Exchange lowestRouter(Exchange a, Exchange b){
		Exchange curr = a;
		try{
			while(!curr.mobileSet.newMobile.list.containsNode(b) && curr != null){
				if(curr.mobileSet.newMobile.list.containsNode(b)){
					return curr;
				}
				curr = curr.parent;
			}
			return null;
		}
		catch(Exception en){
			return null;
		}
	}*/
	public Exchange lowestRouter(Exchange a, Exchange b){
		if(null == this.root){
			return null;
		}else{
				for(int y=0; y< this.root.numChildren(); y++){
					if(this.root.subTree(y).containsNode(a) && this.root.subTree(y).containsNode(b)){
						if(this.root.subTree(y).root != null){
							//System.out.print(this.root.number + " ");
							return this.root.subTree(y).lowestRouter(a,b);
						}
					}
				}
				//System.out.println(this.root.number);
				return this.root;
		}
	}
	/*public ExchangeList routeCall(MobilePhone a, MobilePhone b){
		try{
			System.out.println(a.baseStation.number + " " +  b.baseStation.number + " " + lowestRouter(a.baseStation,b.baseStation).number);
			ExchangeList routingList = new ExchangeList();
			Exchange curr = a.baseStation;
			if(a.baseStation == b.baseStation){
				routingList.newExchangeList.insertRear(a.baseStation);
				return routingList;
			}else{
				while(curr != lowestRouter(a.baseStation,b.baseStation) && curr !=null){
					routingList.newExchangeList.insertRear(curr);
					System.out.println(curr.number + " ");
					curr = curr.parent;
				}
				int y;
				routingList.newExchangeList.insertRear(curr);
				System.out.println(curr.number);
				System.out.println("a");
				while(curr != b.baseStation){
					for(y=0; y<curr.numChildren(); y++){
						if(curr.child(y).mobileSet.newMobile.list.containsNode(b) ){
							//System.out.println(curr.child(y).mobileSet.newMobile.list.containsNode(b));
							routingList.newExchangeList.insertRear(curr.child(y));
							System.out.println(curr.child(y).number);
							break;
						}
					}	
					curr = curr.child(y);
				}
				//System.out.println(curr.number);
				//routingList.newExchangeList.insertRear(curr);
				return routingList;
			}
		}
		catch(Exception ex){
			System.out.println(ex);
			return null;
		}
	}*/
	public ExchangeList routeCall(MobilePhone a, MobilePhone b){
		try{
			ExchangeList routingList = new ExchangeList();
			Exchange curr = a.baseStation;
			if(a.baseStation.number == b.baseStation.number){
				routingList.newExchangeList.insertRear(a.baseStation);
				return routingList;
			}else{
				while(curr.number != lowestRouter(a.baseStation,b.baseStation).number){
					routingList.newExchangeList.insertRear(curr);
				//	System.out.println(curr.number);
					curr = curr.parent;
				}
				if(curr.number == lowestRouter(a.baseStation,b.baseStation).number && curr != null){
					routingList.newExchangeList.insertRear(curr);
					//System.out.println(curr.number);
				}
				ExchangeList tempList = new ExchangeList();
				curr = b.baseStation;
				while(curr.number != lowestRouter(a.baseStation,b.baseStation).number){
					//System.out.println(curr.number);
					tempList.newExchangeList.insertRear(curr);
					curr = curr.parent;
				}
				System.out.println(" \n");
				while(!tempList.newExchangeList.isEmpty()){ 
					curr = (Exchange)(tempList.newExchangeList.deleteFront().data);
					routingList.newExchangeList.insertRear(curr);
				}
				return routingList;
			}
		}
		catch(Exception ex){
			System.out.println(ex);
			return null;
		}

	}
	public void movePhone(MobilePhone a, Exchange b){
		try{
			Exchange curr = a.baseStation;
			//System.out.println(a.baseStation.number);
			//oldBase.mobileSet.newMobile.delete(a);
			while(curr != null){
				curr.mobileSet.newMobile.delete(a);
				curr = curr.parent;
			}
			curr = b;
			while(curr != null){
				curr.mobileSet.newMobile.insert(a);
				curr = curr.parent;
			}
			//b.mobileSet.newMobile.insert(a);
			a.baseStation = b;
			//System.out.println(a.baseStation.number);
			return;
		}
		catch(Exception ex){
			return;
		}	
	}
}

class node<Object>{
	public Object data;
	public node next;
	public node(Object obj){
		this.data =obj;
		this.next = null;
	}
}

class linkedList{
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
	public linkedList(){
	    this.head = null;
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
		if(null == this.head){
			this.head = new node<Object>(obj);
			return;
		}else{
		    node<Object> curr = this.head;
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
			//System.out.println(ex);
		}
	}
	public node deleteFront(){
		try{
			if(null == head){
				return null;
			}else if(null == head.next){
				node <Object> curr = head;
				head = null;
				return curr;
			}else{
				node <Object> curr = head;
				head = head.next;
				return curr;
			}
		}
		catch(Exception ex){
			return null;
		}
	}
	public node deleteRear(){
		try{
			if(null == this.head){
				return null;
			}else if(head.next == null){
				head = null;
				return null;
			}else if(head.next.next == null){
				node <Object> curr = head.next;
				head.next = null;
				return curr;
			}else{
				node <Object> curr = head;
				while(curr.next.next != null){
					curr = curr.next;
				}
				node<Object> answer = curr.next;
				curr.next = null;
				return answer;
			}
		}
		catch(Exception ex){
			return null;
		}
	}
	public boolean containsNode(Object obj){
		node<Object> curr = this.head;
		while(curr != null){
			if(curr.data == obj){
				return true;
			}
			curr = curr.next;
		}
		return false;
	}
}

class MySet{
	public linkedList list;
	public MySet(){
	    this.list = new linkedList();
	}
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
class MobilePhone{
	public int number; //unique identification for a mobile phone is number
	public boolean status;//is the status on or off
	public Exchange baseStation; //base location of the mobile phone
	public void switchOn(){
		// turns status of the mobile phone to on
		this.status = true;
	}
	public void switchOff(){
		// turns status of the mobile phone to off
		this.status = false;
	}
	public Exchange location(){
	 // returns the base location of the phone if on. returns error if phone is off
		try{
			if(false == this.status){
				throw new Exception();
			//	return null;
			}else{
				return this.baseStation;
			}
		}
		catch(Exception ex){
		//	System.out.println(ex);	
			return null;
		}
	}
	public MobilePhone(int number){
		// sets number of mobile phone
		this.number = number;
	}
}
class MobilePhoneSet{
	public MySet newMobile;
	public MobilePhoneSet(){
		newMobile = new MySet();
	}
}

class Exchange{
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
		//returns the child at the ith position. Indexing from 0 to numChildren() - 1
		try{
		    //listOfChildren=new ExchangeList();
			if(null == this){
			    //System.out.println("Reads myNode as null");
				return null;
			}
			node curr = this.listOfChildren.newExchangeList.head;
			for(int l=0; curr != null; l++){
				if(l == i){
					return (Exchange)curr.data;
				}
				curr = curr.next;
			}
			return null;
		}
		catch(Exception ex){
			//System.out.println(ex);
			return null;
		}
	}
	public int numChildren(){
		//returns the number of children
		if(this==null){
			return 0;
		}else if(this.isExternal()){
		    return 0;
		}else{
		    return this.listOfChildren.newExchangeList.getLength();// why is this giving error
		}
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
			    //System.out.println(null == this.listOfChildren.newExchangeList.head);
				return (null == this.listOfChildren.newExchangeList.head);
			}
		}
		catch(Exception ep){
			//System.out.println(ep);
			return false;
		}
	}
	public RoutingMapTree subTree(int i){
		//returns the subtree at the ith position
		RoutingMapTree newTree = new RoutingMapTree();
		newTree.root = this.child(i);
		return newTree;
	}
	public void insertMobile(Exchange b, MobilePhone Phone){
	    Exchange curr = b;
	    if(null == b){
	        return;
	    }else{
	        while(curr != null){
	        curr.mobileSet.newMobile.insert(Phone);
	       // System.out.println("Message");
	        curr = curr.parent;
	        }
	        return;
	    }
	    //return;
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
		this.listOfChildren=new ExchangeList();
		this.mobileSet=new MobilePhoneSet();
	}
	public MobilePhone getTargetPhone(int number){
		//given an integer, finds mobile phone corresponding to the integer
		linkedList myList = this.mobileSet.newMobile.list;
		node curr = myList.head;
		while(curr != null){
			if(((MobilePhone)curr.data).number == number){
				return ((MobilePhone)curr.data);
			}
			curr = curr.next;
		}
		if(null == curr){
			return null;
		}
		return null;
	}
}

class ExchangeList{
	public linkedList newExchangeList;
	public ExchangeList(){
		newExchangeList = new linkedList();
	}
}

class checker
{
	public static void main ( String args [])
	{
		BufferedReader br = null;
		RoutingMapTree r = new RoutingMapTree();

		try {
			String actionString;
			br = new BufferedReader(new FileReader("actions1.txt"));

			while ((actionString = br.readLine()) != null) {
				r.performAction(actionString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}




