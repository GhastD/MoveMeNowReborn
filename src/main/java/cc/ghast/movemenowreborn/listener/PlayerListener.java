package cc.ghast.movemenowreborn.listener;

import cc.ghast.movemenowreborn.MoveMeNowReborn;
import cc.ghast.movemenowreborn.utils.BungeeUtil;
import net.md_5.bungee.api.AbstractReconnectHandler;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

/**
 * @author Ghast
 * @since 01-Apr-20
 */
public class PlayerListener implements Listener {
    private MoveMeNowReborn plugin;

    public PlayerListener(MoveMeNowReborn plugin) {
        this.plugin = plugin;
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);
    }

    @EventHandler
    public void onKick(ServerKickEvent e){

        ServerInfo kickedFrom = null;

        // STOLEN FROM MOVEMENOW BECAUSE FUCK IT IT'S A REBORN
        if (e.getPlayer().getServer() != null) {
            kickedFrom = e.getPlayer().getServer().getInfo();
        } else if (this.plugin.getProxy().getReconnectHandler() != null) {// If first server and recohandler
            kickedFrom = this.plugin.getProxy().getReconnectHandler().getServer(e.getPlayer());
        } else { // If first server and no recohandler
            kickedFrom = AbstractReconnectHandler.getForcedHost(e.getPlayer().getPendingConnection());
            if (kickedFrom == null) // Can still be null if vhost is null...
            {
                kickedFrom = ProxyServer.getInstance().getServerInfo(e.getPlayer().getPendingConnection().getListener().getDefaultServer());
            }
        }
        // END OF PURE SKIDDING

        boolean kickIfInLobby = plugin.getConfig().getBoolean("disconnect-if-in-lobby");
        List<String> servers = plugin.getConfig().getStringList("servers");

        // Check if the player first is in the lobby servers
        if (servers.contains(kickedFrom.getName()) && kickIfInLobby){
            e.getPlayer().disconnect(e.getKickReasonComponent());
            return;
        }

        boolean blacklist = plugin.getConfig().getString("mode").equalsIgnoreCase("blacklist");


        List<String> words = plugin.getConfig().getStringList("list-of-words");

        // CHECK IF THE WORD IS IN THE KICK MESSAGE
        boolean valid = false;
        for (String s : words){
            if (e.getKickReason().contains(s)){
                valid = true;
            }
        }

        List<String> message = plugin.getConfig().getStringList("message-sent");

        ServerInfo kickTo = getInfo(servers);
        if (kickTo == null) return;

        if ((blacklist && !valid) || (!blacklist && valid)){
            e.setCancelled(true);
            e.setCancelServer(kickTo);
            for (String line : message) {
                e.getPlayer().sendMessage(TextComponent.fromLegacyText(
                        ChatColor.translateAlternateColorCodes('&', line)));
            }
        }
    }


    private ServerInfo getInfo(List<String> servers){
        for (String server : servers){
            String name = server.split(":")[0];
            int maxSize = Integer.parseInt(server.split(":")[1]);
            int size = BungeeUtil.countInServer(name);

            if (size == maxSize || size < 0) continue;

            return ProxyServer.getInstance().getServerInfo(name);
        }
        return null;
    }


}
