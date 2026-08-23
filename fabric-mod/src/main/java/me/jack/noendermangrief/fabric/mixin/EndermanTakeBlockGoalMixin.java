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
 * Targets the private inner class net.minecraft.world.entity.monster.EnderMan$EndermanTakeBlockGoal,
 * which governs enderman block pickup. canUse() already gates on the mobGriefing gamerule and a
 * random chance; we inject at RETURN so we only override (and log) the rare ticks where the goal
 * would have actually activated, rather than every tick it's polled.
 */
@Mixin(targets = "net/minecraft/world/entity/monster/EnderMan$EndermanTakeBlockGoal")
public abstract class EndermanTakeBlockGoalMixin {

    private static final DateTimeFormatter LOG_TIMESTAMP_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Shadow
    private EnderMan enderman;

    @Inject(method = "canUse", at = @At("RETURN"), cancellable = true)
    private void noEndermanGrief$preventPickup(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ() && NoEndermanGriefMod.getConfig().enabled) {
            cir.setReturnValue(false);

            if (NoEndermanGriefMod.getConfig().loggingEnabled) {
                StringBuilder message = new StringBuilder("[EndermanBlocked] ");
                if (NoEndermanGriefMod.getConfig().loggingIncludeTimestamp) {
                    message.append(LocalDateTime.now().format(LOG_TIMESTAMP_FORMAT)).append(" - ");
                }
                message.append("Prevented enderman block pickup in ")
                        .append(this.enderman.level().dimension().location())
                        .append(" near ")
                        .append(this.enderman.blockPosition());
                NoEndermanGriefMod.LOGGER.info(message.toString());
            }
        }
    }
}
