package pl.jerzykiryczuk.algorithmapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jerzykiryczuk.algorithmapi.entities.*;

@RestController
@RequestMapping("/algorithms")
public class AlgorithmController {

    public static final String WRONG_REQUEST_MESSAGE = "Wrong request - only numbers are accepted";
    private AlgorithmService algorithmService;

    @Autowired
    public AlgorithmController(final AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    @GetMapping("/")
    public String TestController(){
        return "Hello World";
    }

    @GetMapping("/getControlNumber")
    public ResponseEntity<?> getControlNumber(@RequestBody LuhnRequest request) {
        try {
            ControlNumberResponse response = new ControlNumberResponse(algorithmService.generateCheckDigit(request.getNumber()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(new BadRequestResponse(WRONG_REQUEST_MESSAGE),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/checkValidity")
    public ResponseEntity<?> checkValidity(@RequestBody LuhnRequest request) {
        try {
            ValidityResponse response = new ValidityResponse(algorithmService.checkNumberValidity(request.getNumber()));
            return new ResponseEntity<ValidityResponse>(response, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(new BadRequestResponse(WRONG_REQUEST_MESSAGE),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/knn")
    public ResponseEntity<NeighbourResponse> nearestNeighbour(@RequestBody NearestNeighbourRequest request) {
        NeighbourResponse response = algorithmService.nearestNeighbour(request.getPointList());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
