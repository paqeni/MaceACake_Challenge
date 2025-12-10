package org.exampl.untitled;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ChallengeMenuPlugin extends JavaPlugin implements Listener {
    private static ChallengeMenuPlugin instance;

    public static ChallengeMenuPlugin getInstance(){
        return instance;
    }

    private final Set<UUID> playersUnderChallenge = new HashSet<>();

    public Set<UUID> getPlayersUnderChallenge() {
        return playersUnderChallenge;
    }

    public void addPlayerUnderChallenge(Player player) {
        playersUnderChallenge.add(player.getUniqueId());
    }

    public void removePlayerUnderChallenge(Player player) {
        playersUnderChallenge.remove(player.getUniqueId());
    }


    @Override
    public void onEnable() {

        ChallengesMenu menu = new ChallengesMenu(this);

        getServer().getPluginManager().registerEvents(menu, this);
        getServer().getPluginManager().registerEvents(this, this);

        getCommand("challenges").setExecutor((sender, command, label, args) -> {
            if (sender instanceof Player) {
                menu.openMenu((Player) sender);
            }
            return true;
        });
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        UUID uuid = player.getUniqueId();

        if (!playersUnderChallenge.contains(uuid)) {
            return;
        }

        AttributeInstance maxHealthAttr = player.getAttribute(Attribute.MAX_HEALTH);

        if (maxHealthAttr == null) {
            return;
        }

        double maxHealth = maxHealthAttr.getBaseValue();

        if (maxHealth > 2.0) {
            double newMax = maxHealth - 2.0;
            maxHealthAttr.setBaseValue(newMax);

            player.sendMessage("§cВы потеряли одно сердечко! Теперь у вас " + (newMax / 2.0) + " сердец.");
        } else {
            playersUnderChallenge.remove(player.getUniqueId());

            player.sendMessage("§cУ вас больше нет сердец, вы выбываете из челленджа!");
            player.sendMessage("§cВремя игры с челленджем:");
            player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(20);
        }
    }
    public class DamageListener implements Listener {



        public class DamageListener implements Listener {

            private final DoubleDamage plugin;

            public DamageListener(DoubleDamage plugin) {
                this.plugin = plugin;
            }

            @EventHandler
            public void onEntityDamage(EntityDamageEvent event) {

                if (!plugin.isDoubleDamageEnabled()) {
                    return;
                }

                Entity entity = event.getEntity();

                if (entity instanceof Player) {
                    double originalDamage = event.getOriginalDamage(EntityDamageEvent.DamageModifier.BASE);
                    double doubledDamage = originalDamage * 2.0;
                    event.setDamage(EntityDamageEvent.DamageModifier.BASE, doubledDamage);
                }
            }
        }
    }
}
