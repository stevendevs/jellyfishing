package blueduck.jellyfishing.items;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class KelpShakeItem extends Item {
    public KelpShakeItem(Settings settings) {
        super(settings.maxCount(16));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerEntity playerentity = entityLiving instanceof PlayerEntity ? (PlayerEntity)entityLiving : null;
        if (playerentity instanceof ServerPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerentity, stack);
        }
        if (playerentity != null) {
            playerentity.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!playerentity.isCreative()) {
                stack.decrement(1);
            }
            playerentity.addStatusEffect(new StatusEffectInstance(StatusEffect.byRawId(9), 200, 4));
            playerentity.addStatusEffect(new StatusEffectInstance(StatusEffect.byRawId(1), 160, 0));
        }

        if (playerentity == null || !playerentity.isCreative()) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (playerentity != null) {
                playerentity.inventory.insertStack(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return stack;
    }
}
