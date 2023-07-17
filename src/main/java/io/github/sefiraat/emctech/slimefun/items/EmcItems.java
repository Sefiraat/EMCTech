package io.github.sefiraat.emctech.slimefun.items;

import org.bukkit.inventory.ItemStack;

import io.github.sefiraat.emctech.EmcTech;
import io.github.sefiraat.emctech.managers.SupportedPluginManager;
import io.github.sefiraat.emctech.slimefun.blocks.Dematerializer;
import io.github.sefiraat.emctech.slimefun.blocks.Materializer;
import io.github.sefiraat.emctech.slimefun.blocks.NodeMaterializer;
import io.github.sefiraat.emctech.slimefun.groups.EmcTechItemGroups;
import io.github.sefiraat.networks.slimefun.NetworksSlimefunItemStacks;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;

import dev.sefiraat.sefilib.itemstacks.GeneralItemStackUtils;

public class EmcItems {

    private EmcItems() {
        throw new IllegalStateException("Utility class");
    }

    // Materials
    public static final UnplaceableBlock UNORTHODOX_COAL;
    public static final UnplaceableBlock UNORTHODOX_COAL_DUST;
    public static final UnplaceableBlock TIGHTLY_PACKED_UNORTHODOX_COAL;
    public static final UnplaceableBlock UNORTHODOX_COAL_BLOCK;
    public static final UnplaceableBlock DEVIANT_COAL;
    public static final UnplaceableBlock DEVIANT_COAL_DUST;
    public static final UnplaceableBlock TIGHTLY_PACKED_DEVIANT_COAL;
    public static final UnplaceableBlock DEVIANT_COAL_BLOCK;
    public static final UnplaceableBlock DIVERGENT_COAL;
    public static final UnplaceableBlock DIVERGENT_COAL_DUST;
    public static final UnplaceableBlock TIGHTLY_PACKED_DIVERGENT_COAL;
    public static final UnplaceableBlock DIVERGENT_COAL_BLOCK;
    public static final UnplaceableBlock ANOMALOUS_COAL;
    public static final UnplaceableBlock ANOMALOUS_COAL_DUST;
    public static final UnplaceableBlock TIGHTLY_PACKED_ANOMALOUS_COAL;
    public static final UnplaceableBlock ANOMALOUS_COAL_BLOCK;
    public static final UnplaceableBlock PERFECTED_COAL;

    // Components
    public static final UnplaceableBlock UNORTHODOX_FRAME;
    public static final UnplaceableBlock DEVIANT_FRAME;
    public static final UnplaceableBlock DIVERGENT_FRAME;
    public static final UnplaceableBlock ANOMALOUS_FRAME;
    public static final UnplaceableBlock PERFECTED_FRAME;
    public static final UnplaceableBlock UNORTHODOX_MACHINE_FRAME;
    public static final UnplaceableBlock DEVIANT_MACHINE_FRAME;
    public static final UnplaceableBlock DIVERGENT_MACHINE_FRAME;
    public static final UnplaceableBlock ANOMALOUS_MACHINE_FRAME;
    public static final UnplaceableBlock PERFECTED_MACHINE_FRAME;

    // Machines
    public static final Dematerializer EMC_DEMATERIALIZER_1;
    public static final Dematerializer EMC_DEMATERIALIZER_2;
    public static final Dematerializer EMC_DEMATERIALIZER_3;
    public static final Dematerializer EMC_DEMATERIALIZER_4;
    public static final Dematerializer EMC_DEMATERIALIZER_5;
    public static final Materializer EMC_MATERIALIZER_1;
    public static final Materializer EMC_MATERIALIZER_2;
    public static final Materializer EMC_MATERIALIZER_3;
    public static final Materializer EMC_MATERIALIZER_4;
    public static final Materializer EMC_MATERIALIZER_5;
    public static final NodeMaterializer EMC_NETWORK_MATERIALIZER;

    static {

        // region Materials

        UNORTHODOX_COAL = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.UNORTHODOX_COAL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.VANILLA_COAL, EmcStacks.VANILLA_COAL, EmcStacks.VANILLA_COAL,
                EmcStacks.VANILLA_COAL, EmcStacks.VANILLA_COAL, EmcStacks.VANILLA_COAL,
                EmcStacks.VANILLA_COAL, EmcStacks.VANILLA_COAL, EmcStacks.VANILLA_COAL
            }
        );

        UNORTHODOX_COAL_DUST = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.UNORTHODOX_COAL_DUST,
            RecipeType.ORE_CRUSHER,
            new ItemStack[]{
                EmcStacks.UNORTHODOX_COAL
            }
        );

        TIGHTLY_PACKED_UNORTHODOX_COAL = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.TIGHTLY_PACKED_UNORTHODOX_COAL,
            RecipeType.COMPRESSOR,
            new ItemStack[]{
                GeneralItemStackUtils.getAsQuantity(EmcStacks.UNORTHODOX_COAL_DUST, 4)
            }
        );

        UNORTHODOX_COAL_BLOCK = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.UNORTHODOX_COAL_BLOCK,
            RecipeType.PRESSURE_CHAMBER,
            new ItemStack[]{
                EmcStacks.TIGHTLY_PACKED_UNORTHODOX_COAL
            }
        );

        DEVIANT_COAL = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.DEVIANT_COAL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.UNORTHODOX_COAL_BLOCK, EmcStacks.UNORTHODOX_COAL_BLOCK, EmcStacks.UNORTHODOX_COAL_BLOCK,
                EmcStacks.UNORTHODOX_COAL_BLOCK, EmcStacks.UNORTHODOX_COAL_BLOCK, EmcStacks.UNORTHODOX_COAL_BLOCK,
                EmcStacks.UNORTHODOX_COAL_BLOCK, EmcStacks.UNORTHODOX_COAL_BLOCK, EmcStacks.UNORTHODOX_COAL_BLOCK
            }
        );

        DEVIANT_COAL_DUST = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.DEVIANT_COAL_DUST,
            RecipeType.ORE_CRUSHER,
            new ItemStack[]{
                EmcStacks.DEVIANT_COAL
            }
        );

        TIGHTLY_PACKED_DEVIANT_COAL = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.TIGHTLY_PACKED_DEVIANT_COAL,
            RecipeType.COMPRESSOR,
            new ItemStack[]{
                GeneralItemStackUtils.getAsQuantity(EmcStacks.DEVIANT_COAL_DUST, 4)
            }
        );

        DEVIANT_COAL_BLOCK = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.DEVIANT_COAL_BLOCK,
            RecipeType.PRESSURE_CHAMBER,
            new ItemStack[]{
                EmcStacks.TIGHTLY_PACKED_DEVIANT_COAL
            }
        );

        DIVERGENT_COAL = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.DIVERGENT_COAL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.DEVIANT_COAL_BLOCK, EmcStacks.DEVIANT_COAL_BLOCK, EmcStacks.DEVIANT_COAL_BLOCK,
                EmcStacks.DEVIANT_COAL_BLOCK, EmcStacks.DEVIANT_COAL_BLOCK, EmcStacks.DEVIANT_COAL_BLOCK,
                EmcStacks.DEVIANT_COAL_BLOCK, EmcStacks.DEVIANT_COAL_BLOCK, EmcStacks.DEVIANT_COAL_BLOCK
            }
        );

        DIVERGENT_COAL_DUST = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.DIVERGENT_COAL_DUST,
            RecipeType.ORE_CRUSHER,
            new ItemStack[]{
                EmcStacks.DIVERGENT_COAL
            }
        );

        TIGHTLY_PACKED_DIVERGENT_COAL = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.TIGHTLY_PACKED_DIVERGENT_COAL,
            RecipeType.COMPRESSOR,
            new ItemStack[]{
                GeneralItemStackUtils.getAsQuantity(EmcStacks.DIVERGENT_COAL_DUST, 4)
            }
        );

        DIVERGENT_COAL_BLOCK = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.DIVERGENT_COAL_BLOCK,
            RecipeType.PRESSURE_CHAMBER,
            new ItemStack[]{
                EmcStacks.TIGHTLY_PACKED_DIVERGENT_COAL
            }
        );

        ANOMALOUS_COAL = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.ANOMALOUS_COAL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.DIVERGENT_COAL_BLOCK, EmcStacks.DIVERGENT_COAL_BLOCK, EmcStacks.DIVERGENT_COAL_BLOCK,
                EmcStacks.DIVERGENT_COAL_BLOCK, EmcStacks.DIVERGENT_COAL_BLOCK, EmcStacks.DIVERGENT_COAL_BLOCK,
                EmcStacks.DIVERGENT_COAL_BLOCK, EmcStacks.DIVERGENT_COAL_BLOCK, EmcStacks.DIVERGENT_COAL_BLOCK
            }
        );

        ANOMALOUS_COAL_DUST = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.ANOMALOUS_COAL_DUST,
            RecipeType.ORE_CRUSHER,
            new ItemStack[]{
                EmcStacks.ANOMALOUS_COAL
            }
        );

        TIGHTLY_PACKED_ANOMALOUS_COAL = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.TIGHTLY_PACKED_ANOMALOUS_COAL,
            RecipeType.COMPRESSOR,
            new ItemStack[]{
                GeneralItemStackUtils.getAsQuantity(EmcStacks.ANOMALOUS_COAL_DUST, 4)
            }
        );

        ANOMALOUS_COAL_BLOCK = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.ANOMALOUS_COAL_BLOCK,
            RecipeType.PRESSURE_CHAMBER,
            new ItemStack[]{
                EmcStacks.TIGHTLY_PACKED_ANOMALOUS_COAL
            }
        );

        PERFECTED_COAL = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.PERFECTED_COAL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.ANOMALOUS_COAL_BLOCK, EmcStacks.ANOMALOUS_COAL_BLOCK, EmcStacks.ANOMALOUS_COAL_BLOCK,
                EmcStacks.ANOMALOUS_COAL_BLOCK, EmcStacks.ANOMALOUS_COAL_BLOCK, EmcStacks.ANOMALOUS_COAL_BLOCK,
                EmcStacks.ANOMALOUS_COAL_BLOCK, EmcStacks.ANOMALOUS_COAL_BLOCK, EmcStacks.ANOMALOUS_COAL_BLOCK
            }
        );

        // endregion

        // Components

        UNORTHODOX_FRAME = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.UNORTHODOX_FRAME,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.UNORTHODOX_COAL, EmcStacks.UNORTHODOX_COAL, EmcStacks.UNORTHODOX_COAL,
                EmcStacks.UNORTHODOX_COAL, SlimefunItems.GOLD_24K_BLOCK, EmcStacks.UNORTHODOX_COAL,
                EmcStacks.UNORTHODOX_COAL, EmcStacks.UNORTHODOX_COAL, EmcStacks.UNORTHODOX_COAL
            }
        );

        UNORTHODOX_MACHINE_FRAME = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.UNORTHODOX_MACHINE_FRAME,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.SOLDER_INGOT, SlimefunItems.ENDER_RUNE, SlimefunItems.SOLDER_INGOT,
                SlimefunItems.SOLDER_INGOT, EmcStacks.UNORTHODOX_FRAME, SlimefunItems.SOLDER_INGOT,
                SlimefunItems.SOLDER_INGOT, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.SOLDER_INGOT
            }
        );

        DEVIANT_FRAME = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.DEVIANT_FRAME,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.DEVIANT_COAL, EmcStacks.DEVIANT_COAL, EmcStacks.DEVIANT_COAL,
                EmcStacks.DEVIANT_COAL, EmcStacks.UNORTHODOX_FRAME, EmcStacks.DEVIANT_COAL,
                EmcStacks.DEVIANT_COAL, EmcStacks.DEVIANT_COAL, EmcStacks.DEVIANT_COAL
            }
        );

        DEVIANT_MACHINE_FRAME = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.DEVIANT_MACHINE_FRAME,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.DURALUMIN_INGOT, EmcStacks.UNORTHODOX_MACHINE_FRAME, SlimefunItems.DURALUMIN_INGOT,
                SlimefunItems.DURALUMIN_INGOT, EmcStacks.DEVIANT_FRAME, SlimefunItems.DURALUMIN_INGOT,
                SlimefunItems.DURALUMIN_INGOT, SlimefunItems.LARGE_CAPACITOR, SlimefunItems.DURALUMIN_INGOT
            }
        );

        DIVERGENT_FRAME = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.DIVERGENT_FRAME,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.DIVERGENT_COAL, EmcStacks.DIVERGENT_COAL, EmcStacks.DIVERGENT_COAL,
                EmcStacks.DIVERGENT_COAL, EmcStacks.DEVIANT_FRAME, EmcStacks.DIVERGENT_COAL,
                EmcStacks.DIVERGENT_COAL, EmcStacks.DIVERGENT_COAL, EmcStacks.DIVERGENT_COAL
            }
        );

        DIVERGENT_MACHINE_FRAME = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.DIVERGENT_MACHINE_FRAME,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.DAMASCUS_STEEL_INGOT, EmcStacks.DEVIANT_MACHINE_FRAME, SlimefunItems.DAMASCUS_STEEL_INGOT,
                SlimefunItems.DAMASCUS_STEEL_INGOT, EmcStacks.DIVERGENT_FRAME, SlimefunItems.DAMASCUS_STEEL_INGOT,
                SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.BIG_CAPACITOR, SlimefunItems.DAMASCUS_STEEL_INGOT
            }
        );

        ANOMALOUS_FRAME = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.ANOMALOUS_FRAME,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.ANOMALOUS_COAL, EmcStacks.ANOMALOUS_COAL, EmcStacks.ANOMALOUS_COAL,
                EmcStacks.ANOMALOUS_COAL, EmcStacks.DIVERGENT_FRAME, EmcStacks.ANOMALOUS_COAL,
                EmcStacks.ANOMALOUS_COAL, EmcStacks.ANOMALOUS_COAL, EmcStacks.ANOMALOUS_COAL
            }
        );

        ANOMALOUS_MACHINE_FRAME = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.ANOMALOUS_MACHINE_FRAME,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.CORINTHIAN_BRONZE_INGOT, EmcStacks.DIVERGENT_MACHINE_FRAME, SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                SlimefunItems.CORINTHIAN_BRONZE_INGOT, EmcStacks.ANOMALOUS_FRAME, SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.CARBONADO_EDGED_CAPACITOR, SlimefunItems.CORINTHIAN_BRONZE_INGOT
            }
        );

        PERFECTED_FRAME = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.PERFECTED_FRAME,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.PERFECTED_COAL, EmcStacks.PERFECTED_COAL, EmcStacks.PERFECTED_COAL,
                EmcStacks.PERFECTED_COAL, EmcStacks.ANOMALOUS_FRAME, EmcStacks.PERFECTED_COAL,
                EmcStacks.PERFECTED_COAL, EmcStacks.PERFECTED_COAL, EmcStacks.PERFECTED_COAL
            }
        );

        PERFECTED_MACHINE_FRAME = new UnplaceableBlock(
            EmcTechItemGroups.MATERIALS,
            EmcStacks.PERFECTED_MACHINE_FRAME,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.REINFORCED_ALLOY_INGOT, EmcStacks.ANOMALOUS_MACHINE_FRAME, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_ALLOY_INGOT, EmcStacks.PERFECTED_FRAME, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.ENERGIZED_CAPACITOR, SlimefunItems.REINFORCED_ALLOY_INGOT
            }
        );

        // endregion

        // region Machines

        EMC_DEMATERIALIZER_1 = new Dematerializer(
            EmcTechItemGroups.MACHINES,
            EmcStacks.EMC_DEMATERIALIZER_1,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.UNORTHODOX_COAL, SlimefunItems.PROGRAMMABLE_ANDROID_3_BUTCHER, EmcStacks.UNORTHODOX_COAL,
                SlimefunItems.ANDROID_INTERFACE_ITEMS, EmcStacks.UNORTHODOX_MACHINE_FRAME, SlimefunItems.ANDROID_INTERFACE_ITEMS,
                EmcStacks.UNORTHODOX_COAL, SlimefunItems.ENERGY_REGULATOR, EmcStacks.UNORTHODOX_COAL
            },
            5,
            1000
        );

        EMC_DEMATERIALIZER_2 = new Dematerializer(
            EmcTechItemGroups.MACHINES,
            EmcStacks.EMC_DEMATERIALIZER_2,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.DEVIANT_COAL, EmcStacks.EMC_DEMATERIALIZER_1, EmcStacks.DEVIANT_COAL,
                SlimefunItems.ANDROID_INTERFACE_ITEMS, EmcStacks.DEVIANT_MACHINE_FRAME, SlimefunItems.ANDROID_INTERFACE_ITEMS,
                EmcStacks.DEVIANT_COAL, SlimefunItems.ENERGY_REGULATOR, EmcStacks.DEVIANT_COAL
            },
            4,
            10000
        );

        EMC_DEMATERIALIZER_3 = new Dematerializer(
            EmcTechItemGroups.MACHINES,
            EmcStacks.EMC_DEMATERIALIZER_3,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.DIVERGENT_COAL, EmcStacks.EMC_DEMATERIALIZER_2, EmcStacks.DIVERGENT_COAL,
                SlimefunItems.ANDROID_INTERFACE_ITEMS, EmcStacks.DIVERGENT_MACHINE_FRAME, SlimefunItems.ANDROID_INTERFACE_ITEMS,
                EmcStacks.DIVERGENT_COAL, SlimefunItems.ENERGY_REGULATOR, EmcStacks.DIVERGENT_COAL
            },
            3,
            100000
        );

        EMC_DEMATERIALIZER_4 = new Dematerializer(
            EmcTechItemGroups.MACHINES,
            EmcStacks.EMC_DEMATERIALIZER_4,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.ANOMALOUS_COAL, EmcStacks.EMC_DEMATERIALIZER_3, EmcStacks.ANOMALOUS_COAL,
                SlimefunItems.ANDROID_INTERFACE_ITEMS, EmcStacks.ANOMALOUS_MACHINE_FRAME, SlimefunItems.ANDROID_INTERFACE_ITEMS,
                EmcStacks.ANOMALOUS_COAL, SlimefunItems.ENERGY_REGULATOR, EmcStacks.ANOMALOUS_COAL
            },
            2,
            1000000
        );

        EMC_DEMATERIALIZER_5 = new Dematerializer(
            EmcTechItemGroups.MACHINES,
            EmcStacks.EMC_DEMATERIALIZER_5,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.PERFECTED_COAL, EmcStacks.EMC_DEMATERIALIZER_4, EmcStacks.PERFECTED_COAL,
                SlimefunItems.ANDROID_INTERFACE_ITEMS, EmcStacks.PERFECTED_MACHINE_FRAME, SlimefunItems.ANDROID_INTERFACE_ITEMS,
                EmcStacks.PERFECTED_COAL, SlimefunItems.ENERGY_REGULATOR, EmcStacks.PERFECTED_COAL
            },
            1,
            10000000
        );

        EMC_MATERIALIZER_1 = new Materializer(
            EmcTechItemGroups.MACHINES,
            EmcStacks.EMC_MATERIALIZER_1,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.UNORTHODOX_COAL_BLOCK, SlimefunItems.PROGRAMMABLE_ANDROID_3_FISHERMAN, EmcStacks.UNORTHODOX_COAL_BLOCK,
                SlimefunItems.ANDROID_INTERFACE_ITEMS, EmcStacks.UNORTHODOX_MACHINE_FRAME, SlimefunItems.ANDROID_INTERFACE_ITEMS,
                EmcStacks.UNORTHODOX_COAL_BLOCK, SlimefunItems.POWER_CRYSTAL, EmcStacks.UNORTHODOX_COAL_BLOCK
            },
            5,
            1000
        );

        EMC_MATERIALIZER_2 = new Materializer(
            EmcTechItemGroups.MACHINES,
            EmcStacks.EMC_MATERIALIZER_2,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.DEVIANT_COAL_BLOCK, EmcStacks.EMC_MATERIALIZER_1, EmcStacks.DEVIANT_COAL_BLOCK,
                SlimefunItems.ANDROID_INTERFACE_ITEMS, EmcStacks.DEVIANT_MACHINE_FRAME, SlimefunItems.ANDROID_INTERFACE_ITEMS,
                EmcStacks.DEVIANT_COAL_BLOCK, SlimefunItems.POWER_CRYSTAL, EmcStacks.DEVIANT_COAL_BLOCK
            },
            4,
            10000
        );

        EMC_MATERIALIZER_3 = new Materializer(
            EmcTechItemGroups.MACHINES,
            EmcStacks.EMC_MATERIALIZER_3,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.DIVERGENT_COAL_BLOCK, EmcStacks.EMC_MATERIALIZER_2, EmcStacks.DIVERGENT_COAL_BLOCK,
                SlimefunItems.ANDROID_INTERFACE_ITEMS, EmcStacks.DIVERGENT_MACHINE_FRAME, SlimefunItems.ANDROID_INTERFACE_ITEMS,
                EmcStacks.DIVERGENT_COAL_BLOCK, SlimefunItems.POWER_CRYSTAL, EmcStacks.DIVERGENT_COAL_BLOCK
            },
            3,
            100000
        );

        EMC_MATERIALIZER_4 = new Materializer(
            EmcTechItemGroups.MACHINES,
            EmcStacks.EMC_MATERIALIZER_4,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.ANOMALOUS_COAL_BLOCK, EmcStacks.EMC_MATERIALIZER_3, EmcStacks.ANOMALOUS_COAL_BLOCK,
                SlimefunItems.ANDROID_INTERFACE_ITEMS, EmcStacks.ANOMALOUS_MACHINE_FRAME, SlimefunItems.ANDROID_INTERFACE_ITEMS,
                EmcStacks.ANOMALOUS_COAL_BLOCK, SlimefunItems.POWER_CRYSTAL, EmcStacks.ANOMALOUS_COAL_BLOCK
            },
            2,
            1000000
        );

        EMC_MATERIALIZER_5 = new Materializer(
            EmcTechItemGroups.MACHINES,
            EmcStacks.EMC_MATERIALIZER_5,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                EmcStacks.PERFECTED_COAL, EmcStacks.EMC_MATERIALIZER_4, EmcStacks.PERFECTED_COAL,
                SlimefunItems.ANDROID_INTERFACE_ITEMS, EmcStacks.PERFECTED_MACHINE_FRAME, SlimefunItems.ANDROID_INTERFACE_ITEMS,
                EmcStacks.PERFECTED_COAL, SlimefunItems.POWER_CRYSTAL, EmcStacks.PERFECTED_COAL
            },
            1,
            10000000
        );

        if (SupportedPluginManager.isNetworks()) {
            EMC_NETWORK_MATERIALIZER = new NodeMaterializer(
                EmcTechItemGroups.MACHINES,
                EmcStacks.EMC_NETWORK_MATERIALIZER,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                    NetworksSlimefunItemStacks.RADIOACTIVE_OPTIC_STAR, EmcStacks.EMC_MATERIALIZER_4, NetworksSlimefunItemStacks.RADIOACTIVE_OPTIC_STAR,
                    NetworksSlimefunItemStacks.NETWORK_MONITOR, EmcStacks.EMC_MATERIALIZER_5, NetworksSlimefunItemStacks.NETWORK_MONITOR,
                    NetworksSlimefunItemStacks.RADIOACTIVE_OPTIC_STAR, NetworksSlimefunItemStacks.EMPOWERED_AI_CORE, NetworksSlimefunItemStacks.RADIOACTIVE_OPTIC_STAR
                },
                1,
                10000000
            );
        } else {
            EMC_NETWORK_MATERIALIZER = null;
        }

        // endregion

    }

    public static void setup() {
        final EmcTech plugin = EmcTech.getInstance();

        UNORTHODOX_COAL.register(plugin);
        UNORTHODOX_COAL_DUST.register(plugin);
        TIGHTLY_PACKED_UNORTHODOX_COAL.register(plugin);
        UNORTHODOX_COAL_BLOCK.register(plugin);
        DEVIANT_COAL.register(plugin);
        DEVIANT_COAL_DUST.register(plugin);
        TIGHTLY_PACKED_DEVIANT_COAL.register(plugin);
        DEVIANT_COAL_BLOCK.register(plugin);
        DIVERGENT_COAL.register(plugin);
        DIVERGENT_COAL_DUST.register(plugin);
        TIGHTLY_PACKED_DIVERGENT_COAL.register(plugin);
        DIVERGENT_COAL_BLOCK.register(plugin);
        ANOMALOUS_COAL.register(plugin);
        ANOMALOUS_COAL_DUST.register(plugin);
        TIGHTLY_PACKED_ANOMALOUS_COAL.register(plugin);
        ANOMALOUS_COAL_BLOCK.register(plugin);
        PERFECTED_COAL.register(plugin);

        UNORTHODOX_FRAME.register(plugin);
        UNORTHODOX_MACHINE_FRAME.register(plugin);
        DEVIANT_FRAME.register(plugin);
        DEVIANT_MACHINE_FRAME.register(plugin);
        DIVERGENT_FRAME.register(plugin);
        DIVERGENT_MACHINE_FRAME.register(plugin);
        ANOMALOUS_FRAME.register(plugin);
        ANOMALOUS_MACHINE_FRAME.register(plugin);
        PERFECTED_FRAME.register(plugin);
        PERFECTED_MACHINE_FRAME.register(plugin);

        EMC_DEMATERIALIZER_1.register(plugin);
        EMC_DEMATERIALIZER_2.register(plugin);
        EMC_DEMATERIALIZER_3.register(plugin);
        EMC_DEMATERIALIZER_4.register(plugin);
        EMC_DEMATERIALIZER_5.register(plugin);
        EMC_MATERIALIZER_1.register(plugin);
        EMC_MATERIALIZER_2.register(plugin);
        EMC_MATERIALIZER_3.register(plugin);
        EMC_MATERIALIZER_4.register(plugin);
        EMC_MATERIALIZER_5.register(plugin);

        if (SupportedPluginManager.isNetworks()) {
            EMC_NETWORK_MATERIALIZER.register(plugin);
        }
    }
}
