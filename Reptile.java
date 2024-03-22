public class Reptile extends Animal{
    boolean poisonous;

    public Reptile(String animalName, float price,
        float visitPrice, boolean poisonous, int amountInArea)
    {
        super(animalName, price, visitPrice, amountInArea);
        this.poisonous = poisonous;
        super.amountOfFeedings = 3;
        super.amountInArea = amountInArea;
        super.type = Animal.typeAnimal.Reptile;
    }

    public void feed(int timeIndicator)
    {
        System.out.println("Reptile " + name + " was fed.");
        if (poisonous)
        {
            System.out.println("Look out if it is trying to touch you!");
        }
        hungry = false;
        switch(timeIndicator)
        {
            case 1:
            fedBefore = true;
            break;

            case 2:
            fedDuring = true;
            break;

            case 3:
            fedAfter = true;
            break;
        }
    }

    @Override
    public String toString()
    {
        String text = "Reptile: " + name + "| ID: "+ id  + "\nPoisonous: ";
        if (poisonous) {
            text += "Positive";
        } else {
            text += "Negative";
        }
        text += "\nNeeds to be fed: " + amountOfFeedings + " times\n" +
        "Buy/Sell Price: $" + price + "\nVisit Price: $" + visitPrice + "\nMax Amount: " + amountInArea;
        return text;
    }

    public float makeFine()
    {
        if (!fedBefore && !fedDuring && !fedAfter)
        {
            System.out.println("ID: " + id + "| Reptile escaped from its area. Better feed it next time!");
            Creator.deleteFromAnimals(Zoo.animals, id);
            return visitPrice * 4.5f;
        }
        return 0.0f;
    }

}
