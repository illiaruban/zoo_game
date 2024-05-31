# Zoo Game #
A Java-based project made by Illia Ruban

**Short Description**: Zoo Game lets you play as the zookeeper and expand your finances and variety of the animals at the same time.
In this explanation we will observe three main classes that were written in the program:

- *Creator* class
- *Zoo* class
- *Animal* class

## Creator class ##
Creator class contains a key functions that enables the flow of the program. The most important function of the Creator class is *createAnimal()*, that has a lot of details which we will discuss while observing the Animal class.
Creator class has the ability to add new animals into the system - *(Creator.addToAnimals())*/delete animals from the system - *(Creator.deleteFromAnimals())*, such operations are done by manipulating the main array which contains all animals in the game - Creator.createdAnimals. Creator class also has the functions of effective search of animals by their classes/names - *(Creator.searchAnimal())* which it shares with Zoo.java to make gaming experience more comfortable. Creator class automatically creates a connection between the name of the animal, its type(about this in description of *_Animal_* class) and the type of food that this animal must be fed with. Creator class has the prices for every food package as well.

## Zoo class ##
Zoo class contains second main array for animals - Zoo.animals. It also contains HashMap objects for establishing how much packages of food the zoo does have. 
We will take a look at all the functions Zoo class has:

-*zooBuyAnimal()* - purchase one animal of a certain kind and place it in the zoo. The amount of the animals with the same type a zoo can have is limited so in this function this is checked.
-*zooSellAnimal()* - sell one animal of a certain kind.
-*buyFood()* - purchase a food for a type of the animal(unlimited amount but only for a certain type!).
-*feedAnimal()* - feed all animals at once with the food the zoo has already purchased. If some animals are not fed, the notification appears on the screen.

## Game Mechanics ##

Now we move to the main part of the project - functionality of the Zoo Game which is located in *_Zoo_* class. If it is to describe the main idea of the flow, i should be explained in stages of program:

### Start ###
When the player starts the game or the new day, the stage is called _beforeTime_. Before everything is handed to the player, he becomes a notification about the inappropriate behavior(if such occurs) with the animals and pays fees. At that time the player is allowed to purchase food for the animals and buy some as well. But the most important thing to do is to feed all the animals that are in the zoo. When the player is finished with preparation, they can change the stage to next one.

### Work Time ###

In the stage _workTime_ the player is only allowed to feed the animals, purchasing food and animals are not allowed during work time.

### After Work ###

After work the player gets a reward for the day. While _afterTime_ player is allowed to purchase animals and food. When he is finished, he can start new day.

The circle of the functions look like that:

-> *moveToNext()* -> *startDay()* -> *endDay()* 

Now we can return to the functions that help to save the data in the txt.file:

-*writeToFile()* -writes all the animals to save them for the next launch.

-*readFromFile()* - reads all the animals from the txt.file when the program launches.

The functions above are also located in the *_Creator_* class, howewer they write/read the whole variety of animals to/from the file.









