package noendermangrief;

import noendermangrief.listener.EndermanBlockListener;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Main plugin entry point.
 *
 * This class is created and managed by the Paper/Spigot server.
 * It must match the "main" value in plugin.yml:
 *   noendermangrief.NoEndermanGriefPlugin
 */
public class NoEndermanGriefPlugin extends JavaPlugin {

    // Formatter for the optional timestamp we include in log messages.
    private static final DateTimeFormatter LOG_TIMESTAMP_FORMAT =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

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
     * Whether to include a timestamp inside the log message itself.
     */
    public boolean isTimestampEnabled() {
        return getConfig().getBoolean("logging.include-timestamp", true);
    }

    /**
     * Logs a message indicating an enderman block change was cancelled,
     * including world and coordinates, and optionally a timestamp.
     */
    public void logEndermanBlockCancel(Block block) {
        String worldName = block.getWorld().getName();
        String coords = block.getX() + ", " + block.getY() + ", " + block.getZ();

        StringBuilder message = new StringBuilder("[EndermanBlocked] ");

        if (isTimestampEnabled()) {
            String timestamp = LocalDateTime.now().format(LOG_TIMESTAMP_FORMAT);
            message.append(timestamp).append(" - ");
        }

        message.append("Cancelled enderman block change in world '")
               .append(worldName)
               .append("' at (")
               .append(coords)
               .append(')');

        getLogger().info(message.toString());
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
