package pl.jerzykiryczuk.algorithmapi

import org.springframework.stereotype.Component
import pl.jerzykiryczuk.algorithmapi.entities.Edge
import pl.jerzykiryczuk.algorithmapi.entities.Point
import kotlin.math.pow
import kotlin.math.sqrt

@Component
class NearestNeighbourAlgorithmComponent {

    fun nearestNeighbour(points: List<Point>) : MutableList<Edge>{
        var consideredPoint = points[0]
        var edges: MutableList<Edge> = mutableListOf()
        var min: Double = Double.MAX_VALUE
        var tester = points.size - 1

        while (tester > 0) {
            consideredPoint.visited = true
            var index = 0
            var calculatedDistance = 0.0
            for (i in 1..points.size - 1) {
                if (!points[i].visited) {
                    calculatedDistance = calcHaversineDistance(consideredPoint, points[i])
                    if (calculatedDistance < min) {
                        min = calculatedDistance
                        index = i
                    }
                }
                min = Double.MAX_VALUE
            }
            edges.add(Edge(consideredPoint, points[index], calculatedDistance))
            consideredPoint = points[index]
            tester -= 1
        }
        edges.add(Edge(consideredPoint, points[0], calcHaversineDistance(consideredPoint, points[0])))
        return edges;
    }

    fun calcEuclideanDistance(fromPoint: Point, toPoint: Point): Double {
        return sqrt((toPoint.lat - fromPoint.lat).pow(2) + (toPoint.long - fromPoint.long).pow(2))
    }
    /**
     * While working on my Bachelor's thesis, I have written a program which consisted distance calculation between two geographical points.
     * To calculate value I used Haversine Formula.
     * Didn't know whether task require to use it or just simple Euclidean distance, so I added both.
     * https://en.wikipedia.org/wiki/Haversine_formula
     */
    fun calcHaversineDistance(fromPoint: Point,
                              toPoint: Point): Double {
        val latDiff = Math.toRadians(toPoint.lat - fromPoint.lat)
        val lonDiff = Math.toRadians(toPoint.long - fromPoint.long)
        val radStartLat = Math.toRadians(fromPoint.lat)
        val radEndLat = Math.toRadians(toPoint.lat)
        val a = hav(latDiff) + Math.cos(radStartLat) * Math.cos(radEndLat) * hav(lonDiff)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return 6371 * c
    }

    fun hav(difference: Double): Double {
        return Math.pow(Math.sin(difference / 2), 2.0)
    }
}