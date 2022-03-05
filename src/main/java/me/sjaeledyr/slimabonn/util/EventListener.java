package me.sjaeledyr.slimabonn.util;

import me.sjaeledyr.slimabonn.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {
    private Main main = Main.getPlugin(Main.class);

    // Initialize global variables.
    int magma = 0;
    int ice = 0;
    boolean isCauldron = false;
    boolean icePlaced = false;

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        // Initialize local variables.
        Player p = e.getPlayer();
        ItemStack inHand = p.getInventory().getItemInMainHand();
        Block block = e.getClickedBlock();
        if(block.getType() == Material.CAULDRON || block.getType() == Material.WATER_CAULDRON){
            isCauldron = true;
        }

        // Magma Cream interaction.
        if(inHand.getType() == Material.MAGMA_CREAM
                && isCauldron){
            p.getInventory().setItemInMainHand(null);
            p.updateInventory();
            p.sendMessage("You place the magma in the cauldron.");
            magma = 1;
        }

        // Ice interaction.
        if(inHand.getType() == Material.ICE && isCauldron){
            p.getInventory().remove(Material.ICE);
            p.updateInventory();
            p.sendMessage("You place the ice into the cauldron.");
            icePlaced = true;
            ice = 1;
        }

        // If player used a magma cream and an ice block, create slime ball.
        if(magma == 1 && ice == 1){
            p.sendMessage("You have turned the magma cream into a slimeball!");
            int emptySlot = p.getInventory().firstEmpty();
            p.getInventory().setItem(emptySlot, new ItemStack(Material.SLIME_BALL));
            magma = 0;
            ice = 0;
            isCauldron = false;
        }
    }
    // Cancel Ice Block Placement on Cauldron right click.
    @EventHandler
    public void BlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if(icePlaced){
            e.setCancelled(true);
            icePlaced = false;
        }
    }
}
