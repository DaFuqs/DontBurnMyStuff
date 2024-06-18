package de.dafuqs.dontburnmystuff;

import de.dafuqs.dontburnmystuff.config.DontBurnMyStuffConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.*;
import net.fabricmc.fabric.api.util.*;
import net.minecraft.enchantment.*;
import net.minecraft.item.*;
import net.minecraft.registry.entry.*;
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
        
        EnchantmentEvents.ALLOW_ENCHANTING.register((enchantment, target, enchantingContext) -> {
			if(enchantment.matchesKey(Enchantments.FIRE_PROTECTION)) {
				return TriState.TRUE;
			}
			return TriState.DEFAULT;
		});
        
        LOGGER.info("[DontBurnMyStuff] Finished initialization");

    }

    public static int getMinFireProtectionLevelRequired() {
        return CONFIG.minFireProtectionLevelRequired;
    }

}
