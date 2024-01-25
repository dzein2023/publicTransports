package com.example.demo.Controllers;



import com.example.demo.services.PublicTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
//@RestController
    public class PublicTransportController {
    @Autowired
    private PublicTransportService PublicTransportservice;

    @PostMapping("/PublicTransport/addPublicTransport")
    public @ResponseBody <PublicTransport> PublicTransport addPublicTransport(@RequestBody PublicTransport c)
    {
        return PublicTransportService.addPublicTransport(c);
    }

}
