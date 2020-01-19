package playerrespect.playerrespect.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import playerrespect.playerrespect.Language;
import playerrespect.playerrespect.PlayerRespect;

import java.util.Objects;

public class PlayerCommands implements CommandExecutor {
    private PlayerRespect plugin;

    public PlayerCommands(PlayerRespect pl) {
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("respect")) {
            try {
                Language language = new Language(plugin);
                String lang = plugin.config.getString("local");

                //Test if command is /respect pay <player> <Number>
                if (args[0].equalsIgnoreCase("pay")) {

                    Player player = Bukkit.getPlayer(args[1]);
                    int amount = Integer.parseInt(args[2]);
                    if (!sender.hasPermission("respect.player")) {
                        noPerm(sender);
                        return true;
                    }
                    else {
                        assert player != null;
                        if (!plugin.playerPoints.contains(player.getName())) {
                            language.sendTranslatedMessage((Player) sender, lang, "invalidPlayer", amount, player);
                            return true;
                        }
                        else {
                            if (amount <= 0) {
                                language.sendTranslatedMessage((Player) sender, lang, "invalidAmount", amount,
                                        player);
                                return true;
                            }
                            else {



                                if (amount > plugin.pointsToGive.getInt(sender.getName())) {
                                    language.sendTranslatedMessage((Player) sender, lang, "lowBudget", amount, player);
                                    return true;
                                }
                                else if(player.getName().equalsIgnoreCase(sender.getName())) {

                                    language.sendTranslatedMessage((Player) sender, lang, "paySelf", amount, player);
                                    return true;
                                }else{

                                language.sendTranslatedMessage((Player) sender, lang, "pointsGiven", amount,
                                        player);
                                if (lang.equalsIgnoreCase("en_US")) {
                                    plugin.pointsToGive.saveWithComments();
                                    plugin.playerPoints.saveWithComments();
                                    Objects.requireNonNull(Bukkit.getPlayer(player.getName())).sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.en_US.getString("Prefix") +
                                            plugin.en_US.getString("pointsReceived").replace("%player%",
                                                    sender.getName()).replace("%giver%", sender.getName()).replace(
                                                    "%receiver%",
                                                    player.getName()).replace("%amount%",
                                                    Integer.toString(amount)).replace("%points%",
                                                    Integer.toString(plugin.playerPoints.getInt(player.getName())))));

                                }
                                else if (lang.equalsIgnoreCase("es_MX")) {
                                    plugin.pointsToGive.saveWithComments();
                                    plugin.playerPoints.saveWithComments();
                                    Objects.requireNonNull(Bukkit.getPlayer(player.getName())).sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.es_MX.getString("Prefix") +
                                            plugin.es_MX.getString("pointsReceived").replace("%player%",
                                                    sender.getName()).replace("%giver%", sender.getName()).replace(
                                                    "%receiver%",
                                                    player.getName()).replace("%amount%",
                                                    Integer.toString(amount)).replace("%points%",
                                                    Integer.toString(plugin.playerPoints.getInt(player.getName())))));
                                }
                                else {
                                    plugin.pointsToGive.saveWithComments();
                                    plugin.playerPoints.saveWithComments();
                                    Objects.requireNonNull(Bukkit.getPlayer(player.getName())).sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.en_US.getString("Prefix") +
                                            plugin.en_US.getString("pointsReceived").replace("%player%",
                                                    sender.getName()).replace("%giver%", sender.getName()).replace(
                                                    "%receiver%",
                                                    player.getName()).replace("%amount%",
                                                    Integer.toString(amount)).replace("%points%",
                                                    Integer.toString(plugin.playerPoints.getInt(player.getName())))));
                                }
                                plugin.playerPoints.set(player.getName(),
                                        plugin.playerPoints.getInt(player.getName()) + amount);
                                plugin.pointsToGive.set(sender.getName(),
                                        plugin.pointsToGive.getInt(sender.getName()) - amount);
                                plugin.pointsToGive.saveWithComments();
                                plugin.playerPoints.saveWithComments();
                            }
                        }

                        }

                    }
                }
                else if(args[0].equalsIgnoreCase("total")){
                    int amount = plugin.playerPoints.getInt(sender.getName());

                    language.sendTranslatedMessage((Player) sender, lang, "total",amount,(Player) sender);
                    return true;

                }
            } catch (Exception e) {
                usage(sender, "&6respect pay <player> <#>");
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
        lang.sendColorMessage(player, ("&c&lUSAGE: &7/" + usage), 0, player);
    }
}
