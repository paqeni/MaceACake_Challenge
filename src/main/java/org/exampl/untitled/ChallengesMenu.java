package org.exampl.untitled;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;

public class ChallengesMenu implements Listener {

    private final ChallengeMenuPlugin plugin;

    public ChallengesMenu(ChallengeMenuPlugin plugin){
        this.plugin=plugin;
    }

    public void openMenu(Player player) {
        Inventory menu = Bukkit.createInventory(null, 45, "Испытания");

        ItemStack challengeItem9 = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = challengeItem9.getItemMeta();
        if (meta != null) {
            boolean inChallenge = plugin.getPlayersUnderChallenge().contains(player.getUniqueId());
            meta.setDisplayName(inChallenge
                    ? "§aИспытание: 9 жизней (включено)"
                    : "§cИспытание: 9 жизней (выключено)");
            meta.lore(List.of(Component.text("")));
            challengeItem9.setItemMeta(meta);
            menu.setItem(11, challengeItem9);
        }


        ItemStack challengeItem2 = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta2 = challengeItem2.getItemMeta();
            if (meta2 != null) {
                boolean inChallenge = plugin.getPlayersUnderChallenge().contains(player.getUniqueId());
                meta2.setDisplayName(inChallenge
                        ? "§aИспытание: Дважды плохо (включено)"
                        : "§cИспытание: Дважды плохо (выключено)");
                meta2.lore(List.of(Component.text("")));
                challengeItem2.setItemMeta(meta2);
                menu.setItem(13, challengeItem2);





        //Декор
        menu.setItem(11, challengeItem9);
        player.openInventory(menu);

        ItemStack decor = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        menu.setItem(0, decor);
        player.openInventory(menu);


        menu.setItem(11, challengeItem9);
        player.openInventory(menu);

        ItemStack decor1 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

        menu.setItem(1, decor1);
        player.openInventory(menu);


        menu.setItem(11, challengeItem9);
        player.openInventory(menu);

        ItemStack decor2 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

        menu.setItem(9, decor2);
        player.openInventory(menu);



        menu.setItem(11, challengeItem9);
        player.openInventory(menu);

        ItemStack decor3 = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        menu.setItem(8, decor3);
        player.openInventory(menu);


        menu.setItem(11, challengeItem9);
        player.openInventory(menu);

        ItemStack decor4 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

        menu.setItem(7, decor4);
        player.openInventory(menu);


        menu.setItem(11, challengeItem9);
        player.openInventory(menu);

        ItemStack decor5 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

        menu.setItem(17, decor5);
        player.openInventory(menu);



        menu.setItem(11, challengeItem9);
        player.openInventory(menu);

        ItemStack decor6 = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        menu.setItem(36, decor6);
        player.openInventory(menu);


        menu.setItem(11, challengeItem9);
        player.openInventory(menu);

        ItemStack decor7 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

        menu.setItem(27, decor7);
        player.openInventory(menu);


        menu.setItem(11, challengeItem9);
        player.openInventory(menu);

        ItemStack decor8 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

        menu.setItem(37, decor8);
        player.openInventory(menu);



        menu.setItem(11, challengeItem9);
        player.openInventory(menu);

        ItemStack decor9 = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        menu.setItem(44, decor9);
        player.openInventory(menu);


        menu.setItem(11, challengeItem9);
        player.openInventory(menu);

        ItemStack decor10 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

        menu.setItem(35, decor10);
        player.openInventory(menu);

        menu.setItem(11, challengeItem9);
        player.openInventory(menu);

        ItemStack decor11 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

        menu.setItem(43, decor11);
        player.openInventory(menu);

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Испытания")) return;

        event.setCancelled(true);

        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getType() != Material.DIAMOND_SWORD) return;

        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();

        if (plugin.getPlayersUnderChallenge().contains(uuid)) {
            plugin.getPlayersUnderChallenge().remove(uuid);
            player.sendMessage("§cИспытание 9 жизней отключено для вас.");
            player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(20);
        } else {
            plugin.getPlayersUnderChallenge().add(uuid);
            player.sendMessage("§aИспытание 9 жизней включено для вас.");
        }

        openMenu(player);
    }
}}
