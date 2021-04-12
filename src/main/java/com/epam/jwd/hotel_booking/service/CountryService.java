package com.epam.jwd.hotel_booking.service;

import com.epam.jwd.hotel_booking.dao.impl.CountryDao;
import com.epam.jwd.hotel_booking.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryService {
    private static CountryDao countryDao = new CountryDao();

    public static List<Country> getCountriesList() {
        Optional<List<Country>> optionalCountryList = countryDao.findAll();
        if (optionalCountryList.isPresent()) {
            return optionalCountryList.get();
        } else {
            List<Country> defaultCountryList = new ArrayList<>();
            Country defaultCountry = new Country();
            defaultCountryList.add(defaultCountry);
            return defaultCountryList;
        }

    }

    public static int getCountryId(String countryName) {
        return countryDao.findEntityByName(countryName).map(Country::getId).orElse(-1);
    }

}
