package com.punchthebag.mjtgbot.view;

import com.punchthebag.mjtgbot.entity.Tile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UnicodeMapper {

    private static final Map<Tile, String> MAPPING = Map.ofEntries(
            Map.entry(Tile.of(0, 0),"\uD83C\uDC07"),
            Map.entry(Tile.of(1, 0),"\uD83C\uDC08"),
            Map.entry(Tile.of(2, 0),"\uD83C\uDC09"),
            Map.entry(Tile.of(3, 0),"\uD83C\uDC0A"),
            Map.entry(Tile.of(4, 0),"\uD83C\uDC0B"),
            Map.entry(Tile.of(5, 0),"\uD83C\uDC0C"),
            Map.entry(Tile.of(6, 0),"\uD83C\uDC0D"),
            Map.entry(Tile.of(7, 0),"\uD83C\uDC0E"),
            Map.entry(Tile.of(8, 0),"\uD83C\uDC0F"),
            Map.entry(Tile.of(0, 1),"\uD83C\uDC19"),
            Map.entry(Tile.of(1, 1),"\uD83C\uDC1A"),
            Map.entry(Tile.of(2, 1),"\uD83C\uDC1B"),
            Map.entry(Tile.of(3, 1),"\uD83C\uDC1C"),
            Map.entry(Tile.of(4, 1),"\uD83C\uDC1D"),
            Map.entry(Tile.of(5, 1),"\uD83C\uDC1E"),
            Map.entry(Tile.of(6, 1),"\uD83C\uDC1F"),
            Map.entry(Tile.of(7, 1),"\uD83C\uDC20"),
            Map.entry(Tile.of(8, 1),"\uD83C\uDC21"),
            Map.entry(Tile.of(0, 2),"\uD83C\uDC10"),
            Map.entry(Tile.of(1, 2),"\uD83C\uDC11"),
            Map.entry(Tile.of(2, 2),"\uD83C\uDC12"),
            Map.entry(Tile.of(3, 2),"\uD83C\uDC13"),
            Map.entry(Tile.of(4, 2),"\uD83C\uDC14"),
            Map.entry(Tile.of(5, 2),"\uD83C\uDC15"),
            Map.entry(Tile.of(6, 2),"\uD83C\uDC16"),
            Map.entry(Tile.of(7, 2),"\uD83C\uDC17"),
            Map.entry(Tile.of(8, 2),"\uD83C\uDC18"),
            Map.entry(Tile.of(0, 3),"\uD83C\uDC00"),
            Map.entry(Tile.of(1, 3),"\uD83C\uDC01"),
            Map.entry(Tile.of(2, 3),"\uD83C\uDC02"),
            Map.entry(Tile.of(3, 3),"\uD83C\uDC03"),
            Map.entry(Tile.of(4, 3),"\uD83C\uDC06"),
            Map.entry(Tile.of(5, 3),"\uD83C\uDC05"),
            Map.entry(Tile.of(6, 3),"\uD83C\uDC04")
    );

    public String getUnicodeSymbol(Tile tile) {
        return MAPPING.get(tile);
    }

}
