package cc.ghast.movemenowreborn.commands;

import cc.ghast.movemenowreborn.MoveMeNowReborn;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

/**
 * @author Ghast
 * @since 01-Apr-20
 */
public class ReloadCommand extends Command {
    private MoveMeNowReborn plugin;

    public ReloadCommand(MoveMeNowReborn plugin) {
        super("mmn", "movemenow.admin");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        switch (args.length){
            case 1: {
                switch (args[0].toLowerCase()){
                    case "reload":
                        plugin.getConfig().reload();
                        return;
                }
            }
            case 2: {
                switch (args[0].toLowerCase()){
                    case "setmode": {
                        plugin.getConfig().set(args[1], "mode");
                        plugin.getConfig().save();
                        return;
                    }
                    case "addserver": {
                        List<String> servers = plugin.getConfig().getStringList("servers");
                        servers.add(args[1]);
                        plugin.getConfig().set(servers, "servers");
                        plugin.getConfig().save();
                        return;
                    }
                }
            }
            default:
                sendHelpMessage(sender);
                break;
        }
    }

    private void sendHelpMessage(CommandSender sender){
        sender.sendMessage(new TextComponent("Please use /mmn reload."));
    }
}