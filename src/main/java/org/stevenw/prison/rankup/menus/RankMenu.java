package org.stevenw.prison.rankup.menus;

import ninja.amp.ampmenus.items.BackItem;
import ninja.amp.ampmenus.items.MenuItem;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.plugin.java.JavaPlugin;
import org.stevenw.prison.rankup.Rank;
import org.stevenw.prison.rankup.RankupRequirement;
import org.stevenw.prison.rankup.sRankup;

import java.util.Collection;

public class RankMenu extends ItemMenu {
    private Rank rank;
    public RankMenu(Rank rank, sRankup plugin, ItemMenu parent) {
        super(rank.getName(), Size.fit(9 + rank.getRequirements().size()), plugin, parent);
        setItem(0, new BackItem());
        setItem(8, new RankupItem(rank));
        int i = 9;
        Collection<RankupRequirement> requirements = rank.getRequirements();
        for(RankupRequirement requirement : requirements) {
            setItem(i, new RequirementItem(requirement));
            i++;
        }
    }

}
