package game.growingPart;

import game.actors.creatures.boss.parts.BossPart;
import java.util.List;

public interface Growable {

    void addDirectPart(BossPart part);

    List<BossPart> getDirectParts();

    void collectProductiveParts(BossPart part, List<BossPart> productiveParts);
    void heal(int points);
    Integer getAttribute(Enum<?> name);
    Integer getAttributeMaximum(Enum<?> name);
}