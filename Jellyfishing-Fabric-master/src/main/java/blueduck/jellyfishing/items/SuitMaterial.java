package blueduck.jellyfishing.items;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.registry.JellyfishingSounds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.lwjgl.system.NonnullDefault;

public class SuitMaterial implements ArmorMaterial {
    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private static final int[] ARMOR_ARRAY = new int[]{3, 5, 7, 3};

    @Override
    public int getDurability(EquipmentSlot slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getEntitySlotId()] * 33;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slotIn) {
        return ARMOR_ARRAY[slotIn.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public SoundEvent getEquipSound() {
        return JellyfishingSounds.BLANK;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.IRON_INGOT);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public String getName() {
        return Jellyfishing.id("air_suit").toString();
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}