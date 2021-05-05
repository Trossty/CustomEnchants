package customenchants.customenchants.enchants;

import customenchants.customenchants.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Freeze extends Enchantment implements Listener {

    public Freeze(String namespace){
        super(new NamespacedKey(Main.getPlugin(Main.class), namespace));
    }

    @Override
    public String getName() {
        return "Freeze";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }

    @EventHandler
    public void freeze(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if(itemStack.getEnchantments().containsKey(Enchantment.getByKey(getKey()))){
            LivingEntity victim = (LivingEntity) event.getEntity();
            victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 100));
        }
    }

}
