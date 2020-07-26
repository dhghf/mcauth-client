package dev.dhdf.mcauth;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static final boolean debug = false;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration config = this.getConfig();

        String address = config.getString("address", "127.0.0.1");
        String token = config.getString("token", "");
        long kickDelay = config.getLong("kick_delay");
        int port = config.getInt("port", 3001);

        Client client = new Client(address, port, token);
        MCListener listener = new MCListener(client, this, kickDelay);
        AltCommands altCommands = new AltCommands(client);

        getServer().getPluginManager().registerEvents(listener, this);
        this.getCommand("addalt").setExecutor(altCommands);
        this.getCommand("remalt").setExecutor(altCommands);
        this.getCommand("listalts").setExecutor(altCommands);
    }
}
