package customenchants.customenchants.enchants;

import customenchants.customenchants.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Firsthit extends Enchantment implements Listener  {
    public Firsthit(String namespace){
        super(new NamespacedKey(Main.getPlugin(Main.class), namespace));
    }

    @Override
    public String getName() {
        return "Firsthit";
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
        return false;
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

    private List<Entity> entityList = new ArrayList<>();

    @EventHandler
    public void firsthit(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        ItemStack itemStack = player.getItemInHand();
        Entity entity = event.getEntity();
        if(itemStack.getEnchantments().containsKey(Enchantment.getByKey(getKey()))){
            if(!entityList.contains(entity)){
                event.setDamage(event.getDamage()*2);
                entityList.add(entity);
            }
            if(event.getEntity().isDead()){
                entityList.remove(entity);
            }
        }
    }

}
