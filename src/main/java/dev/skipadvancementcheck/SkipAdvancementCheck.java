package dev.skipadvancementcheck;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(SkipAdvancementCheck.MOD_ID)
public final class SkipAdvancementCheck {
    public static final String MOD_ID = "skip_advancement_check";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SkipAdvancementCheck() {
        LOGGER.info("Skip Advancement Check is active; advancement processing is disabled");
    }
}
