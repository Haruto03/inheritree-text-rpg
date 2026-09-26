# Inheritree — Java によるテキストベース RPG

[English](README.md) | 日本語

『Elden Ring』に着想を得た、ターン制のテキスト描画型 RPG です。小規模なオブジェクト指向
ゲームエンジンの上に Java で実装しました。プレイヤーは農夫となり、*Valley of the Inheritree* と
*Limveld* という 2 つのマップを探索します。大地は Blight(枯死)に侵されており、聖なる樹を
植えて地面を浄化し、クリーチャーや成長するボスと戦い、呪文を唱え、釣りをし、商人と取引し、
NPC と会話します。

Haruto Iriyama が 5 人チームの一員として、2 回の設計イテレーションを通じて開発しました。
エンジン(`src/edu/…/engine`)は提供されたもので、`src/game` 配下はすべてチームの自作コードです。

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

私自身が主に担当したのは、クリーチャー・卵・孵化のシステム、`Produce` 行動、そして
Bed of Chaos ボス(再帰的な `BossPart` の設計、`GrowPartAction` / `GrowPartBehaviour`、
および専用武器)です。

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
