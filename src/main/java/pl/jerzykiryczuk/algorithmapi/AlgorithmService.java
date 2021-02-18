package pl.jerzykiryczuk.algorithmapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgorithmService {

    LuhnAlghoritmComponent component;

    @Autowired
    public AlgorithmService(LuhnAlghoritmComponent component) {
        this.component = component;
    }

    public String getMockup(){
      return component.test();
    }
}
