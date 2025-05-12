package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ActorProducible;

public class ProduceAction extends Action {

    // producerActorはexecuteメソッドのactor引数で渡されるため、フィールドとして持つ必要は必ずしもない。
    // BehaviourがこのActionを返す際に特定の情報（例：生産するアイテムの種類など）を渡したい場合にフィールドを使う。
    // 今回はActorProducibleインターフェースのメソッドをactor自身が呼び出す形にする。

    public ProduceAction() {
        // 必要であれば引数を取る
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof ActorProducible) {
            ActorProducible producer = (ActorProducible) actor;
            // produceOffspring メソッドがメッセージを返すことを期待
            String result = producer.produceOffspring(actor, map);
            if (result == null || result.isEmpty()) {
                return actor + " tries to produce offspring.";
            }
            return result;
        }
        return actor + " is unable to produce offspring.";
    }

    @Override
    public String menuDescription(Actor actor) {
        // このアクションは通常、ビヘイビアによって自動的に実行されるため、
        // プレイヤーメニューには表示されないことが多い。
        return actor + " produces offspring";
    }
}