package noendermangrief;

import noendermangrief.listener.EndermanBlockListener;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin entry point.
 *
 * This class is created and managed by the Paper/Spigot server.
 * It must match the "main" value in plugin.yml:
 *   noendermangrief.NoEndermanGriefPlugin
 */
public class NoEndermanGriefPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Ensure default config.yml is saved to the plugin data folder
        // (plugins/NoEndermanGrief/config.yml) if it does not exist.
        saveDefaultConfig();

        getLogger().info("NoEndermanGrief is enabling...");

        // Register our event listener so we can intercept enderman block changes.
        getServer().getPluginManager().registerEvents(
                new EndermanBlockListener(this),
                this
        );

        getLogger().info("NoEndermanGrief has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("NoEndermanGrief has been disabled.");
    }

    /**
     * Checks whether the plugin is enabled for a specific world.
     *
     * We first look for an explicit entry under "worlds.<worldName>".
     * If there is none, we fall back to the "default-enabled" flag.
     */
    public boolean isWorldEnabled(String worldName) {
        boolean defaultEnabled = getConfig().getBoolean("default-enabled", true);

        ConfigurationSection worldsSection = getConfig().getConfigurationSection("worlds");
        if (worldsSection != null && worldsSection.contains(worldName)) {
            return worldsSection.getBoolean(worldName);
        }

        return defaultEnabled;
    }

    /**
     * Whether per-event logging is enabled.
     */
    public boolean isLoggingEnabled() {
        return getConfig().getBoolean("logging.enabled", false);
    }

    /**
     * Logs that an enderman's block pickup or placement was denied. Bukkit's logger already
     * prefixes console output with "[NoEndermanGrief]" and its own timestamp, so the message
     * itself stays short.
     */
    public void logEndermanBlockCancel(Block block, String action) {
        String coords = block.getX() + ", " + block.getY() + ", " + block.getZ();
        getLogger().info("Denied " + action + " at (" + coords + ").");
    }

    /**
     * Simple command handler for: /negreload
     * Allows reloading config.yml without restarting the server.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("negreload")) {
            return false; // Not our command.
        }

        if (!sender.hasPermission("noendermangrief.reload")) {
            sender.sendMessage("You do not have permission to use this command.");
            return true;
        }

        reloadConfig();
        sender.sendMessage("NoEndermanGrief configuration reloaded.");
        getLogger().info("Configuration reloaded by " + sender.getName());
        return true;
    }
}
