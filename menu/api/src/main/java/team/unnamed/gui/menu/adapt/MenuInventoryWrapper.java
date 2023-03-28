package team.unnamed.gui.menu.adapt;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import team.unnamed.gui.menu.type.MenuInventory;

public interface MenuInventoryWrapper extends InventoryHolder{

    @NotNull MenuInventory getMenuInventory();

    @NotNull Inventory getRawInventory();

    @NotNull InventoryHolder getHolder();

}
