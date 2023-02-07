package com.example.restaurante3.validator;

import com.example.restaurante3.domain.MenuItem;

public class MenuItemValidator implements Validator<MenuItem> {

    @Override
    public void validate(MenuItem menuItem) throws ValidatorException {
        String errors = "";
        if(menuItem.getCategory() == null){
            errors += "Category can't be null!\n";
        }
        if(menuItem.getItem() == null){
            errors += "Item can't be null!\n";
        }
        if(menuItem.getPrice() < 0){
            errors += "Price has to be positive!\n";
        }
        if(menuItem.getCurrency() == null){
            errors += "Currency can't be null!\n";
        }
        if(!errors.isEmpty()){
            throw new ValidatorException(errors);
        }
    }
}
