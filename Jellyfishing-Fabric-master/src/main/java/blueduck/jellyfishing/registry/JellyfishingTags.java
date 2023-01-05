package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class JellyfishingTags {
    public static final Tag.Identified<Block> JELLY_BLOCKS = (Tag.Identified<Block>) create("jelly_blocks", TagRegistry::block);

    private static <E> Tag<E> create(String name, Function<Identifier, Tag<E>> tagCreateSupplier) {
        return tagCreateSupplier.apply(Jellyfishing.id(name));
    }
}