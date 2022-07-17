package fairyShop.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShopImpl implements Shop {


    @Override
    public void craft(Present present, Helper helper) {
        //•	Keep working until the present is done or the helper has energy (and instruments to use).
        while (present.isDone() || helper.canWork()){
            for (Instrument instrument : helper.getInstruments()) {
                while (!instrument.isBroken()) {
                    instrument.use();
                    present.getCrafted();
                    helper.work();
                    if(present.isDone()){
                        break;
                    }
                }
                if(present.isDone()){
                    break;
                }
            }
            if(present.isDone()){
                break;
            }
        }

////    The helper starts crafting the present. This is only possible if the helper has energy
//      and an instrument that isn't broken.


        /*List<Instrument> helperInstruments = new ArrayList<>(helper.getInstruments());

        //	Keep working until the present is done or the helper has energy (and instruments to use).
        while (helper.canWork()) {

            Instrument instrument =
                    helperInstruments.stream().filter(i -> !i.isBroken()).findFirst().orElse(null);
//	If at some point the power of the current instrument reaches or drops below 0, meaning it is broken,
//      then the helper should take the next instrument from its collection, if it has any left.

            if (instrument == null) {
                break;
            }
            while (!present.isDone()) {
                helper.work();
                instrument.use();
                present.getCrafted();

                if (!helper.canWork() || instrument.isBroken()) {
                    break;
                }
            }
            if (present.isDone()) {
                break;
            }
        }*/




        /*while (helper.canWork()){
            Instrument instrument =
                    helper.getInstruments().stream().filter(i -> !i.isBroken()).findFirst().orElse(null);
            if (instrument==null){
                break;
            }
            while (!present.isDone()) {
                helper.work();
                instrument.use();
                present.getCrafted();

                if (!helper.canWork() || instrument.isBroken()) {
                    break;
                }
            }
        }*/


    }
}
