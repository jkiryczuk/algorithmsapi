package pl.jerzykiryczuk.algorithmapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jerzykiryczuk.algorithmapi.entities.Edge;
import pl.jerzykiryczuk.algorithmapi.entities.NeighbourResponse;
import pl.jerzykiryczuk.algorithmapi.entities.Point;
import pl.jerzykiryczuk.algorithmapi.exceptions.WrongRequestException;

import java.util.List;
import java.util.regex.Pattern;

import static pl.jerzykiryczuk.algorithmapi.exceptions.Messages.WRONG_PATTERN_MESSAGE;

@Service
public class AlgorithmService {

    public static final String REGEX = "[0-9]+";
    private LuhnAlgorithmComponent luhnAlgorithmComponent;
    private NearestNeighbourAlgorithmComponent nearestNeighbourAlgorithmComponent;
    private Pattern pattern;


    @Autowired
    public AlgorithmService(final LuhnAlgorithmComponent luhnAlgorithmComponent, final NearestNeighbourAlgorithmComponent nearestNeighbourAlgorithmComponent) {
        this.luhnAlgorithmComponent = luhnAlgorithmComponent;
        this.nearestNeighbourAlgorithmComponent = nearestNeighbourAlgorithmComponent;
        pattern = Pattern.compile(REGEX);
    }

    private boolean validateInput(String number){
        return pattern.matcher(number).matches();
    }

    public String generateCheckDigit(String number) throws WrongRequestException {
        if (!validateInput(number)) throw new WrongRequestException(WRONG_PATTERN_MESSAGE);
        return luhnAlgorithmComponent.calculateCheckDigit(number);
    }

    public Boolean checkNumberValidity(String number) throws WrongRequestException {
        if (!validateInput(number)) throw new WrongRequestException(WRONG_PATTERN_MESSAGE);
        return luhnAlgorithmComponent.checkValidity(number);
    }

    public NeighbourResponse nearestNeighbour(List<Point> points) {
        List<Edge> edges = nearestNeighbourAlgorithmComponent.nearestNeighbour(points);
        StringBuilder message = new StringBuilder();
        StringBuilder debugMessage = new StringBuilder();
        double distance = 0.0;
        for(Edge edge : edges){
            message.append(edge.getFromPoint().getName()).append(" -> ");
            debugMessage.append(edge.getFromPoint()).append("\n -> \n");
            distance += edge.getDistance();
        }
        message.append(edges.get(edges.size() - 1).getToPoint().getName());
        debugMessage.append(edges.get(edges.size() - 1).getToPoint());
        return new NeighbourResponse(message.toString(), debugMessage.toString(), distance);
    }
}
