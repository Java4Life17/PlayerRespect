package playerrespect.playerrespect.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import playerrespect.playerrespect.Language;
import playerrespect.playerrespect.PlayerRespect;
import sun.security.krb5.internal.CredentialsUtil;

public class AdminCommands implements CommandExecutor {
    private PlayerRespect plugin;

    public AdminCommands(PlayerRespect plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Language language = new Language(plugin);
        if (cmd.getName().equalsIgnoreCase("AdminRespect")) {
            Player player = (Player) sender;
            try {
                String lang = plugin.config.getString("local");
                if (args[0].equalsIgnoreCase("give")) {
                    if (!sender.hasPermission("respect.give")) {
                        noPerm(sender);
                        return true;
                    }
                    else {
                        try {

                            //Respect give <player> <amount>
                            Player receiver = plugin.getServer().getPlayer(args[1]);
                            int amount = Integer.parseInt(args[2]);

                            assert receiver != null;
                            if (!plugin.playerPoints.contains("" + receiver.getName())) {
                                language.sendTranslatedMessage(player, lang, "invalidPlayer", amount, receiver);
                                return true;
                            }
                            else {
                                if (amount <= 0) {
                                    language.sendTranslatedMessage(player, lang, "invalidAmount", amount, receiver);
                                    return true;
                                }
                                else {
                                    plugin.playerPoints.set(receiver.getName(),
                                            plugin.playerPoints.getInt(receiver.getName()) + amount);
                                    plugin.playerPoints.save();
                                    language.sendTranslatedMessage(player, lang, "pointsGiven", amount, receiver);
                                    language.sendTranslatedMessage(receiver, lang, "pointsReceived", amount, receiver);

                                }
                            }

                        } catch (Exception e) {
                            usage(sender, "[respect, resp] give <Player> <Points>");
                        }
                    }
                }//respect take <player> <points>
                else if (args[0].equalsIgnoreCase("take")) {
                    try {
                        Player participant = Bukkit.getPlayer(args[1]);
                        int amount = Integer.parseInt(args[2]);

                        if (!sender.hasPermission("respect.take")) {
                            noPerm(sender);
                            return true;
                        }
                        else {
                            assert participant != null;
                            if (!plugin.playerPoints.contains("" + participant.getName())) {
                                language.sendTranslatedMessage(player, lang, "invalidPlayer", amount, participant);
                                return true;
                            }
                            else {
                                if (amount > plugin.playerPoints.getInt(participant.getName())) {
                                    language.sendTranslatedMessage(player, lang, "invalidPlayer", amount, participant);
                                    return true;
                                }
                                else {
                                    plugin.playerPoints.set(participant.getName(),
                                            plugin.playerPoints.getInt(participant
                                            .getName()) - amount);
                                    plugin.playerPoints.save();
                                    language.sendTranslatedMessage(player, lang, "pointsTaker", amount, participant);
                                }
                            }
                        }
                    } catch (Exception e) {
                        usage(sender, "[respect, resp] take <Player> <Amount>");
                    }
                }
                else if (args[0].equalsIgnoreCase("reset")) {
                    if(!sender.hasPermission("respect.reset")){
                        noPerm(sender);
                        return true;
                    } else {
                        try {
                            //respect reset <Player>
                            Player participant = Bukkit.getPlayer(args[1]);
                            assert participant != null;
                            if (!plugin.playerPoints.contains(participant.getName())) {
                                language.sendTranslatedMessage(player, lang, "invalidPlayer", 0, participant);
                                return true;
                            }
                            else {
                                int resetCount = 0;
                                plugin.playerPoints.set(participant.getName(), resetCount);
                                plugin.playerPoints.save();
                                language.sendTranslatedMessage(player, lang, "pointsReset",
                                        plugin.playerPoints.getInt(participant.getName()), participant);
                            }
                        } catch (Exception e) {
                            usage(sender, "respect reset <player>");
                        }

                    }
                }
                else if(args[0].equalsIgnoreCase("reload")){
                    if(!sender.hasPermission("respect.reload")){
                        noPerm(sender);
                        return true;
                    }
                    else{
                        try{

                            plugin.config.load();
                            plugin.playerPoints.load();
                            plugin.pointsToGive.load();
                            plugin.es_MX.load();
                            plugin.en_US.load();
                            plugin.Track.load();

                            plugin.config.saveWithComments();
                            plugin.playerPoints.saveWithComments();
                            plugin.pointsToGive.saveWithComments();
                            plugin.es_MX.saveWithComments();
                            plugin.en_US.saveWithComments();
                            plugin.Track.saveWithComments();

                            language.sendColorMessage((Player) sender, "&a&lPlugin Reloaded", 0, (Player) sender);

                        }catch (Exception e){
                            language.sendColorMessage(player, "&cError: &7/adminresp give, take, reset, reload", 0,
                                    (Player) sender);
                        }
                    }
                }
            }catch (Exception e){
                language.sendColorMessage(player, "&cError: &7/adminresp give, take, reset, reload", 0,
                        (Player) sender);
            }
        }
        return true;
    }

    public void noPerm(CommandSender sender) {
        Language lang = new Language(plugin);
        Player player = (Player) sender;
        lang.sendTranslatedMessage(player, plugin.getLanguage(), "noPermission", 0, (Player) sender);
    }

    public void usage(CommandSender sender, String usage) {
        Language lang = new Language(plugin);
        Player player = (Player) sender;
        lang.sendColorMessage(player, ("&c&lUSAGE: &7/" + usage), 0, (Player) sender);
    }


}
