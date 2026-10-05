# Inheritree — Java によるテキストベース RPG

[English](README.md) | 日本語

『Elden Ring』に着想を得た、ターン制のテキスト描画型 RPG です。小規模なオブジェクト指向
ゲームエンジンの上に Java で実装しました。プレイヤーは農夫となり、*Valley of the Inheritree* と
*Limveld* という 2 つのマップを探索します。大地は Blight(枯死)に侵されており、聖なる樹を
植えて地面を浄化し、クリーチャーや成長するボスと戦い、呪文を唱え、釣りをし、商人と取引し、
NPC と会話します。

Haruto Iriyama が 5 人チームの一員として、2 回の設計イテレーションを通じて開発しました。
エンジン(`src/edu/…/engine`)は提供されたもので、`src/game` 配下はすべてチームの自作コードです。

<img src="docs/screenshot-gameplay.png" alt="ターミナルで動作中のゲーム画面。Valley of the Inheritree のマップ、農夫の体力・スタミナ・マナ・ルーン、呪文や釣り・種まきを含む行動メニュー、そしてターンのログ" width="560">

<sub>1 ターン分の画面です。マップ、農夫のステータス、選択可能な行動、そしてターンログ
(Golden Beetle の産卵と、Bed of Chaos の攻撃)が表示されています。</sub>

## 機能

| 領域 | 内容 |
|---|---|
| **ワールド** | テレポーテーションゲートでつながる 2 つのマップ。capability を持つ地形(Blight、Soil、Floor、Wall、Pond、燃焼中/一時的な地形) |
| **クリーチャー** | Spirit Goat、Omen Sheep、Golden Beetle。それぞれが優先度順またはランダムの行動選択(徘徊・追跡・攻撃・産出)を持つ |
| **Bed of Chaos(ボス)** | 毎ターン*成長*するボス。枝と葉が `BossPart` として再帰的に生成され、生やしたパーツの数に応じて攻撃力が上昇する |
| **卵と孵化** | クリーチャーが卵を産み、ルールベースの条件(時間経過、呪われた環境)で孵化する。食べるとバフを得られる |
| **植物** | Inheritree と Bloodrose の種。植えると Blight を浄化し、樹は周囲のアクターを回復、Bloodrose はダメージを与える |
| **戦闘** | 武器アイテム(Broadsword、Katana、Dragonslayer Greatsword)と固有武器。攻撃アクションは capability によって駆動される |
| **NPC と取引** | Sellen、Kale、Guts がゲームの状況に応じて変化する台詞を話す。商人は武器を販売し、購入時に効果(回復、最大 HP / スタミナ上昇、クリーチャー召喚)が発動する |
| **呪文** | Spellbook から Fire、Heal、Poison、Teleport の各呪文を使用。マナを消費し、アクターまたは地形に効果を適用する |
| **釣り** | 釣り竿と池。確率テーブル付きの釣果(サーモン、黄金の魚、有毒ウナギ、古い長靴)に加え、植物を掘り起こすシャベルも実装 |
| **治癒** | 呪われたアクターと地形を浄化する Talisman |

### 設計上の工夫

- **継承より合成。** 行動(`AttackBehaviour`、`FollowBehaviour`、`WanderBehaviour`、`ProduceBehaviour`、`GrowPartBehaviour`)は `BehaviourSelector` を通じてアクターに差し込みます。そのため新しいクリーチャーの追加はクラス階層の拡張ではなく設定の組み合わせになります。
- **条件と効果をファーストクラスのオブジェクトとして扱う。** `Condition`(低体力、ルーン不足、周囲の capability、ターン経過など)と `Effect`(ダメージ、回復、継続ダメージ、アクター召喚など)は小さな組み合わせ可能なインターフェースで、NPC の会話・商人の販売・呪文がすべて同じ仕組みを再利用します。
- **`instanceof` ではなく capability。** 地形・アイテム・アクターは `GeneralCapability` / `GroundCapability` の enum を公開し、アクション側は capability を確認します。これによりエンジンとゲームの結合を避けています。
- **再帰的に成長するボス。** `BedOfChaos` は `Branch` / `Leaf` のツリーを保持します。成長は再帰的な走査として実装され、枝がさらに枝や葉を伸ばせるようになっており、ダメージはツリー全体の合計として計算されます。

## 担当した部分

チーム内では、クリーチャー関連 — 繁殖のしかた、卵の孵化、そして自ら成長するボス
Bed of Chaos — を担当しました。

**Bed of Chaos — 木構造としてのボス。** カウンタで強さを上げるのではなく、`BossPart` の
リストを持ち、毎ターン 1 つずつ部位を増やしていきます。インターフェースは
`getDamageContribution()` と `grow(actor, directParts)` の 2 つだけですが、実装の意味は
大きく異なります。

| 部位 | ダメージ | `grow()` の動作 |
|---|---|---|
| `Branch` | 3 | 50% の確率で `Branch` か `Leaf` を追加 |
| `Leaf` | 1 | 何も追加せず、代わりにボスを 5 回復させる |

`Leaf` に同じ `grow()` を持たせつつ意味を変えたことで、ボス側に型判定が一切要らなく
なっています。また `Branch` は `Leaf` を生やした時点で `isProductive = false` になるため、
成長は指数的に発散せず自然に収束します。攻撃力は保持せず、攻撃の直前に部位を再集計して
武器へ書き込みます(`BASE_DAMAGE + getDamageContribution()`)。値が木の状態とずれることが
原理的に起きません。

実装上の細部をひとつ挙げると、`attemptGrow()` は拡張 for ではなく添字による `while` で
リストを走査しています。ループ本体が走査中のリストに要素を追加するため、拡張 for では
枝が伸びた瞬間に `ConcurrentModificationException` になるからです。

**成長を特別扱いせず「行動」として実装。** ボス自身は*いつ*成長するかを知りません。
`GrowPartBehaviour` が周囲のマスを調べ、隣接マスにアクターがいれば `null` を返します。
すると `BehaviourSelector` の中で攻撃行動に順番が渡ります。結果として「手が届く相手が
いないときだけ成長する」という規則が 1 箇所に収まりました。この行動が依存しているのは
`BedOfChaos` ではなく `Growable`(`attemptGrow()`)インターフェースなので、他のものも
同じ仕組みで成長させられます。

**繁殖条件はクリーチャーごとに持たせる。** `ActorProducible`(`canProduceOffspring` /
`produceOffspring`)により、1 つの `ProduceBehaviour` が条件の異なる 3 種類に対応します。

| クリーチャー | 産出する条件 |
|---|---|
| Golden Beetle | 一定ターン経過 |
| Omen Sheep | 一定ターン経過 |
| Spirit Goat | 隣接マスに `BLESSED` の capability がある |

Spirit Goat の条件は `NearbyCapabilityCondition` を再利用しているため、「周囲に X があるか
調べる」処理をクリーチャーごとに書き直してはいません。

**孵化は分岐ではなくデータとして表現。** 卵は `HatchingRules` のリストを公開し、各ルールは
`Condition` と `Supplier<Actor>` の組になっています。

```java
new HatchingRules(new NearbyCapabilityCondition(location, GeneralCapability.CURSED),
                  GoldenBeetle::new)   // 黄金虫の卵:呪われた地面の近くで孵化
new HatchingRules(new TurnBasedCondition(turnOnGround, HATCH_DURATION),
                  OmenSheep::new)      // 羊の卵:一定ターン経過で孵化
```

`Supplier` にしている点が重要で、条件を満たしたときに初めてクリーチャーが生成されます。
生まれるかもしれないアクターを作らずにルールだけ宣言できるということです。新しい卵を
追加する作業は、`if` の連鎖を書き足すことではなくルールを 1 つ足すことになります。さらに
卵は `Item` かつ `Eatable` でもあるため、同じオブジェクトを拾う・捨てる・食べてバフを得る、
といった扱いができます。

このほか `FollowBehaviour`、上記の条件群が参照する `GeneralCapability` enum、ボス専用の
武器と攻撃アクションも担当しました。

## 実行方法

JDK 17 以上が必要です。

```bash
javac -d out $(find src -name "*.java")
java -cp out game.Application
```

または IntelliJ IDEA でプロジェクトを開き、`game.Application` を実行してください。
ゲームはターミナル上で進行します。毎ターン、実行可能なアクションの一覧が表示されるので、
選びたいもののキーを入力します。

## ドキュメント

- `docs/design/game.png`、`docs/design/engine.png` — game パッケージと engine パッケージのクラス図
- `docs/design/uml_spells_and_fishing.png` — 呪文システムと釣りシステムの UML
- `docs/design/iteration-1/`、`docs/design/iteration-2/` — 2 回のイテレーションにおける各機能の設計根拠、UML、シーケンス図
