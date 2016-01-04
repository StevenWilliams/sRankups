package org.stevenw.prison.rankup;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class RankupRequirement {
    private final ConfigurationSection section;
    private final sRankup plugin;
    public RankupRequirement(sRankup plugin, ConfigurationSection section) {
        this.section = section;
        this.plugin = plugin;
    }

    public String getType() {
        return this.getClass().getName();
    }
    public abstract boolean isFulfilled(Player player);
    public abstract String getName(Player player); //ChatColor stripped
    public List<String> getDescription(Player player) {
        return null;
    }
    public void rankup(Player player) {
        return;
    }
    public abstract Material getDefaultMaterial();
    public ItemStack getItem(Player player) {
        Material material = getDefaultMaterial();
        if(section.getString("material") != null) {
            material = Material.getMaterial(section.getString("material"));
        }
        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();
        if(isFulfilled(player)) {
            meta.setDisplayName(ChatColor.GREEN + ChatColor.stripColor(getName(player)));
        } else {
            meta.setDisplayName(ChatColor.RED + org.bukkit.ChatColor.stripColor(getName(player)));
        }
        meta.setLore(getDescription(player));
        item.setItemMeta(meta);
        return item;
    }
}
