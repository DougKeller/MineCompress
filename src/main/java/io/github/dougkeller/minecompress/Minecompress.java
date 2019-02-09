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

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("MineCompress started.");
        Sponge.getEventManager().registerListeners(this, new ItemPickupListener());
    }

    public Logger getLogger() {
        return logger;
    }
}

