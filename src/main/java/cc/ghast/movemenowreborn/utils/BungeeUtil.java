package cc.ghast.movemenowreborn.utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

/**
 * @author Ghast
 * @since 01-Apr-20
 */
public class BungeeUtil {

    public static int countInServer(String serverName){
        String name = serverName.split(":")[0];
        ServerInfo server = ProxyServer.getInstance().getServerInfo(name);
        return server == null ? -1 : (server.isRestricted() ? -2 : server.getPlayers().size());
    }

}
