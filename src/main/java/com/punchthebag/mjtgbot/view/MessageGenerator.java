package com.punchthebag.mjtgbot.view;

import com.punchthebag.mjtgbot.entity.AnalysisResult;
import com.punchthebag.mjtgbot.entity.DiscardOption;
import com.punchthebag.mjtgbot.entity.Side;
import com.punchthebag.mjtgbot.entity.Tile;
import org.springframework.stereotype.Service;

@Service
public class MessageGenerator {

    private UnicodeMapper unicodeMapper;

    public MessageGenerator(UnicodeMapper unicodeMapper) {
        this.unicodeMapper = unicodeMapper;
    }

    public String generateResponse(AnalysisResult result) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(result.getHand().getContent());
        stringBuilder.append(System.lineSeparator());

        for (Tile tile : result.getHand().getTiles()) {
            stringBuilder.append(unicodeMapper.getUnicodeSymbol(tile));
        }
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append(getShantenFormatted(result.getShanten()));
        stringBuilder.append(System.lineSeparator());

        for (DiscardOption discardOption : result.getDiscardOptions()) {
            stringBuilder.append(getDiscardOptionFormatted(discardOption));
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private String getShantenFormatted(int shanten) {
        if (shanten == -1) {
            return "Winning hand";
        } else if (shanten == 0) {
            return "Ready hand";
        } else {
            return shanten + "-shanten";
        }
    }

    private String getDiscardOptionFormatted(DiscardOption discardOption) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("— ");
        stringBuilder.append(unicodeMapper.getUnicodeSymbol(discardOption.getTile()));
        stringBuilder.append(" ");
        stringBuilder.append(discardOption.getTile().getRank() + 1);
        stringBuilder.append(discardOption.getTile().getSuit().getLetter());
        stringBuilder.append(" outs: ");
        stringBuilder.append(discardOption.getOutsCount());
        stringBuilder.append(" ");
        for (Side side : discardOption.getOuts()) {
            stringBuilder.append(unicodeMapper.getUnicodeSymbol(side.getTile()));
        }

        stringBuilder.append(" ");
        for (Side side : discardOption.getOuts()) {
            stringBuilder.append(side.getAmount());
            stringBuilder.append("x");
            stringBuilder.append(side.getTile().getRank() + 1);
            stringBuilder.append(side.getTile().getSuit().getLetter());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}
