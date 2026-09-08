package dev.skipadvancementcheck.mixin;

import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Removes all per-player advancement work while leaving existing advancement
 * save files untouched.
 */
@Mixin(PlayerAdvancements.class)
abstract class PlayerAdvancementsMixin {
    /**
     * Skips parsing progress, automatic criteria checks, and registering every
     * advancement criterion listener during login and data-pack reloads.
     */
    @Inject(method = "load", at = @At("HEAD"), cancellable = true)
    private void skipAdvancementLoad(ServerAdvancementManager manager, CallbackInfo callback) {
        callback.cancel();
    }

    /**
     * Loading is disabled, so saving must also be disabled. Otherwise vanilla
     * would replace the player's existing advancement JSON with an empty file.
     */
    @Inject(method = "save", at = @At("HEAD"), cancellable = true)
    private void preserveExistingAdvancementFile(CallbackInfo callback) {
        callback.cancel();
    }

    /**
     * Avoids the initial full advancement-tree visibility pass and client sync
     * when the player joins.
     */
    @Inject(method = "flushDirty", at = @At("HEAD"), cancellable = true)
    private void skipAdvancementSync(ServerPlayer player, CallbackInfo callback) {
        callback.cancel();
    }
}
