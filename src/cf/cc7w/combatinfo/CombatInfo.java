package cf.cc7w.combatinfo;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CombatInfo extends JavaPlugin implements Listener{
	Logger inf = getLogger();
	public static String ToDamagerMessage;
	public static String ToVictimMessage;
	public static Boolean useheart;
	
	public static Boolean chat;
	public static Boolean actionbar;
	public static Boolean title;
	
	public void onEnable(){
		inf.info("CombatInfo 插件已启动！");
		if(Bukkit.getPluginManager().isPluginEnabled("ActionAnnouncer")){
			inf.info("检测到 ActionAnnouncer 插件，可使用 ActionBar 消息！");
		}
		loadcfg();
		getServer().getPluginManager().registerEvents(new damage(), this);
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable(){
		inf.info("CombatInfo 插件已关闭！");
		HandlerList.unregisterAll();
	}
	
	public void loadcfg(){
		
		if(!getDataFolder().exists()) {getDataFolder().mkdir();}
		File file=new File(getDataFolder(),"config.yml");
		if (!(file.exists())) {saveDefaultConfig();}
		reloadConfig();
		
		ToDamagerMessage = getConfig().getString("message.damager");
		ToVictimMessage = getConfig().getString("message.victim");
		useheart = getConfig().getBoolean("format.health-use-heart");
		
		chat = getConfig().getBoolean("setting.message-to-chat");
		actionbar = getConfig().getBoolean("setting.message-to-action-bar");
		title = getConfig().getBoolean("setting.message-to-title");
	}
	
	
	public boolean onCommand
	(CommandSender sender, Command cmd, String label,String[] args){

	    if (label.equalsIgnoreCase("cinfo")){
	    	
	    	if(args.length==0){
    	    	sender.sendMessage("§e┏━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
    	    	sender.sendMessage("§e┃ §a§l· §b您的指令输入有误§a,§c请输入 §5§l/cinfo help §c查看帮助");
    	    	sender.sendMessage("§e┗━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    		return true;
	    	}
	    	
	    	switch (args[0]){
	    	    case "reload":
	    	    	if(!sender.hasPermission("cinfo.reload")){
		    	    	sender.sendMessage("§e┏━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
		    	    	sender.sendMessage("§e┃ §a§l· §4§l你没有权限重载本插件§d(§c§l×§d)");
		    	    	sender.sendMessage("§e┗━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    		return true;
	    	    	}
	    	    	sender.sendMessage("§e┏━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    	sender.sendMessage("§e┃ §a§l· §b§l配置已经重新读取§d(§c§l√§d)");
	    	    	sender.sendMessage("§e┗━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    	loadcfg();
	    	    	return true;
	    	    case "info":
	    	    	sender.sendMessage("§e┏━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    	sender.sendMessage("§e┃ §a§l· §e§lCombatInfo§a(§d§l1.0§a)");
	    	    	sender.sendMessage("§e┃ §a§l· §e§l作者：§d§lc7w");
	    	    	sender.sendMessage("§e┃ §a§l· §e§l最后更新时间：§d§l17/06/28");
	    	    	sender.sendMessage("§e┗━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    	return true;
	    	    case "set":
	    	    	if(!sender.hasPermission("cinfo.set")){
		    	    	sender.sendMessage("§e┏━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
		    	    	sender.sendMessage("§e┃ §a§l· §4§l你没有权限使用该命令§d(§c§l×§d)");
		    	    	sender.sendMessage("§e┗━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    		return true;
	    	    	}
	    	    	if(!(args.length == 3)){
		    	    	sender.sendMessage("§e┏━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
		    	    	sender.sendMessage("§e┃ §a§l· §b您的指令输入有误§a,§c请输入 §5§l/cinfo set <chat|action-bar|title> <true|false>");
		    	    	sender.sendMessage("§e┗━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    	    return true;
	    	    	}
	    	    	String a = args[2];
	    	    	String b = args[1];
	    	    	Boolean c = b.equals("chat") || b.equals("action-bar") || b.equals("title");
	    	    	if(!a.equals("true") && !a.equals("false")){
		    	    	sender.sendMessage("§e┏━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
		    	    	sender.sendMessage("§e┃ §a§l· §b您的指令输入有误§a,§c请输入 §5§l/cinfo set <chat|action-bar|title> <true|false> ★");
		    	    	sender.sendMessage("§e┗━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    	    return true;
	    	    	}
	    	    	if(c == false){
		    	    	sender.sendMessage("§e┏━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
		    	    	sender.sendMessage("§e┃ §a§l· §b您的指令输入有误§a,§c请输入 §5§l/cinfo set <chat|action-bar|title> <true|false>");
		    	    	sender.sendMessage("§e┗━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
		    	    	
	    	    	    return true;
	    	    	}
	    	    	
	    	    	sender.sendMessage("§e┏━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    	sender.sendMessage("§e┃ §a§l· §b已设置 §4"+args[1] + " §b的值为 §e" + args[2]);
	    	    	sender.sendMessage("§e┃ §a§l· §b§l配置已经重新读取§d(§c§l√§d)");
	    	    	sender.sendMessage("§e┗━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    	String path = "setting.message-to-"+b;
	    	    	getConfig().set(path, Boolean.valueOf(args[2]));
	    	    	saveConfig();
	    	    	loadcfg();
	    	    	return true;
	    	    case "help":
	    	    	sender.sendMessage("§e┏━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    	sender.sendMessage("§e┃ §a§l· §e§l/cinfo help §a§l———— §c§l显示插件指令帮助信息");
	    	    	sender.sendMessage("§e┃ §a§l· §e§l/cinfo reload §a§l———— §c§l重载插件配置");
	    	    	sender.sendMessage("§e┃ §a§l· §e§l/cinfo info §a§l———— §c§l显示插件信息");
	    	    	sender.sendMessage("§e┃ §a§l· §e§l/cinfo set <chat|action-bar|title> <true|false> §a§l———— §c§l设置插件配置");
	    	    	sender.sendMessage("§e┗━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    	return true;
	    	    default:
	    	    	sender.sendMessage("§e┏━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
	    	    	sender.sendMessage("§e┃ §a§l· §b您的指令输入有误§a,§c请输入 §5§l/cinfo help §c查看帮助");
	    	    	sender.sendMessage("§e┗━━━━━━━━━━ §4§k!§e§lCombatInfo§4§k!§e ━━━━━━━━━━━");
		    		return true;
	    	}
	    	
	    }
        
	    return true;
	}

	
	
	}

	

