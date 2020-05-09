package net.cyberkitsune.prefixchat.tags;

import net.cyberkitsune.prefixchat.KitsuneChat;
import net.cyberkitsune.prefixchat.adapters.FactionAdapter;
import net.cyberkitsune.prefixchat.channels.KitsuneChannel;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class FactionsTag implements ChatTag {
    @Override
    public String getPlaceholder() {
        return "faction,faction-name,faction-relationship";
    }

    @Override
    public String getReplacement(String message, KitsuneChannel channel, AsyncPlayerChatEvent context, String placeholder) {
        // Let's look for MC Factions...
        FactionAdapter adapter = FactionAdapter.getAnyLoadedFactions();
        if (adapter != null)
        {
            switch (placeholder)
            {
                case "faction":
                case "faction-name":
                    if (adapter.isPlayerInFaction(context.getPlayer()))
                    {
                        return adapter.getFactionNameFor(context.getPlayer());
                    }
                case "faction-relationship":
                default:
                    return "";
            }
        }
        else
        {
            KitsuneChat.getInstance().mcLog.warning("[KitsuneChat] Factions tag requested but no factions detected!");
        }


        return "";
    }

    private boolean checkFactions()
    {
        return false;
    }
}
