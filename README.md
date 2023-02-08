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

## Ressources
* Food
* Population

## Terrain 
* water
* forest
* berries
* field
* random generation?
### Berries
* regeneration of ressources
* destruction to field
* harvest

## Allies
### Na'vi
* Move
* Build village
* Harvest
* Fight au contact
* Stop
* Wounded
* Dead

### Village
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