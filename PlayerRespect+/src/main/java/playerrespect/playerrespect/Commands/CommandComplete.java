package playerrespect.playerrespect.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import playerrespect.playerrespect.PlayerRespect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandComplete implements TabCompleter {
    private PlayerRespect plugin;
    public CommandComplete(PlayerRespect plugin){this.plugin = plugin;}

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {

        if(cmd.getName().equalsIgnoreCase("respect")){
            List<String> players = new ArrayList<>();
            players.addAll(plugin.playerPoints.getKeys(false));
            List<String> list1 = Arrays.asList("pay", "store", "total");
            List<String> finalOne = new ArrayList<>();

            if(args.length == 1){
                for(String s : list1){
                    if(s.toLowerCase().startsWith(args[0].toLowerCase())){finalOne.add(s);}

                }
                return finalOne;
            }
            if(args.length == 2){
                for(String s : players){
                    if(s.toLowerCase().startsWith(args[1].toLowerCase())){finalOne.add(s);}

                }
                return finalOne;
            }

        } //aresp give <Player> <Amount> //aresp reset <Player> //aresp take <player> <Amount>
        else if(cmd.getName().equalsIgnoreCase("AdminRespect")){
            List<String> options = new ArrayList<>();
            List<String> list1 = Arrays.asList("give" , "reset" , "take", "reload");
            options.addAll(plugin.playerPoints.getKeys(false));
            List<String> quantity = new ArrayList<>();
            for(int count = 1; count <= 100000; count++){
                quantity.add(Integer.toString(count));
            }


            List<String> finalOne = new ArrayList<>();

            if(args.length == 1){
                for(String s: list1){
                    if(s.toLowerCase().startsWith(args[0].toLowerCase())){finalOne.add(s);}
                }
                return finalOne;
            }
            if(args.length == 2){
                for(String s: options){
                    if(s.toLowerCase().startsWith(args[1].toLowerCase())){finalOne.add(s);}
                }
                return finalOne;
            }
            if(args.length == 3){
                for(String s : quantity){
                    if(s.toLowerCase().startsWith(args[2].toLowerCase())){finalOne.add(s);}
                }
                return finalOne;
            }


        }

        return null;
    }
}
