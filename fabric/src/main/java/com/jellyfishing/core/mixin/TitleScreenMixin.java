package com.jellyfishing.core.mixin;

import com.jellyfishing.core.fabric.JellyfishingFabric;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method = "render", at = @At("RETURN"))
    private void onRenderTitleScreen (CallbackInfo ci) {
        JellyfishingFabric.joinLocalWorld("what");
    }
}
