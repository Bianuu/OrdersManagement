package BusinessLogicBLL.validari;

import Model.produse;

public class verificareProduseStoc implements Validator<produse> {

    @Override
    public void validate(produse obj) throws IllegalArgumentException {
        if (obj.getStoc() < 0) {
            throw new IllegalArgumentException("Nu sunt suficiente " + obj.getNume() + " in stoc!");
        }
    }
}
