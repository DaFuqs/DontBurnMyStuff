package de.dafuqs.dontburnmystuff;

import de.dafuqs.dontburnmystuff.config.DontBurnMyStuffConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DontBurnMyStuff implements ModInitializer {

    public static final String MOD_ID = "DontBurnMyStuff";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static DontBurnMyStuffConfig CONFIG;

    @Override
    public void onInitialize() {

        LOGGER.info("[DontBurnMyStuff] Starting up");

        AutoConfig.register(DontBurnMyStuffConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(DontBurnMyStuffConfig.class).getConfig();

        LOGGER.info("[DontBurnMyStuff] Finished initialization");

    }

    public static int getMinFireProtectionLevelRequired() {
        return CONFIG.minFireProtectionLevelRequired;
    }

}
