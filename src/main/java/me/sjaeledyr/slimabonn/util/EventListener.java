package me.sjaeledyr.slimabonn.util;

import me.sjaeledyr.slimabonn.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {
    private Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack inHand = p.getInventory().getItemInMainHand();
        Block block = e.getClickedBlock();
        int magma = 0;
        int ice = 0;
        if(inHand.getType() == Material.MAGMA_CREAM
           && block.getType() == Material.CAULDRON){
            p.getInventory().setItemInMainHand(new ItemStack((Material.AIR)));
            p.sendMessage("You place the magma in the cauldron.");
            magma = 1;
        }
        if(inHand.getType() == Material.ICE
           && block.getType() == Material.CAULDRON ){
            p.getInventory().setItemInMainHand(new ItemStack((Material.AIR)));
            p.sendMessage("You place the ice into the cauldron.");
            ice = 1;
        }
        if(magma == 1 && ice == 1){
            p.sendMessage("You have turned the magma cream into a slimeball!");
            int emptySlot = p.getInventory().firstEmpty();
            p.getInventory().setItem(emptySlot, new ItemStack(Material.SLIME_BALL));
        }
    }
}
