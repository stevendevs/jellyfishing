package blueduck.jellyfishing.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.HashSet;
import java.util.Set;

@Environment(EnvType.CLIENT)
public class ClientUtil {
    public static final Set<Item> tridentPerspectiveItemList = new HashSet<>();
    public static final Set<Item> leftRightPerspectiveItemList = new HashSet<>();

    public static void registerTridentPerspective(Item... items) {
        for (Item item: items) {
            tridentPerspectiveItemList.add(item);
            ModelLoadingRegistry.INSTANCE.registerModelProvider(
                    (resourceManager, out) -> out.accept(new ModelIdentifier(Registry.ITEM.getId(item) + "_in_hand#inventory"))
            );
        }
    }

    public static void registerLeftRightPerspective(Item... items) {
        for (Item item: items) {
            leftRightPerspectiveItemList.add(item);
            ModelLoadingRegistry.INSTANCE.registerModelProvider((resourceManager, out) -> {
                out.accept(new ModelIdentifier(Registry.ITEM.getId(item) + "_left#inventory"));
                out.accept(new ModelIdentifier(Registry.ITEM.getId(item) + "_right#inventory"));
            });
        }
    }
}