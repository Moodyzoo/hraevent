package me.moodyzoo.hraevent.hra;

import me.moodyzoo.hraevent.hra.modifiers.BaseMod;
import me.moodyzoo.hraevent.hra.tools.EventManager;
import me.moodyzoo.hraevent.hra.tools.PlayerManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Commands implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
                             @NotNull String[] args) {
        if (args.length == 0) {
            return true;
        }

        Player player = (Player) sender;

        //check if player had permission hra.admin
        if (!sender.hasPermission("hra.admin")) {
            sender.sendMessage(ChatColor.RED + "Hond je hebt geen perms!");
            return true;
        }


            if (args[0].equalsIgnoreCase("toggle")) {
                if (Hra.inGame) {
                            sender.sendMessage(Component.text(ChatColor.RED + "Game gestopt"));
                    Hra.inGame = false;
                } else {
                    Hra.inGame = true;
                    sender.sendMessage(Component.text(ChatColor.GREEN + "Game gestart"));
                }

            }

            if (args[0].equalsIgnoreCase("mods")) {
                if (args.length == 1) {
                    StringBuilder stringToSend = new StringBuilder();
                    for (String mod : Hra.mods.keySet()) {
                        stringToSend.append(mod).append(": ")
                                .append(Hra.enabledMods.get(mod) != null ? ChatColor.GREEN+"Enabled" : ChatColor.RED+"Disabled");
                        stringToSend.append("\n");
                    }
                    sender.sendMessage(stringToSend.toString());
                    return true;
                }
                Class<? extends BaseMod> mod;
                switch (args[1]) {
                    case "enable":
                        if (args.length != 3) {
                            sender.sendMessage("/hra mods enable <mod>");
                            return true;
                        }
                        mod = Hra.mods.get(args[2]);
                        if (mod == null) {
                            sender.sendMessage("Deze mod bestaat niet");
                            return true;
                        }

                        try {
                            BaseMod enabledMod = mod.getConstructor().newInstance();
                            enabledMod.enable();
                            Hra.enabledMods.put(args[2], enabledMod);
                            sender.sendMessage(args[2] + " is enabled");
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                                 NoSuchMethodException e) {
                            sender.sendMessage("Er ging iets fout!!! Kijk de console!!!");
                            throw new RuntimeException(e);
                        }
                        break;
                    case "disable":
                        if (args.length != 3) {
                            sender.sendMessage("/hra mods disable <mod>");
                            return true;
                        }
                        mod = Hra.mods.get(args[2]);
                        if (mod == null) {
                            sender.sendMessage("Deze mod bestaat niet");
                            return true;
                        }

                        BaseMod enabledMod = Hra.enabledMods.get(args[2]);
                        enabledMod.disable();
                        Hra.enabledMods.remove(args[2]);

                        sender.sendMessage(args[2] + " is disabled");
                        break;
                }
            }

            if(args[0].equalsIgnoreCase("event")) {
                switch(args[1].toLowerCase()) {
                    case "settp":

                        EventManager.setEventLocation(player.getLocation());
                        sender.sendMessage(Component.text(ChatColor.GREEN + "Teleport locatie gezet"));
                        break;
                    case "teleportplayers":
                        EventManager.teleportToEventLocation();
                        sender.sendMessage(Component.text(ChatColor.GREEN + "Spelers geteleporteerd"));
                        break;
                    case "start":
                        PlayerManager.addPlayers();
                        sender.sendMessage(Component.text(ChatColor.GREEN + "Players added"));
                        break;
                }
            }

            return true;
            };



}
