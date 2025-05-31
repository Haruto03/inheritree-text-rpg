# FIT2099 Assignment (Semester 1, 2025)

```
`7MM"""YMM  `7MMF'      `7MM"""Yb. `7MM"""YMM  `7MN.   `7MF'    MMP""MM""YMM `7MMF'  `7MMF'`7MMF'`7MN.   `7MF' .g8"""bgd  
  MM    `7    MM          MM    `Yb. MM    `7    MMN.    M      P'   MM   `7   MM      MM    MM    MMN.    M .dP'     `M  
  MM   d      MM          MM     `Mb MM   d      M YMb   M           MM        MM      MM    MM    M YMb   M dM'       `  
  MMmmMM      MM          MM      MM MMmmMM      M  `MN. M           MM        MMmmmmmmMM    MM    M  `MN. M MM           
  MM   Y  ,   MM      ,   MM     ,MP MM   Y  ,   M   `MM.M           MM        MM      MM    MM    M   `MM.M MM.    `7MMF'
  MM     ,M   MM     ,M   MM    ,dP' MM     ,M   M     YMM           MM        MM      MM    MM    M     YMM `Mb.     MM  
.JMMmmmmMMM .JMMmmmmMMM .JMMmmmdP' .JMMmmmmMMM .JML.    YM         .JMML.    .JMML.  .JMML..JMML..JML.    YM   `"bmmmdPY  
```

[Contribution Log](https://docs.google.com/spreadsheets/d/1gKEzBsCYIDqmTIBYuMdgHVlxKsTblQpj1Mp7jnL7QN4/edit?usp=sharing)


Requirement 3 and 4 checking: [](https://docs.google.com/document/d/1YSkGT26D4_QkVSjapRd8jFQSzq5kpSmDq_fLxjvh4HM/edit?usp=sharing)
Requirement 3: Spells Casting
Design: 
Players cast spells in game, managed by a Spellbook that consist of spells like FireSpell, HealSpell, and TeleportSpell. Spells apply effects to Actors or Locations through SpellEffects interfaces in the effects package. We will add mana to the farmer.

Higher-level class
CastSpellAction: This CastSpellAction will extend Action, check casting condition and execute spells via action menu. 
Abstract/interface class
Spellbook (Item): This abstract class spellbook act as an applycastingeffect to the effects. Then the allowableactions will be checking if the spell is for self or opponent. 
Lower-level classes
FireSpell: Extend SpellBook. Check if surrounding has enemy, then surrounding 8 tiles will burn, and tiles will change to a different character for 1 turn. It will have a FireEffect.
TeleportSpell:Extend SpellBook, teleports the caster randomly. Using allowableActions for self. Cost 20 mana. It will have a TeleportEffect. 
PoisonSpell: Extend SpellBook, damages a target Actor for a few turns in the game.. Cost 40 mana. We will have a PoisonStatusEffect extending StatusEffect
The spell will be executed by getting the actor, location and map. 
HealSpell: Extend SpellBook, heals a target Actor with a HealEffect. Cost 30 mana

Requirement 4: Fishing System
Design: 

Higher level class
FishingAction: This will extend Action, when near pond player will be able to fish from the pond. 
Pond: this is a ground type, where it takes an area in the map. There will be fishable items in the pond. 
Abstract/interface class
Fishable: This will be an interface, that shows what items are fishable in the pond area. 
Lower level classes
FreshFish: This fish will extend item and implement fishable. It can be eaten by the player and increase certain health. There is a 50% chance of fishing it. 
RottenFish: This fish will extend item and implement fishable. It will decrease health if player eat it. There will be a 70% chance of fishing
Shovel: This shovel extends item. It will use DigAction, where its able to remove the plants in the map and return back into a soil ground.
FishingRod: This FishingRod extends Item, where it enables fishing function with a capability of CAN_FISH. 