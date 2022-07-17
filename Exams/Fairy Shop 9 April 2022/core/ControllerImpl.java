package fairyShop.core;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;
import fairyShop.repositories.Repository;

public class ControllerImpl implements Controller {

    private Repository<Helper> helperRepository;
    private Repository<Present> presentRepository;
    //private Shop shop;
    private Present present;
    private int craftedPresents;

    //The constructor of ControllerImpl does not take any arguments
    public ControllerImpl() {
        helperRepository = new HelperRepository();
        presentRepository = new PresentRepository();
        //this.shop = new ShopImpl();
        //this.present = new PresentImpl(present.getName(), present.getEnergyRequired());
    }

    @Override
    public String addHelper(String type, String helperName) {
        //Creates a helper with the given name of the given type and adds it to the corresponding repository
        Helper helper;
        if ("Happy".equals(type)) {
            helper = new Happy(helperName);
        } else if ("Sleepy".equals(type)) {
            helper = new Sleepy(helperName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }
        //and adds it to the corresponding repository
        this.helperRepository.add(helper);
        return String.format(ConstantMessages.ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = this.helperRepository.findByName(helperName);

        if (helper == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }
        Instrument instrument = new InstrumentImpl(power);
        //adds instrument to the collection of the helper.
        helper.addInstrument(instrument);    // adds Object
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);

    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {

        //•	The helpers that you should select are the ones with energy above 50 units.
        Helper suitableHelper = this.helperRepository
                .getModels()
                .stream()
                .filter(h -> h.getEnergy() > 50)    //above 50 units
                .findFirst().orElse(null);
        if (suitableHelper == null) {    // •	If no helpers are ready, throw ...
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }
        //•	After the work is done, you must return the following message, reporting whether the present is done and
        //how many instruments have been broken in the process:
        Shop shop = new ShopImpl();
        Present present = this.presentRepository.findByName(presentName);
        shop.craft(present, suitableHelper);

        long countBroken = suitableHelper.getInstruments().stream().filter(Instrument::isBroken).count();

        String isDone;
        if (present.isDone()) {
            isDone = "done";
            craftedPresents++;
        } else {
            isDone = "not done";
        }
        //"Present {presentName} is {done/not done}.
        //{countBrokenInstruments} instrument/s have been broken while working on it!"
        return String.format(ConstantMessages.PRESENT_DONE, presentName, isDone) +
                String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, countBroken);
    }

    @Override
//    "{countCraftedPresents} presents are done!"
//    "Helpers info:"

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d presents are done!", craftedPresents)).append(System.lineSeparator());
        sb.append("Helpers info:").append(System.lineSeparator());

        this.helperRepository
                .getModels()
                .forEach(helper -> sb.append(helper.toString())
                        .append(System.lineSeparator()));
        return sb.toString().trim();

    }
}
