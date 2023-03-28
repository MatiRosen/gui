package team.unnamed.gui.menu.adapt;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import team.unnamed.gui.menu.type.MenuInventory;

public class MenuInventoryWrapperImpl implements MenuInventoryWrapper {

    private final Inventory raw;
    private final MenuInventory menuInventory;

    public MenuInventoryWrapperImpl(MenuInventory menuInventory){
        this.menuInventory = menuInventory;

        raw = Bukkit.createInventory(this, menuInventory.getSlots(), menuInventory.getTitle());
    }

    @Override
    public Inventory getInventory() {
        return raw;
    }

    @Override
    @NotNull
    public InventoryHolder getHolder(){
        return this;
    }

    @Override
    @NotNull
    public Inventory getRawInventory() {
        return raw;
    }

    @Override
    @NotNull
    public MenuInventory getMenuInventory() {
        return menuInventory;
    }

}
