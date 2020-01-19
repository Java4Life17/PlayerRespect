package playerrespect.playerrespect.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import playerrespect.playerrespect.PlayerRespect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimeReset implements Listener {
    private PlayerRespect plugin;

    public TimeReset(PlayerRespect pl) {
        plugin = pl;
    }
    public void startCounter() {
        new BukkitRunnable() {
             int defaultSeconds = plugin.config.getInt("resetSeconds");
             int secondsLeft = plugin.Track.getInt("timeLeft");
             int givePoints = plugin.config.getInt("resetPoints");
            @Override
            public void run() {
                plugin.Track.set("timeLeft", (plugin.Track.getInt("timeLeft")) - 1);
                secondsLeft--;
                if (secondsLeft <= 0) {
                    for (String key : plugin.pointsToGive.getKeys(true)) {
                        plugin.pointsToGive.set(key, givePoints);
                        try {
                            plugin.pointsToGive.saveWithComments();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    secondsLeft = defaultSeconds;
                    plugin.Track.set("timeLeft", defaultSeconds);
                    if (plugin.config.getString("local").equalsIgnoreCase("en_US")) {
                        if (plugin.config.getBoolean("broadcast")) {
                            List<Player> players = new ArrayList<>();
                            players.addAll(plugin.getServer().getOnlinePlayers());
                            for(Player receiver : players){
                                receiver.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                        plugin.en_US.getString("Prefix") + plugin.en_US.getString("broadcast")));
                            }
                        }
                    }
                    else if (plugin.config.getString("local").equalsIgnoreCase("es_MX")) {
                        if (plugin.config.getBoolean("broadcast")) {
                            List<Player> players = new ArrayList<>();
                            players.addAll(plugin.getServer().getOnlinePlayers());
                            for(Player receiver : players){
                                receiver.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                        plugin.es_MX.getString("Prefix") + plugin.es_MX.getString("broadcast")));
                            }
                        }
                    }

                }

            }
        }.runTaskTimer(plugin, 0, 20);
    }


}
