package customenchants.customenchants.events;

import customenchants.customenchants.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HumoreEnch implements Listener {

    @EventHandler
    public void humore(EntityRegainHealthEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        ItemStack itemStack = player.getInventory().getChestplate();
        if(itemStack.getEnchantments().containsKey(Enchantment.getByKey(Main.humore.getKey()))){
            if(player.getHealth() <= 10){
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,999999,2));
            }else{
                player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            }
        }
    }
}
