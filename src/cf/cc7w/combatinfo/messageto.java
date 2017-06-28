package cf.cc7w.combatinfo;

import org.bukkit.entity.Player;
import me.clip.actionannouncer.ActionAPI;

public class messageto{
	
	@SuppressWarnings("deprecation")
	public static void messageto
	(Player dam,Player vic,double damage,String damname,String vicname,double damhel,double vichel)
	{
		String todammsg = CombatInfo.ToDamagerMessage;
		String todamage = banlance.dealmessage(todammsg,damname,vicname,damhel,vichel);
		
		String tovicmsg = CombatInfo.ToVictimMessage;
		String tovictim = banlance.dealmessage(tovicmsg,damname,vicname,damhel,vichel);
		
		Boolean chat = CombatInfo.chat;
		Boolean actionbar = CombatInfo.actionbar;
		Boolean title = CombatInfo.title;
		
		
		if(chat){
			dam.sendMessage(todamage);
			vic.sendMessage(tovictim);
		}
		
		if(actionbar){
			ActionAPI.sendPlayerAnnouncement(dam, todamage);
			ActionAPI.sendPlayerAnnouncement(vic, tovictim);
		}
		
		if(title){
			dam.sendTitle("", todamage);
			vic.sendTitle("", tovictim);
		}
		

	}
	

}
