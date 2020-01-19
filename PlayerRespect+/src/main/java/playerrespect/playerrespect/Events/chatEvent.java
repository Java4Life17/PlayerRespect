package playerrespect.playerrespect.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import playerrespect.playerrespect.PlayerRespect;

public class chatEvent implements Listener {

    private PlayerRespect plugin;
    public chatEvent(PlayerRespect plugin){this.plugin = plugin;}
    @EventHandler(priority = EventPriority.HIGHEST)
    //This function will get the instance of the player talking and will replace his/her message with
    // the correct format.
    public void onChatEvent(AsyncPlayerChatEvent event){
        String format = event.getFormat().replace("respect",
                Integer.toString(plugin.playerPoints.getInt(event.getPlayer().getName()))).replace(
                        "percentpointspercent",
                Integer.toString(plugin.playerPoints.getInt(event.getPlayer().getName())));
        event.setFormat(format);


    } //This area here will get rid of having to code all this when sending a player a message. And will just send a
    // player a message according to their language. This will also replace %player% and %points% with the player's
    // name and points
    public void sendConfigMessage(String path, Player player){
        if(plugin.config.getString("local").equalsIgnoreCase("en_US")){
            sendColorMessage(plugin.en_US.getString("Prefix") + plugin.en_US.getString(plugin.en_US.getString(path)),
                    player);
        }
        else if(plugin.config.getString("local").equalsIgnoreCase("es_MX")){
            sendColorMessage(plugin.en_US.getString("Prefix") + plugin.es_MX.getString(plugin.es_MX.getString(path)).replace("%player%", player.getName()).replace("%points%",
                    Integer.toString(plugin.playerPoints.getInt(player.getName()))),
                    player);
        }


    }

    //This will get the whole message from the function above and will finally send it to a player.
    public void sendColorMessage(String message, Player player){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
