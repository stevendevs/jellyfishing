package blueduck.jellyfishing.items;

import blueduck.jellyfishing.registry.JellyfishingSounds;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.lwjgl.system.NonnullDefault;

public class KelpMaterial implements ArmorMaterial {
    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return 0;
    }

    @Override
    public int getDurability(EquipmentSlot slotIn) {
        return 180;
    }

    @Override
    public SoundEvent getEquipSound() {
        return JellyfishingSounds.BLANK;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.KELP);
    }

    @Override
    public String getName() {
        return "mustache";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 5;
    }
}