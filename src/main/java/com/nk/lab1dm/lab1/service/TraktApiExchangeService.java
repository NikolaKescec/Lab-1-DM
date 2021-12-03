package com.nk.lab1dm.lab1.service;

import com.nk.lab1dm.lab1.service.dto.TraktApiExchangeResponse;
import com.nk.lab1dm.lab1.service.dto.WeatherApiExchangeResponse;
import com.nk.lab1dm.lab1.service.dto.city.Coordinates;

public interface TraktApiExchangeService {

    TraktApiExchangeResponse fetchTraktByTitle(String title);

    TraktApiExchangeResponse fetchTraktById(String title);

}
