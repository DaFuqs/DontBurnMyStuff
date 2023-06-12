package de.dafuqs.dontburnmystuff.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "DontBurnMyStuff")
public class DontBurnMyStuffConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 2)
    public int minFireProtectionLevelRequired = 1;

}
