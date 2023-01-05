package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.misc.AgilityEnchantment;
import blueduck.jellyfishing.misc.PlunderingEnchantment;
//import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class JellyfishingEnchantments{ // implements Runnable {
//    @Override
//    public void run() {
//        //TODO: Make this work
//        var mapResolver = FabricLoader.getInstance().getMappingResolver();
//
//        var enchantmentTargetClass = mapResolver.mapClassName("intermediary", "net.minecraft.class_1886");
//
//        var enchantmentTargetAdder = ClassTinkerers.enumBuilder(enchantmentTargetClass, new Class[0]);
//        enchantmentTargetAdder.addEnumSubclass("NET", "blueduck.jellyfishing.misc.JellyNetTarget");
//        enchantmentTargetAdder.build();
//    }
//
//    public static final EnchantmentTarget NET_TARGET = ClassTinkerers.getEnum(EnchantmentTarget.class, "NET");

    public static final Enchantment AGILITY = register("agility", new AgilityEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    public static final Enchantment PLUNDERING = register("plundering", new PlunderingEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, Jellyfishing.id(name), enchantment);
    }
}