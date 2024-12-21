package de.dafuqs.dontburnmystuff.mixin;

import de.dafuqs.dontburnmystuff.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.registry.entry.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
	
	// This would be more cool to have in ItemStack.takesDamageFrom(),
	// but we have no world reference there
	@Inject(method = "isFireImmune()Z", at = @At("HEAD"), cancellable = true)
	private void dontburnmystuff$isFireImmune(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		int minFireProtectionLevelRequired = DontBurnMyStuff.getMinFireProtectionLevelRequired();
		if (minFireProtectionLevelRequired == 0) {
			callbackInfoReturnable.setReturnValue(true);
		} else {
			ItemEntity itemEntity = ((ItemEntity) (Object) this);
			Optional<RegistryEntry.Reference<Enchantment>> enchant = itemEntity.getWorld().getRegistryManager().getOptionalEntry(Enchantments.FIRE_PROTECTION);
			if (enchant.isPresent() && EnchantmentHelper.getLevel(enchant.get(), itemEntity.getStack()) >= minFireProtectionLevelRequired) {
				callbackInfoReturnable.setReturnValue(true);
			}
		}
	}
	
}
