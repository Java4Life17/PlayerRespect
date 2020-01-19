package playerrespect.playerrespect;


import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.simpleyaml.configuration.file.YamlFile;
import playerrespect.playerrespect.Commands.AdminCommands;
import playerrespect.playerrespect.Commands.CommandComplete;
import playerrespect.playerrespect.Commands.PlayerCommands;
import playerrespect.playerrespect.Events.TimeReset;
import playerrespect.playerrespect.Events.chatEvent;
import playerrespect.playerrespect.Events.joinEvent;

import java.io.IOException;
import java.util.Objects;


public final class PlayerRespect extends JavaPlugin {

    public YamlFile config;
    public YamlFile playerPoints;
    public YamlFile en_US;
    public YamlFile es_MX;
    public YamlFile Track;
    public YamlFile pointsToGive;

    public void onEnable() {
        config = new YamlFile("plugins/PlayerRespect/config.yml");
        playerPoints = new YamlFile("plugins/PlayerRespect/playerPoints.yml");
        en_US = new YamlFile("plugins/PlayerRespect/locals/en_US.yml");
        es_MX = new YamlFile("plugins/PlayerRespect/locals/es_MX.yml");
        Track = new YamlFile("plugins/PlayerRespect/Track.yml");
        pointsToGive = new YamlFile("plugins/PlayerRespect/pointsToGive.yml");
        System.out.println(ChatColor.translateAlternateColorCodes('&', "&d\n\n\n" +
                "\n\n\n===============================================================\n\n\n"));
          System.out.println(ChatColor.translateAlternateColorCodes('&', "&aPlayer Respect by " +
                "Proxytimeout " +
                "has" +
                " been" +
                " enabled"));
        System.out.println(ChatColor.translateAlternateColorCodes('&', "&d\n\n\n" +
                "\n\n\n===============================================================\n\n\n"));
        loadNecessaryFiles();
        getServer().getPluginManager().registerEvents(new joinEvent(this), this);
        getServer().getPluginManager().registerEvents(new chatEvent(this), this);
        Objects.requireNonNull(this.getCommand("AdminRespect")).setExecutor(new AdminCommands(this));
        Objects.requireNonNull(getCommand("respect")).setExecutor(new PlayerCommands(this));
        TimeReset timeReset = new TimeReset(this);
        timeReset.startCounter();
        Objects.requireNonNull(getCommand("respect")).setTabCompleter(new CommandComplete(this));
        Objects.requireNonNull(getCommand("AdminRespect")).setTabCompleter(new CommandComplete(this));
    }

    public String getLanguage() {
        return this.config.getString("local");
    }

    public void onDisable() {
        System.out.println(ChatColor.translateAlternateColorCodes('&', "&d\n\n\n===============================================================\n\n\n"));
        System.out.println(ChatColor.translateAlternateColorCodes('&', "&cPlayer Respect by Proxytimeout has been disabled"));
        System.out.println(ChatColor.translateAlternateColorCodes('&', "&d\n\n\n===============================================================\n\n\n"));
        try {
            Track.saveWithComments();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToConsole(String message) {
        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
    public void loadNecessaryFiles(){
        try{
            if(!config.exists()){
                sendMessageToConsole("&a&lNo data for plugin PlayerRespect found. Creating new one. (Config.yml)");
                this.saveResource("config.yml", true);

            }else{
                sendMessageToConsole("&dLoading config.yml");
            }
            config.load();
            config.saveWithComments();
        }catch (Exception e) {
            e.printStackTrace();
        }
        try{
            if(!playerPoints.exists()){
                sendMessageToConsole("&a&lNo data for plugin PlayerRespect found. Creating new one. (Player Points)");
                this.saveResource("playerPoints.yml", true);


            }else{
                sendMessageToConsole("&dLoading playerPoints");
            }
            playerPoints.load();
            playerPoints.saveWithComments();
        }catch (Exception e) {
            e.printStackTrace();
        }
        try{
            if(!en_US.exists()){
                sendMessageToConsole("&a&lNo data for plugin PlayerRespect found. Creating new one. (en_US)");
                this.saveResource("locals/en_US.yml", true);


            }else{
                sendMessageToConsole("&dLoading language/EN_US");
            }
            en_US.load();
            en_US.saveWithComments();
        }catch (Exception e) {
            e.printStackTrace();
        }
        try{
            if(!es_MX.exists()){
                sendMessageToConsole("&a&lNo data for plugin PlayerRespect found. Creating new one. (ES_MX)");
                this.saveResource("locals/es_MX.yml", true);


            }else{
                sendMessageToConsole("&dLoading language/ES_MX.");
            }
            es_MX.load();
            es_MX.saveWithComments();
        }catch (Exception e) {
            e.printStackTrace();
        }
        try{
            if(!Track.exists()){
                sendMessageToConsole("&a&lNo data for plugin PlayerRespect found. Creating new one. (ES_MX)");
                this.saveResource("Track.yml", true);


            }else{
                sendMessageToConsole("&dLoading Track.yml");
            }
            Track.load();
            Track.saveWithComments();
        }catch (Exception e) {
            e.printStackTrace();
        }
        try{
            if(!pointsToGive.exists()){
                sendMessageToConsole("&a&lNo data for plugin PlayerRespect found. Creating new one. (pointsToGive" +
                        ".yml)");
                this.saveResource("pointsToGive.yml", true);


            }else{
                sendMessageToConsole("&dLoading pointsToGive.yml");
            }
            pointsToGive.load();
            pointsToGive.saveWithComments();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
