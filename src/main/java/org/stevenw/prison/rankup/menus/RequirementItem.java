package org.stevenw.prison.rankup.menus;


import ninja.amp.ampmenus.items.MenuItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.stevenw.prison.rankup.RankupRequirement;

public class RequirementItem extends MenuItem {
    private final RankupRequirement requirement;
    public RequirementItem(RankupRequirement requirement) {
        super("requirement", new ItemStack(Material.STONE, 1));
        this.requirement = requirement;
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        return requirement.getItem(player);
    }

}
