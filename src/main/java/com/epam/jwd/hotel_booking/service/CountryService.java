package com.epam.jwd.hotel_booking.service;

import com.epam.jwd.hotel_booking.dao.impl.CountryDao;
import com.epam.jwd.hotel_booking.dao.impl.LoginDao;
import com.epam.jwd.hotel_booking.model.Country;
import com.epam.jwd.hotel_booking.model.Login;
import com.epam.jwd.hotel_booking.model.LoginRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryService {
    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);



    public static List<Country> getCountriesList() {
        CountryDao countryDao = new CountryDao();
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

    public static int getCountryId(String countryName){
        CountryDao countryDao = new CountryDao();
        return countryDao.findEntityByName(countryName).map(Country::getId).orElse(-1);
    }

}
