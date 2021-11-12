
import com.burmantov.simpleapp.controllers.AnimalControllerImpl;
import com.burmantov.simpleapp.models.Cat;
import com.burmantov.simpleapp.repositories.AnimalRepoImpl;
import com.burmantov.simpleapp.services.AnimalCellImpl;

import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    AnimalControllerImpl animalController = prepareAnimalController();

    Cat cat3 = new Cat("Васька", "51");
    String savedCat = animalController.saveCat(cat3);
        System.out.println(savedCat);

}
    private static AnimalControllerImpl prepareAnimalController(){
        AnimalRepoImpl animalRepo = new AnimalRepoImpl();
        AnimalCellImpl animalCell = new AnimalCellImpl(animalRepo);
        return new AnimalControllerImpl(animalCell);
    }
}
