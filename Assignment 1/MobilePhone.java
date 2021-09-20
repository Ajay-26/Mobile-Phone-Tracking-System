import java.util.*;

public class MobilePhone{
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
			System.out.println(ex);	
			return null;
		}
	}
	public MobilePhone(int number){
		// sets number of mobile phone
		this.number = number;
	}
}
