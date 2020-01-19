package playerrespect.playerrespect;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Language implements Listener {
    private PlayerRespect plugin;
    public Language(PlayerRespect plugin){this.plugin = plugin;}
    //THIS CLASS WILL SEND A MESSAGE TO A PLAYER ACCORDING TO THEIR LANGUAGE. TO AVOID TYPING THIS EVERYTHING A MESSAGE
    //IS SENT TO PLAYER, THIS CLASS WAS CREATED

    public void sendTranslatedMessage(Player player, String language, String path, int amount, Player receiver){
        if(language.equalsIgnoreCase("en_US")){
            sendColorMessage(player, plugin.en_US.getString("Prefix") + plugin.en_US.getString(path), amount, receiver);
        }
        else if(language.equalsIgnoreCase("es_MX")){
            sendColorMessage(player, plugin.es_MX.getString("Prefix") + plugin.es_MX.getString(path), amount,
                    receiver);
        }else{
            sendColorMessage(player, plugin.en_US.getString("Prefix") + plugin.en_US.getString(path), amount, receiver);
        }
    }
    public void sendColorMessage(Player player, String message, int amount, Player receiver){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message).replace("%points%", Integer.toString(
                plugin.playerPoints.getInt(player.getName()))).replace("%player%", player.getName()).replace("%giver%",
                player.getName()).replace("%amount%", Integer.toString(amount)).replace("%receiver%", receiver.getName()));
    }
}
