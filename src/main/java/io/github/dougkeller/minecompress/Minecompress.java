package io.github.dougkeller.minecompress;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "minecompress",
        name = "MineCompress",
        description = "Automatically compresses resources you collect as you mine to save space.",
        authors = {
                "DougKeller"
        }
)
public class Minecompress {
    public static Logger logger;

    @Inject
    public Minecompress(Logger logger) {
        Minecompress.logger = logger;
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        Sponge.getEventManager().registerListeners(this, new ItemPickupListener());
    }
}

