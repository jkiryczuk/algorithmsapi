package pl.jerzykiryczuk.algorithmapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jerzykiryczuk.algorithmapi.entities.ControlNumberResponse;
import pl.jerzykiryczuk.algorithmapi.entities.LuhnRequest;
import pl.jerzykiryczuk.algorithmapi.entities.ValidityResponse;

@RestController
@RequestMapping("/algorithms")
public class AlghoritmController {

    private AlgorithmService algorithmService;

    @Autowired
    public AlghoritmController(final AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    @GetMapping("/getControlNumber")
    public ResponseEntity<ControlNumberResponse> getControlNumber(@RequestBody LuhnRequest request) {
        try {
            ControlNumberResponse response = new ControlNumberResponse(algorithmService.generateCheckDigit(request.getNumber()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/checkValidity")
    public ResponseEntity<ValidityResponse> checkValidity(@RequestBody LuhnRequest request) {
        try {
            ValidityResponse response = new ValidityResponse(algorithmService.checkNumberValidity(request.getNumber()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
