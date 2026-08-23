package me.jack.noendermangrief.fabric.mixin;

import me.jack.noendermangrief.fabric.NoEndermanGriefMod;
import net.minecraft.world.entity.monster.EnderMan;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Targets the private inner class net.minecraft.world.entity.monster.EnderMan$EndermanLeaveBlockGoal,
 * which governs enderman block placement. See EndermanTakeBlockGoalMixin for why RETURN (not HEAD)
 * is used.
 */
@Mixin(targets = "net/minecraft/world/entity/monster/EnderMan$EndermanLeaveBlockGoal")
public abstract class EndermanLeaveBlockGoalMixin {

    private static final DateTimeFormatter LOG_TIMESTAMP_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Shadow
    private EnderMan enderman;

    @Inject(method = "canUse", at = @At("RETURN"), cancellable = true)
    private void noEndermanGrief$preventPlacement(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ() && NoEndermanGriefMod.getConfig().enabled) {
            cir.setReturnValue(false);

            if (NoEndermanGriefMod.getConfig().loggingEnabled) {
                StringBuilder message = new StringBuilder("[EndermanBlocked] ");
                if (NoEndermanGriefMod.getConfig().loggingIncludeTimestamp) {
                    message.append(LocalDateTime.now().format(LOG_TIMESTAMP_FORMAT)).append(" - ");
                }
                message.append("Prevented enderman block placement in ")
                        .append(this.enderman.level().dimension().location())
                        .append(" near ")
                        .append(this.enderman.blockPosition());
                NoEndermanGriefMod.LOGGER.info(message.toString());
            }
        }
    }
}
