package pl.jerzykiryczuk.algorithmapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/algorithms")
public class AlghoritmController {

    private AlgorithmService algorithmService;

    @Autowired
    public AlghoritmController(final AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    @GetMapping("/getCountries")
    public String getCountries() {
        return algorithmService.getMockup();
    }
}
