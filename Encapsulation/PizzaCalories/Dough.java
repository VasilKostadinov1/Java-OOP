package Encapsulation_Exercises.PizzaCalories;

public class Dough {

    //fields
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        //валидни-> White или Wholegrain
        if (flourType.equals("White") || flourType.equals("Wholegrain")) {
            this.flourType = flourType;
        } else {
            throw new IllegalArgumentException("Invalid type of dough.");
        }

    }

    private void setBakingTechnique(String bakingTechnique) {
        //валидна -> Crispy	Chewy Homemade
        switch(bakingTechnique) {
            case "Crispy":
            case "Chewy":
            case "Homemade":
                this.bakingTechnique = bakingTechnique;
                break;
            default:
                throw new IllegalArgumentException("Invalid type of dough.");
        }

    }

    private void setWeight(double weight) {
        //валидно от 1 до 200
        if(weight >= 1 && weight <= 200) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }

    }

    public double calculateCalories() {
        //(2 * weight) * flourType modificator * bakingTechnique modificator
        double flourTypeModificator = this.getFlourTypeModificator(this.flourType);
        double bakingTechniqueModificator = this.getBakingTechniqueModificator(this.bakingTechnique);
        return (2 * this.weight) * flourTypeModificator * bakingTechniqueModificator;
    }

    private double getBakingTechniqueModificator(String bakingTechnique) {
        switch(bakingTechnique) {
            case "Crispy":
                return 0.9;
            case "Chewy":
                return 1.1;
            case "Homemade":
                return 1;
            default:
                return 0;
        }
    }

    private double getFlourTypeModificator(String flourType) {
        if (flourType.equals("White")) {
            return 1.5;
        } else if (flourType.equals("Wholegrain")){
            return 1;
        } else {
            return 0;
        }
    }


}
