# FlowerCraft

## orga 
M
### Model

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

MainView : ViewMap, ViewPanel
ViewPanel : Panel

ViewMap : [[ViewCell]], Grille
ViewCell : Cell
### Control

## Liste des Classes :

MainControl : MainModel, MainView, Display, ControlMap, ControlPanel
Display extends thread: MainView
    Classe qui appelle repaint
ControlMap : Map, MainModel, ViewMap, [[ControlCell]]
    Créé les ControlCell. Mais les ViewCell correspondantes en mouseListener des ControlCell. Je ne croit pas que cette classe ait d'autre utilité
ControlCell implements MouseListener: Cell, MainModel
    Previent MainModel que un clique a été fait sur sa case

ControlPanel : MainModel, ViewPanel, Panel
    Je sais pas comment géré le Panel

