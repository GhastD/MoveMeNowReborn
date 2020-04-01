package cc.ghast.movemenowreborn.utils;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Ghast
 * @since 27-Mar-20
 */
public class BConfiguration {
    private Plugin bungeePlugin;
    private Configuration config;
    private String name;

    public BConfiguration(String file, Plugin bungeePlugin){
        if (!file.contains(".yml")) throw new UnsupportedOperationException("ONLY YAML IS SUPPORTED AS OF NOW");
        this.name = file;
        this.bungeePlugin = bungeePlugin;
        load();
    }

    public void load(){
        try {
            File file1 = new File(bungeePlugin.getDataFolder(), name);
            if (!file1.exists()){
                if (file1.getParentFile() != null){
                    file1.getParentFile().mkdir();
                } else {
                    file1.getAbsoluteFile().getParentFile().mkdir();
                }
                try (InputStream in = bungeePlugin.getResourceAsStream(name)) {
                    //in = FileUtil.toUTF8InputStream()
                    //FileUtils.copyInputStreamToFile(in, file1);
                    Files.copy(in, file1.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            config = ConfigurationProvider
                    .getProvider(YamlConfiguration.class)
                    .load(file1);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void save(){
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .save(config, new File(bungeePlugin.getDataFolder(), name));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void reload(){
        load();
    }

    public String getString(String path){
        return config.getString(path);
    }

    public int getInt(String path){
        return config.getInt(path);
    }

    public List<String> getStringList(String path){
        return config.getStringList(path);
    }

    public boolean getBoolean(String path){return config.getBoolean(path);}

    public void set(Object object, String path){
        config.set(path, object);
    }
}
