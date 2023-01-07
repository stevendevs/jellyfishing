package com.jellyfishing.core.registry;

import com.jellyfishing.common.misc.AgilityEnchantment;
import com.jellyfishing.common.misc.PlunderingEnchantment;
import com.jellyfishing.core.Jellyfishing;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;

public class JellyfishingEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Jellyfishing.MOD_ID, Registries.ENCHANTMENT);

//    public static EnchantmentCategory NET_ENCHANTMENT_CATEGORY = getNetCategory("net", AgilityEnchantment::canEnchant);

    public static final RegistrySupplier<Enchantment> AGILITY = ENCHANTMENTS.register("agility", AgilityEnchantment::newInstance);
    public static final RegistrySupplier<Enchantment> PLUNDERING = ENCHANTMENTS.register("plundering", PlunderingEnchantment::newInstance);

//    @ExpectPlatform
//    public static EnchantmentCategory getNetCategory(String id, Predicate<Item> predicate) {
//        throw new AssertionError();
//    }
}