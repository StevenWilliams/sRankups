package org.stevenw.prison.rankup.requirements;

//import net.milkbowl.vault.item.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.stevenw.prison.rankup.RankupRequirement;
import org.stevenw.prison.rankup.sRankup;

import java.util.ArrayList;
import java.util.List;

public class RequirementItem extends RankupRequirement {
    private final sRankup plugin;
    private final ConfigurationSection section;
    private final ItemStack item;

    public RequirementItem(sRankup plugin, ConfigurationSection section) {
        super(plugin, section);
        this.plugin = plugin;
        this.section = section;
        this.item = new ItemStack( Material.getMaterial(section.getString("value.type")), section.getInt("value.amount"));
    }

    @Override
    public boolean isFulfilled(Player player) {
        return count(player) >= item.getAmount();
    }

    private int count(Player player) {
        int count = 0;
        for(ItemStack itemStack : player.getInventory()) {
            if(item.isSimilar(itemStack)) {
                count += itemStack.getAmount();
            }
        }
        return count;
    }

    @Override
    public String getName(Player player) {
        return item.getAmount() + " " + item.getItemMeta().getDisplayName();
    }
    @Override
    public List<String> getDescription(Player player){
        List<String> desc = new ArrayList<>();
        desc.add(ChatColor.GOLD + "Must have items in inventory!");
        return desc;
    }

    @Override
    public Material getDefaultMaterial() {
        return item.getType();
    }

    @Override
    public void rankup(Player player) {
        player.getInventory().removeItem(item);
        player.updateInventory();
    }
}
