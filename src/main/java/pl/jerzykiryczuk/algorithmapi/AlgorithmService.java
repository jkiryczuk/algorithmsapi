package pl.jerzykiryczuk.algorithmapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgorithmService {

    LuhnAlgorithmComponent component;

    @Autowired
    public AlgorithmService(LuhnAlgorithmComponent component) {
        this.component = component;
    }

    public String generateCheckDigit(String number) {
        return component.calculateCheckDigit(number);
    }

    public Boolean checkNumberValidity(String number){
        return component.checkValidity(number);
    }
}
