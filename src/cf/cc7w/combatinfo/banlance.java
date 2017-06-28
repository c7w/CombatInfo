package cf.cc7w.combatinfo;

public class banlance{

	
	
	public static double backd(double d){
	    
		if(String.valueOf(d).length() > 3){
			
			String nd = String.valueOf(d);
			
			char firstl = nd.charAt(0);
			char secondl = nd.charAt(1);
			char thirdl = nd.charAt(2);
			
			nd = String.valueOf(firstl) + String.valueOf(secondl) + String.valueOf(thirdl);
			
			d = Double.valueOf(nd).doubleValue();
		}
		
		return d;
	}
	
	public static String dealmessage(String message,String damager,String victim,double damagerhealth,double victimhealth){
	    
		
		
		String msg = message.replaceAll("&","§")
			                       .replaceAll("%damager%", damager)
			                       .replaceAll("%victim%", victim)
			                       .replaceAll("%damager-health%", String.valueOf(damagerhealth))
			                       .replaceAll("%victim-health%", String.valueOf(victimhealth));
	return msg;

	
}
	
	
	public static double useheart(double health){
		
		health = health / 2;
		health = backd(health);
		
		return health;
	}
	
}
