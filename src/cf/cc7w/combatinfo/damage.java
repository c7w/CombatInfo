package cf.cc7w.combatinfo;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class damage implements Listener{
	
	@EventHandler()
	public static void pvp(EntityDamageByEntityEvent evt){
		
		String damtype = String.valueOf(evt.getDamager().getType());
		String victype = String.valueOf(evt.getEntityType());
	
        Boolean damep = damtype.equals("PLAYER");
        Boolean vicep = victype.equals("PLAYER");

		if(damep && vicep){
			double damage = evt.getDamage();
			String damname = evt.getDamager().getName();
			String vicname = evt.getEntity().getName();
			
			Player dam = (Player) evt.getDamager();
			Player vic = (Player) evt.getEntity();
			
			double damhel = dam.getHealth();
			double vichel = vic.getHealth();
			
					
			damhel = damhel + 0.5 ;
			vichel = vichel + 0.5 - damage;
			
			damage = banlance.backd(damage);	
			damhel = banlance.backd(damhel);
			vichel = banlance.backd(vichel);
			
			Boolean useh = CombatInfo.useheart;
			if(useh){
				damhel = banlance.useheart(damhel);
				vichel = banlance.useheart(vichel);
			}
		
			
			messageto.messageto(dam,vic,damage,damname,vicname,damhel,vichel);
			
			
		}
		
	}
}
