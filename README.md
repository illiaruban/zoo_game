# Zoo Game #
A Java-based project made by Illia Ruban

**Short Description**: Zoo Game lets you play as the zookeeper and expand your finances and variety of the animals at the same time.
In this explanation we will observe three main classes that were written in the program:

- *Creator* class
- *Zoo* class
- *Animal* class

## Creator class ##
Creator class has the ability to add new animals into the system # (Creator.addToAnimals()) #/delete animals from the system#(Creator.deleteFromAnimals())#, such operations are done by manipulating the main array which contains all animals in the game - Creator.createdAnimals. Creator class also has the functions of effective search of animals by their classes/names#(Creator.searchAnimal())# which it shares with Zoo.java to make gaming experience more comfortable. Creator class automatically creates a connection between the name of the animal, its type(about this in description of ### Animal ### class) and the type of food that this animal must be fed with. Creator class has the prices for every food package as well.

## Zoo class ##
Zoo class contains second main array for animals - Zoo.animals. It also contains HashMap objects for establishing how much packages of food the zoo does have. 




