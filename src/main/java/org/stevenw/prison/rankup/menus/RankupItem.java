package org.stevenw.prison.rankup.menus;


import ninja.amp.ampmenus.events.ItemClickEvent;
import ninja.amp.ampmenus.items.MenuItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.stevenw.prison.rankup.Rank;
import org.stevenw.prison.rankup.RankupRequirement;

import java.util.ArrayList;
import java.util.List;

public class RankupItem extends MenuItem {
    private final Rank rank;
    public RankupItem(Rank rank) {
        super("Rankup", new ItemStack(Material.STONE, 1));
        this.rank = rank;
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        ItemStack item = new ItemStack(Material.LADDER, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(rank.canRankup(player)) {
            meta.setDisplayName(ChatColor.GREEN + "Rankup");
            lore.add(ChatColor.GOLD + "Click to rankup!");
        } else {
            meta.setDisplayName(ChatColor.RED + "Rankup");
            lore.add("You cannot rankup!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        if(rank.canRankup(event.getPlayer())) {
            rank.rankup(event.getPlayer());
            event.getPlayer().sendMessage(ChatColor.GOLD + "You have ranked up!");
            rank.getPlugin().getServer().broadcastMessage(ChatColor.GOLD + event.getPlayer().getName() + " has ranked up to " + rank.getName());
        } else {
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot rankup!");
        }
    }
}
