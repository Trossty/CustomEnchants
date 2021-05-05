package customenchants.customenchants.events;

import customenchants.customenchants.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FreezeEnch implements Listener {
    @EventHandler
    public void freeze(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        ItemStack itemStack = player.getItemInHand();
        if(itemStack.getEnchantments().containsKey(Enchantment.getByKey(Main.freeze.getKey()))){
                LivingEntity victim = (LivingEntity) event.getEntity();
                victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 100));
        }
    }
}