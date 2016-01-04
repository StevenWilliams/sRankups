package org.stevenw.prison.rankup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.stevenw.prison.rankup.menus.RankMenu;
import org.stevenw.prison.rankup.menus.RanksMenu;
import org.stevenw.prison.rankup.sRankup;

public class RankupCommand implements CommandExecutor {
    private sRankup plugin;
    private RanksMenu menu;
    public RankupCommand(sRankup plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String tag, String[] args) {
        if(sender instanceof Player) {
            new RanksMenu(plugin, (Player) sender).open((Player) sender);
            return true;
        }
        return false;
    }
}
