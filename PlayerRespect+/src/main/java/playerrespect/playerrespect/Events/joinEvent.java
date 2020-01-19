package playerrespect.playerrespect.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import playerrespect.playerrespect.PlayerRespect;

import java.io.IOException;

public class joinEvent implements Listener {
    private PlayerRespect plugin;
    public String prefix;
    public String dataLoad;
    public String dataCreated;

    public joinEvent(PlayerRespect plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        String language = plugin.getLanguage();
        Player player = event.getPlayer();
        String playerName = player.getName();
        this.prefix = plugin.en_US.getString("Prefix");
        String dataLoad = plugin.en_US.getString("dataLoad").replace("%player%", playerName).replace("%points%",
                Integer.toString(plugin.playerPoints.getInt(playerName)));
        //If the player's name is in the configuration section, then send a message to a player when he Joins the
        // server.
        if (plugin.playerPoints.contains(playerName)) {
            if (plugin.config.getBoolean("joinAlert")) {
                if (language.equalsIgnoreCase("en_US")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.en_US.getString("Prefix")
                            + plugin.en_US.getString("dataLoad").replace("%player%", playerName).replace("%points%",
                            Integer.toString(plugin.playerPoints.getInt(playerName)))));
                }
                else if (language.equalsIgnoreCase("es_MX")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.es_MX.getString("Prefix")
                            + plugin.es_MX.getString("dataLoad").replace("%player%", playerName).replace("%points%",
                            Integer.toString(plugin.playerPoints.getInt(playerName)))));
                    this.prefix = plugin.es_MX.getString("Prefix");
                    this.dataLoad = plugin.es_MX.getString("dataLoad").replace("%player%", playerName).replace(
                            "%points%",
                            Integer.toString(plugin.playerPoints.getInt(playerName)));
                }
                else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix
                            + plugin.en_US.getString("dataLoad").replace("%player%", playerName).replace("%points%",
                            Integer.toString(plugin.playerPoints.getInt(playerName)))));
                }

            }
            else {
                plugin.sendMessageToConsole(prefix + dataLoad);
            } //Create a configuration section for that player if it does not exist!
        }
        else {
            if (plugin.config.getBoolean("joinAlert")) {


                if (language.equalsIgnoreCase("en_US")) {
                    this.prefix = plugin.en_US.getString("Prefix");
                    this.dataCreated = plugin.en_US.getString("dataCreated");
                }
                else if (language.equalsIgnoreCase("es_MX")) {
                    this.prefix = plugin.es_MX.getString("Prefix");
                    this.dataCreated = plugin.es_MX.getString("dataCreated");
                }
                else {
                    this.prefix = plugin.en_US.getString("Prefix");
                    this.dataCreated = plugin.en_US.getString("dataCreated");
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + dataCreated));
                plugin.playerPoints.createSection(playerName);
                int defaultPoints = 0;
                int toGiveDefault = 3;
                plugin.playerPoints.set(playerName, defaultPoints);
                plugin.sendMessageToConsole(prefix + "&b" + playerName + " + playerPoints.yml");
                plugin.playerPoints.save();
                plugin.pointsToGive.createSection(playerName);
                plugin.pointsToGive.set(playerName, toGiveDefault);
                plugin.pointsToGive.saveWithComments();
            }
        }
    }
}
