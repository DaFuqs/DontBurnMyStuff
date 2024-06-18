package de.dafuqs.dontburnmystuff.mixin;

import de.dafuqs.dontburnmystuff.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
	
	@Inject(method = "isFireImmune()Z", at = @At("HEAD"), cancellable = true)
	private void dontburnmystuff$isFireImmune(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		ItemEntity itemEntity = ((ItemEntity) (Object) this);
		ItemStack stack = itemEntity.getStack();
		
		int level = EnchantmentHelper.getLevel(itemEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.FIRE_PROTECTION).get(), stack);
		if (level > 0) {
			int minFireProtectionLevelRequired = DontBurnMyStuff.getMinFireProtectionLevelRequired();
			if (minFireProtectionLevelRequired == 0 || (level >= minFireProtectionLevelRequired)) {
				callbackInfoReturnable.setReturnValue(true);
			}
		}
	}
	
}
