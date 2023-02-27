# Avatar
As human consumption continues, a group of people seperates itself from the civilisation, reclaims a land
and develops its own "tribe".\
The new formed tribe lived in peace with itself and the Terra Mater.\
Nature has revealed them it has a soul and can punish or compensate their actions.\
beaucoup de prosperite .. la civilisation en continuant son irresponsabilite envers la planete se trouve dans la merde.\
The other group looks more and more at them with envy / jealousy.\
Reconnaissance groups and stuff.

The ennemies attack by waves.

# Organisation

## But du jeu
Control the na'vi to :
Gather food
Build camp
Get new na'vi
Defend the holy tree against the soldier

## Rules
* If Holy tree is destroyed the game is over
* The soldiers move toward the tree
* Actions take certain time
* You cannot produce more navis than 4 * (number of camps)
* navi can take 20 from berries in  4s
* food has to drop food back to a camp or the tree to be used
* if berries has no more food it become a field
* building a village cost 100 food and takes 15s for a navi
* camp and tree are entity that can't move
* navi and soldier move 1 time each second
* village produces a navi cost 40 food in 10s
* if a soldier is next to a navi he attack each second
* the navi ripost each time he is attacked
* an entity die in 4 hit
* soldier die in 4 hit
* na'vi does 2 hit if in forest
* each 30s a wave arrive
* a wave is 3 soldier always on the same side of the map
* One entity per cell

## Map
* Each time the same
* Create default map
* One entity per cell
* View Map
*

## Control Panel
When clicked on a cell, a Panel appears with following information:
* Image of the selection
* Name of the selection
* Description of the selection ?
* Possible action of the selected entity

## Ressources
We consider there are two types of ressources:
* Food
* Population\
  Food has to be collected from berry fields\
  Population is created in Camps, there are 4 Navis per Camp, so the number of Living navis can not go over 4 * numOfCamps\
  If a camp is destroyed by Soldiers, navi's number will not reduce s, perhaps the total number

## Terrain
* water
* forest
* berries
* field

### Berries
* regeneration of ressources
* destruction to field
* harvest

## Allies
### Na'vi
* Move
* Build camp
* Harvest
* Fight au contact
* Stop
* Wounded
* Dead

### Camp
* En construction
* Create Na'vi
* Under Attack
* Destroy

### Holy tree
* Under Attack
* Destroy and defeat

## Ennemies
### Wave of ennemies
* How many waves? (infinite?)
* How much in each wave?
* Where
* When

### Soldier
* How to choose target?
* Move
* Fight au contact
* Wounded
* Dead

## Functionalities
* View window
* View map
* View Cell(mouse listener of Control cell, image, entity)
* Panel(selected cell or entity, selected action)
* Control Cell (send clicked cell to model)
* Terrain(field, forest, water)
* berries extends field
* Control Berries extends Control Cell(grow food, harvest food, become field)
* PheromoneTree
* PheromoneCamps
* Control entity
* Action stop
* Action move to next cell
* Action move (a*, pheromone)
* Fight(soldier)
* Ripost(na'vi)
* Take damage(Tree, Camp, Soldier, Na'vi)
* Harvest (na'vi et berries)
* Dead/destroy (Tree, Camp, Soldier, Na'vi)
* Control Wave
* Generate wave
* Food(drop food, produce navi, build camp, view)
* Population(number of navi < number of village * 4, view)
* Score(food gathered, time survived, soldiers killed, number of village, number of na'vi)




## Brainstorming


### Ressources:
* food
* population

### Humans attack with
* Soldiers
* Tanks
* Fighter planes ?
* Motos
* ExoSuits

### Tribes forces are organized into :
* people being able to gather ressources and fight\
  **move ** :
    * algo a etoile
    * algo pheromone
      **when gathering** :
    * fait des aller retours
    * move sur une case
    * move retour au village
    * recolter prends un temps, reduit les ressources de la case
* cavalry
* village


### Terra Mater's events:
* tsunami
* stampede

### Tribes Units are:
Name 						| Speed | Ability 	| Force
---							|---	|---		|---|
Thanator Palulukan 			| fast 	|aggresive 	| strong
Équidius Pa’li 				| fast 	| gentle 	| doesn't attack
Titanosaure Marteau Angtsìk | slow 	|  huge 	| strong++
Loup-vipèrins Nantang	 	| fast 	| aggresive | in pack,

#  Old Model

## Liste des Classes :

MainModel : Panel, Map, [Entity]
Map :[[Case]]
Cell : [Entity], Accessible
Entity,Cell?
Classe fille de Entity :
• Gardener
• Rabbit
• Building
• Flower

Panel : Entity, Cell
Le panel selectionne soit une case et liste les entités dedans pour selectionner celle qui nous intéresse ou une entité avec ses informations et ses actions. Comment gérer la différense de comportement entre ces 2 états?
### View
Sépartion Panel Map
Affichage des cases
Grille de JPanel

## Liste des Classes :

MainView : ViewMap, ViewPanel\
ViewPanel : Panel

ViewMap : [[ViewCell]], Grille\
ViewCell : Cell
### Control

## Liste des Classes :

MainControl : MainModel, MainView, Display, ControlMap, ControlPanel\
Display extends thread: MainView\
*   Classe qui appelle repaint \
    ControlMap : Map, MainModel, ViewMap, [[ControlCell]]\
    Créé les ControlCell. Mais les ViewCell correspondantes en mouseListener des ControlCell. Je ne croit pas que cette classe ait d'autre utilité\
    ControlCell implements MouseListener: Cell, MainModel\
    Previent MainModel que un clique a été fait sur sa case\

ControlPanel : MainModel, ViewPanel, Panel\
Je sais pas comment géré le Panel\
