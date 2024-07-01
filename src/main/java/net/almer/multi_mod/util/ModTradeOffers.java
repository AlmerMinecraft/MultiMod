package net.almer.multi_mod.util;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;

public class ModTradeOffers {
    public static final Int2ObjectMap<TradeOffers.Factory[]> OLD_RAVAGER_TRADES;
    private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> map) {
        return new Int2ObjectOpenHashMap(map);
    }
    static{
        OLD_RAVAGER_TRADES = copyToFastUtilMap(ImmutableMap.of(1, new TradeOffers.Factory[]{
                new TradeOffers.SellItemFactory(Items.IRON_AXE, 8, 1, 5, 1),
                new TradeOffers.SellItemFactory(Items.CROSSBOW, 8, 1, 5, 1),
                new TradeOffers.SellItemFactory(Items.TOTEM_OF_UNDYING, 54, 1, 1, 10)}));
    }
}
