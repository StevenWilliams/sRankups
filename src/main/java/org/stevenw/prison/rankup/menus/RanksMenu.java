package org.stevenw.prison.rankup.menus;

import ninja.amp.ampmenus.items.CloseItem;
import ninja.amp.ampmenus.items.SubMenuItem;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.stevenw.prison.rankup.Rank;
import org.stevenw.prison.rankup.sRankup;

import java.util.List;

public class RanksMenu extends ItemMenu{
    public RanksMenu(sRankup plugin, Player player) {
        super("Rankup", Size.FOUR_LINE, plugin);
        CloseItem close = new CloseItem();
        close.setNameAndLore(new ItemStack(Material.FENCE_GATE, 1), ChatColor.RED + "Close", null); //TODO: not changing
        setItem(0, close);
        int i = 18;
        List<Rank> ranks =  plugin.getRanks();
        for(Rank rank : ranks) {
            if(!rank.has(player)) {
                RankMenu rankmenu = new RankMenu(rank, plugin, this);
                setItem(i, new SubMenuItem(plugin, ChatColor.GOLD + "" + ChatColor.BOLD + rank.getName(), new ItemStack(Material.getMaterial(rank.getConfigSection().getString("icon", "STONE")), 1), rankmenu));
                i++;
            }
        }
    }

}
