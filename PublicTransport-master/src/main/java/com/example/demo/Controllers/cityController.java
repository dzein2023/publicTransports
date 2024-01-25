package com.example.demo.Controllers;

import com.example.demo.domains.City;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.CityService;

@RequestMapping("api/cities")
@RestController
public class cityController {

    private final CityService cityService;

    public cityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public @ResponseBody City addCity(@RequestBody City c)
    {
        return cityService.addCity(c);
    }
    @PutMapping("/{cityId}")
    public City updateCity(@PathVariable Long cityId,@RequestBody City c){

        return cityService.updateCity(cityId,c);
    }
   @DeleteMapping("/{cityId}")
    public void deleteCity(@PathVariable Long cityId){
        cityService.deleteCity(cityId);
   }
   @GetMapping("/{cityId}")
    public City getCity(@PathVariable Long cityId){
      return   cityService.get(cityId);

  }
}
