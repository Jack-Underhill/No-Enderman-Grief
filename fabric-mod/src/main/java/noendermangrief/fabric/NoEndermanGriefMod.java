package noendermangrief.fabric;

import net.fabricmc.api.ModInitializer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.EnderMan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class NoEndermanGriefMod implements ModInitializer {

    public static final String MOD_ID = "no-enderman-grief";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static final DateTimeFormatter LOG_TIMESTAMP_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static NoEndermanGriefConfig config;

    @Override
    public void onInitialize() {
        config = NoEndermanGriefConfig.load();
        LOGGER.info("NoEndermanGrief has been initialized.");
    }

    public static NoEndermanGriefConfig getConfig() {
        return config;
    }

    /**
     * Called by the pickup/placement mixins whenever a block change was prevented. Logs a detailed
     * line to the console/log file, and — if the enderman is in a server level — announces a short,
     * color-coded version in chat so players (not just admins reading logs) can see it happened.
     */
    public static void announceBlocked(EnderMan enderman, String action) {
        if (!config.loggingEnabled) {
            return;
        }

        String coords = "(" + enderman.getBlockX() + ", " + enderman.getBlockY() + ", " + enderman.getBlockZ() + ")";

        StringBuilder logMessage = new StringBuilder("[EndermanBlocked] ");
        if (config.loggingIncludeTimestamp) {
            logMessage.append(LocalDateTime.now().format(LOG_TIMESTAMP_FORMAT)).append(" - ");
        }
        logMessage.append("Prevented enderman block ")
                .append(action)
                .append(" in ")
                .append(enderman.level().dimension().location())
                .append(" near ")
                .append(coords);
        LOGGER.info(logMessage.toString());

        if (enderman.level() instanceof ServerLevel serverLevel) {
            MutableComponent chatMessage = Component.literal("[NoEndermanGrief] ")
                    .withStyle(ChatFormatting.LIGHT_PURPLE)
                    .append(Component.literal("Denied " + action + " near ")
                            .withStyle(ChatFormatting.GRAY))
                    .append(Component.literal(coords).withStyle(ChatFormatting.GREEN));
            serverLevel.getServer().getPlayerList().broadcastSystemMessage(chatMessage, false);
        }
    }
}
