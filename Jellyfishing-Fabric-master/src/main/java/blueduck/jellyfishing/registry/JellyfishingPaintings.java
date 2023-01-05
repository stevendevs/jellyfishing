package blueduck.jellyfishing.registry;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class JellyfishingPaintings {
    public static PaintingMotive CAPTAIN;
    public static PaintingMotive BOLD_AND_BRASH;
    public static PaintingMotive JELLYFISH;
    public static PaintingMotive JELLYFISH_FIELDS;
    public static PaintingMotive PATTY_WAGON;
    public static PaintingMotive MILLIONTH_DOLLAR;

    public static void init() {
        CAPTAIN = createPainting("captain", 64, 48);
        BOLD_AND_BRASH = createPainting("bold_and_brash", 16, 32);
        JELLYFISH = createPainting("jellyfish", 16, 16);
        JELLYFISH_FIELDS = createPainting("jellyfish_fields", 32, 16);
        PATTY_WAGON = createPainting("patty_wagon", 32, 32);
        MILLIONTH_DOLLAR = createPainting("millionth_dollar", 32, 16);
    }

    private static PaintingMotive createPainting(String id, int width, int height) {
        final PaintingMotive type = new PaintingMotive(width, height);
        Registry.register(Registry.PAINTING_MOTIVE, new Identifier("jellyfishing", id), type);
        return type;
    }
}