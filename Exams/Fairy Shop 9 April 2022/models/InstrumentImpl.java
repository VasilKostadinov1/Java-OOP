package fairyShop.models;

import fairyShop.common.ExceptionMessages;

public class InstrumentImpl implements Instrument {

    private int power;
    private static final int DECREASE_POWER = 10;
    private static final int ZERO_POWER = 0;

    public InstrumentImpl(int power) {
        this.setPower(power);
    }

    private void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void use() {
        //•	An instrument's power should not drop below 0. (If the power becomes less than 0, set it to 0).
        this.power = Math.max(ZERO_POWER, this.power - DECREASE_POWER);

//        this.power -= 10;
//        if(this.power < 0){
//            this.power = 0;
//        }
    }

    @Override
    public boolean isBroken() {
        return this.power == ZERO_POWER;
    }
}
