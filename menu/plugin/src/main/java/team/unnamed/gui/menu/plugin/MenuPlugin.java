package team.unnamed.gui.menu.plugin;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.gui.item.ItemBuilder;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.listener.InventoryClickListener;
import team.unnamed.gui.menu.listener.InventoryCloseListener;
import team.unnamed.gui.menu.listener.InventoryOpenListener;
import team.unnamed.gui.menu.type.MenuInventory;

import java.util.ArrayList;
import java.util.List;

import static team.unnamed.gui.item.util.DecorateItemUtils.stainedPane;

public class MenuPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new InventoryClickListener(), this);
        pluginManager.registerEvents(new InventoryOpenListener(), this);
        pluginManager.registerEvents(new InventoryCloseListener(this), this);

        getCommand("gui").setExecutor((sender, command, label, args) -> {
            Player player = (Player) sender;

            switch (args[0]) {
                case "default": {
                    player.openInventory(MenuInventory.newBuilder("Test")
                            .fillBorders(ItemClickable.onlyItem(stainedPane(DyeColor.PINK)))
                            .item(ItemClickable.builder(22)
                                    .item(new ItemStack(Material.ENDER_PEARL))
                                    .action(inventory -> {
                                        player.sendMessage("Testing");
                                        player.closeInventory();
                                        return true;
                                    })
                                    .build())
                            .openAction(inventory -> {
                                player.sendMessage("Opening...");
                                return false;
                            })
                            .closeAction(inventory -> {
                                player.sendMessage("Closing...");

                                return false;
                            })
                            .build());
                    break;
                }
                case "paginated": {
                    List<ItemStack> entities = new ArrayList<>();

                    for (int i = 0; i <= 90; i++) {
                        entities.add(ItemBuilder.builder(Material.ENDER_PEARL)
                                .name("Item #" + i)
                                .build()
                        );
                    }

                    ItemClickable decorationItem = ItemClickable.onlyItem(
                            stainedPane(DyeColor.PINK)
                    );

                    player.openInventory(MenuInventory
                            .newPaginatedBuilder(ItemStack.class, "Paginated Test")
                            .entities(entities)
                            .itemsPerRow(7)
                            .entityParser(ItemClickable::onlyItem)
                            .skipSlots(10, 16, 28, 34, 37, 38, 42, 43)
                            .bounds(10, 44)
                            .itemIfNoPreviousPage(decorationItem)
                            .itemIfNoNextPage(decorationItem)
                            .nextPageItem(page -> ItemClickable.onlyItem(ItemBuilder.builder(Material.ARROW)
                                    .name("Next page - " + page)
                                    .build()))
                            .previousPageItem(page -> ItemClickable.onlyItem(ItemBuilder.builder(Material.ARROW)
                                    .name("Previous page - " + page)
                                    .build()))
                            .layoutLines(
                                    "xxxxxxxxx",
                                    "xseeeeesx",
                                    "xeeeeeeex",
                                    "xseeeeesx",
                                    "xsseeessx",
                                    "xpxxxxxnx"
                            )
                            .layoutItem('s', ItemClickable.onlyItem(stainedPane(DyeColor.WHITE)))
                            .layoutItem('x', decorationItem)
                            .build());
                    break;
                }
                default: break;
            }

            return true;
        });
    }

}
