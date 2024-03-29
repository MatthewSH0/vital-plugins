package dev.vital.scripts.cooking.tasks;

import dev.vital.scripts.cooking.VitalWineConfig;
import net.unethicalite.api.account.LocalPlayer;
import net.unethicalite.api.commons.Rand;
import net.unethicalite.api.commons.Time;
import net.unethicalite.api.input.Keyboard;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.movement.Movement;
import net.runelite.api.Item;
import net.runelite.api.ItemID;
import net.runelite.api.MenuAction;
import net.runelite.api.Player;

public class MakeWine implements ScriptTask
{

	VitalWineConfig config;

	public MakeWine(VitalWineConfig config) {
		this.config = config;
	}

	@Override
	public boolean validate() {

		Player local = LocalPlayer.get();
		return local != null && !Inventory.contains(ItemID.JUG) && Inventory.contains(ItemID.JUG_OF_WATER) && Inventory.contains(ItemID.GRAPES);
	}

	@Override
	public int execute() {

		Player local = LocalPlayer.get();

		if (local.isAnimating() || Movement.isWalking()) {

			return 2300;
		}

		Item grapes = Inventory.getFirst(ItemID.GRAPES);

		grapes.useOn(Inventory.getFirst(ItemID.JUG_OF_WATER));

		Time.sleep(Rand.nextInt(1800, 2500));

		Keyboard.sendSpace();
		System.out.println(MenuAction.WIDGET_FIRST_OPTION.name());
		return Rand.nextInt(2000, 4000);
	}
}
