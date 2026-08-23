package me.jack.noendermangrief.fabric;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NoEndermanGriefMod implements ModInitializer {

    public static final String MOD_ID = "no-enderman-grief";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static NoEndermanGriefConfig config;

    @Override
    public void onInitialize() {
        config = NoEndermanGriefConfig.load();
        LOGGER.info("NoEndermanGrief has been initialized.");
    }

    public static NoEndermanGriefConfig getConfig() {
        return config;
    }
}
