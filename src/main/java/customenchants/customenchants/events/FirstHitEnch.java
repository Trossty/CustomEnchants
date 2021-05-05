package customenchants.customenchants.events;

import customenchants.customenchants.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class FirstHitEnch implements Listener {

    int hits = 0;

    @EventHandler
    public void firsthit(EntityDamageByEntityEvent event){
            if(!(event.getDamager() instanceof Player)) return;
            Player player = (Player) event.getDamager();
            ItemStack itemStack = player.getItemInHand();
            if(itemStack.getEnchantments().containsKey(Enchantment.getByKey(Main.firsthit.getKey()))){
                if(hits == 0){
                    event.setDamage(event.getDamage()*2);
                    hits++;
                }
                if(event.getEntity().isDead()){
                    this.hits = 0;
                }
            }
    }
}


