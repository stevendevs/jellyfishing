package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.registry.Registry;

public class JellyfishingSounds {
    public static final SoundEvent STING = register("sting", "entity.jellyfish.sting");

    public static final SoundEvent JELLYFISH_FIELDS = register("jellyfish_fields", "music.jellyfishfields");
    public static final SoundEvent BACKGROUND_MUSIC = register("background_music", "music.general");
    public static final SoundEvent BLANK = register("blank", "blank");

    public static final BlockSoundGroup CARPETED_WOOD = new BlockSoundGroup(1.0F, 1.0F, SoundEvents.BLOCK_WOOD_BREAK, SoundEvents.BLOCK_WOOL_STEP, SoundEvents.BLOCK_WOOD_PLACE, SoundEvents.BLOCK_WOOD_HIT, SoundEvents.BLOCK_WOOL_FALL);

    private static SoundEvent register(String id, String soundID) {
        return Registry.register(Registry.SOUND_EVENT, Jellyfishing.id(id), new SoundEvent(Jellyfishing.id(soundID)));
    }
}