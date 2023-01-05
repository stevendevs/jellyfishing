package blueduck.jellyfishing.misc;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

import java.util.Random;

//TODO: move this to differentiate
@SuppressWarnings({"unused"})
public abstract class JellyfishingSellItemFactory {
    public static class SellItemFactory implements TradeOffers.Factory {
        private final ItemStack sell;
        private final ItemStack offer;
        private final int price;
        private final int count;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellItemFactory(Block block, Block sell, int price, int count, int maxUses, int experience) {
            this(new ItemStack(block), new ItemStack(sell), price, count, maxUses, experience);
        }

        public SellItemFactory(Item item, Item sell, int price, int count, int experience) {
            this(new ItemStack(item), new ItemStack(sell), price, count, 12, experience);
        }

        public SellItemFactory(Item item, Item sell, int price, int count, int maxUses, int experience) {
            this(new ItemStack(item), new ItemStack(sell), price, count, maxUses, experience);
        }

        public SellItemFactory(Item item, int price, Item sell, int count, int maxUses, int experience, float multiplier) {
            this(new ItemStack(item), new ItemStack(sell), price, count, maxUses, experience, multiplier);
        }

        public SellItemFactory(ItemStack stack, ItemStack sell, int price, int count, int maxUses, int experience) {
            this(stack, sell, price, count, maxUses, experience, 0.05F);
        }

        public SellItemFactory(ItemStack offer, ItemStack sell, int price, int count, int maxUses, int experience, float multiplier) {
            this.sell = sell;
            this.offer = offer;
            this.price = price;
            this.count = count;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        public TradeOffer create(Entity entity, Random random) {
            return new TradeOffer(new ItemStack(this.offer.getItem(), this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
        }
    }
}
