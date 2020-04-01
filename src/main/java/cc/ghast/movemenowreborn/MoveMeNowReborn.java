package cc.ghast.movemenowreborn;

import cc.ghast.movemenowreborn.commands.ReloadCommand;
import cc.ghast.movemenowreborn.listener.PlayerListener;
import cc.ghast.movemenowreborn.utils.BConfiguration;
import net.md_5.bungee.api.plugin.Plugin;

public class MoveMeNowReborn extends Plugin {

    private BConfiguration config;

    @Override
    public void onEnable() {
        config = new BConfiguration("config.yml", this);
        this.getProxy().getPluginManager().registerListener(this, new PlayerListener(this));
        this.getProxy().getPluginManager().registerCommand(this, new ReloadCommand(this));
    }

    @Override
    public void onDisable() {

    }

    public BConfiguration getConfig() {
        return config;
    }
}
